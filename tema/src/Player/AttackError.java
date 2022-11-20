package Player;

import fileio.Coordinates;

public class AttackError {
    String command;
    Coordinates cardAttacker;
    Coordinates cardAttacked;
    String error;

    public AttackError(String command, Coordinates cardAttacker, Coordinates cardAttacked, String error) {
        this.command = command;
        this.cardAttacker = cardAttacker;
        this.cardAttacked = cardAttacked;
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

    public Coordinates getCardAttacked() {
        return cardAttacked;
    }

    public void setCardAttacked(Coordinates cardAttacked) {
        this.cardAttacked = cardAttacked;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
