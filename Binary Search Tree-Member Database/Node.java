/* 
Programmer: Zach Carlson
Class: CS &145
Date Due: 3/15/2024
Assignment: Lab 6 - Binary Search Tree Dictionary Lab
Purpose: A series of classes that will process input from a user,
storing that information in a binary search tree with menu options
for searching and printing that tree in specific traversals or
modify the data as the user desires. 

Node Class
*/

public class Node {
    int memberID; // Member ID for unique identification
    Person person; // Person object to store individual's personal information
    Node left, right; // References to the left and right child nodes

    // Constructor initializing the Node with a Person object and a member ID
    public Node(int memberID, Person person) {
        this.memberID = memberID;
        this.person = person;
        this.left = null; // Left and right children are set to null at the start
        this.right = null;
    } // end of constructor

    // To override method from superclass for error handling
    @Override
    public String toString() {
        // Combines the memberID with the person's details
        return "Member ID: " + memberID + ", " + person.toString();
    } // end of toString method

} // end of Node class
