/* 
Programmer: Zach Carlson
Class: CS &145
Date Due: 2/6/2024
Assignment: Lab 4 - Word Search Generator
Purpose: A class that will process input from a user and 
return an completed word search puzzle incorporate most if not all
of the words input by the user
Word Search class
*/

import java.util.*;

public class WordSearch {
    private char[][] grid; // Two-dimensional array for the word search grid
    private char[][] solution; // Two-dimensional array for the solution grid
    private int gridSize; // Size of the word search grid
    private Random random = new Random(); // Random object for generating letters and positions

    // Constructor for the WordSearch class
    public WordSearch(int size) {
        this.gridSize = size; // Set the grid size
        grid = new char[size][size]; // Initialize the grid array
        solution = new char[size][size]; // Initialize the solution array
        initializeGrid(); // Initialize both grids with spaces
    } // end of constructor

    // Initialize both the grid and solution arrays with spaces and '*' respectively
    private void initializeGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = ' '; // Fill the grid cell with space
                solution[i][j] = '*'; // Fill the solution grid cell with '*'
            }
        }
    } // end of initializeGrid method

    // Adds a word to the word search grid
    public void addWord(String word) {
        word = word.toUpperCase(); // Convert the word to uppercase
        int maxAttempts = 100; // Maximum number of attempts to place a word
        boolean placed = false; // Flag to indicate if the word has been placed

        for (int attempt = 0; attempt < maxAttempts && !placed; attempt++) {
            int orientation = random.nextInt(3); // Randomly choose orientation (0: horizontal, 1: vertical, 2: diagonal)
            int row = random.nextInt(gridSize); // Randomly choose starting row
            int col = random.nextInt(gridSize); // Randomly choose starting column

            switch (orientation) {
                case 0: // Horizontal placement
                    if (col + word.length() <= gridSize && canPlaceWordAt(word, row, col, 0, 1)) {
                        placeWord(word, row, col, 0, 1); // Place the word horizontally
                        placed = true; // Mark as placed
                    }
                    break;
                case 1: // Vertical placement
                    if (row + word.length() <= gridSize && canPlaceWordAt(word, row, col, 1, 0)) {
                        placeWord(word, row, col, 1, 0); // Place the word vertically
                        placed = true; // Mark as placed
                    }
                    break;
                case 2: // Diagonal placement
                    if (row + word.length() <= gridSize && col + word.length() <= gridSize
                            && canPlaceWordAt(word, row, col, 1, 1)) {
                        placeWord(word, row, col, 1, 1); // Place the word diagonally
                        placed = true; // Mark as placed
                    }
                    break;
            } // end of switch case
        } // end of for loop

        if (!placed) {
            System.out.println("Could not place word: " + word + " after " + maxAttempts + " attempts."); 
            // Error message for unplaced words
        }
    } // end of addWord method

    // Check if a word can be placed at a specific position with a given orientation
    private boolean canPlaceWordAt(String word, int row, int col, int rowDelta, int colDelta) {
        for (int i = 0; i < word.length(); i++) {
            if (row < 0 || col < 0 || row >= gridSize || col >= gridSize) { // Confirm words fits in grid
                return false; // Return false if word does not fit
            }

            if (grid[row][col] != ' ' && grid[row][col] != word.charAt(i)) { // Check if the cell conflicts with the word
                return false; // Return false if there's a conflict
            }

            row += rowDelta; // Increment row index/vector
            col += colDelta; // Increment column index/vector
        }
        return true; // Return true if the word can be placed
    } // end of canPlaceWordAt method

    // Place a word at a specific position with a given orientation
    private void placeWord(String word, int row, int col, int rowDelta, int colDelta) {
        for (int i = 0; i < word.length(); i++) {
            grid[row][col] = word.charAt(i); // Place the letter in the grid
            solution[row][col] = word.charAt(i); // Update the solution grid with the letter
            row += rowDelta; // Increment the row index/vector
            col += colDelta; // Increment the column index/vector
        }
    } // end of placeWord method

    // Prints the current word search grid
    public void print() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(grid[i][j] + " "); // Print each letter in the grid
            }
            System.out.println(); // Move to the next line after printing a row
        }
    } // end of print method

    // Prints the solution grid
    public void showSolution() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(solution[i][j] + " "); // Print each letter in the solution grid
            }
            System.out.println(); // Move to the next line after printing a row
        }
    } // end of showSolution method

    // Method to fill empty cells with random letters
    public void fillEmptyCells() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == ' ') { // Check if the cell is empty
                    grid[i][j] = (char) ('A' + random.nextInt(26)); // Fill with a random uppercase letter
                } 
            } // end of inner for loop
        }// end of outer for loop
    } // end of fillEmptyCells method

} // end of class