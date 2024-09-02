import java.util.Scanner;

// Calls RegularPolygon to function as intended
public class TestTriangle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter 3 sides of the triangle
        System.out.print("Enter side 1 of the triangle: ");
        double side1 = input.nextDouble();
        System.out.print("Enter side 2 of the triangle: ");
        double side2 = input.nextDouble();
        System.out.print("Enter side 3 of the triangle: ");
        double side3 = input.nextDouble();

        // Prompt the user to enter the color of the triangle
        System.out.print("Enter the color of the triangle: ");
        String color = input.next();

        // Prompt the user to enter whether the triangle is filled or not
        System.out.print("Is the triangle filled (true/false): ");
        boolean filled = input.nextBoolean();

        // Create a Triangle object with the specified sides
        Triangle triangle = new Triangle(side1, side2, side3);
        triangle.setColor(color);
        triangle.setFilled(filled);

        // Display the area, perimeter, color, and whether it is filled
        System.out.println("Area of the triangle: " + triangle.getArea());
        System.out.println("Perimeter of the triangle: " + triangle.getPerimeter());
        System.out.println("Color of the triangle: " + triangle.getColor());
        System.out.println("Is the triangle filled? " + triangle.isFilled());
    }
}
