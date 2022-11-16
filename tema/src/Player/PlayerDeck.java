package Player;

import java.util.ArrayList;

public class PlayerDeck {
    String command ;
    int PlayerIdx;
    ArrayList<card> output;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getPlayerIdx() {
        return PlayerIdx;
    }

    public void setPlayerIdx(int playerIdx) {
        PlayerIdx = playerIdx;
    }

    public ArrayList<card> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<card> output) {
        this.output = output;
    }

    public PlayerDeck(String command, int playerIdx, ArrayList<card> output) {
        this.command = command;
        this.PlayerIdx = playerIdx;
        this.output = output;
    }

    @Override
    public String toString() {
        return "PlayerDeck{" +
                "command='" + command + '\'' +
                ", PlayerIdx=" + PlayerIdx +
                ", output=" + output +
                '}';
    }
}
