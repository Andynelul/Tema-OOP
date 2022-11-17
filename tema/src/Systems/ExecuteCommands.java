package Systems;

import Player.card;
import Player.cardMinion;
import Player.player;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.GameInput;

public class ExecuteCommands {
    public ArrayNode GetCommands(GameInput game, player p1, player p2,Table table){
        int playerTurn=game.getStartGame().getStartingPlayer();
        int counter=0;
        int round=1;
        int p1col=0,p2col=3;
        int number1=0,number2=0;
        ObjectMapper objectMapper=new ObjectMapper() ;
        ArrayNode out=objectMapper.createArrayNode();
        for( ActionsInput action: game.getActions()) {
            String command = action.getCommand();
            Commands doCommand = new Commands();
            if ( command.equals("getPlayerDeck") ) {
                if ( action.getPlayerIdx() == 1 )
                    out.add(objectMapper.valueToTree(doCommand.getPlayerDeck(action.getPlayerIdx(),
                            p1.getDeck())));
                else
                    out.add(objectMapper.valueToTree(doCommand.getPlayerDeck(action.getPlayerIdx(),
                            p2.getDeck())));
            }
            if ( command.equals("getPlayerHero") ) {
                if ( action.getPlayerIdx() == 1 )
                    out.add(objectMapper.valueToTree(doCommand.getPlayerHero(action.getPlayerIdx(),
                            p1.getChampion())));
                else
                    out.add(objectMapper.valueToTree(doCommand.getPlayerHero(action.getPlayerIdx(),
                            p2.getChampion())));
            }
            if ( command.equals("getPlayerTurn") ) {

                out.add(objectMapper.valueToTree(doCommand.getPlayerTurn(playerTurn)));

            }
            if ( command.equals("endPlayerTurn") ) {
                if ( playerTurn == 1 ) {
                    playerTurn = 2;
                    counter++;
                } else {
                    playerTurn = 1;
                    counter++;
                }
                if ( counter == 2 ) {
                    counter = 0;
                    //  Round.next(p1,p2,round)

                    round++;
                    if ( round < 10 ) {
                        p1.setMana(round + p1.getMana());
                        p2.setMana(round + p2.getMana());
                    } else {
                        p1.setMana(10 + p1.getMana());
                        p2.setMana(10 + p2.getMana());
                    }
                    if ( !p1.getDeck().isEmpty() ) {
                        p1.getHand().add(p1.getDeck().get(0));
                        p1.getDeck().remove(0);
                    }
                    if ( !p2.getDeck().isEmpty() ) {
                        p2.getHand().add(p2.getDeck().get(0));
                        p2.getDeck().remove(0);
                    }
                }
            }
            if ( command.equals("getCardsInHand") ) {
                if ( action.getPlayerIdx() == 1 )
                    out.add(objectMapper.valueToTree(doCommand.getCardsInHand(1,
                            p1.getHand())));
                else
                    out.add(objectMapper.valueToTree(doCommand.getCardsInHand(2,
                            p2.getHand())));
            }
            if ( command.equals("getCardsOnTable") ) {
                out.add(objectMapper.valueToTree(doCommand.getCardsOnTable(table)));
            }
            if ( command.equals("getPlayerMana") ) {
                if ( action.getPlayerIdx() == 1 )
                    out.add(objectMapper.valueToTree(doCommand.getPlayerMana(1,
                            p1.getMana())));
                else
                    out.add(objectMapper.valueToTree(doCommand.getPlayerMana(2,
                            p2.getMana())));
            }
            if ( command.equals("placeCard") ) {
                if ( playerTurn == 1 ) {
                    card chosencard = (p1.getHand().get(action.getHandIdx()));
                    if ( !p1.getHand().isEmpty() ) {
                        if ( (p1.getMana() >= p1.getHand().get(action.getHandIdx()).getMana()) ) {
                                if(chosencard instanceof cardMinion ){
                            if ( chosencard.getName().equals("Warden") || chosencard.getName().equals("Goliath") || chosencard.getName().equals("The Ripper") || chosencard.getName().equals("Miraj") ) {
                                if(number1<5){
                                p1.setMana(p1.getMana() - chosencard.getMana());
                                table.getTable().get(1).add(chosencard);
                                p1.getHand().remove(action.getHandIdx());
                                number1++;
                                }
                                else{out.add(objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Cannot place card on table since row is full.")));}
                            } else {
                                if(p1col<5){
                                p1.setMana(p1.getMana() - chosencard.getMana());
                                table.getTable().get(0).add(chosencard);
                                p1.getHand().remove(action.getHandIdx());
                                p1col++;}
                                else{out.add(objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Cannot place card on table since row is full.")));}
                            }
                                } else out.add((objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Cannot place environment card on table."))));
                        } else out.add(objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Not enough mana to place card on table.")));
                    }
                } else {
                    if ( !p2.getHand().isEmpty() ) {
                        if ( (p2.getMana() >= p2.getHand().get(action.getHandIdx()).getMana()) ) {

                            card chosencard = (p2.getHand().get(action.getHandIdx()));
                            if(chosencard instanceof cardMinion ){
                            if ( chosencard.getName().equals("Warden") || chosencard.getName().equals("Goliath") || chosencard.getName().equals("The Ripper") || chosencard.getName().equals("Miraj") ) {
                                if(number2<5) {
                                p2.setMana(p2.getMana() - p2.getHand().get(action.getHandIdx()).getMana());

                                table.getTable().get(2).add(p2.getHand().get(action.getHandIdx()));
                                p2.getHand().remove(action.getHandIdx());
                                number2++;}
                                else out.add(objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Cannot place card on table since row is full.")));
                            } else {
                                if(p2col<5)
                                {  p2.setMana(p2.getMana() - p2.getHand().get(action.getHandIdx()).getMana());

                                table.getTable().get(3).add(p2.getHand().get(action.getHandIdx()));
                                p2.getHand().remove(action.getHandIdx());
                                p2col++;}
                                else out.add(objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Cannot place card on table since row is full.")));

                            }}else out.add((objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Cannot place environment card on table."))));

                        } else out.add(objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Not enough mana to place card on table.")));

                    }
                }
            }
        }
        return out;
    }
}
//class Round{
//    static public void next(player p1,player p2)
//    {   round++;
//
//    }
//}
