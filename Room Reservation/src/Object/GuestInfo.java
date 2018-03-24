package Object;


import java.sql.Date;

public class GuestInfo {
    int id;
    String name;
    Date birthday;
    int phone_num;
    int credit_card_num;

    public GuestInfo(int id, String name, Date birthday, int phone_num, int credit_card_num) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phone_num = phone_num;
        this.credit_card_num = credit_card_num;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getPhoneNum(){
        return phone_num;
    }

    public int getCredit_card_num(){
        return phone_num;
    }

}