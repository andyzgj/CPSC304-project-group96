package Object;

public class Booked_At {

    int room_num;
    int reserve_num;

    public Booked_At(int room_num, int reserve_num){
        this.room_num = room_num;
        this.reserve_num = reserve_num;
    }

    public int getRoom_num(){return room_num;}

    public int getReserve_num(){return reserve_num;}

}
