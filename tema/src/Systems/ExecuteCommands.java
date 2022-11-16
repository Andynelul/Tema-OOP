package Systems;

import Player.player;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.GameInput;

public class ExecuteCommands {
    public ArrayNode GetCommands(GameInput game, player p1, player p2){
        ObjectMapper objectMapper=new ObjectMapper() ;
        ArrayNode out=objectMapper.createArrayNode();
        for( ActionsInput action: game.getActions())
        {   String command=action.getCommand();
            Commands doCommand =new Commands();
            if(command.equals("getPlayerDeck"))
            {    if(action.getPlayerIdx()==1)
                out.add(objectMapper.valueToTree(doCommand.getPlayerDeck(action.getPlayerIdx(),
                        p1.getDeck())));
            else
                out.add(objectMapper.valueToTree(doCommand.getPlayerDeck(action.getPlayerIdx(),
                        p2.getDeck())));
            }
            if(command.equals("getPlayerHero"))
            {
                if(action.getPlayerIdx()==1)
                    out.add(objectMapper.valueToTree(doCommand.getPlayerHero(action.getPlayerIdx(),
                            p1.getChampion())));
                else
                    out.add(objectMapper.valueToTree(doCommand.getPlayerHero(action.getPlayerIdx(),
                            p2.getChampion())));
            }
            if(command.equals("getPlayerTurn"))
            {
                if(game.getStartGame().getStartingPlayer()==1)
                    out.add(objectMapper.valueToTree(doCommand.getPlayerTurn(1)));
                else
                    out.add(objectMapper.valueToTree(doCommand.getPlayerTurn(2)));
            }
        }
        return out;
    }
}
