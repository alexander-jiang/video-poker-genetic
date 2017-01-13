package videopoker;

/**
 * Defines a pay table for a game of video poker, expressed in multiples of the original bet.
 */
public interface PayTable {
  /**
   * Returns the payout for a given hand value based on the pay table.
   */
  default int payout(VideoPokerHand.HandValue handValue) {
    switch (handValue) {
      case ROYAL_FLUSH:
        return payRoyalFlush();
      case STRAIGHT_FLUSH:
        return payStraightFlush();
      case FOUR_OF_A_KIND:
        return payFourOfAKind();
      case FULL_HOUSE:
        return payFullHouse();
      case FLUSH:
        return payFlush();
      case STRAIGHT:
        return payStraight();
      case THREE_OF_A_KIND:
        return payThreeOfAKind();
      case TWO_PAIR:
        return payTwoPair();
      case JACKS_OR_BETTER:
        return payJacksOrBetter();
      default:
        return 0;
    }
  }

  int payRoyalFlush();

  int payStraightFlush();

  int payFourOfAKind();

  int payFullHouse();

  int payFlush();

  int payStraight();

  int payThreeOfAKind();

  int payTwoPair();

  int payJacksOrBetter();
}
