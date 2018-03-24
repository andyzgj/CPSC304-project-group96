package Object;


import java.sql.Date;

public class Includes_MealInfo {


    int reserve_num;
    double price;
    String name;
    Date time;
    public  Includes_MealInfo(int reserve_num, double price, String name, Date time){
        this.reserve_num = reserve_num;
        this.price = price;
        this.name = name;
        this.time = time;
    }

    public int getReserve_num(){return reserve_num;}

    public double getPrice(){return price;}

    public String getName(){return name;}

    public Date time(){return time;}

}
