package Oracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OraIncludes_Meal {
    Omanager manager;
    Connection con;

    public OraIncludes_Meal(){
        manager = Omanager.getInstance();
        con = manager.getConnection();
    }

    public List<Object.Includes_MealInfo> getMeal() {
        List<Object.Includes_MealInfo> meal = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Includes_Meal");

            while (rs.next()) {
                int reserve_num = rs.getInt("reserve_num");
                double price = rs.getDouble("price");
                String name = rs.getString("mname");
                Date time = rs.getDate("time");

                Object.Includes_MealInfo imi = new Object.Includes_MealInfo(reserve_num,price,name,time);
                meal.add(imi);
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meal;
    }

    public void InsertMeal(int reserve_num, double price, String name, Date time) {
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO Make_Reservation VALUES (?,?,?,?)");
            ps.setInt(1, reserve_num);
            ps.setDouble(2, price);
            ps.setString(3, name);
            ps.setDate(4,time);

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

    public boolean deleteMeal(int reserve_num, double price, String name, Date time) {
        manager.getConnection();
        int rowCount = manager.execute("DELETE from Includes_Meal WHERE reserve_num = " + reserve_num);
        manager.disconnect();
        if (rowCount == 1)
            return true;
        else
            return false;
    }

}