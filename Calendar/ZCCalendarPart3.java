// Programmer: Zach Carlson
// Class: CS &141
// Date: Due 8/15/2023
// Assignment: Calendar (Part 3)
// Purpose: Part three of the calendar assignement with ASCII art and Event Planning with text file input, reading and output

import java.util.*; // class import for java Util
import java.io.*; // class inport for input/output operations

public class ZCCalendarPart3 {

   // constants list
   private static boolean dateSelected = false; // setting a flag to indicate if the user has placed an initial date value
   private static int selectDay = 0; // preset value of variable
   private static int selectMonth = 0; // preset value of variable
   private static int selectYear = 0; // preset value of variable
   private static int initialSelectMonth = 0; // preset value of variable
   private static int initialSelectYear = 0;  // preset value of variable
   private static String eventDate = ""; // static string variable assinged to empty string
   private static String eventTitle = ""; // static string variable assinged to empty string
   private static String[][] eventArray = new String[12][]; // event array [months] [days]

   public static void main(String[] args) {
      asciiTop(); // woooo art
      System.out.println("                         Welcome to IT Services Inc. interactive Calendar!"); // Welcome prompt
      
      Scanner input = new Scanner(System.in); // Create a Scanner to obtain input from the command window
      space();
      
      // Interactive Section - User loop
      
      for (int month = 1; month <= 12; month++) {        // Loop through each month from 1 to 12
         int daysInMonth = daysInMonth(selectYear, month);     // Call a method to calculate the number of days in the current month in the specified year
         eventArray[month - 1] = new String[daysInMonth]; // Initialize the eventArray with an array of strings, sized to the number of days in that month
      }
      
      loadEventsFromFile(); // method to look for and load any events from "calendarEvents.txt" file if found
      
      String choiceString; // declaring the string variable
      
      do {
         menu();
         choiceString = input.next().toLowerCase(); // single or double chars caught as string either way
         
         if (choiceString.equals("ev")) {
            System.out.println("Please enter the date and event title in 'mm/dd event_title' format:"); // request user input event in requested format
            input.nextLine(); 
            String dateEventInput = input.nextLine();
            String[] parts = dateEventInput.split(" ", 2); // Split the line into two parts using the space
            String date = parts[0]; // Extract the date part
            String title = parts[1]; // Extract the title part
            int month = Integer.parseInt(date.split("/")[0]); // Extract the month from the date
            int day = Integer.parseInt(date.split("/")[1]); // Extract the day from the date
            eventArray[month - 1][day - 1] = title; // Assign the title to the corresponding location in the event array
            System.out.println("Event added for " + date + ": " + title); // verbose output for user
            continue; // Skip to the next portion of loop
         }
         
         if (choiceString.equals("fp")) {
            System.out.println("Please enter the desired month and file name (Example: 08 Test_Month.txt):");
            input.nextLine(); // user input
            String fileInput = input.nextLine();
            String[] parts = fileInput.split(" ", 2); // Split the line into two parts using the space
            int month = Integer.parseInt(parts[0]); // Extract the month from the input
            String fileName = parts[1]; // store input output file name in array
         
         // Error catch if month is not valid
            if (month < 1 || month > 12) {
               System.out.println("Invalid month. Please try again.");
               continue; // Skip to the next portion of loop
            }
         
            try {
               PrintStream writer = new PrintStream(new File(fileName)); // Create a new file with the given fileName and connect a PrintStream to write to it
               writeMonthToFile(selectYear, month, writer); // Call the method to write the specified month and year to the file using the writer
               writer.close(); // Closing the writer
               System.out.println("Month written to file: " + fileName); // verbose message for user
               space();
            } catch (FileNotFoundException e) {
               System.out.println("Error writing to file. Please try again."); // printed error catch
            }
            continue; // Skip to the next step of the loop
         }
         
         char choice = choiceString.toLowerCase().charAt(0); // moving to a char for switch case selection
                  
         switch (choice) {
            case 'e':
            // code for entering a desired date to display
               if (fromDate(input)) { // dayFromDate and monthFromDate consolidated and then year added
                  drawMonth(selectYear, selectMonth, selectDay); // calendar draw
                  space();
                  dateSelected = true; // flag catch so that n and p will check for this flag before attempting to execute the command
               }
               break;
            
            case 't':
            // code for displaying todays date
               Calendar today = Calendar.getInstance();
               selectYear = today.get(Calendar.YEAR);
               selectMonth = today.get(Calendar.MONTH) + 1; // Months start at 0, so adding 1
               selectDay = today.get(Calendar.DAY_OF_MONTH);
               
               initialSelectMonth = selectMonth; // Store the current month
               initialSelectYear = selectYear;   // Store the current year
               
               displayDate(selectYear, selectMonth, selectDay); // displaying selected date
               drawMonth(selectYear, selectMonth, selectDay); // calendar draw
               space();
            
               dateSelected = true; // flag again
               break;
            
            case 'n':
            // code for displaying the next month
               if (dateSelected) {
                  selectMonth++;
                  
                  if (selectMonth > 12) {
                     selectMonth = 1;
                     selectYear++;
                  }
                  
                  System.out.println("Selected: " + selectMonth + "-" + selectYear); // verbose message for user
                  drawMonth(selectYear, selectMonth, selectDay); // calendar draw
                  space();
               }
               break;
            
            case 'p':
            // code for displaying the previous month
               if (dateSelected) {
                  selectMonth--;
                  
                  if (selectMonth < 1) {
                     selectMonth = 12;
                     selectYear--;
                  }
                  
                  System.out.println("Selected: " + selectMonth + "-" + selectYear); // verbose message for user
                  drawMonth(selectYear, selectMonth, selectDay); // calendar draw
                  space();
               }
               break;
            
            case 'q':
               // code to quit the program from menu
               space();
               line();
               space();
               asciiBottom(); // woooo art
               salutation();
               break;
               
            default:
               System.out.println("Option not found. Please try again!"); // error catch operation
         }
         
      } while (!choiceString.equals("q"));
   
   }  // end of main method
   
// SUPPLEMENTAL METHOD LIST   
   
   public static void line() {
      for (int l = 1; l <= 99; l++) {
         System.out.print("#");
      }
   } // line method
   
   public static void space() {
      System.out.println();
   } // new line method
   
   public static void daysWeek() {
      System.out.println("|     SUN     |     MON     |    TUES     |    WEDS     |    THURS    |     FRI     |     SAT     |");
   } // days of the week method
   
   public static void walls() {
      System.out.println("|             |             |             |             |             |             |             |");
   } // end of walls method
   
   public static void asciiBottom() {
      int rows = 50;
      
      for (int i = rows; i >= 1; i--) {
         for (int j = 1; j <= rows - i; j++) {
            System.out.print(" "); // loop for spaces
         }
         for (int k = 1; k <=i * 2 - 1; k++) {
            System.out.print("v"); // loop for v
         }
         space();
      } 
   } // end of ascii art at the end of the calendar presentation method
   
   public static void asciiTop() {
      int rows = 50;
      
      for (int i = 1; i <= rows; i++) {
         for (int j = 1; j <= rows - i; j++) {
            System.out.print(" "); // loop for spaces
         }
         for (int k = 1; k <=i * 2 - 1; k++) {
            System.out.print("^"); // loops for ^
         }
         space();
      } 
   } // end of the top asci art method
   
   public static void salutation() {
      System.out.println("                                  THANK YOU AND HAVE A GREAT DAY!                                  ");
   } // end of saluation method
   
   public static void header() {
      line();
      space();
      daysWeek();
      line();
      space();
   } // end of header method
   
   public static int[] getDaysInMonths(int year) { // array
    // Initialize an array representing the number of days in each month.
      int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
   
    // If the given year is a leap year, update February's days to 29.
      if (leapYear(year)) {
         daysInMonths[1] = 29;
      }
      return daysInMonths;
   } // end of getDaysInMonth method
      
   public static int daysInMonth (int year, int month) { // integer 
      int[] daysInMonths = getDaysInMonths(year);
      return daysInMonths[month - 1];
   } // end of daysInMonth method
   
   public static boolean leapYear(int year) {
      if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
         return true; // year is divible by 400 or is divible by 4 but not by 100 then return true statement
      }
      else {
         return false; // year is not a leap year
      }
   } // end of leap year method
   
   public static void menu() {
      System.out.println("Please select the desired calendar command option: ");
      System.out.printf("\t 'e' to enter a date and display the cooresponding calendar.%n"); // enter a date
      System.out.printf("\t 't' to view today's date and display today's calendar.%n"); // today's date
      System.out.printf("\t 'n' to display the next month.%n"); // next month
      System.out.printf("\t 'p' to display the previous month.%n"); // previous month
      System.out.printf("\t 'ev' to add a new event using 'mm/dd event_title' format.%n"); // event add to calendar
      System.out.printf("\t 'fp' to specify a month to output to a file. (mm) (exampleEvent.txt)%n"); // print month to output file
      System.out.printf("\t 'q' to quit the program.%n");
      space();
   } // end of menu method
   
   public static void drawMonth(int year, int month, int day) {
      header();
      
      Calendar calendar = Calendar.getInstance();
      calendar.set(year, month - 1, 1);
      
      int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Minus 1 since Monday is 1 in Calendar class
      int daysInMonth = daysInMonth(year, month);
      
      int currentDay = 1; // initializing currentDay variable
      
      while (currentDay <= daysInMonth) {
         currentDay = drawRow(currentDay, day, dayOfWeek, daysInMonth, month, year);
         
         if (currentDay == 1) {
            dayOfWeek = 0; // After the first row, start printing days from the first box
         }
      }
   } // end of drawMonth method
   
   public static int drawRow(int currentDay, int selectedDay, int startDayOfWeek, int daysInMonth, int month, int year) {
    // Input day numbers
      System.out.print("|"); // Start with a pipe for alignment
      int eventDay = currentDay; // Initialize eventDay to currentDay
      
      // Loops through each day of the week
      for (int i = 0; i < 7; i++) {
         // If the current day is greater than the days in the month, input an empty cell
         if (currentDay > daysInMonth) {
            System.out.print("             |");
         } 
         // Input empty cells before the first day
         else if (i < startDayOfWeek && currentDay == 1) {
            System.out.print("             |"); 
         } 
         // Otherwise, input the day and increment currentDay
         else {
            printDay(currentDay, selectedDay, month, year, false);
            currentDay++;
         }
      }
      System.out.println(); // new line to separate rows
   
    // Input events
      System.out.print("|"); // Start the event row with a pipe for alignment
      // Loop through each day of the week for events
      for (int i = 0; i < 7; i++) {
         // If the eventDay is greater than the days in the month, input an empty cell
         if (eventDay > daysInMonth) {
            System.out.print("             |");
         } 
         // input empty cells before the first day and the event is on the first day
         else if (i < startDayOfWeek && eventDay == 1) {
            System.out.print("             |");
         } 
         // Otherwise, input the day's event and increment eventDay
         else {
            printDay(eventDay, selectedDay, month, year, true);
            eventDay++;
         }
      }
      System.out.println(); // new line to separate rows
   
      walls();
      walls();
      line();
      space();
   
      return currentDay; // Return the current day value after it is updated
   } // end of drawRow method
   
   public static void printDay(int currentDay, int day, int month, int year, boolean isEvent) {
      String event = eventArray[month - 1][currentDay - 1]; // Retrieve the event from the eventArray based on the month and current day
   
      if (isEvent) { // If true, print the event
         if (event != null) { // If there is an event, print it
            System.out.printf("%-13s|", event);
         } else { // If there's no event, print an empty space
            System.out.printf("%-13s|", "");
         }
      } 
      else { // If false, print the date
         if (currentDay == day && month == initialSelectMonth && year == initialSelectYear) { // If the current day, month, and year match the selected values, print the day with an asterisk
            System.out.printf("%2d*          |", currentDay);
         } 
         else { // Otherwise, print the current day without an asterisk
            System.out.printf("%2d           |", currentDay);
         }
      }
   } // end of printDay method
         
   public static void displayDate(int year, int month, int day) {
      System.out.println("Selected date: " + month + "-" + day + "-" + year);
   } // end of displayDate method
   
   public static boolean fromDate(Scanner input) {
      System.out.println("Please enter the desired date: (dd/mm/yyyy) ");
      String dateInput = input.next(); // user input
      
      if(!dateInput.matches("\\d{2}/\\d{2}/\\d{4}")) { // error catch for incorrect date format
         System.out.println("Incorrect date format. Please try again.");
         return false;
      }
    
      selectDay = Integer.parseInt(dateInput.substring(0, 2));
      selectMonth = Integer.parseInt(dateInput.substring(3, 5));
      selectYear = Integer.parseInt(dateInput.substring(6));
      
      if (selectMonth < 1 || selectMonth > 12) { // error catch for incorrect month
         System.out.println("Invalid month. Please try again.");
         space();
         return false;
      }
   
      if (selectDay < 1 || selectDay > daysInMonth(selectYear, selectMonth)) { // error catch for incorrect day
         System.out.println("Invalid day for the given month and year. Please try again.");
         space();
         return false;
      }
   
      initialSelectMonth = selectMonth; // Store the current month
      initialSelectYear = selectYear;   // Store the current year
      return true;
   } // end of fromDate method
   
   public static void loadEventsFromFile() {
      try {
         File file = new File("calendarEvents.txt"); // Create a File object representing the events file
         Scanner fileScanner = new Scanner(file); // Create a Scanner object to read from the file
      
         while (fileScanner.hasNextLine()) { // Loop through each line of the file
            String line = fileScanner.nextLine(); // Read the next line from the file
            String[] parts = line.split(" ", 2); // Split the line into two parts using the space
            String date = parts[0]; // Extract the date part
            String title = parts[1]; // Extract the title part
            int month = Integer.parseInt(date.split("/")[0]); // Extract the month from the date
            int day = Integer.parseInt(date.split("/")[1]); // Extract the day from the date
            eventArray[month - 1][day - 1] = title; // Assign the title to the corresponding location in the event array
         }
      
         fileScanner.close(); // Close the fileScanner
      } catch (FileNotFoundException e) { // Handle the case where the file is not found
         System.out.println("calendarEvents.txt file not found. Continuing without loading events."); // verbose communication to user. continuing with action.
      }
   } // end of loadEventsFromFile method
   
   public static void writeMonthToFile(int year, int month, PrintStream writer) {
      header(writer); // Call the header method to print the header of the month
   
      Calendar calendar = Calendar.getInstance(); // Create a Calendar object
      calendar.set(year, month - 1, 1); // Set the calendar to the first day of the specified month and year
   
      int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Get the day of the week for the first day of the month (subtracting 1 to align)
      int daysInMonth = daysInMonth(year, month); // Get the number of days in the specified month and year
   
      int currentDay = 1; // Initialize the current day to 1
   
      while (currentDay <= daysInMonth) { // Loop through all the days in the month
      // Write a row to the file using the writeRowToFile method, and update currentDay with the returned value
         currentDay = writeRowToFile(currentDay, dayOfWeek, daysInMonth, month, year, writer);
      
         if (currentDay == 1) {
            dayOfWeek = 0; // After the first row, start printing days from the first box
         }
      }
   } // end of writeMonthToFile method
   
   public static void header(PrintStream writer) {
      line(writer);
      space(writer);
      daysWeek(writer);
      line(writer);
      space(writer);
   } // end of header method for file output
   
   public static void line(PrintStream writer) {
      for (int l = 1; l <= 99; l++) {
         writer.print("#");
      }
   } // end of line method for file output
   
   public static int writeRowToFile(int currentDay, int startDayOfWeek, int daysInMonth, int month, int year, PrintStream writer) {
   // Draw day numbers
      writer.print("|"); // Start with a pipe for alignment
      int eventDay = currentDay; // Initialize eventDay to currentDay
      for (int i = 0; i < 7; i++) {
         if (currentDay > daysInMonth) {
            writer.print("             |"); // Print empty cells if currentDay exceeds daysInMonth
         } 
         else if (i < startDayOfWeek && currentDay == 1) {
            writer.print("             |"); // Print empty cells before the first day of the month
         } 
         else {
            String event = eventArray[month - 1][currentDay - 1]; // Retrieve the event from the eventArray
            if (currentDay == selectDay && month == initialSelectMonth && year == initialSelectYear) {
               writer.printf("%2d*          |", currentDay); // Print current day with an asterisk if selected
            } 
            else {
               writer.printf("%2d           |", currentDay); // Print current day without an asterisk
            }
            currentDay++;
         }
      }
      writer.println(); // Print a new line after drawing day numbers
   
   // Draw events
      writer.print("|"); // Start the event row with a pipe for alignment
      for (int i = 0; i < 7; i++) {
         if (eventDay > daysInMonth) {
            writer.print("             |"); // Print empty cells if eventDay exceeds daysInMonth
         } 
         else if (i < startDayOfWeek && eventDay == 1) {
            writer.print("             |"); // Print empty cells before the first day of the month
         } 
         else {
            String event = eventArray[month - 1][eventDay - 1]; // Retrieve the event from the eventArray
            if (event != null) {
               writer.printf("%-13s|", event); // Print the event if exists
            } else {
               writer.printf("%-13s|", ""); // Print an empty space if there's no event
            }
            eventDay++;
         }
      }
      writer.println(); // Print a new line after drawing events
   
      writer.println("|             |             |             |             |             |             |             |"); // Print divider line
      writer.println("|             |             |             |             |             |             |             |"); // Print divider line
      line(writer); // line method for file output
      
      writer.println(); // Print a new line
   
      return currentDay; // Return updated currentDay value
   } // end of writeRowToFile method, basically the drawRow method but with file output
   
   public static void space(PrintStream writer) {
      writer.println();
   } // new line method for file output

   public static void daysWeek(PrintStream writer) {
      writer.println("|     SUN     |     MON     |    TUES     |    WEDS     |    THURS    |     FRI     |     SAT     |");
   } // days of the week method for file output
   
} // end of class