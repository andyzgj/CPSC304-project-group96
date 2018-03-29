package Oracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Object.Parking_SpraceInfo;
public class OraParking_Space {

    Omanager manager;
    Connection con;

    public OraParking_Space(){
        manager = Omanager.getInstance();
        con = manager.getConnection();
    }

    public List<Parking_SpraceInfo> getParking() {
        List<Parking_SpraceInfo> parking = new ArrayList<>();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Parking_Space");

            while(rs.next()) {
                int stall_num = rs.getInt("stall_num");
                String plate_num = rs.getString("plate_num");

                Parking_SpraceInfo pk = new Parking_SpraceInfo(plate_num, stall_num);
                parking.add(pk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parking;
    }

    public Parking_SpraceInfo getParkingWithStallNum(int stall_num) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Parking_Space where stall_num = " + stall_num);

            if(rs.next()) {
                String plate_num = rs.getString("plate_num");
                return new Parking_SpraceInfo(plate_num, stall_num);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteParkingSpace(int stall_num) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE from Parking_Space WHERE stall_num = " + stall_num);
            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateParkingInfo(int stall_num, String plate_num) {
        manager.getConnection();
        int rowCount = manager.execute("UPDATE Parking_Space SET plate_num = '"
                + plate_num
                + "' WHERE stall_num = "
                + stall_num);
        manager.disconnect();
        if (rowCount == 1)
            return true;
        else
            return false;
    }

    public Parking_SpraceInfo getParkingInfoWithReserveNum(int reserve_num){
        Parking_SpraceInfo ps = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select s.stall_num, s.plate_num from Parking_Space s, Provides p where s.stall_num = p.stall_num and reserve_num = " + reserve_num);

            while(rs.next()) {
                int stall_num = rs.getInt("stall_num");
                String plate_num = rs.getString("plate_num");


                ps = new Parking_SpraceInfo(plate_num,stall_num);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;

    }



}
