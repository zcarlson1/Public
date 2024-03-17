// Programmer: Zach Carlson
// Class: CS &145
// Date Due: 1/4/2024
// Assignment: Lab 4 - Guessing Game (Revised)
// Purpose: An interactive game for a user to guess with the program for the purpose of finding a random integer

import java.util.Scanner; // Importing the Scanner class for user input
import java.util.Random; // Importing the Random class for generating random numbers
import java.util.InputMismatchException; // Importing InputMismatchException for handling input errors

public class GuessingGame3 {

   public static void main(String[] args) {
      intro(); // Calling the intro method to display the game introduction
      Scanner input = new Scanner(System.in); // Creating a Scanner object for reading user input
      Random rand = new Random(); // Creating a Random object for generating random numbers
   
      // Ask the user if they want to play just once
      System.out.print("Are you going to play just once? (yes/no): ");
      boolean playOnce = input.next().toLowerCase().startsWith("y"); // Reading user input and converting to boolean with some error catching
   
      int bestGuess = 100; // Initializing bestGuess to a high number
      int guessTotal = 0; // Initializing the total number of guesses made in all games to zero
      int numGames = 0; // Initializing the number of games played to zero
   
      do {
         int randomInt = rand.nextInt(100) + 1; // Generating a random number between 1 and 100, added 1 because start is at 0
         System.out.println(randomInt);
         int guessCount = 0; // Initializing the guess counter for the current game
         theGame(); // Calling theGame method to present the game
      
         while (true) {
            System.out.print("Your guess: "); // Prompting the user for a guess
            int guess = getValidGuess(input); // Reading and validating the user's guess
            guessCount++; // Incrementing the guess counter
         
            if (guess > randomInt) {
               System.out.println("It's lower."); // Informing the user that the guess is too high
            } 
            else if (guess < randomInt) {
               System.out.println("It's higher."); // Informing the user that the guess is too low
            } 
            else {
               if (guessCount == 1) {
                  System.out.println("Wow! You got it right in 1 guess!"); // Handling for guessing correctly on the first try
               } else {
                  System.out.println("You got it right in " + guessCount + " guesses."); // User guessed correctly
               }
               bestGuess = Math.min(bestGuess, guessCount); // Updating the bestGuess if the current guessCount is lower
               guessTotal += guessCount; // Adding the current guessCount to the total guesses
               numGames++; // Incrementing the number of games played
               break; // Breaking out of the guessing loop
            }
         }
      
         if (playOnce) {
            break; // Breaking out of the game loop if the user chose to play only once
         }
      } while (playAgain(input)); // Checking if the user wants to play again
   
      gameStats(guessTotal, numGames, bestGuess); // Presenting the game statistics, with input parameters
      input.close(); // Closing the Scanner object
   }

   public static int getValidGuess(Scanner input) {
      while (true) {
         try {
            return input.nextInt(); // Reading an integer guess from the user
         } 
         catch (InputMismatchException e) {
            System.out.println("Input not valid. Please enter a number."); // Handling invalid input
            input.next(); // Discards the invalid input
         }
      }
   }

   public static boolean playAgain(Scanner input) { // entering scanner input
      System.out.print("Do you want to play again? "); // Asking the user if they want to play again
      while (true) {
         String playAgain = input.next().toLowerCase(); // Reading the user's response
         if (playAgain.equals("yes") || playAgain.equals("y")) {
            return true; // Returning true if the user wants to play again
         } 
         else if (playAgain.equals("no") || playAgain.equals("n")) {
            return false; // Returning false if the user does not want to play again
         } 
         else {
            System.out.println("Invalid input. Do you want to play again? "); // Handling invalid input
         }
      }
   }

   public static void gameStats(int guessTotal, int numGames, int bestGuess) { // 3 entered parameters
      System.out.println("You guessed " + guessTotal + " times."); // Displaying total guesses
      System.out.println("You played " + numGames + " games."); // Displaying total number of games
      if (numGames > 0) {
         double guessAvg = (double) guessTotal / numGames; // Calculating the average guesses per game
         System.out.println("You averaged " + guessAvg + " guesses per game."); // Displaying average guesses
      }
      System.out.println("Your best guess game was " + bestGuess + " guesses."); // Displaying the best game
   }

   public static void theGame() {
      System.out.println("I'm thinking of a number between 1 and 100..."); // Presenting the game...
   }

   public static void intro() {
      // Printing the game introduction
      System.out.println("This program allows you to play a guessing game.");
      System.out.println("I will think of a number between 1 and 100 and will allow you to guess until you get it.");
      System.out.println("For each guess, I will tell you whether the right answer is higher or lower than your guess... Good Luck!");
      System.out.println();
   } // end of intro method
   
} // end of class
