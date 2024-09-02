// GeometricObject.java: The abstract GeometricObject class
public abstract class GeometricObject {
  private String color = "white";
  private boolean filled;

  /**Default constructor*/
  protected GeometricObject() {
  }

  /**Constructor with parameters*/
  protected GeometricObject(String color, boolean filled) {
      this.color = color;
      this.filled = filled;
  }

  /**Getter method for color*/
  public String getColor() {
      return color;
  }

  /**Setter method for color*/
  public void setColor(String color) {
      this.color = color;
  }

  /**Getter method for filled*/
  public boolean isFilled() {
      return filled;
  }

  /**Setter method for filled*/
  public void setFilled(boolean filled) {
      this.filled = filled;
  }

  /**Abstract method getArea*/
  public abstract double getArea();

  /**Abstract method getPerimeter*/
  public abstract double getPerimeter();
}