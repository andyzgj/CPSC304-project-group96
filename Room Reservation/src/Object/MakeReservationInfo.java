package Object;

import com.sun.xml.internal.bind.v2.model.core.ID;

public class MakeReservationInfo {

    int reserve_num;
    int number_of_guest;
    String staying_period;
    double discount;
    int ID;

    public MakeReservationInfo(int reserve_num, int number_of_guest, String staying_period, double discount, int ID){
        this.reserve_num = reserve_num;
        this.number_of_guest = number_of_guest;
        this.staying_period = staying_period;
        this.discount = discount;
        this.ID = ID;
    }

    public int getReserve_num(){return reserve_num;}

    public int getNumber_of_guest(){return number_of_guest;}

    public String getStaying_period(){return staying_period;}

    public double getDiscount(){return discount;}

    public int getID(){return ID;}


}
