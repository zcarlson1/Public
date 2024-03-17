/* 
Programmer: Zach Carlson
Class: CS &145
Date Due: 2/14/2024
Assignment: Assignment 2 - Phone Book
Purpose: A series of classes that will process input from a user and 
return an formatted contact listing for all contacts entered,
and additionally allow the user to modify contacts, in full or in part.
Phonebook Manager class
*/

import java.util.*; // Importing Java utility ilbrary
import java.io.*; // Importing File Input/Output library

// Manages phonebook operations like adding, deleting, searching, and editing contacts
public class PhonebookManager {
    private ListNode head; // Head of the linked list, initialized as null

    // Constructor initializes an empty phonebook
    public PhonebookManager() {
        head = null;
    } // end of constructor

    // Adds a new contact to the phonebook
    public void addContact(String name, String address, String phoneNumber) {
        ListNode newNode = new ListNode(name, address, phoneNumber); // Create a new ListNode for the contact
        if (head == null) {
            head = newNode; // If the list is empty, set the new node as the head
        } 
        else {
            ListNode current = head; // Start from the head of the list
            while (current.next != null) { // Progress to the end of the list
                current = current.next;
            }
            current.next = newNode; // Add the new node at the end of the list
        }
    } // end of addContact method

    // Deletes a contact by name
    public void deleteContact(String name) {
        if (head == null) { // Check if the phonebook is empty
            System.out.println("Phonebook is empty.");
            return;
        }
        if (head.name.equals(name)) { // If the contact to delete is the head, update the head
            head = head.next;
            return;
        }
        ListNode current = head;
        // Search until the contact is found or the end of the list is reached
        while (current.next != null && !current.next.name.equals(name)) { 
            current = current.next;
        }
        if (current.next != null) { // If the contact is found, remove it from the list
            current.next = current.next.next;
            System.out.println("Contact deleted successfully.");
        } 
        else {
            System.out.println("Contact not found."); // If the contact is not found, print a message
        }
    } // end of deleteContact method

    // Search for a contact by name and print the details
    public boolean searchByName(String name) {
        ListNode current = head; // Start from the head
        boolean found = false; // Flag to indicate if a match is found
        while (current != null) { // Process the list
            if (current.name.equalsIgnoreCase(name)) { // If a match is found
                printContact(current); // Print the contact's details
                found = true;
                break; // Break after finding the first match
            }
            current = current.next; // Move to the next node
        }
        return found; // Return true if a match is found, false otherwise
    } // end of searchByName method

    // Search for a contact by address and print the details
    public boolean searchByAddress(String address) {
        ListNode current = head; // Start from the head
        boolean found = false; // Flag to indicate if a match is found
        while (current != null) { // Process the list
            if (current.address.equalsIgnoreCase(address)) { // If a match is found
                printContact(current); // Print the contact's details
                found = true;
                break; // Break after finding the first match
            }
            current = current.next; // Move to the next node
        }
        return found; // Return true if a match is found, false otherwise
    } // end of searchByAddress method

    // Search for a contact by phone number and print the details
    public boolean searchByPhoneNumber(String phoneNumber) {
        ListNode current = head; // Start from the head
        boolean found = false; // Flag to indicate if a match is found
        while (current != null) { // Process the list
            if (current.phoneNumber.equals(phoneNumber)) { // If a match is found
                printContact(current); // Print the contact's details
                found = true;
                break; // Break after finding the first match
            }
            current = current.next; // Move to the next node
        }
        return found; // Return true if a match is found, false otherwise
    }// end of searchByNumber method

    // Helper method to print a single contact's details
    private void printContact(ListNode contact) {
        System.out.println(); // spacing for ease of viewing
        System.out.println("Name: " + contact.name + ", \nAddress: " + contact.address + ",");
        System.out.println("Phone: " + contact.phoneNumber);
    } // end of printContact method

    // Print the entire list of contacts
    public void printContacts() {
        if (head == null) { // Check if the list is empty
            System.out.println("Phonebook is empty."); // Inform the user that there are no contacts to display
            return; // Exit the method as there is nothing to print
        }
        int count = 1; // Initialize a counter to enumerate the contacts
        ListNode current = head; // Start from the head to Process the list
        while (current != null) { // Continue until the end of the list is reached
            // Print the enumeration and current contact's details, each on a new line
            System.out.println(count + ". Name: " + current.name);
            System.out.println("   Address: " + current.address);
            System.out.println("   Phone: " + current.phoneNumber);
            System.out.println(); // Print a blank line for spacing between contacts
            current = current.next; // Move to the next node in the list
            count++; // Increment the counter for the next contact
        }
    } // end of printContacts method

    public boolean importContacts(String filename) {
        // Print the current working directory for the user to know where the file should be
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        File file = new File(filename);
            try (Scanner fileReader = new Scanner(file)) {
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    String[] contactDetails = data.split(",");
                    if (contactDetails.length == 3) {
                    // array to track import contact details, and trim excess
                    addContact(contactDetails[0].trim(), contactDetails[1].trim(), contactDetails[2].trim());
                    } 
                    else {
                    System.out.println("Invalid contact format: " + data); // File format error handling
                    }
                }
                return true; // Boolean flag for success notification
            } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred: File not found."); // Error handling
            return false; // Boolean flag for error handling
        }
    } // end of importContacts method

    // Clear all contacts from the phonebook
    public void clearAllEntries() {
        head = null; // Set the head to null to clear the list
    } // end of clearAllEntries method

    // Contact sorting method, alphabetically
    public void sortContacts() {
        if (head == null || head.next == null) {
            return; // The list is empty or has only one element, so it's already sorted.
        }
        boolean wasSwapped;
        do {
            ListNode current = head;
            ListNode prev = null;
            wasSwapped = false;
            while (current != null && current.next != null) {
                if (current.name.compareToIgnoreCase(current.next.name) > 0) {
                    // Swap needs to occur
                    ListNode temp = current.next;
                    current.next = temp.next;
                    temp.next = current;
                    if (prev == null) {
                        head = temp;
                    } 
                    else {
                        prev.next = temp;
                    }
                    prev = temp;
                    wasSwapped = true;
                } 
                else {
                    prev = current;
                    current = current.next;
                }
            }
        } while (wasSwapped);
    } // end of sortContacts method

    // Method to edit a contact's name
    public void editContactName(String currentName, String newName) {
        ListNode current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(currentName)) {
                current.name = newName; // Update the name
                return;
            }
            current = current.next;
        }
        System.out.println("Contact not found.");
    } // end of editContactsName method

    // Method to edit a contact's address
    public void editContactAddress(String name, String newAddress) {
        ListNode current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                current.address = newAddress; // Update the address
                return;
            }
            current = current.next;
        }
        System.out.println("Contact not found.");
    } // end of editContactsAddress method

    // Method to edit a contact's phone number
    public void editContactPhoneNumber(String name, String newPhone) {
        ListNode current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                current.phoneNumber = newPhone; // Update the phone number
                return;
            }
            current = current.next;
        }
        System.out.println("Contact not found.");
    } // end of editContactsPhoneNumber method

} // end of class