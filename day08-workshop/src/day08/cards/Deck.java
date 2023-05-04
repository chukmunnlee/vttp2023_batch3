package day08.cards;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Deck {

    private List<Card> cards = new LinkedList<>();

    public Deck() {
        for (String s: Constants.SUIT) {
            for (int i = 0; i < Constants.NAME.length; i++)
                cards.add(new Card(Constants.NAME[i], s, Constants.VALUE[i]));
        }
    }

    public void shuffle() {
        Random rand = new SecureRandom();
        for (int i = 0; i < cards.size(); i++) {
            int swapIdx = rand.nextInt(cards.size());
            Card c = cards.get(i);
            Card d = cards.get(swapIdx);
            cards.set(i, d);
            cards.set(swapIdx, c);
        }
    }

    public Optional<Card> take() {
        List<Card> taken = take(1);
        if (!taken.isEmpty())
            return Optional.of(taken.get(0));
        return Optional.empty();
    }

    public List<Card> take(int count) {
        List<Card> taken = new LinkedList<>();
        int i = 0;
        while ((i < count) && !cards.isEmpty()) {
            taken.add(cards.remove(0));
            i++;
        }
        return taken;
    }

    public List<Card> getCards() {
        return cards;
    }
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Deck [cards=" + cards + "]";
    }
    
}
