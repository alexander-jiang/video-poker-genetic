package cards;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

/**
 * French-suited playing card deck(s) with optional joker(s). Wrapper around a List of cards, with additional
 * functionality for shuffling, dealing, etc.
 */
public class Deck {
  /**
   * Note: cards stored as two-character strings: rank (2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A) followed by
   * suit (c, d, h, s).
   * Jokers have integer ranks (1, 2, ...) and their suit is represented by a lowercase j.
   */
  private static final List<String> STANDARD_DECK = Arrays.asList(
      "2c", "3c", "4c", "5c", "6c", "7c", "8c", "9c", "Tc", "Jc", "Qc", "Kc", "Ac",
      "2d", "3d", "4d", "5d", "6d", "7d", "8d", "9d", "Td", "Jd", "Qd", "Kd", "Ad",
      "2h", "3h", "4h", "5h", "6h", "7h", "8h", "9h", "Th", "Jh", "Qh", "Kh", "Ah",
      "2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s", "Ts", "Js", "Qs", "Ks", "As");
  private List<String> cardStack;

  private Deck(List<String> cardStack) {
    this.cardStack = cardStack;
  }

  public Deck(int numDecks, int numJokers) {
    Stream<String> standardCards = (range(0, numDecks).mapToObj(String::valueOf)).flatMap(dummy -> STANDARD_DECK.stream());
    Stream<String> jokers = (range(0, numJokers).mapToObj(String::valueOf)).map(numStr -> numStr + "j");
    cardStack = Stream.concat(standardCards, jokers).collect(Collectors.toList());
  }

  /**
   * Returns the next card to be dealt from the stack (but doesn't actually deal that card).
   */
  public String peek() {
    if (cardStack.isEmpty()) {
      return "No cards left";
    } else {
      return cardStack.get(0);
    }
  }

  /**
   * Deals the next card from the stack (removes that card from the stack).
   */
  public String deal() {
    if (cardStack.isEmpty()) {
      return "No cards left";
    } else {
      return cardStack.remove(0);
    }
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
    List<AbstractMap.SimpleEntry<String, Double>> ordering = range(0, cardStack.size()).mapToObj(String::valueOf)
        .map(indexStr -> new AbstractMap.SimpleEntry<>(indexStr, random.nextDouble())).collect(Collectors.toList());
    ordering.sort(new PairedComparator());
    List<String> shuffledStack = ordering.stream().reduce(new LinkedList<>(),
        (list, pair) -> {
          list.add(pair.getKey());
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
