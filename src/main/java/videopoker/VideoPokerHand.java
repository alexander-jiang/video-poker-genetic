package videopoker;

import cards.Card;
import cards.Hand;

import java.util.Collection;
import java.util.Comparator;

/**
 * Five-card hand for video poker.
 */
public class VideoPokerHand extends Hand {
  enum HandValue {
    ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, JACKS_OR_BETTER, NONE
  }

  /**
   * Constructs a new hand given 5 cards (no duplicates).
   */
  VideoPokerHand(Collection<? extends Card> cards) {
    super(cards);
    if (this.size() != 5 || this.containsDuplicates()) {
      this.clear();
    }
  }

  /**
   * Assigns this hand to its maximum value (e.g. straight-flush instead of straight).
   */
  public HandValue classify() {
    if (this.isEmpty()) {
      return HandValue.NONE;
    }

    if (isRoyalFlush()) {
      return HandValue.ROYAL_FLUSH;
    } else if (isStraightFlush()) {
      return HandValue.STRAIGHT_FLUSH;
    } else if (isFourOfAKind()) {
      return HandValue.FOUR_OF_A_KIND;
    } else if (isFullHouse()) {
      return HandValue.FULL_HOUSE;
    } else if (isFlush()) {
      return HandValue.FLUSH;
    } else if (isStraight()) {
      return HandValue.STRAIGHT;
    } else if (isThreeOfAKind()) {
      return HandValue.THREE_OF_A_KIND;
    } else if (isTwoPair()) {
      return HandValue.TWO_PAIR;
    } else if (isJacksOrBetter()) {
      return HandValue.JACKS_OR_BETTER;
    } else {
      return HandValue.NONE;
    }
  }

  private boolean isRoyalFlush() {
    // isStraight and isFlush are true, and this includes both A and K
    return isStraight() && isFlush() && this.numOfRank(1) > 0 && this.numOfRank(13) > 0;
  }

  private boolean isStraightFlush() {
    // isStraight and isFlush are true
    return isStraight() && isFlush();
  }

  private boolean isFourOfAKind() {
    // four of the five cards in this match rank
    for (int rank = 1; rank <= 13; rank++) {
      if (this.numOfRank(rank) >= 4) {
        return true;
      }
    }
    return false;
  }

  private boolean isFullHouse() {
    // isThreeOfAKind and isTwoPair are True
    return isThreeOfAKind() && isTwoPair();
  }

  private boolean isFlush() {
    // all cards in this match suit
    for (Card.Suit suit : Card.Suit.values()) {
      if (this.numOfSuit(suit) == 5) {
        return true;
      }
    }
    return false;
  }

  private boolean isStraight() {
    // all cards have consecutive ranks (Aces can go low or high i.e. A-5 or A-T)
    Hand copyHand = (Hand) (this.clone());
    copyHand.sort(new CardRankComparator());
    return copyHand.get(1).getRank() - copyHand.get(0).getRank() == 1 &&
        copyHand.get(2).getRank() - copyHand.get(1).getRank() == 1 &&
        copyHand.get(3).getRank() - copyHand.get(2).getRank() == 1 &&
        ((copyHand.get(4).getRank() - copyHand.get(3).getRank() == 1) ||
            (copyHand.get(4).getRank() == 1 && copyHand.get(3).getRank() == 13) ||
            (copyHand.get(4).getRank() == 1 && copyHand.get(3).getRank() == 5));
  }

  private boolean isThreeOfAKind() {
    // three of the five cards in this match rank
    for (int rank = 1; rank <= 13; rank++) {
      if (this.numOfRank(rank) >= 3) {
        return true;
      }
    }
    return false;
  }

  private boolean isTwoPair() {
    // two of the five cards in this match rank, another two match rank (but not the same as the first)
    int numPairs = 0;
    for (int rank = 1; rank <= 13; rank++) {
      if (this.numOfRank(rank) >= 2) {
        numPairs++;
      }
    }
    return numPairs == 2;
  }

  private boolean isJacksOrBetter() {
    // two of the five cards in this match rank (J or higher)
    for (int rank = 11; rank <= 13; rank++) {
      if (this.numOfRank(rank) >= 2) {
        return true;
      }
    }
    return this.numOfRank(1) >= 2;
  }

  private class CardRankComparator implements Comparator<Card> {
    /**
     * Compares cards by rank (Aces are high).
     */
    @Override
    public int compare(Card card1, Card card2) {
      if (card1.getRank() == card2.getRank()) {
        return 0;
      } else if (card1.getRank() == 1) {
        return 1;
      } else if (card2.getRank() == 1) {
        return -1;
      } else {
        return card1.getRank() - card2.getRank();
      }
    }
  }
}
