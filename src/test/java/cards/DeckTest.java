package cards;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Deck class.
 */
public class DeckTest {
  @Test
  public void testConstructors() throws Exception {
    Deck standardDeck = new Deck(1, 0);
    assertEquals(52, standardDeck.numCards());

    Deck deckWithJokers = new Deck(1, 2);
    assertEquals(54, deckWithJokers.numCards());
  }

  @Test
  public void testPeek() throws Exception {
    Deck standardDeck = new Deck(1, 0);
    assertEquals("2c", standardDeck.peek());
    assertEquals("2c", standardDeck.peek());

    Deck deckWithJokers = new Deck(1, 2);
    assertEquals("2c", deckWithJokers.peek());
    assertEquals("2c", deckWithJokers.peek());
  }

  @Test
  public void testDeal() throws Exception {
    Deck standardDeck = new Deck(1, 0);
    assertEquals("2c", standardDeck.deal());
    assertEquals("3c", standardDeck.deal());
    assertEquals(50, standardDeck.numCards());

    Deck deckWithJokers = new Deck(1, 2);
    assertEquals("2c", deckWithJokers.deal());
    assertEquals("3c", deckWithJokers.deal());
    assertEquals(52, deckWithJokers.numCards());
  }

  @Test
  public void testShuffle() throws Exception {
    Deck standardDeck = new Deck(1, 0);
    assertEquals(52, standardDeck.numCards());
    Deck shuffledDeck = standardDeck.shuffle();
    assertEquals(52, shuffledDeck.numCards());
  }

}