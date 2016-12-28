package cards;

/**
 * French-suited playing card (could be a joker).
 */
public class Card {
  public enum Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES, JOKER
  }

  private final int rank;
  private final Suit suit;

  public Card(int rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public int getRank() {
    return rank;
  }

  public Suit getSuit() {
    return suit;
  }

  public boolean isJoker() {
    return suit == Suit.JOKER;
  }

  /**
   * Returns a two-character string representation of each card: its rank (2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A)
   * followed by its suit (c, d, h, s).
   * Jokers have integer ranks (1, 2, ...) and their suit is represented by a lowercase j.
   */
  public String toString() {
    if (suit != Suit.JOKER) {
      return rankToString() + suitToString();
    } else {
      return String.valueOf(rank) + suitToString();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Card)) {
      return false;
    }
    Card other = (Card) o;

    return other.getRank() == this.rank && other.getSuit() == this.suit;
  }

  private String suitToString() {
    switch (suit) {
      case CLUBS:
        return "c";
      case DIAMONDS:
        return "d";
      case HEARTS:
        return "h";
      case SPADES:
        return "s";
      case JOKER:
        return "j";
      default:
        return "";
    }
  }

  private String rankToString() {
    if (rank > 1 && rank < 10) {
      return String.valueOf(rank);
    } else if (rank == 1) {
      return "A";
    } else if (rank == 10) {
      return "T";
    } else if (rank == 11) {
      return "J";
    } else if (rank == 12) {
      return "Q";
    } else if (rank == 13) {
      return "K";
    } else {
      return "";
    }
  }
}
