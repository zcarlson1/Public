/* 
Programmer: Zach Carlson
Class: CS &145
Date Due: 2/14/2024
Assignment: Assignment 2 - Phone Book
Purpose: A series of classes that will process input from a user and 
return an formatted contact listing for all contacts entered,
and additionally allow the user to modify contacts, in full or in part.
Phonebook Test class
*/

// Import the Scanner class to read input from the console
import java.util.Scanner; 

public class PhonebookTest {
    public static void main(String[] args) {
        // Create a Scanner object to read input
        Scanner scanner = new Scanner(System.in); 
        // Create a PhonebookManager object to manage contacts
        PhonebookManager manager = new PhonebookManager(); 
        String input; // Variable to store user input

        // Main loop of the Phonebook
        do {
            // Print a blank line for spacing and easier viewing
            System.out.println();
            // Display the menu options to the user
            displayMenu();
            // User input converted to lowercase for consistency
            input = scanner.nextLine().toLowerCase(); 
            
            System.out.println(); // Line of space for viewing ease
            // Switch case menu both complete word recognition and number options for each selection
            switch (input) {
                case "1":
                case "add": // Add a new contact
                    System.out.println("Enter the complete Name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter complete Address:");
                    String address = scanner.nextLine();
                    System.out.println("Enter a 9 digit Phone Number: (XXX-XXX-XXXX)");
                    String phoneNumber = scanner.nextLine();
                     // Call the addContact method with user-provided details
                    manager.addContact(name, address, phoneNumber);
                    System.out.println("Contact added successfully.");
                    break;
                case "2":
                case "delete": // Delete an existing contact
                    System.out.println("Enter the full name of the contact to delete:");
                    name = scanner.nextLine();
                    // Call the deleteContact method with the name provided by the user
                    manager.deleteContact(name); 
                    break;
                case "3":
                case "view contacts": // View all contacts
                    manager.sortContacts(); // Sort list alphabetically
                    manager.printContacts(); // Call the printContacts method to display all contacts
                    break;
                case "4":
                case "name search": // Search by name
                    System.out.println("Enter the full name to search for:");
                    String searchName = scanner.nextLine();
                    boolean found = manager.searchByName(searchName);
                    if (!found) { // error handling
                        System.out.println("No contact found with the name: " + searchName);
                    }
                    break;
                case "5":
                case "address search": // Search by address
                    System.out.println("Enter the complete address to search for:");
                    String searchAddress = scanner.nextLine();
                    found = manager.searchByAddress(searchAddress);
                    if (!found) { // error handling
                        System.out.println("No contact found with the address: " + searchAddress);
                    }
                    break;
                case "6":
                case "phone number search": // Search by phone number
                    System.out.println("Enter the 9 digit phone number to search for: (XXX-XXX-XXXX)");
                    String searchPhoneNumber = scanner.nextLine();
                    found = manager.searchByPhoneNumber(searchPhoneNumber); 
                    if (!found) { // error handling
                        System.out.println("No contact found with the phone number: " + searchPhoneNumber);
                    }
                    break;
                    case "7": // Edit Name
                    System.out.println("Enter the current full name of the contact you want to edit:");
                    String currentName = scanner.nextLine();
                    System.out.println("Enter the complete new name:");
                    String newName = scanner.nextLine();
                    manager.editContactName(currentName, newName);
                    System.out.println("Contact name updated successfully.");
                    break;
                case "8": // Edit Address
                    System.out.println("Enter the full name of the contact whose address you want to edit:");
                    String nameForAddress = scanner.nextLine();
                    System.out.println("Enter the full new address:");
                    String newAddress = scanner.nextLine();
                    manager.editContactAddress(nameForAddress, newAddress);
                    System.out.println("Contact address updated successfully.");
                    break;
                case "9": // Edit Phone Number
                    System.out.println("Enter the full name of the contact whose phone number you want to edit:");
                    String nameForPhone = scanner.nextLine();
                    System.out.println("Enter the complete phone number: (XXX-XXX-XXXX)");
                    String newPhone = scanner.nextLine();
                    manager.editContactPhoneNumber(nameForPhone, newPhone);
                    System.out.println("Contact phone number updated successfully.");
                    break;
                // Handling txt file import
                case "10":
                case "contact import": // Case for importing contacts
                    System.out.println("Enter the filename to import contacts from (e.g., 'contacts.txt'):");
                    // Read the filename from user input
                    String filename = scanner.nextLine(); 
                    // Call the importContacts method and capture the result
                    boolean success = manager.importContacts(filename); 
                    if (success) {
                        System.out.println("Contacts imported successfully.");
                    } 
                    else {
                        System.out.println("Failed to import contacts. Please check the filename and try again.");
                    }
                    break;
                // Wipe the current directory
                case "99":
                case "clear all entries":
                    manager.clearAllEntries();
                    System.out.println("All contacts have been cleared."); // User notification
                    break;
                // Quit the program
                case "0":
                case "quit phonebook":
                    System.out.println("Exiting Phonebook...");
                    System.out.println(); // spacing for ease of viewing
                    break;
                default: // Handle invalid input
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
            // Continue until the user decides to quit
        } while (!input.equals("0") && !input.equals("quit phonebook")); 

        scanner.close(); // Close the scanner object
    }// end of main method

    private static void displayMenu() {
        System.out.println("Please type your desired selection: (Number or complete description)");
        System.out.println("1 - Add\n2 - Delete\n3 - View Contacts");
        System.out.println("4 - Name Search\n5 - Address Search\n6 - Phone Number Search");
        System.out.println("7 - Edit Name\n8 - Edit Address\n9 - Edit Phone Number");
        System.out.println("10 - Contact Import\n99 - Clear All Entries");
        System.out.println("0 - Quit Phonebook");
    } // end of displayMenu method

} // end of class
