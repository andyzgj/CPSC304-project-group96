package Oracle;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Object.GuestInfo;

public class OraGuest {


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



}
