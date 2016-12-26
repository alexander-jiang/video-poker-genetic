package cards;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Hand class.
 */
public class HandTest {
  @Test
  public void testNumOfRank() throws Exception {
    Hand fourAces = Hand.of(new Card(1, Card.Suit.CLUBS), new Card(1, Card.Suit.DIAMONDS),
        new Card(1, Card.Suit.HEARTS), new Card(1, Card.Suit.SPADES));
    assertEquals(4, fourAces.numOfRank(1));
    assertEquals(0, fourAces.numOfRank(13));

    Hand duplicates = Hand.of(new Card(1, Card.Suit.JOKER), new Card(2, Card.Suit.CLUBS), new Card(2, Card.Suit.CLUBS),
        new Card(1, Card.Suit.SPADES), new Card(1, Card.Suit.SPADES), new Card(1, Card.Suit.SPADES));
    assertEquals(3, duplicates.numOfRank(1));
    assertEquals(2, duplicates.numOfRank(2));
    assertEquals(0, duplicates.numOfRank(6));
  }

  @Test
  public void testNumOfSuit() throws Exception {
    Hand fourAces = Hand.of(new Card(1, Card.Suit.CLUBS), new Card(1, Card.Suit.DIAMONDS),
        new Card(1, Card.Suit.HEARTS), new Card(1, Card.Suit.SPADES));
    assertEquals(1, fourAces.numOfSuit(Card.Suit.CLUBS));
    assertEquals(1, fourAces.numOfSuit(Card.Suit.HEARTS));
    assertEquals(0, fourAces.numOfSuit(Card.Suit.JOKER));

    Hand duplicates = Hand.of(new Card(1, Card.Suit.JOKER), new Card(2, Card.Suit.CLUBS), new Card(2, Card.Suit.CLUBS),
        new Card(1, Card.Suit.SPADES), new Card(1, Card.Suit.SPADES), new Card(1, Card.Suit.SPADES));
    assertEquals(3, duplicates.numOfSuit(Card.Suit.SPADES));
    assertEquals(2, duplicates.numOfSuit(Card.Suit.CLUBS));
    assertEquals(1, duplicates.numOfSuit(Card.Suit.JOKER));
  }

  @Test
  public void testToString() throws Exception {
    Hand fourAces = Hand.of(new Card(1, Card.Suit.CLUBS), new Card(1, Card.Suit.DIAMONDS),
        new Card(1, Card.Suit.HEARTS), new Card(1, Card.Suit.SPADES));
    assertEquals("Hand(Ac, Ad, Ah, As)", fourAces.toString());

    Hand emptyHand = Hand.makeEmpty();
    assertEquals("Hand()", emptyHand.toString());

    Hand duplicates = Hand.of(new Card(1, Card.Suit.JOKER), new Card(2, Card.Suit.CLUBS), new Card(2, Card.Suit.CLUBS),
        new Card(1, Card.Suit.SPADES), new Card(1, Card.Suit.SPADES), new Card(1, Card.Suit.SPADES));
    assertEquals("Hand(1j, 2c, 2c, As, As, As)", duplicates.toString());
  }

  @Test
  public void testContainsDuplicates() throws Exception {
    Hand fourAces = Hand.of(new Card(1, Card.Suit.CLUBS), new Card(1, Card.Suit.DIAMONDS),
        new Card(1, Card.Suit.HEARTS), new Card(1, Card.Suit.SPADES));
    assertFalse(fourAces.containsDuplicates());

    Hand duplicates = Hand.of(new Card(1, Card.Suit.JOKER), new Card(2, Card.Suit.CLUBS), new Card(2, Card.Suit.CLUBS),
        new Card(1, Card.Suit.SPADES), new Card(1, Card.Suit.SPADES), new Card(1, Card.Suit.SPADES));
    assertTrue(duplicates.containsDuplicates());
  }

}