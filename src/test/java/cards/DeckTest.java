package cards;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Deck class.
 */
public class DeckTest {
  @Test
  public void testConstructors() throws Exception {
    Deck emptyDeck = new Deck(0, 0);
    assertEquals(0, emptyDeck.numCards());

    Deck standardDeck = new Deck(1, 0);
    assertEquals(52, standardDeck.numCards());

    Deck deckWithJokers = new Deck(1, 2);
    assertEquals(54, deckWithJokers.numCards());
  }

  @Test
  public void testPeek() throws Exception {
    Deck emptyDeck = new Deck(0, 0);
    assertFalse(emptyDeck.peek().isPresent());

    Deck standardDeck = new Deck(1, 0);
    assertEquals("Ac", standardDeck.peekString());
    assertEquals("Ac", standardDeck.peekString());

    Deck deckWithJokers = new Deck(1, 2);
    assertEquals("Ac", deckWithJokers.peekString());
    assertEquals("Ac", deckWithJokers.peekString());
  }

  @Test
  public void testDeal() throws Exception {
    Deck emptyDeck = new Deck(0, 0);
    assertFalse(emptyDeck.deal().isPresent());

    Deck standardDeck = new Deck(1, 0);
    assertEquals("Ac", standardDeck.dealString());
    assertEquals("2c", standardDeck.dealString());
    assertEquals(50, standardDeck.numCards());

    Deck deckWithJokers = new Deck(1, 2);
    assertEquals("Ac", deckWithJokers.dealString());
    assertEquals("2c", deckWithJokers.dealString());
    assertEquals(52, deckWithJokers.numCards());
  }

  @Test
  public void testShuffle() throws Exception {
    Deck emptyDeck = new Deck(0, 0);
    assertEquals(0, emptyDeck.shuffle().numCards());

    Deck standardDeck = new Deck(1, 0);
    assertEquals(52, standardDeck.numCards());
    Deck shuffledDeck = standardDeck.shuffle();
    assertEquals(52, shuffledDeck.numCards());
    assertTrue(shuffledDeck.peek().equals(shuffledDeck.deal()));
    assertEquals(shuffledDeck.peek(), shuffledDeck.deal());
  }

}