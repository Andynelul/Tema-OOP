package Player;

import Systems.Table;

import java.util.ArrayList;

public class TableDeck {
    String command;
    ArrayList<ArrayList<cardMinion>> output =new ArrayList<>(4);

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ArrayList<ArrayList <cardMinion>> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<ArrayList <cardMinion>> output) {
        this.output = output;
    }
    public ArrayList<ArrayList<cardMinion>> outputgetic(ArrayList<ArrayList<cardTable>> cards ){
        ArrayList<ArrayList<cardMinion>> outputic =new ArrayList<>(4);
        for(int i=0;i<cards.size();i++)
        {   outputic.add(new ArrayList <cardMinion>(5));
            for(int j=0;j<cards.get(i).size();j++)
            {
                outputic.get(i).add(new cardMinion(cards.get(i).get(j)));
            }
        }
        return outputic;
    }
    public TableDeck(String command, Table table) {
        this.command = command;
       for(int i=3;i>=0;i--)
        {  ArrayList<ArrayList<cardMinion>> outputic=outputgetic(table.getTable());
           output.add(outputic.get(i));
        }
    }
}
