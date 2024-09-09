import java.math.BigInteger;
import java.util.Scanner;

public class RationalTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter the first rational number
        System.out.print("Enter the numerator and denominator of the first rational number: ");
        BigInteger num1 = input.nextBigInteger();
        BigInteger denom1 = input.nextBigInteger();

        // Prompt the user to enter the second rational number
        System.out.print("Enter the numerator and denominator of the second rational number: ");
        BigInteger num2 = input.nextBigInteger();
        BigInteger denom2 = input.nextBigInteger();

        // Create two Rational objects
        Rational r1 = new Rational(num1, denom1);
        Rational r2 = new Rational(num2, denom2);

        // Display the results
        System.out.println("First rational number: " + r1);
        System.out.println("Second rational number: " + r2);

        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
    }
}
