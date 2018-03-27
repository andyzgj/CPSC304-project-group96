package Oracle;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Object.GuestInfo;

public class OraGuest {

        Random rand = new Random();;
        Omanager manager;
        Connection c;

        public OraGuest() {
            manager = Omanager.getInstance();
            c = manager.getConnection();
        }

        public List<GuestInfo> getGuest() {
            List<GuestInfo> guests = new ArrayList<>();
            try {
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery("select * from Guest");

                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String name = rs.getString("gname");
                    Date birthday = rs.getDate("birthday");
                    long phone = rs.getLong("phone_num");
                    long credit = rs.getLong("credit_card_num");

                    GuestInfo gi = new GuestInfo(id, name, birthday, phone, credit);
                    guests.add(gi);
                }

            st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return guests;
        }


        public int InsertGuest(String name , Date birthday, long phone, long credit ) {
            PreparedStatement ps;
            int id = generateID();
            try {
                ps = c.prepareStatement("INSERT INTO Guest VALUES (?,?,?,?,?)");
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setDate(3, birthday);
                ps.setLong(4, phone);
                if (credit == 0) {
                    ps.setNull(5, java.sql.Types.INTEGER);
                } else {
                    ps.setLong(5, credit);
                }
                ps.executeUpdate();
                c.commit();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
           return id;
        }

        public int generateID(){

            int id = rand.nextInt(89999999) + 10000000; //randomly generate a number between 0 and 9999
            if (isValidID(id)) {
                return generateID();
            }
            return id;
        }

        public boolean isValidID(int id) {
        try {
            Statement st = c.createStatement();
            String query = "select * from Guest where ID = " + id;
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) return false; //when there are no more guest id in the result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

        public boolean isValidPhoneNumber(long phone_num) {
        try {
            Statement st = c.createStatement();
            String query = "select * from Guest where phone_num = " + phone_num;
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) return false; //when there are no more guest id in the result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

        public boolean updateGuestInfo( int id , String name , Date birthday, long phone, long credit_card_num) {
            manager.getConnection();
            int rowCount = manager.execute("UPDATE Guest SET gname = "
                    + name
                    + " , birthday = "
                    + birthday
                    + ", phone_num = "
                    + phone
                    + ", credit_card_num = "
                    + credit_card_num
                    + " WHERE ID = "
                    + id);
            manager.disconnect();
            if (rowCount == 1)
                return true;
            else
                return false;
        }

        public GuestInfo getGuestById(int id) {
        GuestInfo gi = null;
        try {
            Statement st = c.createStatement();
            String query = "select * from Guest where ID = " + id;
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                String name = rs.getString("gname");
                Date birthday = rs.getDate("birthday");
                long phone_num = rs.getLong("phone_num");
                long credit = rs.getLong("credit_card_num");
                gi = new GuestInfo(id,name,birthday,phone_num, credit);

            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gi;
    }

        public GuestInfo getGuestByPhoneNumber(long phone_num) {
        GuestInfo gi = null;
        try {
            Statement st = c.createStatement();
            String query = "select * from Guest where phone_num = " + phone_num;
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("gname");
                Date birthday = rs.getDate("birthday");
                long credit = rs.getLong("credit_card_num");
                gi = new GuestInfo(id,name,birthday,phone_num, credit);

            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gi;

    }

        public void deleteGuest(int id) {
        try {
            PreparedStatement ps = c.prepareStatement("DELETE from Guest WHERE ID = " + id);
            ps.executeUpdate();
            c.commit();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
