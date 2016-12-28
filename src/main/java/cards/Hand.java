package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A collection of French-suited playing cards (could contain duplicates). Essentially a wrapper around
 * an ArrayList of cards with some methods to check for existence of any card with a specific rank or suit.
 */
public class Hand extends ArrayList<Card> {
  /**
   * Creates a new Hand with the specified cards.
   */
  public Hand(Collection<? extends Card> cards) {
    super(cards);
  }

  /**
   * Creates an empty Hand.
   */
  public Hand() {
    super();
  }

  /**
   * Creates a new Hand with the specified cards.
   */
  public static Hand of(Card... cards) {
    return new Hand(Arrays.asList(cards));
  }

  /**
   * Creates an empty Hand.
   */
  public static Hand makeEmpty() {
    return new Hand();
  }

  /**
   * Counts the number of cards with the given rank (ignores jokers).
   */
  public int numOfRank(int rank) {
    return this.stream().filter(card -> !card.isJoker() && card.getRank() == rank).collect(Collectors.toList()).size();
  }

  /**
   * Counts the number of cards with the given suit.
   */
  public int numOfSuit(Card.Suit suit) {
    return this.stream().filter(card -> card.getSuit() == suit).collect(Collectors.toList()).size();
  }

  @Override
  public String toString() {
    return "Hand(" + this.stream().map(Card::toString).collect(Collectors.joining(", ")) + ")";
  }

  /**
   * Checks if the hand contains duplicate cards.
   */
  public boolean containsDuplicates() {
    // runs in O(n^2) time, but hands won't be that large? Can sort for better performance
    for (int i = 0; i < this.size(); i++) {
      if (lastIndexOf(this.get(i)) != i) {
        return true;
      }
    }
    return false;
  }
}
