//Determines the area and perimeter of three polygons based on predetermined data
//Version 3.1
public class RegularPolygon {
    private int n = 3;
    private double side = 1;
    private double x = 0;
    private double y = 0;

    public RegularPolygon() {
    }

    public RegularPolygon(int n, double side) {
        this.n = n;
        this.side = side;
    }

    public RegularPolygon(int n, double side, double x, double y) {
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Method to get the perimeter of the polygon
    public double getPerimeter() {
        return n * side;
    }

    // Method to get the area of the polygon
    public double getArea() {
        return (n * side * side) / (4 * Math.tan(Math.PI / n));
    }

    // Generates measurements based on variables determined at the start of the program
    public static void main(String[] args) {
        RegularPolygon polygon1 = new RegularPolygon();
        RegularPolygon polygon2 = new RegularPolygon(6, 4);
        RegularPolygon polygon3 = new RegularPolygon(10, 4, 5.6, 7.8);

        System.out.println("Polygon 1 Perimeter: " + polygon1.getPerimeter());
        System.out.println("Polygon 1 Area: " + polygon1.getArea());

        System.out.println("Polygon 2 Perimeter: " + polygon2.getPerimeter());
        System.out.println("Polygon 2 Area: " + polygon2.getArea());

        System.out.println("Polygon 3 Perimeter: " + polygon3.getPerimeter());
        System.out.println("Polygon 3 Area: " + polygon3.getArea());
    }
}