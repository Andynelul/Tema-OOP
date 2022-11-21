package Player;

import Systems.Table;

import java.util.ArrayList;

public class heroAbilities {
    public void ability(cardChampion att,ArrayList<cardTable> row)
    {
        if (att.getName().equals("Lord Royce"))
        {   int bigAtt=row.get(0).getAttackDamage();
            int cardNum=0;
            for(int i=0;i<row.size();i++)
            {
                if(row.get(i).getAttackDamage()>bigAtt)
                {   cardNum=i;
                    bigAtt=row.get(i).getAttackDamage();
                }
            }
        row.get(cardNum).setFrozen(1);
        }
        if (att.getName().equals("Empress Thorina"))
        {
            int bigHealth=row.get(0).getHealth();
            int cardNum=0;
            for(int i=0;i<row.size();i++)
            {
                if(row.get(i).getHealth()>bigHealth)
                {   cardNum=i;
                    bigHealth=row.get(i).getAttackDamage();
                }
            }
            row.remove(cardNum);
        }
        if(att.getName().equals("King Mudface"))
        {
            for(int i=0;i< row.size();i++)
            {
                row.get(i).setHealth(row.get(i).getHealth()+1);
            }
        }
        if(att.getName().equals("General Kocioraw"))
        {
            for(int i=0;i< row.size();i++)
            {
                row.get(i).setAttackDamage(row.get(i).getAttackDamage()+1);
            }
        }
    }
}
