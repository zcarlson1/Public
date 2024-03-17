/* 
Programmer: Zach Carlson
Class: CS &145
Date Due: 3/5/2024
Assignment: Lab 5 - Pascal's Triangle
Purpose: A series of classes that will process input from a user and 
return an formatted triangle using the principle of Pascal's Triangle
using recursion.

PTriangle Test class
*/

import java.util.Scanner; // import the scanner library because we need user input

public class PTriangleTest {
    public static void main(String[] args) {
        intro();  // Call the intro method to introduce the program
        
        Scanner scanner = new Scanner(System.in);
        int rows;

        do {
            System.out.print("Please enter the number of rows for the Pascal Triangle (or enter 0 to quit): ");
            rows = scanner.nextInt();  // Read user input for the number of rows

            // Check if the user wants to quit
            if (rows == 0) {
                System.out.println("Exiting program. Thank you for using the Pascal Triangle Generator!");
                break;  // Exit the loop, ending the program
            }
            
            // Validate the input to ensure it's a positive integer
            if (rows < 0) {
                System.out.println("The number of rows should be a positive integer. Please try again.");
            } 
            else {
                // Call the static method of the PTriangleDrawing class to print the triangle
                PTriangleDrawing.printTriangle(rows);
            }
            System.out.println(); // Print a blank line for organization between attempts

        } while (rows != 0);  // Continue until the user enters 0

        scanner.close();  // Close the scanner after use
    }

    // Method to introduce the program to the user
    public static void intro() {
        System.out.println("Welcome to the Pascal Triangle Generator!");
        System.out.println("This program creates a Pascal Triangle with the number of lines");
        System.out.println("you specify. The exterior line of 1's will be both vertical and");
        System.out.println("horizontal, with additional values oriented in their lines.");
        System.out.println("Enter the number of lines for your triangle, and let's start!");
    } // end of intro method

} // end of PTriangleTest class
