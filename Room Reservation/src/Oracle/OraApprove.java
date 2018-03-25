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
    public List<Object.ApproveInfo> getApprove() {
        List<Object.ApproveInfo> approvelist = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from APPROVE");

            while (rs.next()) {
                int reserve_num = rs.getInt("reserve_num");
                int employee_ID = rs.getInt("employee_ID");

                Object.ApproveInfo ai = new Object.ApproveInfo(reserve_num,employee_ID);
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
            ps = con.prepareStatement("INSERT INTO APPROVE VALUES (?,?)");
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

}