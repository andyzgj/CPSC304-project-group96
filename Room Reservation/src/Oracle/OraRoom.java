package Oracle;

import Object.RoomInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OraRoom {
    Omanager manager;
    Connection con;

    public OraRoom(){
        manager = Omanager.getInstance();
        con = manager.getConnection();
    }

    public List<RoomInfo> getRooms() {
        List<RoomInfo> rooms = new ArrayList<>();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Room");

            while(rs.next()) {
                int room_num = rs.getInt("room_num");
                String type = rs.getString("type");
                double price = rs.getDouble("price");

                RoomInfo r = new RoomInfo(room_num,type,price);
                rooms.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public double getPrice(int room_num) {
        try {
            Statement st = con.createStatement();
            String query = "select price from Room where room_num = " + room_num ;
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<Integer> getNumBySelectType(String type) {
        List<Integer> num = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            String query = "select room_num from Room where type = '" + type +"'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                num.add(rs.getInt("room_num"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    //select type from Room where price < p, and return the corresponding room information
    //reflects select and projection query
    public List<RoomInfo> getRoomInfo(String t,double p) {
        List<RoomInfo> rooms = new ArrayList<>();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select t from Room");

            while(rs.next()) {
                int room_num = rs.getInt("room_num");
                String type = rs.getString("type");
                double price = rs.getDouble("price");

                RoomInfo r = new RoomInfo(room_num,type,price);
                rooms.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    //select room number from Room where price < p, and return the corresponding room information,overload
    //reflects select and projection query
    public List<RoomInfo> getRoomInfo(int n,double p) {
        List<RoomInfo> rooms = new ArrayList<>();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select n from Room");

            while(rs.next()) {
                int room_num = rs.getInt("room_num");
                String type = rs.getString("type");
                double price = rs.getDouble("price");

                RoomInfo r = new RoomInfo(room_num,type,price);
                rooms.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
