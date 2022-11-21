package Player;

import fileio.Coordinates;

public class AttackHeroError {
    String command;
    Coordinates cardAttacker;
    String error;

    public AttackHeroError(String command, Coordinates cardAttacker, String error) {
        this.command = command;
        this.cardAttacker = cardAttacker;
        this.error = error;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Coordinates getCardAttacker() {
        return cardAttacker;
    }

    public void setCardAttacker(Coordinates cardAttacker) {
        this.cardAttacker = cardAttacker;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
