package Player;

import fileio.CardInput;

public class cardChampion extends card{
    private int health=30;
    public cardChampion(CardInput card) {
        super(card);
    }

    public int getHealth() {
        return health;
    }
}
