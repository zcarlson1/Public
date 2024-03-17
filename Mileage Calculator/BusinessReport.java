// Programmer: Zach Carlson
// Class: CS &141
// Date: 7/3/2023
// Assignment: Lab 1
// Business Name: IT Services Inc.
// Business Address: 21 Chester Place Los Angeles, CA 90007
// Purpose: Business Report - Mileage Calculation
// This program is intended to work as a quick interactice calculator for employees. 

import java.util.Scanner; //program imports the Scanner class from library

public class BusinessReport // name
{
   public static void main(String[] args) { // main method
      System.out.println("Welcome to IT Services Inc. reimbursement calculator!"); // Welcome prompt
      
      Scanner sc = new Scanner(System.in); // Create a Scanner to obtain input from the command window
      
      System.out.println("Please enter your name."); // User enters their name
      String str = sc.nextLine(); // reads string 
      System.out.println("Thank you very much, " + str); // Thank the user
      
      System.out.println("Enter personal miles traveled (Home to Office and return) rounded down to the nearest whole number: "); // first integer mile prompt
      int number1 = sc.nextInt(); // reads first input integer
      
      System.out.println("Enter business miles traveled (Office to destination and return) rounded down to the nearest whole number: "); // second integer mile prompt
      int number2 = sc.nextInt(); // reads second input integer
      
      int sum = number1 + number2; // adds mileage together
      
      System.out.printf("Reimburseable mileage is \t %d!%n", sum); // displays sum
      
      System.out.print("Thank you for using our calculator!"); // Action complete message
      } // end of main method
} // end of class
    
      
      
      
      
