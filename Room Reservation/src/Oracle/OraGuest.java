package Oracle;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Object.GuestInfo;

public class OraGuest {

        Random rand;
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
                    int id = rs.getInt("id");
                    String name = rs.getString("gname");
                    Date birthday = rs.getDate("birthday");
                    int phone = rs.getInt("phone_num");
                    int credit = rs.getInt("credit_card_num");

                    GuestInfo gi = new GuestInfo(id, name, birthday, phone, credit);
                    guests.add(gi);
                }

            st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return guests;
        }

        public void InsertGuest(int id , String name , Date birthday, int phone, int credit ) {
            PreparedStatement ps;
            rand = new Random();
            try {
                ps = c.prepareStatement("INSERT INTO Guest VALUES (?,?,?,?,?)");
                ps.setInt(1, id);

                ps.setString(2, name);

                ps.setDate(3, birthday);

                ps.setInt(4, phone);

                if (credit == 0) {
                    ps.setNull(5, java.sql.Types.INTEGER);
                } else {
                    ps.setInt(5, credit);
                }

                ps.executeUpdate();
                c.commit();
                ps.close();
            }
            catch (SQLException ex)
            {
                System.out.println("Message: " + ex.getMessage());
                try
                {
                    // undo the insert
                    c.rollback();
                }
                catch (SQLException ex2)
                {
                    System.out.println("Message: " + ex2.getMessage());
                    System.exit(-1);
                }
            }

        }

        public boolean isValidID(int id) {
        try {
            Statement st = c.createStatement();
            String query = "select 1 from Guest where ID = " + id;
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) return false; //when there are no more guest id in the result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isValidPhoneNumber(int phone_num) {
        try {
            Statement st = c.createStatement();
            String query = "select 1 from Guest where phone_num = " + phone_num;
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) return false; //when there are no more guest id in the result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

        public boolean updateGuestInfo( int id , String name , Date birthday, String phone, int credit_card_num) {
            manager.getConnection();
            int rowCount = manager.execute("UPDATE Guest SET gname = "
                    + name
                    + " , birthday = "
                    + birthday
                    + ", phone = "
                    + phone
                    + ", credit_card_num = "
                    + credit_card_num
                    + " WHERE id = "
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
                int phone_num = rs.getInt("phone_num");
                int credit = rs.getInt("credit_card_num");
                gi = new GuestInfo(id,name,birthday,phone_num, credit);

            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gi;
    }

    public GuestInfo getGuestByPhoneNumber(int phone_num) {
        GuestInfo gi = null;
        try {
            Statement st = c.createStatement();
            String query = "select * from Guest where phone_num = " + phone_num;
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("gname");
                Date birthday = rs.getDate("birthday");
                int credit = rs.getInt("credit_card_num");
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