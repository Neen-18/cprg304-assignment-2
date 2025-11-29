package appDomain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class XMLParser {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java XMLParser <xml-file-path>");
            return;
        }

        String filePath = args[0];
        parseXML(filePath);
    }

    public static void parseXML(String filePath) {
        Stack<String> tagStack = new Stack<>();
        int lineNumber = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // Ignore empty lines or processing instructions
                if (line.isEmpty() || line.startsWith("<?")) continue;

                int index = 0;
                while (index < line.length()) {
                    int start = line.indexOf('<', index);
                    if (start == -1) break;
                    int end = line.indexOf('>', start);
                    if (end == -1) {
                        System.out.println("Line " + lineNumber + ": Missing closing >");
                        break;
                    }

                    String tag = line.substring(start + 1, end).trim();

                    // Ignore self-closing tags
                    if (tag.endsWith("/")) {
                        index = end + 1;
                        continue;
                    }

                    // Closing tag
                    if (tag.startsWith("/")) {
                        tag = tag.substring(1);
                        if (tagStack.isEmpty() || !tagStack.peek().equals(tag)) {
                            System.out.println("Line " + lineNumber + ": Unexpected closing tag </" + tag + ">");
                        } else {
                            tagStack.pop();
                        }
                    } 
                    // Opening tag
                    else {
                        // Only take the tag name, ignore attributes
                        if (tag.contains(" ")) {
                            tag = tag.substring(0, tag.indexOf(" "));
                        }
                        tagStack.push(tag);
                    }

                    index = end + 1;
                }
            }

            // Any tags left unclosed
            while (!tagStack.isEmpty()) {
                System.out.println("Unclosed tag: <" + tagStack.pop() + ">");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
