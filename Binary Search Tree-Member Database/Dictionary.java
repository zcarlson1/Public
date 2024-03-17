/* 
Programmer: Zach Carlson
Class: CS &145
Date Due: 3/15/2024
Assignment: Lab 6 - Binary Search Tree Dictionary Lab
Purpose: A series of classes that will process input from a user,
storing that information in a binary search tree with menu options
for searching and printing that tree in specific traversals or
modify the data as the user desires. 

Dictionary Class (Main)
*/

import java.util.InputMismatchException; // importing error handling
import java.util.Scanner; // importing scanner library

public class Dictionary {
    // New instance of the binary search tree created and assigned to bst
    private static BinarySearchTree bst = new BinarySearchTree(); 
    // Intialize scanner object and assign to scanner
    private static Scanner scanner = new Scanner(System.in); 

    public static void main(String[] args) {
        // Friendly program introduction
        System.out.println("Welcome to the Dictionary Program!");
        System.out.println("You can manage employee, customer, or member records.");

        // Hard coded randomized examples for testing purposes
        // By 1000's to test traversal types/node placement
        // Used a random generator on the internet. Some of these are weird.
        // comment these out of not desired
        bst.insert(1000, new Person("Jennifer", "Estrada", "66002 Linda Loaf",
                     "Richardmouth", "Utah", 70373, "udavis@yahoo.com", "8713750084"));
        bst.insert(2000, new Person("Cynthia", "Soto", "8282 William Drive Suite 128", 
                     "Lake Jessicaville", "Oregon", 41265, "hkemp@gmail.com", "5101852431"));
        bst.insert(3000, new Person("Robert", "Perry", "300 Robertson Corners", 
                     "Royfort", "Kentucky", 70734, "samuel37@carey.biz", "8021737724"));
        bst.insert(4000, new Person("Paul", "Smith", "5421 Edward Key Apt. 799",
                     "Diazmouth", "Nevada", 91931, "jennifercooper@yahoo.com", "2286062133"));
        bst.insert(5000, new Person("Randy", "Brewer", "346 Rosario Stream",
                     "Martinport", "New Mexico", 66987, "rodneyrodriguez@gmail.com", "9354643134"));
        bst.insert(6000, new Person("Mark", "Lopez", "8190 Kimberly Haven Suite 703",
                     "West Joseph", "Indiana", 45332, "howarddrew@hotmail.com", "3887351093"));
        bst.insert(7000, new Person("Troy", "Benjamin", "58788 Patricia Inlet Apt. 429",
                     "Simmonsland", "Alabama", 63812, "marcus69@keller.com", "6414939302"));
        bst.insert(8000, new Person("April", "Houston", "5183 Lisa Center Suite 965",
                     "Angelaside", "Washington", 13481, "montoyaalexis@fleming.com", "5297234784"));
        bst.insert(9000, new Person("Daniel", "Cook", "96205 Andrew Hill Suite 684",
                     "South William", "Idaho", 12669, "aaron28@brown-saunders.biz", "1223260371"));
        bst.insert(10000, new Person("Kevin", "Mcmillan", "6816 Teresa Harbor Suite 749",
                     "Lake Jeffrey", "New York", 40374, "alanbennett@hernandez.com", "3549085933"));
        // end of hard coded examples

        int choice;
        do {
            // Display the main menu and prompt for user input
            System.out.println("\nPlease the number of your desired option:");
            System.out.println("1. Add a record");
            System.out.println("2. Delete a record");
            System.out.println("3. Modify a record");
            System.out.println("4. Lookup a record");
            System.out.println("5. List number of records");
            System.out.println("6. Exit the program");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // Handle the user's choice
            switch (choice) {
                case 1:
                    addRecord();
                    break;
                case 2:
                    deleteRecord();
                    break;
                case 3:
                    modifyRecord();
                    break;
                case 4:
                    lookupRecord();
                    break;
                case 5:
                    System.out.println("Number of records: " + bst.size()); 
                    break;
                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    // error handling
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        } while (choice != 6);
    } // end of main method

    private static void addRecord() {
        System.out.println("Adding a new record.");
        int memberID = 0; // Initialize memberID
        boolean validInput = false; // Flag to check for valid input
    
        // Loop until a valid Member ID is provided
        while (!validInput) {
            System.out.print("Enter Member ID (1-10000): ");
            try {
                memberID = scanner.nextInt();
                if (memberID >= 1 && memberID <= 10000) {
                    validInput = true; // Valid Member ID
                } 
                else {
                    System.out.println("Error: Member ID must be between 1 and 10000.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Member ID must be an integer.");
                scanner.nextLine(); // Clear the buffer
            }
        } // end of while to check member ID input is within range
        scanner.nextLine(); // Clear the buffer
    
        // Check for duplicate Member ID
        while (bst.lookup(memberID) != null) {
            System.out.println("Error: A record with the same Member ID already exists.");
            System.out.println("Please enter a unique Member ID (1-10000):");
            memberID = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer
        }
    
        // Collect other personal details
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter street address:");
        String streetAddress = scanner.nextLine();
        System.out.println("Enter city:");
        String city = scanner.nextLine();
        System.out.println("Enter state:");
        String state = scanner.nextLine();
        
        // Loop until a valid zip code is provided
        int zip = 0; // Initialize zip
        validInput = false; // Reset flag for zip code validation
        while (!validInput) {
            System.out.println("Enter zip code:");
            String zipInput = scanner.nextLine(); // Read zip code as String
            // Check if it's 5 digits and all numbers
            if (zipInput.length() == 5 && zipInput.matches("\\d+")) { 
                zip = Integer.parseInt(zipInput);
                validInput = true; // Valid zip code flag
            } 
            else {
                System.out.println("Error: Zip code must be exactly 5 digits long and numeric.");
            }
        } // end of while loop to check zip code
    
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();
    
        // Create a new Person object with collected details
        Person person = new Person(firstName, lastName, streetAddress, 
                                    city, state, zip, email, phoneNumber);
        bst.insert(memberID, person);
        System.out.println("Record added successfully.");
    } // end of addRecord method

    private static void deleteRecord() {
        System.out.println("Enter the Member ID of the record you wish to delete:");
        int memberID = scanner.nextInt();
    
        // Delete the record from the BST, with a bit of blanket error handling
        bst.delete(memberID);
        System.out.println("Record deleted successfully, if it existed.");
    } // end of deleteRecord method

    private static void modifyRecord() {
        // Inform the user about the action being taken
        System.out.println("Modifying an existing record.");
        
        // Prompt for the Member ID with an option to exit
        System.out.print("Enter Member ID of the record to modify (or 0 to exit): ");
        int memberID = scanner.nextInt();
        scanner.nextLine(); // Clear the scanner buffer after reading an integer
    
        // Check if the user chose to exit the modification process
        if (memberID == 0) {
            return; // Exit the method if 0 is entered
        }
    
        // Attempt to find the node with the given Member ID
        Node nodeToModify = bst.lookupNode(memberID);
    
        // If the node wasn't found, inform the user and exit
        if (nodeToModify == null) {
            System.out.println("Record not found.");
            return;
        }
    
        boolean done = false; // Flag to control the modification loop
        // Loop for modifying the record until the user decides to finish
        while (!done) {
            // Display options for fields to modify
            System.out.println("Select the field to modify:");
            System.out.println("1. First Name" + "\n2. Last Name" + "\n3. Street Address" + 
                   "\n4. City" + "\n5. State" + "\n6. Zip" + "\n7. Email" + 
                   "\n8. Phone Number" + "\n0. Done with modifications");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the scanner buffer after reading an integer
    
            // Process the user's choice
            switch (choice) {
                case 1:
                    System.out.print("Enter new First Name: ");
                    nodeToModify.person.firstName = scanner.nextLine(); // Update the first name
                    break;
                case 2:
                    System.out.print("Enter new Last Name: ");
                    nodeToModify.person.lastName = scanner.nextLine(); // Update the last name
                    break;
                case 3:
                    System.out.print("Enter new Street Address: ");
                    nodeToModify.person.streetAddress = scanner.nextLine(); // Update the street address
                    break;
                case 4:
                    System.out.print("Enter new City: ");
                    nodeToModify.person.city = scanner.nextLine(); // Update the city
                    break;
                case 5:
                    System.out.print("Enter new State: ");
                    nodeToModify.person.state = scanner.nextLine(); // Update the state
                    break;
                case 6: // Zip code modification case
                    boolean zipValid = false;
                    while (!zipValid) {
                        System.out.print("Enter new Zip Code (5 digits): ");
                        String zipInput = scanner.nextLine(); // Read the input as a String
                        // Check if the input is exactly 5 digits and numeric
                        if (zipInput.matches("\\d{5}")) {
                            nodeToModify.person.zip = Integer.parseInt(zipInput); // Convert and update the zip code
                            zipValid = true; // Mark as valid to exit the loop
                        } 
                        else {
                            System.out.println("Invalid Zip Code. Please enter exactly 5 digits.");
                            // The loop will repeat, prompting for a valid zip code
                        }
                    }
                    break;
                case 7:
                    System.out.print("Enter new Email: ");
                    nodeToModify.person.email = scanner.nextLine(); // Update the email
                    break;
                case 8:
                    System.out.print("Enter new Phone Number: ");
                    nodeToModify.person.phoneNumber = scanner.nextLine(); // Update the phone number
                    break;
                case 0:
                    done = true; // Set the flag to exit the loop
                    break;
                default:
                    // Inform the user about invalid choices and prompt to try again
                    // Error handling
                    System.out.println("Invalid choice. Please enter a valid option or 0 to exit.");
                    break;
            }
        } // end of modify selection while loop
    
        // Inform the user that modifications are complete
        System.out.println("Modifications completed.");
    } // end of modifyRecord method

    private static void lookupRecord() {
        System.out.println("Lookup a record");
        // Submenu for choosing traversal type
        System.out.println("1. Pre-order");
        System.out.println("2. In-order");
        System.out.println("3. Post-order");
        System.out.print("Choose a traversal type for lookup: ");
        int choice = scanner.nextInt();

        // Based on the choice, complete the corresponding traversal
        switch (choice) {
            case 1:
                // Calling Pre-order traversal
                bst.preOrder();
                break;
            case 2:
                // Calling In-order traversal
                bst.inOrder();
                break;
            case 3:
                // Calling Post-order traversal
                bst.postOrder();
                break;
            default:
                // Error handling
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
        } // end of traversal switch/case
    } // end of lookupRecord method

} // end of Dictionary class

