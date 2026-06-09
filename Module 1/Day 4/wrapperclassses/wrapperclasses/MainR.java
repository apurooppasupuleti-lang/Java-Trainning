

public class MainR {
    public static void main(String[] args) {

        Rectangle rect = new Rectangle(5, 2);

        double area = rect.area();
        double perimeter = rect.perimeter();

        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + perimeter);
    }

    static class Rectangle {
        private final double width;
        private final double height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        double area() {
            return width * height;
        }

        double perimeter() {
            return 2* (width + height);
        }
    }
}