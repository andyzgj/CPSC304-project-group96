package Oracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Object.ApproveInfo;

public class OraApprove {
    Omanager manager;
    Connection con;

    public OraApprove() {
        manager = Omanager.getInstance();
        con = manager.getConnection();
    }

    //get information about approval
    public List<ApproveInfo> getApprove() {
        List<ApproveInfo> approvelist = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Approve");

            while (rs.next()) {
                int reserve_num = rs.getInt("reserve_num");
                int employee_ID = rs.getInt("employee_ID");

                ApproveInfo ai = new ApproveInfo(reserve_num,employee_ID);
                approvelist.add(ai);
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return approvelist;
    }

    //insert the approve information with reservation number and corresponding employee ID who is in charge of
    //this reservation
    public void InsertApprove(int reserve_num,int employee_ID) {
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("insert into Approve values (?,?)");
            ps.setInt(1, reserve_num);
            ps.setInt(2, employee_ID);

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

    //update VIP points if VIPs' reservation is approved
    public void VIP_by_employee() {

        try {
            Statement st = con.createStatement();
            String query = "create view VIP_by_employees as "
                    +"select VIP.points,Make_Reservation.price, VIP.ID"
                    +" from VIP join Make_Reservation on VIP.ID = Make_Reservation.ID"
                    +" join Approve on Approve.reserve_num = Make_Reservation.reserve_num";
             st.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean approveOrNot(int reserve_num){

        try {
            Statement st = con.createStatement();
            String query = "select * from Approve where reserve_num = " + reserve_num;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                return true;
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public void update(){
        try{
            OraVIP VIP = new OraVIP();
            Statement st = con.createStatement();
            VIP_by_employee();
            String query = "select * from VIP_by_employee";
            ResultSet rs = st.executeQuery(query);

            double points = rs.getDouble("points");
            double price = rs.getDouble("price");
            int id = rs.getInt("ID");
            points = points + price * 0.1;
            VIP.updateVIP(id,points);
            dropReservationWithEmployee();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    private void dropReservationWithEmployee() {
        try {
            Statement st = con.createStatement();
            st.executeQuery("drop view VIP_by_employees");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getApproveReserveNum(){
        List<Integer> unApprove = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            String query = "select reserve_num from Approve";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                int ai = rs.getInt("reserve_num");;
                unApprove.add(ai);
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unApprove;

    }
    }

    public List<Integer> getUnApproveReserveNUm(){
        List<Integer> unApprove = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            String query = "select m.reserve_num from Make_Reservation m minus select a.reserve_num from Approve a";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                int ai = rs.getInt("reserve_num");;
                unApprove.add(ai);
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unApprove;

    }
}