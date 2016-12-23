package cards;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Deck class.
 */
public class CardTest {
  @Test
  public void testToString() throws Exception {
    Card joker1 = new Card(1, Card.Suit.JOKER);
    assertEquals("1j", joker1.toString());
    Card threeClubs = new Card(3, Card.Suit.CLUBS);
    assertEquals("3c", threeClubs.toString());
    Card jackHearts = new Card(11, Card.Suit.HEARTS);
    assertEquals("Jh", jackHearts.toString());
    Card tenDiamonds = new Card(10, Card.Suit.DIAMONDS);
    assertEquals("Td", tenDiamonds.toString());
    Card queenSpades = new Card(12, Card.Suit.SPADES);
    assertEquals("Qs", queenSpades.toString());
  }

  @Test
  public void testEquals() throws Exception {
    Card kingHearts = new Card(13, Card.Suit.HEARTS);
    Card kingSpades = new Card(13, Card.Suit.SPADES);
    Card aceHearts = new Card(1, Card.Suit.HEARTS);
    assertTrue(!kingHearts.equals(kingSpades));
    assertTrue(!kingHearts.equals(aceHearts));

    Card joker1 = new Card(1, Card.Suit.JOKER);
    Card joker2 = new Card(2, Card.Suit.JOKER);
    assertTrue(!joker1.equals(joker2));

    Card aceHeartsAgain = new Card(1, Card.Suit.HEARTS);
    assertTrue(aceHearts.equals(aceHeartsAgain));
  }

}