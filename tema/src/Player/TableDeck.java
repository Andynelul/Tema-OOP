package Player;

import Systems.Table;

import java.util.ArrayList;

public class TableDeck {
    String command;
    ArrayList<ArrayList<card>> output =new ArrayList<>();

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ArrayList<ArrayList <card>> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<ArrayList <card>> output) {
        this.output = output;
    }

    public TableDeck(String command, Table table) {
        this.command = command;
       for(int i=3;i>=0;i--)
        {
          //  output.add(table.getTable().get(i));
            output.add(table.getTable().get(i));
      //  }
    }}
}
