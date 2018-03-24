package Object;

import java.sql.Date;

public class VIPInfo extends GuestInfo{

    int id;
    int points;

    public VIPInfo(int id, String name, Date birthday, int phone_num, int credit_card_num){
        //todo
    }


     public int getID(){return id;}

     public int getPoints(){return points;}

}
