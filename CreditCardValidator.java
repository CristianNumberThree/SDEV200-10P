//Validates credit cards based on Mod 10 check method
//version 1.2
import java.util.Scanner;

public class CreditCardValidator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a credit card number as a long integer: ");
        long number = input.nextLong();

        if (isValid(number)) {
            System.out.println(number + " is valid.");
        } else {
            System.out.println(number + " is invalid.");
        }
        
        input.close();
    }

    // Return true if the card number is valid
    public static boolean isValid(long number) {
        return (getSize(number) >= 13 && getSize(number) <= 16) &&
               (prefixMatched(number, 4) || prefixMatched(number, 5) ||
                prefixMatched(number, 37) || prefixMatched(number, 6)) &&
               ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0);
    }

    // Retrieve results
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String numStr = Long.toString(number);
        for (int i = getSize(number) - 2; i >= 0; i -= 2) {
            sum += getDigit(Character.getNumericValue(numStr.charAt(i)) * 2);
        }
        return sum;
    }

    // Return this number if it is a single digit, otherwise,
     // return the sum of the two digits
    public static int getDigit(int number) {
        if (number < 9) {
            return number;
        } else {
            return number / 10 + number % 10;
        }
    }

    // Return sum of odd-place digits in number 
    public static int sumOfOddPlace(long number) {
        int sum = 0;
        String numStr = Long.toString(number);
        for (int i = getSize(number) - 1; i >= 0; i -= 2) {
            sum += Character.getNumericValue(numStr.charAt(i));
        }
        return sum;
    }

    // Return true if the number d is a prefix for number 
    public static boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }

    // Return the number of digits in variable d 
    public static int getSize(long d) {
        return Long.toString(d).length();
    }

    // Return the first variable k number of digits from number. If the
     // number of digits in number is less than variable k, return number. 
    public static long getPrefix(long number, int k) {
        if (getSize(number) > k) {
            return Long.parseLong(Long.toString(number).substring(0, k));
        }
        return number;
    }
}