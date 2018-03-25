package Oracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OraBooked_At {
    Omanager manager;
    Connection con;

    public OraBooked_At() {
        manager = Omanager.getInstance();
        con = manager.getConnection();
    }

    //get booking information
    public List<Object.Booked_AtInfo> getBooked_At() {
        List<Object.Booked_AtInfo> booklist = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from BOOKED_AT");

            while (rs.next()) {
                int room_num = rs.getInt("room_num");
                int reserve_num = rs.getInt("reserve_num");

                Object.Booked_AtInfo bai = new Object.Booked_AtInfo(room_num,reserve_num);
                booklist.add(bai);
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booklist;
    }

    //insert booking information
    public void InsertBook_At(int room_num,int reserve_num) {
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO BOOKED_AT VALUES (?,?)");
            ps.setInt(1, room_num);
            ps.setInt(2, reserve_num);

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (SQLException ex1)
        {
            System.out.println("Message: " + ex1.getMessage());
            try
            {
                // undo the insert
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }

    }


}
