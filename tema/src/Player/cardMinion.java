package Player;

import fileio.CardInput;

import java.util.ArrayList;

public class cardMinion extends card{
    private int attackDamage;
    private int health;

    public int getAttackDamage() {

        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {

        this.attackDamage = attackDamage;
    }

    public int getHealth() {
        return health;
    }


    public void setHealth(int health) {
        this.health = health;
    }
    public cardMinion(CardInput card) {
        this.setMana(card.getMana());
        this.attackDamage = card.getAttackDamage();
        this.health = card.getHealth();
        this.setDescription(card.getDescription());
        this.setColors(card.getColors());
        this.setName(card.getName());
    }
    public cardMinion(cardTable card) {
        this.setMana(card.getMana());
        this.attackDamage = card.getAttackDamage();
        this.health = card.getHealth();
        this.setDescription(card.getDescription());
        this.setColors(card.getColors());
        this.setName(card.getName());
    }
}
