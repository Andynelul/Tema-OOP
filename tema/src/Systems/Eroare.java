package Systems;

public class Eroare {
    String command;
    int handIdx;
    String error;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getHandIdx() {
        return handIdx;
    }

    public void setHandIdx(int handIdx) {
        this.handIdx = handIdx;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Eroare(String command, int handIdx, String error) {
        this.command = command;
        this.handIdx = handIdx;
        this.error = error;
    }
}
