class Shape {
   private int numberOfSides;
   
   public Shape(int sides) {
      numberOfSides = sides;
   }

   public int getNumberOfSides() {
      return numberOfSides;
   }

   public void setNumberOfSides(int sides) {
      numberOfSides = sides;
   }
}

class Circle extends Shape {
   private double radius;

   public Circle(double radius) {
      super(0);  
      this.radius = radius;
   }

   public double getRadius() {
      return radius;
   }

   public void setRadius(double radius) {
      this.radius = radius;
   }
}

class Rectangle extends Shape {
   private double length;
   private double width;

   public Rectangle(double length, double width) {
      super(4);  
      this.length = length;
      this.width = width;
   }

   public double getLength() {
      return length;
   }

   public void setLength(double length) {
      this.length = length;
   }

   public double getWidth() {
      return width;
   }

   public void setWidth(double width) {
      this.width = width;
   }
}

public class Main {
   public static void main(String[] args) {
      
      Circle circle = new Circle(5.0);
      
      System.out.println("Number of sides in the Circle: " + circle.getNumberOfSides());

      Rectangle rectangle = new Rectangle(4.0, 6.0);
    
      System.out.println("Number of sides in the Rectangle: " + rectangle.getNumberOfSides());
   }
}
