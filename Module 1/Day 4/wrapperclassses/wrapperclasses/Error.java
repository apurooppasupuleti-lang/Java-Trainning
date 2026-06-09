package wrapperclasses;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Error {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter Number a:");
            int n = sc.nextInt();

            System.out.println("Enter Number b:");
            int m = sc.nextInt();

            double result = n / m;
            String str = null;
            System.out.println(str.length());


            System.out.println("Result is " + result);
            System.out.println("Hello World");
        }

        catch (ArithmeticException e) {
            System.out.println("Division by zero is not allowed");
        }

        catch (InputMismatchException e) {
            System.out.println("Please enter a valid number");
        }

        catch (Exception e) {
            System.out.println("Some error occurred");
        }

        finally {
            System.out.println("Program execution completed");
            sc.close();
        }
    }
}