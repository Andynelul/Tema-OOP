package gwentStone;

import Player.player;
import Systems.ExecuteCommands;
import Systems.Statistics;
import Systems.Table;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.*;
import java.util.ArrayList;
import java.util.Random;
import static java.util.Collections.shuffle;

public class StartGame {
    public ArrayNode start(final Input inputdata) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode out = objectMapper.createArrayNode();
        for (int i = 0; i < inputdata.getGames().size(); i++) {
            Statistics.setGames(Statistics.getGames() + 1);
            GameInput game = inputdata.getGames().get(i);
            int p1deck = game.getStartGame().getPlayerOneDeckIdx();
            int p2deck = game.getStartGame().getPlayerTwoDeckIdx();
            ArrayList<CardInput> d1 = new ArrayList<>(
                    inputdata.getPlayerOneDecks().getDecks().get(p1deck));
            ArrayList<CardInput> d2 = new ArrayList<>(
                    inputdata.getPlayerTwoDecks().getDecks().get(p2deck));
            shuffle(d1, new Random(game.getStartGame().getShuffleSeed()));
            shuffle(d2, new Random(game.getStartGame().getShuffleSeed()));
            player p1 = new player(1, inputdata.getPlayerOneDecks().getNrCardsInDeck(), d1);
            player p2 = new player(2, inputdata.getPlayerTwoDecks().getNrCardsInDeck(), d2);
            p1.setChampion(game.getStartGame().getPlayerOneHero());
            p2.setChampion(game.getStartGame().getPlayerTwoHero());
            p1.getHand().add(p1.getDeck().get(0));
            p1.getDeck().remove(0);
            p2.getHand().add(p2.getDeck().get(0));
            p2.getDeck().remove(0);
            Table table = new Table();
            p1.setMana(1);
            p2.setMana(1);
           ExecuteCommands commands = new ExecuteCommands();
           commands.GetCommands(game, p1, p2, table, out);
        }
            return out;
        }
}