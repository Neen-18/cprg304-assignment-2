package appDomain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class XMLParser {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Parser <xml-file-path>");
            return;
        }

        parseXML(args[0]);
    }

    public static void parseXML(String filePath) {
        // Stacks for open tags
        Stack<String> tagNames = new Stack<>();
        Stack<Integer> tagLines = new Stack<>();
        Stack<String> tagRaws = new Stack<>();

        int lineNumber = 0;
        boolean hasError = false; // used only for printing message when no errors are shown

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                String current = line;
                String trimmed = current.trim();
                
                // skip empty lines and declarations
                if (trimmed.isEmpty() || trimmed.startsWith("<?")) {
                    continue;
                }

                int index = 0;
                // go through line and find <...> tags
                while (index < current.length()) {
                    int start = current.indexOf('<', index);
                    if (start == -1) break;

                    int end = current.indexOf('>', start + 1);
                    // if a tag has '<' but no matching '>' then it's not constructed correctly
                    if (end == -1) {
                        String raw = current.substring(start).trim();
                        System.out.println("Error at line: " + lineNumber + " " + raw + " is not constructed correctly.");
                        hasError = true;
                        break;
                    }
                    
                    // get the whole tag including the brackets
                    String rawTag = current.substring(start, end + 1).trim();
                    // get the contents of the tag without brackets 
                    String inner  = rawTag.substring(1, rawTag.length() - 1).trim();
                    
                    // skip any empty tags
                    if (inner.isEmpty() || inner.charAt(0) == '?') {
                        index = end + 1;
                        continue;
                    }
                    
                    // determine the type of tag i.e. </> or <>
                    boolean isClosing = inner.charAt(0) == '/';
                    boolean isSelfClosing = inner.endsWith("/");

                    String namePart = inner;
                    if (isClosing) {
                        namePart = namePart.substring(1).trim();
                    }
                    if (isSelfClosing) {
                        namePart = namePart.substring(0, namePart.length() - 1).trim();
                    }

                    // Extract tag name
                    int pos = 0;
                    while (pos < namePart.length() && !Character.isWhitespace(namePart.charAt(pos))) {
                        pos++;
                    }
                    
                    // if tag has no name then it's not constructed correctly
                    String tagName = (pos == 0) ? "" : namePart.substring(0, pos);

                    if (tagName.isEmpty()) {
                        System.out.println("Error at line: " + lineNumber + " " + rawTag + " is not constructed correctly.");
                        hasError = true;
                        index = end + 1;
                        continue;
                    }
                    
                    // handles closing tag
                    if (isClosing) {
                        if (tagNames.isEmpty()) {
                            // No opening tag at all
                            System.out.println("Error at line: " + lineNumber + " " + rawTag + " is not constructed correctly.");
                            hasError = true;
                        } else if (tagNames.peek().equals(tagName)) {
                        	// top of the stack matches closing tag so we pop
                            tagNames.pop();
                            tagLines.pop();
                            tagRaws.pop();
                        } else {
                        	// no matching so we check lower in the stack
                            int matchIndex = -1;
                            for (int i = tagNames.size() - 1; i >= 0; i--) {
                                if (tagNames.get(i).equals(tagName)) {
                                    matchIndex = i;
                                    break;
                                }
                            }
                            
                            // no match so tag no constructed correctly
                            if (matchIndex == -1) {
                                System.out.println("Error at line: " + lineNumber + " " + rawTag + " is not constructed correctly.");
                                hasError = true;
                            } else {
                                int openLine = tagLines.get(matchIndex);
                                
                                // tag with the same name, but some nested tags were never closed
                                if (openLine == lineNumber) {
                                    System.out.println("Error at line: " + lineNumber + " " + rawTag + " is not constructed correctly.");
                                    hasError = true;
                                    
                                    for (int i = tagNames.size() - 1; i >= matchIndex; i--) {
                                        int badLine = tagLines.get(i);
                                        String badRaw = tagRaws.get(i);
                                        System.out.println("Error at line: " + badLine + " " + badRaw + " is not constructed correctly.");
                                        hasError = true;
                                    }
                                } else {
                                	// matching tag is on a different line so all tags above it in the stack were not closed
                                    for (int i = tagNames.size() - 1; i > matchIndex; i--) {
                                        int badLine = tagLines.get(i);
                                        String badRaw = tagRaws.get(i);
                                        System.out.println("Error at line: " + badLine + " " + badRaw + " is not constructed correctly.");
                                        hasError = true;
                                    }
                                }

                                // Pop everything from top down to the matching tag
                                while (tagNames.size() > matchIndex) {
                                    tagNames.pop();
                                    tagLines.pop();
                                    tagRaws.pop();
                                }
                            }
                        }
                    } else {
                    	// handles opening and self-closing tgs
                        if (!isSelfClosing) {
                        	// only push to stack if opening tag is normal
                            tagNames.push(tagName);
                            tagLines.push(lineNumber);
                            tagRaws.push(rawTag);
                        }
                    }

                    index = end + 1;
                }
            }

            // Any remaining unclosed tags
            while (!tagNames.isEmpty()) {
                int    badLine = tagLines.pop();
                String badRaw  = tagRaws.pop();
                tagNames.pop();
                System.out.println("Error at line: " + badLine + " " + badRaw + " is not constructed correctly.");
                hasError = true;
            }

            if (!hasError) {
                System.out.println("XML document is constructed correctly.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
