package Systems;

import Player.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.GameInput;

import java.util.ArrayList;

public class ExecuteCommands {
    public void GetCommands(GameInput game, player p1, player p2,Table table,ArrayNode out){
        int playerTurn=game.getStartGame().getStartingPlayer();
        int counter=0;
        int round=1;
        int randul0=0,randul3=0;
        int randul1=0,randul2=0;
        int p1ChampAt=0,p2ChampAt=0;
        ObjectMapper objectMapper=new ObjectMapper() ;
       // ArrayNode out=objectMapper.createArrayNode();
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
                    table.tableUnfrozep1();
                    p1ChampAt=0;
                } else {
                    playerTurn = 1;
                    counter++;
                    table.tableUnfrozep2();
                    p2ChampAt=0;
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
                            if ( chosencard instanceof cardMinion ) {
                                if ( chosencard.getName().equals("Warden") || chosencard.getName().equals("Goliath") || chosencard.getName().equals("The Ripper") || chosencard.getName().equals("Miraj") ) {
                                    if ( randul1 < 5 ) {
                                        p1.setMana(p1.getMana() - chosencard.getMana());
                                        table.getTable().get(1).add(new cardTable((cardMinion) chosencard));
                                        p1.getHand().remove(action.getHandIdx());
                                        randul1++;
                                    } else {
                                        out.add(objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Cannot place card on table since row is full.")));
                                    }
                                } else {
                                    if ( randul0 < 5 ) {
                                        p1.setMana(p1.getMana() - chosencard.getMana());
                                        table.getTable().get(0).add(new cardTable((cardMinion) chosencard));
                                        p1.getHand().remove(action.getHandIdx());
                                        randul0++;
                                    } else {
                                        out.add(objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Cannot place card on table since row is full.")));
                                    }
                                }
                            } else
                                out.add((objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Cannot place environment card on table."))));
                        } else
                            out.add(objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Not enough mana to place card on table.")));
                    }
                } else {
                    if ( !p2.getHand().isEmpty() ) {
                        if ( (p2.getMana() >= p2.getHand().get(action.getHandIdx()).getMana()) ) {

                            card chosencard = (p2.getHand().get(action.getHandIdx()));
                            if ( chosencard instanceof cardMinion ) {
                                if ( chosencard.getName().equals("Warden") || chosencard.getName().equals("Goliath") || chosencard.getName().equals("The Ripper") || chosencard.getName().equals("Miraj") ) {
                                    if ( randul2 < 5 ) {
                                        p2.setMana(p2.getMana() - p2.getHand().get(action.getHandIdx()).getMana());

                                        table.getTable().get(2).add(new cardTable((cardMinion) p2.getHand().get(action.getHandIdx())));
                                        p2.getHand().remove(action.getHandIdx());
                                        randul2++;
                                    } else
                                        out.add(objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Cannot place card on table since row is full.")));
                                } else {
                                    if ( randul3 < 5 ) {
                                        p2.setMana(p2.getMana() - p2.getHand().get(action.getHandIdx()).getMana());

                                        table.getTable().get(3).add(new cardTable((cardMinion) p2.getHand().get(action.getHandIdx())));
                                        p2.getHand().remove(action.getHandIdx());
                                        randul3++;
                                    } else
                                        out.add(objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Cannot place card on table since row is full.")));

                                }
                            } else
                                out.add((objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Cannot place environment card on table."))));

                        } else
                            out.add(objectMapper.valueToTree(doCommand.getError("placeCard", action.getHandIdx(), "Not enough mana to place card on table.")));

                    }
                }
            }
            if(command.equals("getCardAtPosition"))
            {   if(table.getTable().get(3-action.getX()).size()>action.getY())
                out.add(objectMapper.valueToTree(doCommand.getCardAtPosition(action.getX(), action.getY(),table.getTable().get(3-action.getX()).get(action.getY()))));
                else
                out.add(objectMapper.valueToTree(doCommand.getError(action.getX(), action.getY())));
            }
            if(command.equals("getEnvironmentCardsInHand"))
            {
                if ( action.getPlayerIdx() == 1 )
                    out.add(objectMapper.valueToTree(doCommand.getEnvironmentCardsInHand(1,
                            p1.getHand())));
                else
                    out.add(objectMapper.valueToTree(doCommand.getEnvironmentCardsInHand(2,
                            p2.getHand())));
            }
            if ( command.equals("cardUsesAttack") ) {
                int coordY, coordX, coordDefY, coorddDefX;
                coordY = action.getCardAttacker().getY();
                coordX =( 3 - action.getCardAttacker().getX());
                coordDefY = action.getCardAttacked().getY();
                coorddDefX = (3 - action.getCardAttacked().getX());
                if(coordY<table.getTable().get(coordX).size()||coordDefY<table.getTable().get(coorddDefX).size()) {
                    if (( table.getTable().get(coordX).get(coordY).getFrozen()) == 0 ) {
                        if ( (table.getTable().get(coordX).get(coordY)).getAttacked() == 0 ) {
                            if ( playerTurn == 1 ) {
                                if ( coorddDefX != 0 && coorddDefX != 1 ) {
                                    int cnt = 0;
                                    for ( int i = 0; i < table.getTable().get(2).size(); i++ ) {
                                        if ( table.getTable().get(2).get(i).getName().equals("Goliath")||table.getTable().get(2).get(i).getName().equals("Warden"))
                                            cnt++;
                                    }
                                    if ( cnt >= 1 ) {
                                        if ( table.getTable().get(coorddDefX).get(coordDefY).getName().equals("Goliath")||table.getTable().get(coorddDefX).get(coordDefY).getName().equals("Warden")) {

                                            cardTable attCard = table.getTable().get(coordX).get(coordY);
                                            cardTable defCard = table.getTable().get(coorddDefX).get(coordDefY);
                                            defCard.setHealth(defCard.getHealth() - attCard.getAttackDamage());
                                            if ( defCard.getHealth() <= 0 ) {
                                                table.getTable().get(coorddDefX).remove(coorddDefX);
                                                randul2--;
                                            }
                                            //    attCard.setFrozen(1);
                                            attCard.setAttacked(1);
                                        } else
                                            out.add(objectMapper.valueToTree(doCommand.getAttackErrors( action.getCardAttacker(), action.getCardAttacked(),"Attacked card is not of type 'Tank'.")));
                                    }
                                    else {
                                        cardTable attCard = (table.getTable().get(coordX).get(coordY));
                                        cardTable defCard = (table.getTable().get(coorddDefX).get(coordDefY));
                                        defCard.setHealth(defCard.getHealth() - attCard.getAttackDamage());
                                        if ( defCard.getHealth() <= 0 ) {
                                            table.getTable().get(coorddDefX).remove(coordDefY);
                                            randul3--;
                                        }
                                        attCard.setAttacked(1);
                                    }
                                } else
                                    out.add(objectMapper.valueToTree((doCommand.getAttackErrors( action.getCardAttacker(), action.getCardAttacked(), "Attacked card does not belong to the enemy."))));
                            } else {
                                if ( coorddDefX != 2 && coorddDefX != 3 ) {
                                    int cnt = 0;
                                    for ( int i = 0; i < table.getTable().get(1).size(); i++ ) {
                                        if ( table.getTable().get(1).get(i).getName().equals("Goliath")||table.getTable().get(1).get(i).getName().equals("Warden"))
                                            cnt++;
                                    }
                                    if ( cnt >= 1 ) {
                                        if ( table.getTable().get(coorddDefX).get(coordDefY).getName().equals("Goliath")||table.getTable().get(coorddDefX).get(coordDefY).getName().equals("Warden")) {

                                            cardTable attCard = (table.getTable().get(coordX).get(coordY));
                                            cardTable defCard = (table.getTable().get(coorddDefX).get(coordDefY));
                                            defCard.setHealth(defCard.getHealth() - attCard.getAttackDamage());
                                            if ( defCard.getHealth() <= 0 ) {
                                                table.getTable().get(coorddDefX).remove(coordDefY);
                                                randul1--;
                                            }
                                            attCard.setAttacked(1);
                                        } else
                                            out.add(objectMapper.valueToTree(doCommand.getAttackErrors( action.getCardAttacker(), action.getCardAttacked(), "Attacked card is not of type 'Tank'.")));
                                    } else {
                                        cardTable attCard = table.getTable().get(coordX).get(coordY);
                                        cardTable defCard = table.getTable().get(coorddDefX).get(coordDefY);
                                        defCard.setHealth(defCard.getHealth() - attCard.getAttackDamage());
                                        if ( defCard.getHealth() <= 0 ) {
                                            table.getTable().get(coorddDefX).remove(coordDefY);
                                            randul0--;
                                        }
                                        attCard.setAttacked(1);
                                    }
                                } else
                                    out.add(objectMapper.valueToTree((doCommand.getAttackErrors( action.getCardAttacker(), action.getCardAttacked(), "Attacked card does not belong to the enemy."))));
                            }
                        } else
                            out.add(objectMapper.valueToTree((doCommand.getAttackErrors( action.getCardAttacker(), action.getCardAttacked(), "Attacker card has already attacked this turn."))));

                    } else
                        out.add(objectMapper.valueToTree((doCommand.getAttackErrors( action.getCardAttacker(), action.getCardAttacked(), "Attacker card is frozen."))));

               }else                                     out.add(objectMapper.valueToTree((doCommand.getAttackErrors(action.getCardAttacker(),action.getCardAttacked(), "Attacked card does not belong to the enemy."))));

            }
            if(command.equals("useEnvironmentCard")) {
                if ( playerTurn == 1 ) {
                    card chosencard = (p1.getHand().get(action.getHandIdx()));
                    if ( chosencard.getName().equals("Winterfell") || chosencard.getName().equals("Firestorm") || chosencard.getName().equals("Heart Hound") ) {
                        if ( chosencard.getMana() <= p1.getMana() ) {
                            if ( (3 - action.getAffectedRow()) == 2 || (3 - action.getAffectedRow()) == 3 ) {
                                if ( chosencard.getName().equals("Firestorm") ) {
                                    for ( int i = 0; i < table.getTable().get(3 - action.getAffectedRow()).size(); i++ ) {
                                        // System.out.println("Viatsa"+table.getTable().get(3-action.getAffectedRow()).get(i).getHealth());
                                        table.getTable().get(3 - action.getAffectedRow()).get(i).setHealth((table.getTable().get(3 - action.getAffectedRow()).get(i).getHealth()) - 1);
                                        //    System.out.println("Viatsa dupapa"+table.getTable().get(3-action.getAffectedRow()).get(i).getHealth());

                                    }
                                    for ( int i = table.getTable().get(3 - action.getAffectedRow()).size() - 1; i >= 0; i-- ) {
                                        if ( table.getTable().get(3 - action.getAffectedRow()).get(i).getHealth() < 1 )
                                            table.getTable().get(3 - action.getAffectedRow()).remove(i);
                                    }
                                    p1.setMana(p1.getMana() - chosencard.getMana());
                                    p1.getHand().remove(action.getHandIdx());
                                }
                               else if ( chosencard.getName().equals("Winterfell") ) {
                                    for ( int i = 0; i < table.getTable().get(3 - action.getAffectedRow()).size(); i++ ) {
                                        table.getTable().get(3 - action.getAffectedRow()).get(i).setFrozen(1);
                                    }
                                    p1.setMana(p1.getMana() - chosencard.getMana());
                                    p1.getHand().remove(action.getHandIdx());
                                }
                              else  if ( chosencard.getName().equals("Heart Hound") ) {
                                    int num = 0;
                                    int bigH = table.getTable().get(3 - action.getAffectedRow()).get(0).getHealth();
                                    for ( int i = 0; i < table.getTable().get(3 - action.getAffectedRow()).size(); i++ ) {
                                        if ( table.getTable().get(3 - action.getAffectedRow()).get(i).getHealth() > bigH ) {
                                            bigH = table.getTable().get(3 - action.getAffectedRow()).get(i).getHealth();
                                            num = i;
                                        }
                                    }
                                    if ( (3 - action.getAffectedRow()) == 2 ) {
                                        if ( randul1 < 5 ) {
                                            table.getTable().get(1).add(table.getTable().get(3 - action.getAffectedRow()).get(num));
                                            table.getTable().get(3 - action.getAffectedRow()).remove(num);
                                            randul1++;
                                            randul2--;
                                        } else
                                            out.add(objectMapper.valueToTree(doCommand.getEnvironmentError("useEnvironmentCard", action.getHandIdx(), action.getAffectedRow(), "Cannot steal enemy card since the player's row is full.")));

                                    } else {
                                        if ( randul0 < 5 ) {
                                            table.getTable().get(0).add(table.getTable().get(3 - action.getAffectedRow()).get(num));
                                            table.getTable().get(3 - action.getAffectedRow()).remove(num);
                                            randul0++;
                                            randul3--;
                                            p1.setMana(p1.getMana() - chosencard.getMana());
                                            p1.getHand().remove(action.getHandIdx());
                                        } else
                                            out.add(objectMapper.valueToTree(doCommand.getEnvironmentError("useEnvironmentCard", action.getHandIdx(), action.getAffectedRow(), "Cannot steal enemy card since the player's row is full.")));
                                    }

                                }
                            } else
                                out.add(objectMapper.valueToTree(doCommand.getEnvironmentError("useEnvironmentCard", action.getHandIdx(), action.getAffectedRow(), "Chosen row does not belong to the enemy.")));

                        } else
                            out.add(objectMapper.valueToTree(doCommand.getEnvironmentError("useEnvironmentCard", action.getHandIdx(), action.getAffectedRow(), "Not enough mana to use environment card.")));

                    } else
                        out.add(objectMapper.valueToTree(doCommand.getEnvironmentError("useEnvironmentCard", action.getHandIdx(), action.getAffectedRow(), "Chosen card is not of type environment.")));
                }
                else {
                    card chosencard = (p2.getHand().get(action.getHandIdx()));
                    if ( chosencard.getName().equals("Winterfell") || chosencard.getName().equals("Firestorm") || chosencard.getName().equals("Heart Hound") ) {
                        if ( chosencard.getMana() <= p2.getMana() ) {
                            if ( (3 - action.getAffectedRow()) == 0 || (3 - action.getAffectedRow()) == 1 ) {
                                if ( chosencard.getName().equals("Firestorm") ) {
                                    for ( int i = 0; i < table.getTable().get(3 - action.getAffectedRow()).size(); i++ ) {//System.out.println("Viatsa"+table.getTable().get(3-action.getAffectedRow()).get(i).getHealth());
                                        table.getTable().get(3 - action.getAffectedRow()).get(i).setHealth((table.getTable().get(3 - action.getAffectedRow()).get(i).getHealth() - 1));
                                        //   System.out.println("Viatsa dupapa"+table.getTable().get(3-action.getAffectedRow()).get(i).getHealth());
                                    }
                                    for ( int i = table.getTable().get(3 - action.getAffectedRow()).size() - 1; i >= 0; i-- ) {
                                        if ( table.getTable().get(3 - action.getAffectedRow()).get(i).getHealth() < 1 )
                                            table.getTable().get(3 - action.getAffectedRow()).remove(i);
                                    }
                                    p2.setMana(p2.getMana() - chosencard.getMana());
                                    p2.getHand().remove(action.getHandIdx());
                                }
                               else if ( chosencard.getName().equals("Winterfell") ) {
                                    for ( int i = 0; i < table.getTable().get(3 - action.getAffectedRow()).size(); i++ ) {
                                        table.getTable().get(3 - action.getAffectedRow()).get(i).setFrozen(1);
                                    }
                                    p2.setMana(p2.getMana() - chosencard.getMana());
                                    p2.getHand().remove(action.getHandIdx());
                                }
                               else if ( chosencard.getName().equals("Heart Hound") ) {
                                    int numar = 0;
                                    int bigH = table.getTable().get(3 - action.getAffectedRow()).get(0).getHealth();
                                    for ( int i = 0; i < table.getTable().get(3 - action.getAffectedRow()).size(); i++ ) {
                                        if ( table.getTable().get(3 - action.getAffectedRow()).get(i).getHealth() > bigH ) {
                                            bigH = table.getTable().get(3 - action.getAffectedRow()).get(i).getHealth();
                                            numar = i;
                                        }
                                    }
                                    if ( (3 - action.getAffectedRow()) == 1 ) {
                                        if ( randul2 < 5 ) {
                                            table.getTable().get(2).add(table.getTable().get(3 - action.getAffectedRow()).get(numar));
                                            table.getTable().get(3 - action.getAffectedRow()).remove(numar);
                                            randul1--;
                                            randul2++;
                                        } else out.add(objectMapper.valueToTree(doCommand.getEnvironmentError("useEnvironmentCard", action.getHandIdx(), action.getAffectedRow(), "Cannot steal enemy card since the player's row is full.")));
                                    }
                                    else {
                                        if ( randul3 < 5 ) {
                                            table.getTable().get(3).add(table.getTable().get(3 - action.getAffectedRow()).get(numar));
                                            table.getTable().get(3 - action.getAffectedRow()).remove(numar);
                                            randul3++;
                                            randul0--;
                                            p2.setMana(p2.getMana() - chosencard.getMana());
                                            p2.getHand().remove(action.getHandIdx());
                                        } else
                                            out.add(objectMapper.valueToTree(doCommand.getEnvironmentError("useEnvironmentCard", action.getHandIdx(), action.getAffectedRow(), "Cannot steal enemy card since the player's row is full.")));
                                    }

                                }
                            } else out.add(objectMapper.valueToTree(doCommand.getEnvironmentError("useEnvironmentCard", action.getHandIdx(), action.getAffectedRow(), "Chosen row does not belong to the enemy.")));
                        } else out.add(objectMapper.valueToTree(doCommand.getEnvironmentError("useEnvironmentCard", action.getHandIdx(), action.getAffectedRow(), "Not enough mana to use environment card.")));
                    } else out.add(objectMapper.valueToTree(doCommand.getEnvironmentError("useEnvironmentCard", action.getHandIdx(), action.getAffectedRow(), "Chosen card is not of type environment.")));
                }
            }
            if(command.equals("getFrozenCardsOnTable"))
            {
                out.add(objectMapper.valueToTree(doCommand.getFrozenCardsOnTable(table)));
            }
            if(command.equals("cardUsesAbility"))
            {   int coordY, coordX, coordDefY, coorddDefX;
                coordY = action.getCardAttacker().getY();
                coordX =( 3 - action.getCardAttacker().getX());
                coordDefY = action.getCardAttacked().getY();
                coorddDefX = (3 - action.getCardAttacked().getX());
                if(coordY<table.getTable().get(coordX).size()||coordDefY<table.getTable().get(coorddDefX).size()) {
                    if (( table.getTable().get(coordX).get(coordY).getFrozen()) == 0 ) {
                        if ( (table.getTable().get(coordX).get(coordY)).getAttacked() == 0 ) {
                            minionAbility doAbility=new minionAbility();
                            if ( playerTurn == 1 ) {
                                cardTable chosencard = table.getTable().get(coordX).get(coordY);
                                if ( chosencard.getName().equals("The Ripper") || chosencard.getName().equals("Miraj") || chosencard.getName().equals("The Cursed One") )
                                {if (coorddDefX== 2 || coorddDefX == 3 )
                                    {
                                        int cnt = 0;
                                        for ( int i = 0; i < table.getTable().get(2).size(); i++ ) {
                                            if ( table.getTable().get(2).get(i).getName().equals("Goliath")||table.getTable().get(2).get(i).getName().equals("Warden"))
                                                cnt++;
                                        }
                                        if ( cnt >= 1 ) {
                                            if ( table.getTable().get(coorddDefX).get(coordDefY).getName().equals("Goliath")||table.getTable().get(coorddDefX).get(coordDefY).getName().equals("Warden")) {

                                                cardTable attCard = table.getTable().get(coordX).get(coordY);
                                                cardTable defCard = table.getTable().get(coorddDefX).get(coordDefY);
                                                doAbility.ability(attCard, defCard);
                                                if ( defCard.getHealth() <1 ) {
                                                    table.getTable().get(coorddDefX).remove(coordDefY);
                                                    randul2--;
                                                }
                                                attCard.setAttacked(1);
                                            } else
                                                out.add(objectMapper.valueToTree(doCommand.getAbilityErrors( action.getCardAttacker(), action.getCardAttacked(),"Attacked card is not of type 'Tank'.")));
                                        }
                                        else {
                                            cardTable attCard = (table.getTable().get(coordX).get(coordY));
                                            cardTable defCard = (table.getTable().get(coorddDefX).get(coordDefY));
                                            doAbility.ability(attCard, defCard);
                                            if ( defCard.getHealth() <1 ) {
                                                table.getTable().get(coorddDefX).remove(coordDefY);
                                                randul3--;
                                            }
                                            attCard.setAttacked(1);
                                        }

                                }else
                                    out.add(objectMapper.valueToTree((doCommand.getAbilityErrors( action.getCardAttacker(), action.getCardAttacked(), "Attacked card does not belong to the enemy."))));
                                }
                                if(chosencard.getName().equals("Disciple"))
                                {if (  coorddDefX == 0 || coorddDefX == 1 )
                                {   cardTable attCard = table.getTable().get(coordX).get(coordY);
                                    cardTable defCard = table.getTable().get(coorddDefX).get(coordDefY);

                                    doAbility.ability(attCard,defCard);
                                    attCard.setAttacked(1);
                                }else
                                    out.add(objectMapper.valueToTree((doCommand.getAbilityErrors( action.getCardAttacker(), action.getCardAttacked(), "Attacked card does not belong to the current player."))));
                                }
                                }
                            else //player 2
                            {
                                {
                                    cardTable chosencard = table.getTable().get(coordX).get(coordY);
                                    if ( chosencard.getName().equals("The Ripper") || chosencard.getName().equals("Miraj") || chosencard.getName().equals("The Cursed One") )
                                    {if ( coorddDefX == 1 || coorddDefX == 0 ) {
                                        {
                                            int cnt = 0;
                                            for ( int i = 0; i < table.getTable().get(1).size(); i++ ) {
                                                if ( table.getTable().get(1).get(i).getName().equals("Goliath")||table.getTable().get(1).get(i).getName().equals("Warden"))
                                                    cnt++;
                                            }
                                            if ( cnt >= 1 ) {
                                                if ( table.getTable().get(coorddDefX).get(coordDefY).getName().equals("Goliath")||table.getTable().get(coorddDefX).get(coordDefY).getName().equals("Warden")) {

                                                    cardTable attCard = table.getTable().get(coordX).get(coordY);
                                                    cardTable defCard = table.getTable().get(coorddDefX).get(coordDefY);
                                                    doAbility.ability(attCard, defCard);

                                                    if ( defCard.getHealth() <1 ) {
                                                        table.getTable().get(coorddDefX).remove(coorddDefX);
                                                        randul1--;
                                                    }

                                                    attCard.setAttacked(1);
                                                } else
                                                    out.add(objectMapper.valueToTree(doCommand.getAbilityErrors( action.getCardAttacker(), action.getCardAttacked(),"Attacked card is not of type 'Tank'.")));
                                            }
                                            else {
                                                cardTable attCard = (table.getTable().get(coordX).get(coordY));
                                                cardTable defCard = (table.getTable().get(coorddDefX).get(coordDefY));
                                                doAbility.ability(attCard, defCard);
                                                if ( defCard.getHealth() < 1) {
                                                    table.getTable().get(coorddDefX).remove(coorddDefX);
                                                    randul0--;
                                                }
                                                attCard.setAttacked(1);
                                            }
                                        }
                                    }else
                                        out.add(objectMapper.valueToTree((doCommand.getAbilityErrors( action.getCardAttacker(), action.getCardAttacked(), "Attacked card does not belong to the enemy."))));
                                    }
                                    if(chosencard.getName().equals("Disciple"))
                                    {if ( coorddDefX == 2 || coorddDefX == 3 )
                                    {   cardTable attCard = table.getTable().get(coordX).get(coordY);
                                        cardTable defCard = table.getTable().get(coorddDefX).get(coordDefY);

                                        doAbility.ability(attCard,defCard);
                                        attCard.setAttacked(1);
                                    }else
                                        out.add(objectMapper.valueToTree((doCommand.getAbilityErrors( action.getCardAttacker(), action.getCardAttacked(), "Attacked card does not belong to the current player."))));
                                    }
                                }
                            }
                        } else out.add(objectMapper.valueToTree((doCommand.getAbilityErrors( action.getCardAttacker(), action.getCardAttacked(), "Attacker card has already attacked this turn."))));

                    } else out.add(objectMapper.valueToTree((doCommand.getAbilityErrors( action.getCardAttacker(), action.getCardAttacked(), "Attacker card is frozen."))));

                }else  out.add(objectMapper.valueToTree((doCommand.getAbilityErrors(action.getCardAttacker(),action.getCardAttacked(), "Attacked card does not belong to the enemy."))));
                        }
            if(command.equals("useAttackHero"))
            {   int coordY, coordX;
                coordY = action.getCardAttacker().getY();
                coordX =( 3 - action.getCardAttacker().getX());
                if (( table.getTable().get(coordX).get(coordY).getFrozen()) == 0 ) {
                    if ( (table.getTable().get(coordX).get(coordY)).getAttacked() == 0 ) {
                cardTable chosen=table.getTable().get(3-action.getCardAttacker().getX()).get(action.getCardAttacker().getY());
                if(playerTurn==1)
                { int cnt=0;
                    for ( int i = 0; i < table.getTable().get(2).size(); i++ ) {
                    if ( table.getTable().get(2).get(i).getName().equals("Goliath")||table.getTable().get(2).get(i).getName().equals("Warden"))
                    cnt++;}
                if(cnt>0)
                    {out.add(objectMapper.valueToTree(doCommand.HeroAttackError( action.getCardAttacker(),"Attacked card is not of type 'Tank'.")));}
                else {
                    p2.getChampion().setHealth(p2.getChampion().getHealth() - chosen.getAttackDamage());
                    chosen.setAttacked(1);
                    if ( p2.getChampion().getHealth() < 1 ) {
                        Statistics.setPlayerOneWins(Statistics.getPlayerOneWins()+1);
                        out.add(objectMapper.valueToTree(doCommand.getEnd("Player one killed the enemy hero.")));
                    }
                }
                }
                else if(playerTurn==2)
                {int cnt=0;
                    for ( int i = 0; i < table.getTable().get(1).size(); i++ ) {
                    if ( table.getTable().get(1).get(i).getName().equals("Goliath")||table.getTable().get(1).get(i).getName().equals("Warden"))
                    cnt++;}
                    if(cnt>0)
                    {out.add(objectMapper.valueToTree(doCommand.HeroAttackError( action.getCardAttacker(),"Attacked card is not of type 'Tank'.")));}
                    else {
                        p1.getChampion().setHealth(p1.getChampion().getHealth() - chosen.getAttackDamage());
                        chosen.setAttacked(1);
                        if ( p1.getChampion().getHealth() < 1 ) {
                            Statistics.setPlayerTwoWins(Statistics.getPlayerTwoWins()+1);
                            out.add(objectMapper.valueToTree(doCommand.getEnd("Player two killed the enemy hero.")));
                        }
                    }
                }
                    } else out.add(objectMapper.valueToTree((doCommand.HeroAttackError(action.getCardAttacker(), "Attacker card has already attacked this turn."))));

                } else out.add(objectMapper.valueToTree((doCommand.HeroAttackError( action.getCardAttacker(), "Attacker card is frozen."))));
            }
            if(command.equals("useHeroAbility"))
            {  int Go=0;
                heroAbilities ability=new heroAbilities();
                ArrayList<cardTable> row=table.getTable().get(3-action.getAffectedRow());
                int rowNr=(3- action.getAffectedRow());
                if(playerTurn==1){
                    if(p1.getMana()>=p1.getChampion().getMana()) {
                        if ( p1ChampAt == 0 ){
                            if(p1.getChampion().getName().equals("Lord Royce")||p1.getChampion().getName().equals("Empress Thorina"))
                            {if(rowNr==2||rowNr==3)
                                Go++;
                            else out.add(objectMapper.valueToTree(doCommand.getchampionAbilityError( action.getAffectedRow(),"Selected row does not belong to the enemy.")));}
                            if(p1.getChampion().getName().equals("General Kocioraw")||p1.getChampion().getName().equals("King Mudface")) {
                                if ( rowNr == 1 || rowNr == 0 )
                                    Go++;
                                else
                                    out.add(objectMapper.valueToTree(doCommand.getchampionAbilityError( action.getAffectedRow(), "Selected row does not belong to the current player.")));
                            }
                            if(Go==1)
                            {  ability.ability(p1.getChampion(), row);
                        p1.setMana(p1.getMana() - p1.getChampion().getMana());
                        p1ChampAt++;
                        if ( (3 - action.getAffectedRow()) == 0 ) {
                            randul0--;
                        } else {
                            randul1--;
                        }
                    }
                        }else out.add(objectMapper.valueToTree(doCommand.getchampionAbilityError(action.getAffectedRow(),"Hero has already attacked this turn.")));
                    }else out.add(objectMapper.valueToTree(doCommand.getchampionAbilityError(action.getAffectedRow(),"Not enough mana to use hero's ability.")));
                }
                else//p2
                {
                    if(p2.getMana()>=p2.getChampion().getMana()) {
                    if ( p2ChampAt == 0 ){
                        if(p2.getChampion().getName().equals("Lord Royce")||p2.getChampion().getName().equals("Empress Thorina"))
                        if(rowNr==1||rowNr==0)
                            Go++;
                        else out.add(objectMapper.valueToTree(doCommand.getchampionAbilityError( action.getAffectedRow(),"Selected row does not belong to the enemy.")));
                        if(p2.getChampion().getName().equals("General Kocioraw")||p2.getChampion().getName().equals("King Mudface"))
                        if(rowNr==3||rowNr==2)
                            Go++;
                        else out.add(objectMapper.valueToTree(doCommand.getchampionAbilityError( action.getAffectedRow(),"Selected row does not belong to the current player.")));
                        if(Go==1) {
                            ability.ability(p2.getChampion(), row);
                            p2.setMana(p2.getMana() - p2.getChampion().getMana());
                            p2ChampAt++;
                            if ( (3 - action.getAffectedRow()) == 2 ) {
                                randul2--;
                            } else {
                                randul3--;
                            }
                        } }else out.add(objectMapper.valueToTree(doCommand.getchampionAbilityError(action.getAffectedRow(),"Hero has already attacked this turn.")));
                }else out.add(objectMapper.valueToTree(doCommand.getchampionAbilityError( action.getAffectedRow(),"Not enough mana to use hero's ability.")));
                }
            }
            if(command.equals("getPlayerOneWins"))
            {
                out.add(objectMapper.valueToTree(doCommand.GetPlayerOneWins(Statistics.getPlayerOneWins())));
            }
            if(command.equals("getPlayerTwoWins"))
            {
                out.add(objectMapper.valueToTree(doCommand.GetPlayerTwoWins(Statistics.getPlayerTwoWins())));
            }
            if(command.equals("getTotalGamesPlayed"))
            {
                out.add(objectMapper.valueToTree(doCommand.GetGames(Statistics.getGames())));
            }
            }

        //return out;
    }
}

//class Round{
//    static public void next(player p1,player p2)
//    {   round++;
//
//    }
//}
