package Systems;

import Player.*;
import fileio.Coordinates;
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

    public PlayerMana getPlayerMana(int playerIdx, int mana) {
        PlayerMana plr = new PlayerMana("getPlayerMana", playerIdx, mana);
        return plr;
    }

    public TableDeck getCardsOnTable(Table table) {
        TableDeck plr = new TableDeck("getCardsOnTable", table);
        return plr;
    }

    public Eroare getError(String command, int handIdx, String eroare) {
        Eroare err = new Eroare(command, handIdx, eroare);
        return err;
    }

    public CardPosition getCardAtPosition(int x, int y, cardTable card) {
        CardPosition plr = new CardPosition("getCardAtPosition", x, y, card);
        return plr;
    }
    public ErrorPosition getError(int x,int y)
    {
        ErrorPosition plr = new ErrorPosition( "getCardAtPosition",x,y,"No card available at that position.");
        return plr;
    }
    public PlayerDeck getEnvironmentCardsInHand(int playerIdx,ArrayList<card> cards)
    {
      ArrayList<card> environmental=new ArrayList<>();
      for(int i=0;i<cards.size();i++)
      {
          if(cards.get(i).getName().equals("Winterfell")||cards.get(i).getName().equals("Firestorm")||cards.get(i).getName().equals("Heart Hound"))
          {
              environmental.add(cards.get(i));
          }
      }
        PlayerDeck plr= new PlayerDeck("getEnvironmentCardsInHand",playerIdx,environmental);
    return plr;
    }
    public Environmenterror getEnvironmentError(String command,int handIdx,int affectedRow,String error)
    {
        Environmenterror plr=new Environmenterror("useEnvironmentCard", handIdx,affectedRow,error);
        return plr;
    }
    public FrozenCards getFrozenCardsOnTable(Table table)
    {
        ArrayList<card> list =new ArrayList<card>();
        for(int i=0;i<3;i++)
            for(int j=0;j<table.getTable().get(i).size();j++)
            {
                if(table.getTable().get(i).get(j).getFrozen()==1)
                {
                    list.add(new card(table.getTable().get(i).get(j)));

                }
            }
        FrozenCards plr=new FrozenCards("getFrozenCardsOnTable",list);
                return plr;
    }
    public AttackError getAttackErrors(Coordinates Att,Coordinates Def,String error)
    {
        AttackError plr=new AttackError("cardUsesAttack",Att,Def,error);
        return plr;
    }
    public AttackError getAbilityErrors(Coordinates Att,Coordinates Def,String error)
    {
        AttackError plr=new AttackError("cardUsesAbility",Att,Def,error);
        return plr;
    }
    public GameEnd getEnd(String string)
    {
        GameEnd plr=new GameEnd(string);
        return plr;
    }
    public AttackHeroError HeroAttackError(Coordinates att,String error)
    {
        AttackHeroError plr= new AttackHeroError("useAttackHero", att, error);
                return plr;
    }
    public championAbilityError getchampionAbilityError(int affectedRow,String error)
    {
        championAbilityError plr=new championAbilityError("useHeroAbility",affectedRow,error);
        return plr;
    }
    public PlayerTurn GetGames(int games)
    {
        PlayerTurn plr=new PlayerTurn("getTotalGamesPlayed",games);
        return plr;
    }
    public PlayerTurn GetPlayerOneWins(int games)
    {
        PlayerTurn plr=new PlayerTurn("getPlayerOneWins",games);
        return plr;
    }
    public PlayerTurn GetPlayerTwoWins(int games)
    {
        PlayerTurn plr=new PlayerTurn("getPlayerTwoWins",games);
        return plr;
    }
}
