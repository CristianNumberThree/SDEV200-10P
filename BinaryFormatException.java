// Custom exception class needed to perform action for exercise 12.9
public class BinaryFormatException extends Exception {
    
    // Default constructor with a default error message
    public BinaryFormatException() {
        super("Not a binary number");
    }

    // Constructor that accepts a custom error message
    public BinaryFormatException(String message) {
        super(message);
    }
}
