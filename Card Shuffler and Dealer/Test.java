// Programmer: Zach Carlson
// Class: CS &145
// Date Due: 1/19/2024
// Assignment: Lab 2 - Card Shuffler and Hand evaluation, Book Exercise 7.16
// Purpose: A series of classes that uses arrays to complete a standard card desk shuffle, and then evaluate the hand it deals.
// Test Class

import java.util.*;

public class Test {
    public static void main(String[] args) {
        try {
            DeckManager deckManager = new DeckManager();

            deckManager.initializeDeck(); // Make sure to initialize the deck
            deckManager.shuffleDeck();

            List<Card> hand = deckManager.dealHand();
            
            System.out.println();
            System.out.println("Dealt Hand:"); // Presenting operation
            System.out.println();
            for (Card card : hand) {
                System.out.println(card.rank + " of " + card.suit); // Presenting listed hand
            }

            deckManager.evaluateHand(hand); // hand evaluation results presented
        } catch (Exception e) { // error handling
            System.out.println("An error occurred: " + e.getMessage());
        }
    } // end of main method

} // end of Test class