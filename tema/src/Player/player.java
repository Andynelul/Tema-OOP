        package Player;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.fasterxml.jackson.databind.node.ArrayNode;
        import fileio.CardInput;
        import fileio.DecksInput;

        import java.util.ArrayList;

        public class player {
    private int playerIdx;
    private int nrCardsInDeck;

    private ArrayList<card> deck= new ArrayList<>();
    private ArrayList<card> hand=new ArrayList<>();
    private cardChampion Champion;

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




//                public player(int playerIdx, int nrCardsInDeck, int nrDecks,ArrayList<ArrayList<CardInput>> decks) {
//                this.playerIdx = playerIdx;
//                this.nrCardsInDeck = nrCardsInDeck;
//                this.nrDecks = nrDecks;

//                for(int i=0; i<nrDecks; i++)
//                { ArrayList<card > this.decks.set(i,new ArrayList<card>());
//                    for(int j=0; j<nrCardsInDeck; j++)
//                    {   CardInput carte=decks.get(i).get(j);
//                         this.decks.get(i).set(j,new card(carte.getMana(),carte.getAttackDamage(),carte.getHealth(), carte.getDescription(), carte.getColors(), carte.getName()));
//                    }
//                }



//            public player(int playerIdx, int nrCardsInDeck, int nrDecks, ArrayList<ArrayList<card>> decks) {
//                this.playerIdx = playerIdx;
//                this.nrCardsInDeck = nrCardsInDeck;
//                this.nrDecks = nrDecks;
//                ObjectMapper objectMapper = new ObjectMapper();
//                ArrayNode output = objectMapper.createArrayNode();
//                output.add(objectMapper.valueToTree(decks));
//                this.decks=output.decks;
//                this.decks = decks;
//            }
//            public player(int playerIdx, int nrCardsInDeck, int nrDecks) {
//               this.playerIdx = playerIdx;
//              this.nrCardsInDeck = nrCardsInDeck;
//               this.nrDecks = nrDecks;}
//


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
        }
