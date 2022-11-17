package Systems;

import Player.card;

import java.util.ArrayList;

public class Table {
    private ArrayList<ArrayList<card>> table=new ArrayList<ArrayList<card>>(4);

    public Table(){
        for(int i=0;i<4;i++)
           table.add(new ArrayList <card>(5));
          // { for ( int j = 0; j < table.get(i).size(); j++ )
   //             table.get(i).add(new card() );
 //  }
        //}
    }

    public ArrayList <ArrayList <card>> getTable() {
        return table;
    }

    public void setTable(ArrayList <ArrayList <card>> table) {
        this.table = table;
    }
}
