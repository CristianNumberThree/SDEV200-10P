import java.util.*;
import java.io.*;

public class CountKeywords {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java CountKeywords <source file>");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (file.exists()) {
            System.out.println("The number of keywords in " + args[0]
                    + " is " + countKeywords(file));
        } else {
            System.out.println("File " + args[0] + " does not exist");
        }
    }

    public static int countKeywords(File file) throws Exception {
        // Array of all Java keywords + true, false and null
        String[] keywordString = {"abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum",
                "extends", "for", "final", "finally", "float", "goto",
                "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "package", "private",
                "protected", "public", "return", "short", "static",
                "strictfp", "super", "switch", "synchronized", "this",
                "throw", "throws", "transient", "try", "void", "volatile",
                "while", "true", "false", "null"};

        Set<String> keywordSet =
                new HashSet<>(Arrays.asList(keywordString));
        int count = 0;
        boolean inBlockComment = false;
        boolean inString = false;

        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {
            String line = input.nextLine().trim();

            // Skip if it's a single-line comment
            if (line.startsWith("//")) {
                continue;
            }

            // Handle block comments and strings in the line
            int i = 0;
            while (i < line.length()) {
                if (inBlockComment) {
                    // Look for the end of the block comment
                    int endComment = line.indexOf("*/", i);
                    if (endComment != -1) {
                        inBlockComment = false;
                        i = endComment + 2;
                    } else {
                        break; // Still in comment, skip the rest of the line
                    }
                } else if (inString) {
                    // Look for the closing quote of the string literal
                    int endString = line.indexOf('"', i);
                    if (endString != -1) {
                        inString = false;
                        i = endString + 1;
                    } else {
                        break; // Still in string, skip the rest of the line
                    }
                } else {
                    // Check for start of block comment, string, or keyword
                    if (line.startsWith("/*", i)) {
                        inBlockComment = true;
                        i += 2;
                    } else if (line.startsWith("\"", i)) {
                        inString = true;
                        i += 1;
                    } else {
                        // Look for keywords if not in comments or strings
                        int nextSpace = line.indexOf(' ', i);
                        if (nextSpace == -1) {
                            nextSpace = line.length();
                        }
                        String word = line.substring(i, nextSpace);
                        if (keywordSet.contains(word)) {
                            count++;
                        }
                        i = nextSpace + 1;
                    }
                }
            }
        }

        return count;
    }
}
