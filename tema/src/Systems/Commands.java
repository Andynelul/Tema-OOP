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
}
