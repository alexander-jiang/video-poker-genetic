package videopoker;

import cards.Card;
import cards.Hand;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the VideoPokerHand class.
 */
public class VideoPokerHandTest {
  @Test
  public void testConstructor() throws Exception {
    Hand fourAces = Hand.of(new Card(1, Card.Suit.CLUBS), new Card(1, Card.Suit.DIAMONDS),
        new Card(1, Card.Suit.HEARTS), new Card(1, Card.Suit.SPADES));
    VideoPokerHand fourAcesHand = new VideoPokerHand(fourAces);
    assertTrue(fourAcesHand.isEmpty());

    Hand duplicates = Hand.of(new Card(1, Card.Suit.JOKER), new Card(2, Card.Suit.CLUBS), new Card(2, Card.Suit.CLUBS),
        new Card(1, Card.Suit.SPADES), new Card(1, Card.Suit.SPADES), new Card(1, Card.Suit.SPADES));
    VideoPokerHand duplicatesHand = new VideoPokerHand(duplicates);
    assertTrue(duplicatesHand.isEmpty());
  }

  @Test
  public void classifyRoyalFlush() throws Exception {
    Hand royalFlush = Hand.of(new Card(11, Card.Suit.CLUBS), new Card(12, Card.Suit.CLUBS), new Card(10, Card.Suit.CLUBS),
        new Card(1, Card.Suit.CLUBS), new Card(13, Card.Suit.CLUBS));
    VideoPokerHand royalFlushHand = new VideoPokerHand(royalFlush);
    assertEquals(VideoPokerHand.HandValue.ROYAL_FLUSH, royalFlushHand.classify());

    Hand notFlush = Hand.of(new Card(11, Card.Suit.CLUBS), new Card(12, Card.Suit.CLUBS), new Card(10, Card.Suit.CLUBS),
        new Card(1, Card.Suit.CLUBS), new Card(13, Card.Suit.HEARTS));
    VideoPokerHand notFlushHand = new VideoPokerHand(notFlush);
    assertEquals(VideoPokerHand.HandValue.STRAIGHT, notFlushHand.classify());

    Hand notStraight = Hand.of(new Card(11, Card.Suit.CLUBS), new Card(4, Card.Suit.CLUBS), new Card(10, Card.Suit.CLUBS),
        new Card(1, Card.Suit.CLUBS), new Card(13, Card.Suit.CLUBS));
    VideoPokerHand notStraightHand = new VideoPokerHand(notStraight);
    assertEquals(VideoPokerHand.HandValue.FLUSH, notStraightHand.classify());

    Hand notRoyal = Hand.of(new Card(11, Card.Suit.CLUBS), new Card(12, Card.Suit.CLUBS), new Card(10, Card.Suit.CLUBS),
        new Card(9, Card.Suit.CLUBS), new Card(13, Card.Suit.CLUBS));
    VideoPokerHand notRoyalHand = new VideoPokerHand(notRoyal);
    assertEquals(VideoPokerHand.HandValue.STRAIGHT_FLUSH, notRoyalHand.classify());
  }

  @Test
  public void classifyStraight() throws Exception {
    Hand straight = Hand.of(new Card(6, Card.Suit.SPADES), new Card(5, Card.Suit.CLUBS), new Card(7, Card.Suit.CLUBS),
        new Card(3, Card.Suit.HEARTS), new Card(4, Card.Suit.DIAMONDS));
    VideoPokerHand straightHand = new VideoPokerHand(straight);
    assertEquals(VideoPokerHand.HandValue.STRAIGHT, straightHand.classify());

    Hand aceHighStraight = Hand.of(new Card(11, Card.Suit.SPADES), new Card(12, Card.Suit.CLUBS), new Card(1, Card.Suit.CLUBS),
        new Card(13, Card.Suit.HEARTS), new Card(10, Card.Suit.DIAMONDS));
    VideoPokerHand aceHighStraightHand = new VideoPokerHand(aceHighStraight);
    assertEquals(VideoPokerHand.HandValue.STRAIGHT, aceHighStraightHand.classify());

    Hand aceLowStraight = Hand.of(new Card(4, Card.Suit.SPADES), new Card(2, Card.Suit.CLUBS), new Card(1, Card.Suit.CLUBS),
        new Card(3, Card.Suit.HEARTS), new Card(5, Card.Suit.DIAMONDS));
    VideoPokerHand aceLowStraightHand = new VideoPokerHand(aceLowStraight);
    assertEquals(VideoPokerHand.HandValue.STRAIGHT, aceLowStraightHand.classify());

    Hand notStraight = Hand.of(new Card(12, Card.Suit.SPADES), new Card(11, Card.Suit.CLUBS), new Card(8, Card.Suit.CLUBS),
        new Card(9, Card.Suit.HEARTS), new Card(11, Card.Suit.DIAMONDS));
    VideoPokerHand pairJacks = new VideoPokerHand(notStraight);
    assertEquals(VideoPokerHand.HandValue.JACKS_OR_BETTER, pairJacks.classify());

    Hand straightsDontWrap = Hand.of(new Card(12, Card.Suit.SPADES), new Card(13, Card.Suit.CLUBS), new Card(1, Card.Suit.CLUBS),
        new Card(3, Card.Suit.HEARTS), new Card(2, Card.Suit.DIAMONDS));
    VideoPokerHand noHand = new VideoPokerHand(straightsDontWrap);
    assertEquals(VideoPokerHand.HandValue.NONE, noHand.classify());
  }

  @Test
  public void classifyFlush() throws Exception {
    Hand flush = Hand.of(new Card(2, Card.Suit.CLUBS), new Card(12, Card.Suit.CLUBS), new Card(7, Card.Suit.CLUBS),
        new Card(9, Card.Suit.CLUBS), new Card(4, Card.Suit.CLUBS));
    VideoPokerHand flushHand = new VideoPokerHand(flush);
    assertEquals(VideoPokerHand.HandValue.FLUSH, flushHand.classify());
  }

  /**
   * Tests hands that are four-of-a-kind, full houses, three-of-a-kind, two-pair, or Jacks-or-better.
   */
  @Test
  public void classifySets() throws Exception {
    Hand fourAces = Hand.of(new Card(1, Card.Suit.CLUBS), new Card(1, Card.Suit.DIAMONDS),
        new Card(1, Card.Suit.HEARTS), new Card(1, Card.Suit.SPADES), new Card(13, Card.Suit.HEARTS));
    VideoPokerHand fourAcesHand = new VideoPokerHand(fourAces);
    assertEquals(VideoPokerHand.HandValue.FOUR_OF_A_KIND, fourAcesHand.classify());

    Hand acesOverKings = Hand.of(new Card(1, Card.Suit.CLUBS), new Card(1, Card.Suit.DIAMONDS),
        new Card(1, Card.Suit.HEARTS), new Card(13, Card.Suit.SPADES), new Card(13, Card.Suit.HEARTS));
    VideoPokerHand acesOverKingsHand = new VideoPokerHand(acesOverKings);
    assertEquals(VideoPokerHand.HandValue.FULL_HOUSE, acesOverKingsHand.classify());

    Hand threeKings = Hand.of(new Card(13, Card.Suit.CLUBS), new Card(13, Card.Suit.DIAMONDS),
        new Card(13, Card.Suit.HEARTS), new Card(3, Card.Suit.SPADES), new Card(10, Card.Suit.HEARTS));
    VideoPokerHand threeKingsHand = new VideoPokerHand(threeKings);
    assertEquals(VideoPokerHand.HandValue.THREE_OF_A_KIND, threeKingsHand.classify());

    Hand sixesAndSevens = Hand.of(new Card(6, Card.Suit.CLUBS), new Card(7, Card.Suit.DIAMONDS),
        new Card(6, Card.Suit.HEARTS), new Card(7, Card.Suit.SPADES), new Card(2, Card.Suit.HEARTS));
    VideoPokerHand twoPairHand = new VideoPokerHand(sixesAndSevens);
    assertEquals(VideoPokerHand.HandValue.TWO_PAIR, twoPairHand.classify());

    Hand pairJacks = Hand.of(new Card(11, Card.Suit.CLUBS), new Card(7, Card.Suit.DIAMONDS),
        new Card(6, Card.Suit.HEARTS), new Card(11, Card.Suit.SPADES), new Card(2, Card.Suit.HEARTS));
    VideoPokerHand pairJacksHand = new VideoPokerHand(pairJacks);
    assertEquals(VideoPokerHand.HandValue.JACKS_OR_BETTER, pairJacksHand.classify());

    Hand pairAces = Hand.of(new Card(1, Card.Suit.CLUBS), new Card(7, Card.Suit.DIAMONDS),
        new Card(6, Card.Suit.HEARTS), new Card(1, Card.Suit.SPADES), new Card(2, Card.Suit.HEARTS));
    VideoPokerHand pairAcesHand = new VideoPokerHand(pairAces);
    assertEquals(VideoPokerHand.HandValue.JACKS_OR_BETTER, pairAcesHand.classify());

    Hand pairTens = Hand.of(new Card(10, Card.Suit.CLUBS), new Card(7, Card.Suit.DIAMONDS),
        new Card(6, Card.Suit.HEARTS), new Card(10, Card.Suit.SPADES), new Card(2, Card.Suit.HEARTS));
    VideoPokerHand pairTensHand = new VideoPokerHand(pairTens);
    assertEquals(VideoPokerHand.HandValue.NONE, pairTensHand.classify());
  }

}