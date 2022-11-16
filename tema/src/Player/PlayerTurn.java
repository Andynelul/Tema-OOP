package Player;


public class PlayerTurn {
    String command;
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

    public PlayerTurn(String command, int output) {
        this.command = command;
        this.output = output;
    }
}
