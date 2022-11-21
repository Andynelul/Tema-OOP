package Player;

public class minionAbility {
    public void ability(cardTable att,cardTable deff)
    {
        if (att.getName().equals("Miraj"))
        {
            Integer health;
            health=new Integer(att.getHealth());
            att.setHealth(deff.getHealth());
            deff.setHealth(health);
        }
        if (att.getName().equals("The Ripper"))
        {
            deff.setAttackDamage(deff.getAttackDamage()-2);
            if(deff.getAttackDamage()<0)
                deff.setAttackDamage(0);
        }
        if(att.getName().equals("The Cursed One"))
        {
            int attack;
            attack= new Integer(deff.getAttackDamage());
            deff.setAttackDamage(deff.getHealth());
            deff.setHealth(attack);
        }
        if(att.getName().equals("Disciple"))
        {
            deff.setHealth(deff.getHealth()+2);
        }
    }
}
