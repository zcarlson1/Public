/* 
Programmer: Zach Carlson
Class: CS &145
Date Due: 2/6/2024
Assignment: Lab 4 - Word Search Generator
Purpose: A class that will process input from a user and 
return an completed word search puzzle incorporate most if not all
of the words input by the user
Word Search Test class
*/

import java.util.*;

public class WordSearchTest {
    private static Scanner scanner = new Scanner(System.in); // Scanner for reading user input
    private static WordSearch wordSearch; // Instance of WordSearch to create and manage the puzzle

    public static void main(String[] args) {
        while (true) {
            printIntro(); // Display the introductory menu
            char choice = scanner.nextLine().toLowerCase().charAt(0); // Prompt for user's choice

            switch (choice) {
                case 'n': // Create a new word search puzzle
                    generate();
                    break;
                case 'p': // Print the current puzzle
                    if (wordSearch != null) {
                        wordSearch.print(); // Print the puzzle if it exists
                    } else {
                        System.out.println("Please generate a word search first.");
                    }
                    break;
                case 's': // Show the solution to the puzzle
                    if (wordSearch != null) {
                        wordSearch.showSolution(); // Show the solution if the puzzle exists
                    } else {
                        System.out.println("Please generate a word search first.");
                    }
                    break;
                case 'q': // Quit the program
                    System.out.println("Quitting program.");
                    return; // Exit the program
                default: // Handle invalid input
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    } // end of main method

    // Method to print the introductory menu and instructions
    private static void printIntro() {
        System.out.println("\nWelcome to the Word Search Puzzle Generator!");
        System.out.println("This program will help you create your own word search puzzle!");
        System.out.println("Please select your option:");
        System.out.println("Input 'n' to create a new word search puzzle");
        System.out.println("Input 'p' to print the puzzle");
        System.out.println("Input 's' to show the solution of the puzzle");
        System.out.println("Input 'q' to quit the program");
    } // end of printIntro method

    // Method to generate a new word search puzzle
    private static void generate() {
        List<String> words = new ArrayList<>(); // List to store user-entered words
        System.out.println("Enter words for the word search (type 'done' to finish): ");
        
        int maxLength = 0; // Variable to track the length of the longest word

        // Loop to collect words from the user, limiting to a maximum of 25 words
        while (words.size() < 25) {
            System.out.print("Enter word " + (words.size() + 1) + " (or 'done'): ");
            String word = scanner.nextLine(); // Read the user's input
            if (word.equalsIgnoreCase("done")) {
                break; // Exit the loop if the user types 'done'n
            }
            words.add(word); // Add the entered word to the list
            maxLength = Math.max(maxLength, word.length()); // Update maxLength if the word is longer
        }

        // Set grid size to be the length of the longest word plus 4
        int gridSize = maxLength + 4;
        wordSearch = new WordSearch(gridSize); // Create a new WordSearch instance with the adjusted grid size

        // Add each word to the word search
        for (String word : words) {
            wordSearch.addWord(word); // Add the word to the word search
        }
        wordSearch.fillEmptyCells(); // Fill empty cells after all words are added
    } // end of generate method

} // end of Test class