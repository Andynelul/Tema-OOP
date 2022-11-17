package Player;

public class PlayerMana {
    String command;
    int playerIdx;
    int output;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public int getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(int playerIdx) {
        this.playerIdx = playerIdx;
    }

    public PlayerMana(String command, int playerIdx, int output) {
        this.command = command;
        this.playerIdx = playerIdx;
        this.output = output;
    }
}
