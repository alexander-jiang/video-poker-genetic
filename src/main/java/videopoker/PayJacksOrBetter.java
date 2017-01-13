package videopoker;

/**
 * Pay table for Jacks or Better video poker.
 */
public class PayJacksOrBetter implements PayTable {

  @Override
  public int payRoyalFlush() {
    return 800;
  }

  @Override
  public int payStraightFlush() {
    return 50;
  }

  @Override
  public int payFourOfAKind() {
    return 25;
  }

  @Override
  public int payFullHouse() {
    return 9;
  }

  @Override
  public int payFlush() {
    return 6;
  }

  @Override
  public int payStraight() {
    return 4;
  }

  @Override
  public int payThreeOfAKind() {
    return 3;
  }

  @Override
  public int payTwoPair() {
    return 2;
  }

  @Override
  public int payJacksOrBetter() {
    return 1;
  }
}
