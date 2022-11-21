package Player;

import java.util.ArrayList;

public class cardTable {
    private int frozen=0;
    private int attacked=0;
    private int attackDamage;
    private int health;
    private int mana;
    private String description;
    private ArrayList <String> colors;
    private String name;

    public int getMana() {
        return mana;
    }

    public void setMana(final int mana) {
        this.mana = mana;
    }


    public void setHealth(final int health) {
        this.health = health;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAttackDamage() {

        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {

        this.attackDamage = attackDamage;
    }

    public int getHealth() {
        return health;
    }


    public int getFrozen() {
        return frozen;
    }


    public void setFrozen(int frozen) {
        this.frozen = frozen;
    }


    public int getAttacked() {
        return attacked;
    }


    public void setAttacked(int attacked) {
        this.attacked = attacked;
    }


    public cardTable(cardMinion card) {
        this.setMana(card.getMana());
        this.setDescription(card.getDescription());
        this.setColors(card.getColors());
        this.setName(card.getName());
        this.setAttackDamage(card.getAttackDamage());
        this.setHealth(card.getHealth());
        this.frozen=0;
        this.attacked=0;
    }

    @Override
    public String toString() {
        return "frozen=" + frozen +
                ", attacked=" + attacked +
                ", attackDamage=" + attackDamage +
                ", health=" + health +
                ", mana=" + mana +
                ", description='" + description + '\'' +
                ", colors=" + colors +
                ", name='" + name + '\'' +
                '}';
    }
}


