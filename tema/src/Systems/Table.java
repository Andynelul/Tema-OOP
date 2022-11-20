package Systems;

import Player.card;
import Player.cardTable;

import java.util.ArrayList;

public class Table {
    private ArrayList<ArrayList<cardTable>> table=new ArrayList<ArrayList<cardTable>>(4);

    public Table(){
        for(int i=0;i<4;i++)
           table.add(new ArrayList <cardTable>(5));
          // { for ( int j = 0; j < table.get(i).size(); j++ )
   //             table.get(i).add(new card() );
 //  }
        //}
    }

    public ArrayList <ArrayList <cardTable>> getTable() {
        return table;
    }

    public void setTable(ArrayList <ArrayList <cardTable>> table) {
        this.table = table;
    }
public void tableUnfrozep1(){
        for(int i=0;i<2;i++)
        {
            for(int j=0;j< getTable().get(i).size();j++)
            {
                if( getTable().get(i).get(j) instanceof cardTable )
                {
                    ( this.getTable().get(i).get(j)).setFrozen(0);
                    (this.getTable().get(i).get(j)).setAttacked(0);
                }
            }
        }
    }
    public void tableUnfrozep2(){
        for(int i=2;i<4;i++)
        {
            for(int j=0;j< getTable().get(i).size();j++)
            {
                if( getTable().get(i).get(j) instanceof cardTable )
                {
                    ( this.getTable().get(i).get(j)).setFrozen(0);
                    (this.getTable().get(i).get(j)).setAttacked(0);
                }
            }
        }
    }
}
