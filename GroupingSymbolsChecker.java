import java.io.*;
import java.util.Stack;

public class GroupingSymbolsChecker {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java GroupingSymbolsChecker sourceFileName");
            System.exit(1);
        }

        String fileName = args[0];
        
        try {
            if (isBalanced(fileName)) {
                System.out.println("The grouping symbols in the file are correctly balanced.");
            } else {
                System.out.println("The grouping symbols in the file are NOT correctly balanced.");
            }
        } catch (IOException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    public static boolean isBalanced(String fileName) throws IOException {
        Stack<Character> stack = new Stack<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int ch;

        while ((ch = reader.read()) != -1) {
            char current = (char) ch;

            // Push the opening symbols to the stack
            if (current == '(' || current == '{' || current == '[') {
                stack.push(current);
            }

            // Check if closing symbols match the top of the stack
            if (current == ')' || current == '}' || current == ']') {
                if (stack.isEmpty()) {
                    return false; // Unmatched closing symbol
                }

                char top = stack.pop();
                if (!isMatchingPair(top, current)) {
                    return false; // Mismatched pair
                }
            }
        }

        reader.close();

        return stack.isEmpty(); // Stack should be empty if all symbols are balanced
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
