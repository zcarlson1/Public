/* 
Programmer: Zach Carlson
Class: CS &145
Date Due: 2/14/2024
Assignment: Assignment 2 - Phone Book
Purpose: A series of classes that will process input from a user and 
return an formatted contact listing for all contacts entered,
and additionally allow the user to modify contacts, in full or in part.
List Node class
*/

// Represents a node in the linked list for storing contact information
public class ListNode {
    // Contact information
    String name;
    String address;
    String phoneNumber;
    // Reference to the next node in the list
    ListNode next;

    // Constructor to create a new node with contact information
    public ListNode(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.next = null; // Next is initially null
    } // end of constructor

} // end of class