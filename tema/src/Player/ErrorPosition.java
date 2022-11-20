package Player;

public class ErrorPosition {
    String command;
    int x;
    int y;
    String output;

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

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public ErrorPosition(String command, int x, int y, String output) {
        this.command = command;
        this.x = x;
        this.y = y;
        this.output = output;
    }
}
