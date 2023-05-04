package day08;

import java.util.List;
import java.util.Optional;

import day08.cards.Deck;
import day08.cards.Card;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        //System.out.printf(">>> before deck: %s\n", deck);
        deck.shuffle();
        //System.out.printf(">>> after deck: %s\n", deck);
        List<Card> fiveCards = deck.take(55);
        System.out.printf(">>> 5 cards: %s\n", fiveCards);

        Optional<Card> opt = deck.take();
        //Card c = opt.orElse(new Card("Joker", "Joker", 0));
        try {
            opt.orElseThrow();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.printf(">>> 1 cards: %s\n", c);

        // if (opt.isPresent()) {
        //     Card c = opt.get();
        //     System.out.printf(">>> 1 cards: %s\n", c);

        // } else {
        //     System.out.println("The deck is empty. No more cards");
        // }

    }
    
}
