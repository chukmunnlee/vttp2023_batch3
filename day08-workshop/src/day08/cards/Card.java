package day08.cards;

public class Card {

    final private String name;
    final private String suit;
    final private Integer value;

    public Card(String name, String suit, Integer value) {
        this.name = name;
        this.suit = suit;
        this.value = value;
    }

    public String getName() { return name; }
    public String getSuit() { return suit; }
    public Integer getValue() { return value; }

    @Override
    public String toString() {
        return "Card [name=" + name + ", suit=" + suit + ", value=" + value + "]";
    }
}