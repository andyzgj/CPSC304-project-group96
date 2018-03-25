package Oracle;

import Object.RoomInfo;

import java.sql.*;


public class OraRoom {
    Omanager manager;
    Connection con;

    public OraRoom(){
        manager = Omanager.getInstance();
        con = manager.getConnection();
    }
}
