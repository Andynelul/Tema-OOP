package Player;

import java.util.ArrayList;

public class PlayerChampion {
    String command ;
    int PlayerIdx;
    cardChampion output;

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

    public cardChampion getOutput() {
        return output;
    }

    public void setOutput(cardChampion output) {
        this.output = output;
    }

    public PlayerChampion(String command, int playerIdx, cardChampion output) {
        this.command = command;
        PlayerIdx = playerIdx;
        this.output = output;
    }
}
