// Triangle.java: The Triangle class extends GeometricObject
public class Triangle extends GeometricObject {
    private double side1;
    private double side2;
    private double side3;
  
    /** Default constructor that creates a default triangle with sides 1.0 */
    public Triangle() {
        this(1.0, 1.0, 1.0);
    }
  
    /** Constructor that creates a triangle with the specified side1, side2, and side3 */
    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
  
    /** Getter method for side1 */
    public double getSide1() {
        return side1;
    }
  
    /** Setter method for side1 */
    public void setSide1(double side1) {
        this.side1 = side1;
    }
  
    /** Getter method for side2 */
    public double getSide2() {
        return side2;
    }
  
    /** Setter method for side2 */
    public void setSide2(double side2) {
        this.side2 = side2;
    }
  
    /** Getter method for side3 */
    public double getSide3() {
        return side3;
    }
  
    /** Setter method for side3 */
    public void setSide3(double side3) {
        this.side3 = side3;
    }
  
    /** Method to calculate and return the area of the triangle */
    @Override
    public double getArea() {
        // Using Heron's formula
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }
  
    /** Method to calculate and return the perimeter of the triangle */
    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }
  
    /** Method to return a string description of the triangle */
    @Override
    public String toString() {
        return "Triangle: side1 = " + side1 + ", side2 = " + side2 + ", side3 = " + side3 +
               ", area = " + getArea() + ", perimeter = " + getPerimeter();
    }
  }
  