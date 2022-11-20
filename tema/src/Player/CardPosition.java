package Player;

public class CardPosition {
    String command;
    int x;
    int y;
    card output;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public card getOutput() {
        return output;
    }

    public void setOutput(card output) {
        this.output = output;
    }

    public CardPosition(String command, int x, int y, cardTable output) {
        this.command = command;
        this.x = x;
        this.y = y;
        this.output =new cardMinion( output);
    }
}
