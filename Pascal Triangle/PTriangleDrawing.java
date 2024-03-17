/* 
Programmer: Zach Carlson
Class: CS &145
Date Due: 3/5/2024
Assignment: Lab 5 - Pascal's Triangle
Purpose: A series of classes that will process input from a user and 
return an formatted triangle using the principle of Pascal's Triangle
using recursion.

PTriangleDrawing Class
*/


public class PTriangleDrawing {

    // Calculate the value at a specific row and column in Pascal's Triangle
    public static int calculateValue(int row, int col) {
        if (col == 0 || col == row) {
            return 1; // The first and last values in each row are 1
        }
        // Recursively calculate the value at a specific position
        return calculateValue(row - 1, col - 1) + calculateValue(row - 1, col);
    } // end of calculateValue method

    // Print Pascal's Triangle in the shape of a pyramid
    public static void printTriangle(int rows) {
        // Calculate the number width of the largest number in the last row for formatting
        int maxNumberWidth = Integer.toString(calculateValue(rows - 1, (rows - 1) / 2)).length();

        // Loop through each row to print it
        for (int row = 0; row < rows; row++) {
            // Calculate and print the required spaces to center the values for the current row
            int leadingSpaces = (rows - row - 1) * (maxNumberWidth + 1) / 2;
            for (int i = 0; i < leadingSpaces; i++) {
                System.out.print(" ");
            }

            // Print each value in the row
            for (int col = 0; col <= row; col++) {
                int value = calculateValue(row, col);
                // Print the number with formatted width and an extra space for separation
                System.out.printf("%-" + (maxNumberWidth + 1) + "d", value);
            }
            // Move to the next line after printing all values in the row
            System.out.println();
        }
    } // end of printTriangle method

} // end of PTriangleDrawing class
