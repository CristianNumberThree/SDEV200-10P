import java.util.Scanner;

// Calls Binaryconverter along with BinaryFormatException to function as intended
public class TestBinaryConverter {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompts the user to enter a binary number as a string of zeroes and ones
        System.out.print("Enter a binary number: ");
        String binaryString = input.nextLine();

        try {
            // Convert the binary string to a decimal number using bin2Dec
            int decimal = BinaryConverter.bin2Dec(binaryString);
            // Display the decimal equivalent
            System.out.println("The decimal equivalent of " + binaryString + " is " + decimal);
        } catch (BinaryFormatException e) {
            // If a BinaryFormatException occurs, display the error message
            System.out.println(e.getMessage());
        }
    }
}
