package Oracle;

import Object.VIPInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OraVIP {
    Omanager manager;
    Connection con;

    public OraVIP() {
        manager = Omanager.getInstance();
        con = manager.getConnection();
    }

    public List<VIPInfo> getVIP() {
        List<VIPInfo> vip = new ArrayList<>();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from VIP");

            while(rs.next()) {
                int id = rs.getInt("id");
              //  String gname = rs.getString("gname");
             //   Date birthday = rs.getDate("birthday");
               // int phone = rs.getInt("phone_num");
              //  int credit = rs.getInt("credit_card_num");
                int points = rs.getInt("points");

                VIPInfo vipInfo = new VIPInfo(id,points);
                vip.add(vipInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vip;
    }

    public VIPInfo getVipWithID(int id) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from VIP where id = " + id);

            if(rs.next()) {
               // String gname = rs.getString("gname");
              //  Date birthday = rs.getDate("birthday");
               // int phone = rs.getInt("phone_num");
               // int credit = rs.getInt("credit_card_num");
                int points = rs.getInt("points");

                return new VIPInfo(id, points);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteVIP(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE from VIP WHERE ID = " + id);
            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateVIP( int id , double points) {
        manager.getConnection();
        int rowCount = manager.execute("UPDATE VIP SET points = "
                + points
                + " WHERE id = "
                + id);
        manager.disconnect();
        if (rowCount == 1)
            return true;
        else
            return false;
    }

}