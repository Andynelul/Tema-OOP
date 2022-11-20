package Player;

import java.util.ArrayList;

public class FrozenCards {
    String command;
    ArrayList<card> output;

    public FrozenCards(String command, ArrayList <card> cards) {
        this.command = command;
        this.output=cards;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ArrayList <card> getOutput() {
        return output;
    }

    public void setOutput(ArrayList <card> output) {
        this.output = output;
    }
}
