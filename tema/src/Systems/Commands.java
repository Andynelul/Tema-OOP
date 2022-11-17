package Systems;

import Player.*;

import java.util.ArrayList;

public class Commands {


    public PlayerDeck getPlayerDeck(int playerIdx, ArrayList <card> deck) {

        PlayerDeck plr1 = new PlayerDeck("getPlayerDeck", playerIdx, deck);

        return plr1;
    }

    public PlayerChampion getPlayerHero(int playerIdx, cardChampion champion) {
        PlayerChampion plr = new PlayerChampion("getPlayerHero", playerIdx, champion);
        return plr;
    }

    public PlayerTurn getPlayerTurn(int playerTurn) {
        PlayerTurn plr = new PlayerTurn("getPlayerTurn", playerTurn);
        return plr;
    }
    public PlayerDeck getCardsInHand(int playerIdx, ArrayList <card> deck) {

        PlayerDeck plr1 = new PlayerDeck("getCardsInHand", playerIdx, deck);

        return plr1;
    }
    public PlayerMana getPlayerMana(int playerIdx,int mana) {
        PlayerMana plr = new PlayerMana("getPlayerMana", playerIdx,mana);
        return plr;
    }
    public TableDeck getCardsOnTable(Table table){
        TableDeck plr=new TableDeck("getCardsOnTable", table);
        return plr;
    }
    public Eroare getError(String command,int handIdx,String eroare)
    {
        Eroare err=new Eroare(command,handIdx,eroare);
        return err;
    }
}
