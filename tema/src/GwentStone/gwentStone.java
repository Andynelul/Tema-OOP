package GwentStone;

import Player.PlayerDeck;
import Player.player;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.*;
import Systems.Commands;

import java.util.Collections.*;
import java.util.ArrayList;
import java.util.Random;
import static java.util.Collections.shuffle;

public class gwentStone {
    public ArrayNode start(Input inputdata){
        ObjectMapper objectMapper=new ObjectMapper() ;
        ArrayNode out=objectMapper.createArrayNode();
           for(int i=0 ;i<inputdata.getGames().size();i++)
           {

               GameInput game=inputdata.getGames().get(i);
               int p1deck=game.getStartGame().getPlayerOneDeckIdx();
               int p2deck=game.getStartGame().getPlayerTwoDeckIdx();
               ArrayList<CardInput> d1=new ArrayList <CardInput>(inputdata.getPlayerOneDecks().getDecks().get(p1deck));
               ArrayList<CardInput> d2=new ArrayList<CardInput>( inputdata.getPlayerTwoDecks().getDecks().get(p2deck));
               shuffle(d1,new Random(game.getStartGame().getShuffleSeed()));
               shuffle(d2,new Random(game.getStartGame().getShuffleSeed()));
               player p1=new player(1,inputdata.getPlayerOneDecks().getNrCardsInDeck(),d1);
               player p2=new player(2,inputdata.getPlayerTwoDecks().getNrCardsInDeck(),d2);
               p1.setChampion(game.getStartGame().getPlayerOneHero());
               p2.setChampion(game.getStartGame().getPlayerTwoHero());
              p1.getHand().add(p1.getDeck().get(0));
              p1.getDeck().remove(0);
              p2.getHand().add(p2.getDeck().get(0));
              p2.getDeck().remove(0);
              for(ActionsInput action: game.getActions())
              {   String command=action.getCommand();
                  Commands doCommand =new Commands();
                  if(command.equals("getPlayerDeck"))
                  {    if(action.getPlayerIdx()==1)
                      out.add(objectMapper.valueToTree(doCommand.getPlayerDeck(action.getPlayerIdx(),p1.getDeck())));
                      else
                          out.add(objectMapper.valueToTree(doCommand.getPlayerDeck(action.getPlayerIdx(),p2.getDeck())));
                  }
                  if(command.equals("getPlayerHero"))
                  {
                      if(action.getPlayerIdx()==1)
                          out.add(objectMapper.valueToTree(doCommand.getPlayerHero(action.getPlayerIdx(), p1.getChampion())));
                      else
                          out.add(objectMapper.valueToTree(doCommand.getPlayerHero(action.getPlayerIdx(), p2.getChampion())));
                  }
                  if(command.equals("getPlayerTurn"))
                  {
                      if(game.getStartGame().getStartingPlayer()==1)
                          out.add(objectMapper.valueToTree(doCommand.getPlayerTurn(1)));
                      else
                          out.add(objectMapper.valueToTree(doCommand.getPlayerTurn(2)));
                  }
              }

              //TODO de aici e player deck
//               PlayerDeck plr1=new PlayerDeck("getPlayerDeck", 1, p1.getDeck());
//               PlayerDeck plr2=new PlayerDeck("getPlayerDeck", 2, p2.getDeck());
//               out.put(plr1));
//               out.add(objectMapper.valueToTree(plr2));
               //PlayerDeck plr1=new PlayerDeck("getPlayerDeck", 1, p1.getDeck());
              // PlayerDeck plr2=new
             //  player p1=new player(game.getStartGame().getPlayerOneDeckIdx(),inputdata.getPlayerOneDecks().getNrCardsInDeck(),inputdata.getPlayerOneDecks().getDecks().get())
           }
           return out;
    }
}

