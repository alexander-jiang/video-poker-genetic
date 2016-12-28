package cards;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

/**
 * French-suited playing card deck(s) with optional joker(s). Essentially a wrapper around a LinkedList of cards
 * (useful for removing from the head of the list), with additional functionality for shuffling, dealing, etc.
 */
public class Deck extends LinkedList<Card> {
  private static final List<Card> STANDARD_DECK = new LinkedList<>(Arrays.asList(
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
      new Card(13, Card.Suit.SPADES)));

  /**
   * Creates an empty Deck.
   */
  public Deck() {
    super();
  }

  /**
   * Creates a Deck with the specified cards.
   */
  public Deck(Collection<? extends Card> cardStack) {
    super(cardStack);
  }

  /**
   * Creates a new Deck with the specified number of standard 52-card decks and jokers.
   */
  public Deck(int numDecks, int numJokers) {
    super();
    Stream<Card> standardCards = (range(0, numDecks).mapToObj(String::valueOf)).flatMap(dummy -> STANDARD_DECK.stream());
    Stream<Card> jokers = (range(0, numJokers).mapToObj(String::valueOf))
        .map(numStr -> new Card(Integer.parseInt(numStr), Card.Suit.JOKER));
    this.addAll(Stream.concat(standardCards, jokers).collect(Collectors.toList()));
  }

  /**
   * Returns a new Deck with the same cards as the current deck, but shuffled in a random order.
   */
  public Deck shuffle() {
    Random random = new Random();
    // map each index of the card stack to a random number and sort by that random number
    List<AbstractMap.SimpleEntry<String, Double>> ordering = range(0, this.size()).mapToObj(String::valueOf)
        .map(indexStr -> new AbstractMap.SimpleEntry<>(indexStr, random.nextDouble())).collect(Collectors.toList());
    ordering.sort(new PairedComparator());

    // TODO consider using TreeMap instead? performance concerns on normal-sized inputs?

    List<Card> shuffledStack = ordering.stream().reduce(new LinkedList<>(),
        (list, pair) -> {
          list.add(this.get(Integer.parseInt(pair.getKey())));
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
