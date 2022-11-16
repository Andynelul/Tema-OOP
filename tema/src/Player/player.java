        package Player;

        import fileio.CardInput;

        import java.util.ArrayList;

        public class player {
    private int playerIdx;
    private int nrCardsInDeck;

    private ArrayList<card> deck= new ArrayList<>();
    private ArrayList<card> hand=new ArrayList<>();
    private cardChampion Champion;

    int mana;

            public int getPlayerIdx() {
                return playerIdx;
            }

            public void setPlayerIdx(int playerIdx) {
                this.playerIdx = playerIdx;
            }

            public int getNrCardsInDeck() {
                return nrCardsInDeck;
            }

            public void setNrCardsInDeck(int nrCardsInDeck) {
                this.nrCardsInDeck = nrCardsInDeck;
            }

            public player(int playerIdx, int nrCardsInDeck, ArrayList<CardInput> decks) {
                this.playerIdx = playerIdx;
                this.nrCardsInDeck = nrCardsInDeck;
                for(int i=0;i<nrCardsInDeck;i++){
                    if(decks.get(i).getHealth()==0)
               this.deck.add(new card(decks.get(i)));
                    else
                        this.deck.add(new cardMinion(decks.get(i)));
                }
            }

            @Override
            public String toString() {
                return "player{" +
                        "playerIdx=" + playerIdx +
                        ", nrCardsInDeck=" + nrCardsInDeck +
                        ", deck=" + deck +
                        '}';
            }

            public ArrayList <card> getDeck() {
                return deck;
            }

            public void setDeck(ArrayList <card> deck) {
                this.deck = deck;
            }

            public ArrayList <card> getHand() {
                return hand;
            }

            public void setHand(ArrayList <card> hand) {
                this.hand = hand;
            }

            public cardChampion getChampion() {
                return Champion;
            }

            public void setChampion(CardInput champion) {
                Champion = new cardChampion(champion);
            }

            public int getMana() {
                return mana;
            }

            public void setMana(int mana) {
                this.mana = mana;
            }
        }
