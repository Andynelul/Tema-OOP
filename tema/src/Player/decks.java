package Player;

import java.util.ArrayList;

public class decks {
    private int nrCardsInDeck;
    private int nrDecks;
    private ArrayList<ArrayList<card>> decks;

    public decks() {
    }

    public int getNrCardsInDeck() {
        return nrCardsInDeck;
    }

    public void setNrCardsInDeck(final int nrCardsInDeck) {
        this.nrCardsInDeck = nrCardsInDeck;
    }

    public int getNrDecks() {
        return nrDecks;
    }

    public void setNrDecks(final int nrDecks) {
        this.nrDecks = nrDecks;
    }

    public ArrayList<ArrayList<card>> getDecks() {
        return decks;
    }

    public void setDecks(final ArrayList<ArrayList<card>> decks) {
        this.decks = decks;
    }

    @Override
    public String toString() {
        return "{"
                + "nr_cards_in_deck="
                + nrCardsInDeck
                +  ", nr_decks="
                + nrDecks
                + ", decks="
                + decks
                + '}';
    }
}
