package Oracle;

import Object.EmployeeInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class OraEmployee {

    Omanager manager;
    Connection con;
    Random rand;

    public OraEmployee() {
        manager = Omanager.getInstance();
        con = manager.getConnection();
    }

    public List<EmployeeInfo> getEmployees() {
        List<EmployeeInfo> employees = new ArrayList<>();

        try {
            Statement st = con.createStatement(); //A Statement is an interface that represents a SQL statement
            ResultSet rs = st.executeQuery("select * from Employee"); //rs is a table of data representing a database result set

            while(rs.next()) {
                String ename = rs.getString("employee_name");
                int eid = rs.getInt("employee_id");
                int phone_num = rs.getInt("phone_number");

                EmployeeInfo employeeInfo = new EmployeeInfo(ename,eid,phone_num);
                employees.add(employeeInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    //insert a new employee information
    public void insertEmployee(String ename, int phone_num) {
        rand = new Random();
        int eid = rand.nextInt(9999); //randomly generate a number between 0 and 9999
        if (!isValidEID(eid)) {
            try {
                PreparedStatement ps = con.prepareStatement("insert into Employee values (?,?,?,?,?)");
                ps.setInt(1,eid);
                ps.setString(2, ename);
                ps.setInt(3, phone_num);
                ps.executeUpdate();
                con.commit();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else insertEmployee(ename,phone_num);
    }


    public boolean isValidEID(int eid) {
        try {
            Statement st = con.createStatement();
            String query = "select 1 from Employee where eid = " + eid;
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) return false; //when there are no more employee id in the result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int getEID(String ename) {
        int eid = 0;
        try {
            Statement st = con.createStatement();
            String query = "select eid from Employee where ename = '" + ename + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) eid = rs.getInt("eid");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eid;
    }




    public void deleteEmployeeInfo(int eid) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from Employee where eid = " + eid);
            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

