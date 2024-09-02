public class BinaryConverter {
// Extension of exercise 12.7
// Version 2.2
    /**
     * Converts a binary string into a decimal number.
     * Per the instructions: throw BinaryFormatException if the string is not a binary string.
     *
     * @param binaryString the binary string to be converted
     * @return the decimal equivalent of the binary string
     * @throws BinaryFormatException if the string is not a valid binary string
     */
    public static int bin2Dec(String binaryString) throws BinaryFormatException {
        int decimal = 0;

        // Check each character in the string
        for (int i = 0; i < binaryString.length(); i++) {
            char ch = binaryString.charAt(i);

            // 12.9 If the character is not '0' or '1', throw a BinaryFormatException
            if (ch != '0' && ch != '1') {
                throw new BinaryFormatException("Not a binary number: " + binaryString);
            }

            // Convert the character to a digit and add to the decimal result
            decimal = decimal * 2 + (ch - '0');
        }

        return decimal;
    }
}
