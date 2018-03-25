package Oracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Object.MakeReservationInfo;

public class OraMakeReservation {
    Random rand;
    Omanager manager;
    Connection con;

    public OraMakeReservation(){
        manager = Omanager.getInstance();
        con = manager.getConnection();
    }

    public List<MakeReservationInfo> getGuest() {
        List<MakeReservationInfo> reservation = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Make_Reservation");

            while (rs.next()) {
                int reserve_num = rs.getInt("reserve_num");
                int number_of_guest = rs.getInt("number_of_guest");
                String staying_period = rs.getString("staying_period");
                double discount = rs.getDouble("discount");
                int ID = rs.getInt("ID");

                MakeReservationInfo gi = new MakeReservationInfo(reserve_num, number_of_guest, staying_period, discount, ID);
                reservation.add(gi);
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    public void InsertReservation(int number_of_guest, String staying_period , double discount, int ID) {
        PreparedStatement ps;

        try {
            int reserve_num = generateReserveNum();
            ps = con.prepareStatement("INSERT INTO Make_Reservation VALUES (?,?,?,?,?)");
            ps.setInt(1, reserve_num);

            ps.setInt(2, number_of_guest);

            ps.setString(3, staying_period);

            if (discount == 0) {
                ps.setNull(4, Types.DOUBLE);
            } else {
                ps.setDouble(4, discount);
            }

            ps.setInt(5, ID);

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
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

    public boolean deleteReservation(int reserve_num) {
        manager.getConnection();
        int rowCount = manager.execute("DELETE from Make_Reservation WHERE reserve_nun = " + reserve_num);
        manager.disconnect();
        if (rowCount == 1)
            return true;
        else
            return false;
    }

    public int generateReserveNum() {
        rand = new Random();
        int tid = rand.nextInt(99999);
        if(isInValidNum(tid))
            generateReserveNum();
        return tid;
    }

    public boolean isInValidNum(int num) {
        try {
            Statement st = con.createStatement();
            String query = "select 1 from Make_Reservation where reserve_num = " + num;
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /*
    public List<MakeReservationInfo> getTransactionsByEmployee(int employee_id) {
        List<MakeReservationInfo> ret = new ArrayList<>();

        try {
            Statement st = con.createStatement();
            String query = "select tid, tamount, tday, ttime, cid from trans_input_empl where eid = " + eid;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int tid = rs.getInt("tid");
                int tamount = rs.getInt("tamount");
                Date tday = rs.getDate("tday");
                String ttime = rs.getString("ttime");
                int cid = rs.getInt("cid");

                MakeReservationInfo ti = new MakeReservationInfo(tid, tamount, tday, ttime, cid, eid);
                ret.add(ti);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
    */
}
