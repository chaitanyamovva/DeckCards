package com.cards;

import java.util.*;
import java.util.stream.Stream;

import com.cards.Card.Suit;
import com.cards.Card.Value;

/**
 * Deck.java
 * 
 * @brief A class that represents a deck of playing cards
 * 
 * @details
 * This class is responsible for shuffling and dealing a deck of cards. The deck should
 * contain 52 cards A,2 - 10, J,Q K or four suits, but no jokers.
 */
class Deck 
{
	
	Card card = new Card();
	public static final int cardSet = 52;
	private Card[] deckOfCards;
	private int currentCard;
  
  public Deck() 
  {
	  deckOfCards = new Card[cardSet];
	  int i = 0;
  }
  
  public void deal_hand( int sets, int cards ) 
  {}

	public void print_deck() {
		String[] cardSuits = getCardEnumValues(Card.Suit.class);
		String[] cardValues = getCardEnumValues(Card.Value.class);

		for (String name : cardSuits) {
			System.out.println(" " + name);
			List<String> objects = Arrays.asList(cardValues);
			String objectsCommaSeparated = String.join(",", objects);
			System.out.println("   " + objectsCommaSeparated);
		}
	}
  
	public void shuffle(int seed) {
		shuffleDeck(seed);
	}
  
  public void sort(){
	  sortFunctionForSuit();
	  System.out.println(" ");
	  sortFunctionForValue();
  }

	 public void shuffleDeck(int seed) {
		String[] SUITS = {
				"Clubs", "Diamonds", "Hearts", "Spades"
		};

		String[] VALUE = {
				"2", "3", "4", "5", "6", "7", "8", "9", "10",
				"Jack", "Queen", "King", "Ace"
		};

		Random rand;

		int n = SUITS.length * VALUE.length;

		if (seed == -1) {
			rand = new Random();
		} else {
			rand = new Random(seed);
		}

		String[] deck = new String[n];
		for (int i = 0; i < VALUE.length; i++) {
			for (int j = 0; j < SUITS.length; j++) {
				deck[SUITS.length * i + j] = VALUE[i] + " of " + SUITS[j];
			}
		}

		for (int i = 0; i < n; i++) {
			int r = i + (int) (Math.random() * (n - i));
			String temp = deck[r];
			deck[r] = deck[i];
			deck[i] = temp;
		}

		for (int i = 0; i < n; i++) {
			System.out.println(deck[i]);
		}
	}

	public Card deal() {

		if (currentCard < cardSet) {
			return (deckOfCards[currentCard++]);
		} else {
			System.out.println("Out of cards error");
			return (null);  // Error;
		}
	}
  
  public static String[] getCardEnumValues(Class<? extends Enum<?>> e) {
	    return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
	}
  
	public void sortFunctionForSuit() {

		Map<String,Integer> suitMap = new HashMap<String,Integer>();

		suitMap.put(String.valueOf(Suit.CLUBS), 3);
		suitMap.put(String.valueOf(Suit.HEARTS), 2);
		suitMap.put(String.valueOf(Suit.DIAMONDS), 4);
		suitMap.put(String.valueOf(Suit.SPADES), 1);

		HashMap<String, Integer> hm1 = sortByMapValue((HashMap<String, Integer>) suitMap, false);
		System.out.println(" *** The suits are valued from highest to lowest:");
		for (Map.Entry<String, Integer> en : hm1.entrySet()) {
			System.out.println("Key = " + en.getKey() +
					", Value = " + en.getValue());
		}
	}

	public void sortFunctionForValue() {

		Map<String, Integer> suitMap = new HashMap<String,Integer>();

		suitMap.put(String.valueOf(Value.KING), 13);
		suitMap.put(String.valueOf(Value.QUEEN), 12);
		suitMap.put(String.valueOf(Value.JACK), 11);
		suitMap.put(String.valueOf(Value.TEN), 10);
		suitMap.put(String.valueOf(Value.NINE), 9);
		suitMap.put(String.valueOf(Value.EIGHT), 8);
		suitMap.put(String.valueOf(Value.SEVEN), 7);
		suitMap.put(String.valueOf(Value.SIX), 6);
		suitMap.put(String.valueOf(Value.FIVE), 5);
		suitMap.put(String.valueOf(Value.FOUR), 4);
		suitMap.put(String.valueOf(Value.THREE), 3);
		suitMap.put(String.valueOf(Value.TWO), 2);
		suitMap.put(String.valueOf(Value.ACE), 1);


		HashMap<String, Integer> hm1 = sortByMapValue((HashMap<String, Integer>) suitMap, true);
		System.out.println(" *** The cards are valued from highest to lowest:");
		for (Map.Entry<String, Integer> en : hm1.entrySet()) {
			System.out.println("Key = " + en.getKey() +
					", Value = " + en.getValue());
		}
	}

	public static HashMap<String, Integer> sortByMapValue(HashMap<String, Integer> hm, boolean order)
	{
		List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
			public int compare(Map.Entry<String, Integer> o1,
							   Map.Entry<String, Integer> o2)
			{
				if (order) {
					return (o2.getValue()).compareTo(o1.getValue());
				} else {
					return (o1.getValue()).compareTo(o2.getValue());
				}
			}
		});

		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	public void deal1(){

		String[] SUITS = {
				"Clubs", "Diamonds", "Hearts", "Spades"
		};

		String[] VALUE = {
				"2", "3", "4", "5", "6", "7", "8", "9", "10",
				"Jack", "Queen", "King", "Ace"
		};
		int[] deck = new int[52];
		int remainingCards = 52;

		for (int i = 0; i < 52; i++) {
			String suit = SUITS[deck[i] / 13];
			String rank = VALUE[deck[i] % 13];
			System.out.println( rank + " of " + suit);
			System.out.println("Remaining cards: " + remainingCards);
		}
	}
}