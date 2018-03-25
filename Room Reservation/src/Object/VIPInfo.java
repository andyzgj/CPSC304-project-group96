package Object;

import java.sql.Date;

public class VIPInfo{

    int id;
    int points;

   // public VIPInfo(int id, String name, Date birthday, int phone_num, int credit_card_num, int points){
      //  super(id,name,birthday,phone_num,credit_card_num);
    //    this.points =  points;
  //  }
   public VIPInfo(int id, int points) {
       this.id = id;
       this.points = points;
   }

     public int getID(){return id;}

     public int getPoints(){return points;}

}
