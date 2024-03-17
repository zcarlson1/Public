// Programmer: Zach Carlson
// Class: CS &141
// Date: Due 8/1/2023
// Assignment: Lab 5 - MadLibs
// Purpose: Create a program that will read input from a plaintext file, find placeholder, prompt the user for input to replace those placeholders and then output the result to a specified file

import java.util.*; // class import for java Util
import java.io.*; // class import for input/output

public class ZCMadLibs {

   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(System.in); // Create a Scanner to obtain input from the command window
      char choice;
   
      intro();
      space();
      
      menu();
      choice = input.nextLine().toLowerCase().charAt(0); // capturing only the first char input and forcing it to be stored lower case
      
      do {
         if (choice == 'c') { // create madlib, "c" selected
            String fileName = inputFileName(input, "Please enter the name of the file to be used: (input.txt)    "); // user specifies the input txt file
            Scanner fileInput = new Scanner(new File(fileName));
            
            String madLibContent = processMadLib(fileInput, input); // both Scanner objects included but separate, made into a string
            fileInput.close();
            
            String outputFileName = getOutputFileName(input); // user specifies the output file name 
            try (PrintStream ps = new PrintStream(outputFileName)) {
               ps.print(madLibContent);
            } 
            catch (FileNotFoundException e) { // using e variable to hold the exception message
               System.out.println("Error writing to the output file.");
            }
         }
         
         else if (choice == 'v') { // view madlib, "v" selected
            viewMadLib(input);
         }
         
         else if (choice == 'q') { // quit, "q" selected
            salutation();
            break;
         }
         
         else { // invalid input catch, anything else input
            System.out.println("Invalid selection.");
            menu();
            space();
         }
         
         space();
         menu();
         choice = input.nextLine().toLowerCase().charAt(0);
      
      } while (choice != 'q'); // end of do while loop
      
   } // end of main method
   
   public static void intro() {
      System.out.println("Welcome to the game of Mad Libs.");
      System.out.println("I will ask you to provide various words");
      System.out.println("and phrases to fill in a story.");
      System.out.println("The result will be written to an output file.");
   } // end of menu method
   
   public static void space() {
      System.out.println();
   } // end of space/new line method
   
   public static void menu() {
      System.out.println("(C)reate mad-lib, (V)iew mad-lib, (Q)uit?");
   } // end of menu method
   
   public static String inputFileName(Scanner input, String message) {
      System.out.print(message);
      String fileName = input.nextLine();
      return fileName;
   } // end of inputFileName method
   
   public static String processMadLib(Scanner fileInput, Scanner input) {
      StringBuilder madlib = new StringBuilder();
      
      while (fileInput.hasNextLine()) {
         String text = fileInput.nextLine();
         while (text.contains("<Dramatic-Chef>")) { // replacement of text content for <Dramatic-Chef> for each instance of the placeholder
            System.out.print("Please type the name of a Dramatic Chef: ");
            text = text.replaceFirst("<Dramatic-Chef>", input.nextLine());
         }
         while (text.contains("<noun>")) { // replacement of text content for <noun> for each instance of the placeholder
            System.out.print("Please type a noun: ");
            text = text.replaceFirst("<noun>", input.nextLine());
         }
         while (text.contains("<plural-noun>")) { // replacement of text content for <plural-noun> for each instance of the placeholder
            System.out.print("Please type a plural noun: ");
            text = text.replaceFirst("<plural-noun>", input.nextLine());
         }
         while (text.contains("<verb>")) { // replacement of text content for <verb> for each instance of the placeholder
            System.out.print("Please type a verb: ");
            text = text.replaceFirst("<verb>", input.nextLine());
         }
         while (text.contains("<adjective>")) { // replacement of text content for <adjective> for each instance of the placeholder
            System.out.print("Please type an adjective: ");
            text = text.replaceFirst("<adjective>", input.nextLine());
         }
         
         text = text.replace(". ", ".\n"); // replacing each . with a . and then starting a new line for cleaner formatting
         if(text.endsWith(".")) {
            text += "\n";
         }
         
         madlib.append(text).append("\n"); // pushing the processed input to StringBuilder
                  
      }
      
      System.out.println("Your Mab Lib has been created!.");
      return madlib.toString(); // returning the content created 
   
   } // end of processMabLib method
   
   public static String getOutputFileName(Scanner input) {
      System.out.print("Please enter the name of the output file (output.txt): "); // user specifies the output txt filename
      return input.nextLine();
   } // end of getOutputFileName method
   
   public static void viewMadLib(Scanner input) throws FileNotFoundException {
      System.out.print("Please enter the name of the output file you want to view (e.g., output.txt): ");
      String fileName = input.nextLine();
      File file = new File(fileName);
   
      if (!file.exists()) {
         System.out.println("The specified file does not exist.");
         return;
      }
   
      Scanner fileScanner = new Scanner(file);
      while (fileScanner.hasNextLine()) {
         String line = fileScanner.nextLine();
         System.out.println(line);
      }
      
      fileScanner.close();
   } // end of viewMadLib method
   
   public static void salutation() {
      System.out.println("Thank you for playing! Have a great day!");
   } // end of salutation method
  
} // end of class