package cards;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

/**
 * French-suited playing card deck(s) with optional joker(s). Essentially a wrapper around a List of cards, with additional
 * functionality for shuffling, dealing, etc.
 */
public class Deck {
  private static final List<Card> STANDARD_DECK = Arrays.asList(
      new Card(1, Card.Suit.CLUBS),
      new Card(2, Card.Suit.CLUBS),
      new Card(3, Card.Suit.CLUBS),
      new Card(4, Card.Suit.CLUBS),
      new Card(5, Card.Suit.CLUBS),
      new Card(6, Card.Suit.CLUBS),
      new Card(7, Card.Suit.CLUBS),
      new Card(8, Card.Suit.CLUBS),
      new Card(9, Card.Suit.CLUBS),
      new Card(10, Card.Suit.CLUBS),
      new Card(11, Card.Suit.CLUBS),
      new Card(12, Card.Suit.CLUBS),
      new Card(13, Card.Suit.CLUBS),
      new Card(1, Card.Suit.DIAMONDS),
      new Card(2, Card.Suit.DIAMONDS),
      new Card(3, Card.Suit.DIAMONDS),
      new Card(4, Card.Suit.DIAMONDS),
      new Card(5, Card.Suit.DIAMONDS),
      new Card(6, Card.Suit.DIAMONDS),
      new Card(7, Card.Suit.DIAMONDS),
      new Card(8, Card.Suit.DIAMONDS),
      new Card(9, Card.Suit.DIAMONDS),
      new Card(10, Card.Suit.DIAMONDS),
      new Card(11, Card.Suit.DIAMONDS),
      new Card(12, Card.Suit.DIAMONDS),
      new Card(13, Card.Suit.DIAMONDS),
      new Card(1, Card.Suit.HEARTS),
      new Card(2, Card.Suit.HEARTS),
      new Card(3, Card.Suit.HEARTS),
      new Card(4, Card.Suit.HEARTS),
      new Card(5, Card.Suit.HEARTS),
      new Card(6, Card.Suit.HEARTS),
      new Card(7, Card.Suit.HEARTS),
      new Card(8, Card.Suit.HEARTS),
      new Card(9, Card.Suit.HEARTS),
      new Card(10, Card.Suit.HEARTS),
      new Card(11, Card.Suit.HEARTS),
      new Card(12, Card.Suit.HEARTS),
      new Card(13, Card.Suit.HEARTS),
      new Card(1, Card.Suit.SPADES),
      new Card(2, Card.Suit.SPADES),
      new Card(3, Card.Suit.SPADES),
      new Card(4, Card.Suit.SPADES),
      new Card(5, Card.Suit.SPADES),
      new Card(6, Card.Suit.SPADES),
      new Card(7, Card.Suit.SPADES),
      new Card(8, Card.Suit.SPADES),
      new Card(9, Card.Suit.SPADES),
      new Card(10, Card.Suit.SPADES),
      new Card(11, Card.Suit.SPADES),
      new Card(12, Card.Suit.SPADES),
      new Card(13, Card.Suit.SPADES));

  private List<Card> cardStack;

  private Deck(List<Card> cardStack) {
    this.cardStack = cardStack;
  }

  /**
   * Creates a new Deck with the specified number of standard 52-card decks and jokers.
   */
  public Deck(int numDecks, int numJokers) {
    Stream<Card> standardCards = (range(0, numDecks).mapToObj(String::valueOf)).flatMap(dummy -> STANDARD_DECK.stream());
    Stream<Card> jokers = (range(0, numJokers).mapToObj(String::valueOf))
        .map(numStr -> new Card(Integer.parseInt(numStr), Card.Suit.JOKER));
    cardStack = Stream.concat(standardCards, jokers).collect(Collectors.toList());
  }

  /**
   * Returns the next card to be dealt from the stack (but doesn't actually deal that card).
   */
  public Optional<Card> peek() {
    if (cardStack.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(cardStack.get(0));
    }
  }

  public String peekString() {
    Optional<Card> ocard = peek();
    return ocard.map(Card::toString).orElse("No cards left");
  }

  /**
   * Deals the next card from the stack (removes that card from the stack).
   */
  public Optional<Card> deal() {
    if (cardStack.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(cardStack.remove(0));
    }
  }

  public String dealString() {
    Optional<Card> ocard = deal();
    return ocard.map(Card::toString).orElse("No cards left");
  }

  /**
   * Returns how many cards are left.
   */
  public int numCards() {
    return cardStack.size();
  }

  /**
   * Returns a new Deck with the same cards as the current deck, but shuffled in a random order.
   */
  public Deck shuffle() {
    Random random = new Random();
    // map each index of the card stack to a random number and sort by that random number
    List<AbstractMap.SimpleEntry<String, Double>> ordering = range(0, cardStack.size()).mapToObj(String::valueOf)
        .map(indexStr -> new AbstractMap.SimpleEntry<>(indexStr, random.nextDouble())).collect(Collectors.toList());
    ordering.sort(new PairedComparator());

    // TODO consider using TreeMap instead? performance concerns on normal-sized inputs?

    List<Card> shuffledStack = ordering.stream().reduce(new LinkedList<>(),
        (list, pair) -> {
          list.add(cardStack.get(Integer.parseInt(pair.getKey())));
          return list;
        }, (list1, list2) -> {
          list1.addAll(list2);
          return list1;
        });
    return new Deck(shuffledStack);
  }

  private class PairedComparator implements Comparator<AbstractMap.SimpleEntry<String, Double>> {
    @Override
    public int compare(AbstractMap.SimpleEntry<String, Double> pair1, AbstractMap.SimpleEntry<String, Double> pair2) {
      return pair1.getValue().compareTo(pair2.getValue());
    }
  }
}
