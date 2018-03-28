package Object;




public class Includes_MealInfo {


    int reserve_num;
    double price;
    String name;
    public  Includes_MealInfo(int reserve_num, double price, String name){
        this.reserve_num = reserve_num;
        this.price = price;
        this.name = name;
    }

    public int getReserve_num(){return reserve_num;}

    public double getPrice(){return price;}

    public String getName(){return name;}

}
