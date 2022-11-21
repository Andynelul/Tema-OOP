package Player;

public class championAbilityError {
    String command;
    int affectedRow;
    String error;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getAffectedRow() {
        return affectedRow;
    }

    public void setAffectedRow(int affectedRow) {
        this.affectedRow = affectedRow;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public championAbilityError(String command, int affectedRow, String error) {
        this.command = command;
        this.affectedRow = affectedRow;
        this.error = error;
    }
}
