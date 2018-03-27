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
                String ename = rs.getString("ename");
                int employee_id = rs.getInt("employee_ID");
                int phone_num = rs.getInt("phone_num");

                EmployeeInfo employeeInfo = new EmployeeInfo(ename,employee_id,phone_num);
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
        int employee_ID = rand.nextInt(9999); //randomly generate a number between 0 and 9999
        if (!isValidEID(employee_ID)) {
            try {
                PreparedStatement ps = con.prepareStatement("insert into Employee values (?,?,?)");
                ps.setInt(1,employee_ID);
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


    public boolean isValidEID(int employee_ID) {
        try {
            Statement st = con.createStatement();
            String query = "select * from Employee where employee_ID = " + employee_ID;
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) return false; //when there are no more employee id in the result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String getEname(int employee_ID) {
            String ename = null;
        try {
            Statement st = con.createStatement();
            String query = "select ename from Employee where employee_ID = " + employee_ID;
            ResultSet rs = st.executeQuery(query);
            if (rs.next())
                ename = rs.getString("ename");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ename;
    }




    public void deleteEmployeeInfo(int employee_ID) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from Employee where employee_ID = " + employee_ID);
            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

