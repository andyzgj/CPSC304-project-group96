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
                Date start_date = rs.getDate("start_date");
                Date end_date = rs.getDate("end_date");
                double discount = rs.getDouble("discount");
                int ID = rs.getInt("ID");

                MakeReservationInfo gi = new MakeReservationInfo(reserve_num, number_of_guest, start_date, end_date, discount, ID);
                reservation.add(gi);
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    public void InsertReservation(int number_of_guest, Date start_date, Date end_date, double discount, int ID) {
        PreparedStatement ps;

        try {
            int reserve_num = generateReserveNum();
            ps = con.prepareStatement("INSERT INTO Make_Reservation VALUES (?,?,?,?,?,?)");
            ps.setInt(1, reserve_num);

            ps.setInt(2, number_of_guest);
            ps.setDate(3, start_date);
            ps.setDate(4, end_date);


            if (discount == 0) {
                ps.setNull(5, Types.DOUBLE);
            } else {
                ps.setDouble(5, discount);
            }

            ps.setInt(6, ID);

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

    public void deleteReservation(int reserve_num) {
            try {
                PreparedStatement ps = con.prepareStatement("DELETE from Make_Reservation WHERE reserve_nun = " + reserve_num);
                ps.executeUpdate();
                con.commit();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public int generateReserveNum() {
        rand = new Random();
        int tid = rand.nextInt(89999) + 10000;
        if(isInValidNum(tid))
            generateReserveNum();
        return tid;
    }

    public boolean isInValidNum(int num) {
        try {
            Statement st = con.createStatement();
            String query = "select * from Make_Reservation where reserve_num = " + num;
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public List<MakeReservationInfo> getReservationWithEmployee(int employee_id) {
        List<MakeReservationInfo> ret = new ArrayList<>();

        try {
            Statement st = con.createStatement();
            String query = "select reserve_num, number_of_guest, start_date, end_date, discount, ID from reserve_with_employee where employee_ID = " + employee_id;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int reserve_num = rs.getInt("reserve_num");
                int number_of_guest = rs.getInt("number_of_guest");
                Date start_date = rs.getDate("start_date");
                Date end_date = rs.getDate("end_date");
                double discount = rs.getDouble("discount");
                int id = rs.getInt("ID");

                MakeReservationInfo m = new MakeReservationInfo(reserve_num, number_of_guest, start_date, end_date, discount, id);
                ret.add(m);
            }
            dropReservationWithEmployee();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }



    private void createReservationWithEmployee() {
        try {
            Statement st = con.createStatement();
            String query = "create view reserve_with_employee as "
                    + "select Employee.ename, Employee.employee_ID, Employee.phone_num, "
                    + "Make_Reservation.reserve_num, Make_Reservation.number_of_guest, Make_Reservation.start_date,Make_Reservation.end_date, Make_Reservation.discount, Make_Reservation.ID "
                    + "from Employee join Approve on Employee.employee_ID = Approve.employee_ID"
                    + " join Make_Reservation on Make_Reservation.reserve_num = Approve.reserve_num";
            st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropReservationWithEmployee() {
        try {
            Statement st = con.createStatement();
            st.executeQuery("drop view reserve_with_employee");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
