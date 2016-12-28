package cards;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Deck class.
 */
// suppress these warning for testing decks
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class DeckTest {
  @Test
  public void testConstructors() throws Exception {
    Deck emptyDeck = new Deck(0, 0);
    assertEquals(0, emptyDeck.size());

    Deck standardDeck = new Deck(1, 0);
    assertEquals(52, standardDeck.size());

    Deck deckWithJokers = new Deck(1, 2);
    assertEquals(54, deckWithJokers.size());
  }

  @Test
  public void testPeek() throws Exception {
    Deck emptyDeck = new Deck(0, 0);
    assertTrue(emptyDeck.isEmpty());

    Deck standardDeck = new Deck(1, 0);
    assertEquals("Ac", standardDeck.peek().toString());
    assertEquals("Ac", standardDeck.peek().toString());

    Deck deckWithJokers = new Deck(1, 2);
    assertEquals("Ac", deckWithJokers.peek().toString());
    assertEquals("Ac", deckWithJokers.peek().toString());
  }

  @Test
  public void testDeal() throws Exception {
    Deck emptyDeck = new Deck(0, 0);
    assertTrue(emptyDeck.isEmpty());

    Deck standardDeck = new Deck(1, 0);
    assertEquals("Ac", standardDeck.remove().toString());
    assertEquals("2c", standardDeck.remove().toString());
    assertEquals(50, standardDeck.size());

    Deck deckWithJokers = new Deck(1, 2);
    assertEquals("Ac", deckWithJokers.remove().toString());
    assertEquals("2c", deckWithJokers.remove().toString());
    assertEquals(52, deckWithJokers.size());
  }

  @Test
  public void testShuffle() throws Exception {
    Deck emptyDeck = new Deck(0, 0);
    assertEquals(0, emptyDeck.shuffle().size());

    Deck standardDeck = new Deck(1, 0);
    assertEquals(52, standardDeck.size());
    Deck shuffledDeck = standardDeck.shuffle();
    assertEquals(52, shuffledDeck.size());
    assertTrue(shuffledDeck.peek().equals(shuffledDeck.remove()));
    assertEquals(shuffledDeck.peek(), shuffledDeck.remove());
  }

}