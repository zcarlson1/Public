// Programmer: Zach Carlson
// Class: CS &145
// Date Due: 1/19/2024
// Assignment: Lab 2 - Card Shuffler and Hand evaluation, Book Exercise 7.16
// Purpose: A series of classes that uses arrays to complete a standard card desk shuffle, and then evaluate the hand it deals.
// Deck Manager Class

import java.util.*;

// DeckManager class to handle operations on a deck of cards
public class DeckManager {
    private List<Card> deck = new ArrayList<>();

    // Method to initialize or reinitialize the deck with one card of each suit and rank
    public void initializeDeck() {
        deck.clear(); // Clear the existing deck, removes all elements of an array
        // referencing the constructor
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    } // end of initializeDeck method

    // Shuffle the deck of cards
    public void shuffleDeck() {
        Random random = new Random(); // Random number generator
        for (int i = deck.size() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1); // Get a random index from 0 to i

            // Swap the elements at indices i and index
            Card temp = deck.get(i);
            deck.set(i, deck.get(index));
            deck.set(index, temp);
        }
    } // end of shuffleDeck method

    // Deal a hand of 5 cards
    public List<Card> dealHand() {
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (!deck.isEmpty()) {
                hand.add(deck.remove(0)); // Remove the top 5 cards from the deck and add it to the hand
            }
        }
        return hand;
    } // end of dealHand method

    // Evaluate the hand for different types of poker hands
    public void evaluateHand(List<Card> hand) {
        System.out.println("\nEvaluating Hand..\n"); // Print a message indicating hand evaluation start

        // Arrays to track found ranks and suits, and count occurrences of each rank
        boolean[] rankFound = new boolean[Card.Rank.values().length];
        boolean[] suitFound = new boolean[Card.Suit.values().length];
        int[] rankCount = new int[Card.Rank.values().length];

        // Iterate over each card in the hand
        for (Card card : hand) {
            int rankIndex = getRankIndex(card.rank); // Get the index for the rank of the current card
            int suitIndex = getSuitIndex(card.suit); // Get the index for the suit of the current card

            rankFound[rankIndex] = true; // Mark the rank as found with a flag
            suitFound[suitIndex] = true; // Mark the suit as found with a flag
            rankCount[rankIndex]++; // Increment the count for this rank
        }

        // Check if the hand is a flush or a straight
        boolean isFlush = checkFlush(suitFound);
        boolean isStraight = checkStraight(rankFound);

        // Determine the type of poker hand and store it in a string
        String handType = evaluateHandType(rankCount, isFlush, isStraight);

        System.out.println(handType); // Print the type of poker hand found
    } // end of evaluateHand method

    // Helper method to get the index of a rank
    private int getRankIndex(Card.Rank rank) {
        switch (rank) { // due to enum constants, cant use integers so numbers are spelled out
            case TWO: return 0;
            case THREE: return 1;
            case FOUR: return 2;
            case FIVE: return 3;
            case SIX: return 4;
            case SEVEN: return 5;
            case EIGHT: return 6;
            case NINE: return 7;
            case TEN: return 8;
            case JACK: return 9;
            case QUEEN: return 10;
            case KING: return 11;
            case ACE: return 12;
            default: return -1;  // Invalid entry handling
        }
    } // end of getRankIndex method

    // Helper method to get the index of a suit
    private int getSuitIndex(Card.Suit suit) {
        // Switch statement to assign an index to each suit
        switch (suit) {
            case HEARTS: return 0;
            case DIAMONDS: return 1;
            case CLUBS: return 2;
            case SPADES: return 3;
            default: return -1; // Invalid entry handling
        }
    } // end of getSuitIndex Method

    // Helper method to check for a flush
    private boolean checkFlush(boolean[] suitFound) {
        int suitsCount = 0;
        for (boolean found : suitFound) {
            if (found) suitsCount++;
        }
        return suitsCount == 1;
    } // end of checkFlush method

    // Helper method to check for a straight
    private boolean checkStraight(boolean[] rankFound) {
        int consecutiveCount = 0;
        for (boolean found : rankFound) {
            if (found) {
                consecutiveCount++;
                if (consecutiveCount == 5) return true;
            } else {
                consecutiveCount = 0;
            }
        }
        return false;
    } // end of checkStraight method

    // Helper method to evaluate hand type, parameters added
    private String evaluateHandType(int[] rankCount, boolean isFlush, boolean isStraight) {
        int pairs = 0, threeOfAKind = 0, fourOfAKind = 0;
        for (int count : rankCount) {
            switch (count) {
                case 2:
                    pairs++;
                    break;
                case 3:
                    threeOfAKind++;
                    break;
                case 4:
                    fourOfAKind++;
                    break;
            }
        }
        // Declarations of hands found
        if (isFlush && isStraight) return "Straight Flush!!";
        if (isFlush) return "Flush!";
        if (isStraight) return "Straight!";
        if (fourOfAKind == 1) return "Four of a Kind!";
        if (threeOfAKind == 1 && pairs == 1) return "Full House!";
        if (threeOfAKind == 1) return "Three of a Kind!";
        if (pairs == 2) return "Two Pairs!";
        if (pairs == 1) return "One Pair!";
        return "High Card..";
    } // end of evaluateHandType

} // end of DeckManager class

