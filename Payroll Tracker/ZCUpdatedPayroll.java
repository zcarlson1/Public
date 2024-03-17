// Programmer: Zach Carlson
// Class: CS &141
// Date Due: 7/18/2023
// Assignment: Lab 3 - Updated Payroll
// Purpose: An interactive lab for multiple users to determine payroll information, now with updates

import java.util.Scanner; //program imports the Scanner class from library

public class ZCUpdatedPayroll { // class
   public static void main(String[] args) { // main method
      asciiMoneyTop(); // top ascii art $$$
      System.out.println("Welcome to IT Services Inc. updated bi-weekly Payroll calculator!"); // Welcome prompt
      space();
        
      Scanner input = new Scanner(System.in); // Create a Scanner to obtain input from the command window

      int numEmployees = numEmp(input); // calling the numEmp method to request the number of employees

      for (int i = numEmployees; i > 0; i--) {
         System.out.print("Please enter your Employee ID: "); // User enters their employment identification
         int employeeID = input.nextInt(); // assigning to integer to a variable
         input.nextLine(); // reads the input
         space();

         System.out.print("Please enter your Name: "); // Current user enters name
         String employeeName = input.nextLine(); // reads string
         space();

         System.out.print("Please confirm hourly (Select 1) or salary (Select 2): "); // Current user enters pay type
         int payType = input.nextInt(); // reads the integer
         space();

         for (; payType != 1 && payType != 2; payType = input.nextInt()) {
            System.out.print("Error, the pay type specified is not valid. Please confirm hourly (Select 1) or salary (Select 2): "); // Error message and repeat of pay type information request
            input.nextLine(); // reads integer as a String
            space();
         } // end of for loop to catch invalid entries.
            
         switch (payType) { 
            case 1: // hourly employee data capture
               hourlySummary(employeeName, employeeID, input); //Presentation of income data for hourly employee
               break;

            case 2: // salary employee data capture
               salarySummary(employeeName, employeeID, input); //Presentation of income data for salary employee
               break;
            
            default:
               System.out.println("Error: Unable to process request. We apologize for any inconvenience! Please start again or reach out to HR for further assistance."); // exit for switch
               break;
            
         } // end of switch
            
      } // end of numEmployee for loop
        
      salutation();
      asciiMoneyBottom();
        
   } // end of main method
   
   public static void moneyLine() {
      for (int m = 1; m<=80; m++) {
         System.out.print("$");
      }
      System.out.println();
   } // end of line of dollar sign method
   
   public static void space() {
      System.out.println();
   } // end of a new line space method
   
   public static void hourlySummary(String employeeName, int employeeID, Scanner input) {
        System.out.print("Please enter your Hours worked (00): "); // User enters amount of hours worked
        int hoursWorked = input.nextInt(); // assigning to integer to a variable
        input.nextLine(); // reads the input
        space();

        System.out.print("Please enter your Hourly rate ($00.00): "); // User enters hourly amount
        double hourlyRate = input.nextDouble(); // assigning to integer to a variable
        input.nextLine(); // reads the input
        space();

        double overtimeRate = (hourlyRate * 1.5); // calculating overtime rate at 150% base hourly rate
        double hourlyGross; // declaring variable

        if (hoursWorked <= 80) {
            hourlyGross = hoursWorked * hourlyRate;
        } 
        else {
            hourlyGross = (80 * hourlyRate) + ((hoursWorked - 80) * overtimeRate);
        }

        double taxRateHourly = 0.22; // declaring tax rate variable
        double totalTax = hourlyGross * taxRateHourly; // calculating total taxed from that pay period
        double hourlyNet = hourlyGross - totalTax; // calculating net income

        System.out.println("Thank you, " + employeeName + ", Employee ID: " + employeeID);
        System.out.println("You worked " + hoursWorked + " hours during the pay period.");
        System.out.printf("Your gross hourly income for the pay period is: $%.2f%n", hourlyGross);
        System.out.printf("Your net hourly income for the pay period is: $%.2f%n", hourlyNet);
        System.out.printf("You were taxed at 22 percent, and the amount is: $%.2f%n", totalTax);
        System.out.println("========================================================================================\n");
   } // end of hourly summary method
   
   public static void salarySummary(String employeeName, int employeeID, Scanner input) {
        System.out.print("Please enter your Hours worked (00): "); // User enters amount of hours worked
        int salaryHours = input.nextInt(); // assigning to integer to a variable
        input.nextLine(); // reads the input
        space();
 
        System.out.print("Please enter your per year salary ($000000): "); // User enters yearly salary
        int salary = input.nextInt(); // assigning to integer to a variable
        input.nextLine();
        space();

        double taxRateSalary = 0.22; // declaring tax rate variable
        double salaryGross = salary / 26; // finding two-week pay amount
        double salaryTax = salaryGross * taxRateSalary; // calculating tax amount
        double salaryNet = salaryGross - salaryTax; // calculating net salary

        System.out.println("Thank you, " + employeeName + ", Employee ID: " + employeeID);
        System.out.println("You worked " + salaryHours + " hours during the pay period.");
        System.out.printf("Your gross salary income for the pay period is: $%.2f%n", salaryGross);
        System.out.printf("Your net salary income for the pay period is: $%.2f%n", salaryNet);
        System.out.printf("You were taxed at 22 percent, and the amount is: $%.2f%n", salaryTax);
        System.out.println("========================================================================================\n");
   } // end of salary summary method
   
   public static void asciiMoneyBottom() {
      int rows = 49;
      
      for (int i = rows; i >= 1; i--) {
         for (int j = 1; j <= rows - i; j++) {
            System.out.print(" "); // loop for spaces
         }
         for (int k = 1; k <=i * 2 - 1; k++) {
            System.out.print("$"); // loop for $
         }
      space();
      } // end of for loop
   } // end of ascii art at the end of the update payroll presentation method
   
   public static void asciiMoneyTop() {
      int rows = 49;
      
      for (int i = 1; i <= rows; i++) {
         for (int j = 1; j <= rows - i; j++) {
            System.out.print(" "); // loop for spaces
         }
         for (int k = 1; k <=i * 2 - 1; k++) {
            System.out.print("$"); // loops for $
         }
      space();
      } 
   } // end of the top asci art method
   
   public static int numEmp(Scanner input) {
      System.out.print("Please enter the number of employees requesting income calculation: "); // Input number of users
      int numEmployees = input.nextInt(); // assigning to integer to a variable
      input.nextLine(); // reads the input
      System.out.println();
      return numEmployees;
   } // end of numEmp method
   
   public static void salutation() {
      System.out.println("Thank you for using our updated income calculator! Please refer to your direct manager or HR for any questions.\n                                         Have a great day!");
   } // end of saluation
   
} // end of class