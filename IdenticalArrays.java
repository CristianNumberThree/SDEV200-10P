//The user provides two arrays each containing three numbers and the program checks if they are identical after which it prionts a message
//version 2.2
import java.util.Scanner;

public class IdenticalArrays {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Declare two 3 by 3 arrays
        int[][] array1 = new int[3][3];
        int[][] array2 = new int[3][3];

        // Prompt the user to enter the first 3 by 3 array
        System.out.println("Enter the first 3x3 array row by row:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array1[i][j] = input.nextInt();
            }
        }

        // Prompt the user to enter the second 3 by 3 array
        System.out.println("Enter the second 3x3 array row by row:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array2[i][j] = input.nextInt();
            }
        }

        // Check if the two arrays are identical and display the result
        if (areIdentical(array1, array2)) {
            System.out.println("The two arrays are identical.");
        } else {
            System.out.println("The two arrays are not identical.");
        }
        
        input.close();
    }

    // Return true if the two arrays are identical
    public static boolean areIdentical(int[][] array1, int[][] array2) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array1[i][j] != array2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}