# Assignment 2: Creating ADTs, Implementing DS and XML Parser

**Course:** &emsp;&emsp;&emsp;&emsp;CPRG 304 - Data Structures and Algorithms </br>
**Institution:** &emsp;&emsp;&ensp;Southern Alberta Institute of Technology </br>
**Group Name:** &emsp;&nbsp;Link </br>
**Members:** &emsp;&emsp;&ensp;&nbsp;Noreen D, </br>
**Instructor:** &emsp;&emsp;&ensp;Name

---

## Overview
This project implements custom **Abstract Data Types (ADTs)** and **data structures** - including an `ArrayList`, `Doubly Linked List`, `Stack`, and `Queue`.  
These custom classes are then used to create an **XML Parser** that validates whether XML documents are properly formatted.

> ⚠️ **Note:** You are **not allowed** to use any Java Collection Framework classes (`java.util.*`) in the implementations of `MyArrayList`, `MyDLL`, `MyStack`, or `MyQueue`.  
> The only exceptions are `Arrays.copyOf()` and `System.arraycopy()` for resizing.

---

## Running the Program

### Requirements
- **Java 8**
- **JUnit 4**
- **Eclipse IDE**

### Steps
1. Import the `Assignment2StarterCode` project into Eclipse.  
2. Implement all classes in the `adt` and `utilities` packages.  
3. Build and export parser as a JAR file named `Parser.jar`.  
4. Run the parser from the command line:

   ```bash
   java -jar Parser.jar sample.xml
