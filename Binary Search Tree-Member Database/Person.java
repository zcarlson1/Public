/* 
Programmer: Zach Carlson
Class: CS &145
Date Due: 3/15/2024
Assignment: Lab 6 - Binary Search Tree Dictionary Lab
Purpose: A series of classes that will process input from a user,
storing that information in a binary search tree with menu options
for searching and printing that tree in specific traversals or
modify the data as the user desires. 

Person Class
*/

public class Person {
    // initializing parameters for member info
    String firstName;
    String lastName;
    String streetAddress;
    String city;
    String state;
    int zip;
    String email;
    String phoneNumber;

    // Constructor to initialize a Person object with all necessary information
    public Person(String firstName, String lastName, String streetAddress, 
                    String city, String state, int zip, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phoneNumber = phoneNumber;
    } // end of Person constructor

    // Override toString() for easy printing of Person's information
    @Override
    public String toString() {
        // Single line of space between groups of member information
        // Prints as below
        // Line 1: Member ID (From Node) and Name
        // Line 2: Street Address, City, State, Zip
        // Line 3: Email and Phone number
        return String.format("Name: %s %s\nAddress: %s, %s, %s, %d\nEmail: %s, Phone: %s\n",
                    firstName, lastName, streetAddress, city, state, zip, email, phoneNumber);
    } // end of toString method

} // end of Person class
