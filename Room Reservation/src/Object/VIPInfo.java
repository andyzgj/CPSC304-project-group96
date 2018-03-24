package Object;

import java.sql.Date;

public class VIPInfo extends GuestInfo{

    int points;

    public VIPInfo(int id, String name, Date birthday, int phone_num, int credit_card_num, int points){
        super(id,name,birthday,phone_num,credit_card_num);
        this.points =  points;
    }


     public int getPoints(){return points;}

}
