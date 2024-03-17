// Programmer: Zach Carlson
// Class: CS &145
// Date Due: 1/19/2024
// Assignment: Lab 2 - Card Shuffler and Hand evaluation, Book Exercise 7.16
// Purpose: A series of classes that uses arrays to complete a standard card desk shuffle, and then evaluate the hand it deals.
// Card Class

public class Card {
    // Enums for the suits and ranks of the cards
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    } // end of Suit enum

    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    } // end of Rank enum

    public Suit suit; // Public field for suit of the card so it can be referenced
    public Rank rank; // Public field for rank of the card so it can be referenced

    // Constructor to initialize a Card object
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    } // end of Card constructor

} // end of Card class

