package Oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Omanager {




        private Connection conn;
        private Statement stmt;

        private static Omanager instance = new Omanager();

        public Omanager() {

        }

        public static Omanager getInstance() {
            return instance;
        }

        public Connection getConnection() {
            if (conn == null) {
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    // Class.forName("oracle.jdbc.driver.OracleDriver");
                    //oracle.jdbc.driver.OracleDriver
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_m1l0b", "a28488154");
                    //stmt = conn.createStatement();
                    System.out.println("Connection succeeded");
                } catch (SQLException e) {
                    e.printStackTrace();
                    troubleshoot();
                }
            }

            return conn;
        }

        private static void troubleshoot(){
            System.out.println("Connection failed");
            System.out.println("Troubleshooting 101:");
            System.out.println("Have you connected to the VPN?");
            System.out.println("Have you tunnelled into the department server?");
            System.out.println("This can be done as follows:");
            System.out.println("ssh -l csid -L localhost:1522:dbhost.ugrad.cs.ubc.ca:1522 thetis.ugrad.cs.ubc.ca");
        }

        //stringForUpdate is the sql command for executing Inserts, Updates, and Deletes in the DBA
        public int execute(String stringForExecute){
            int rowCount = -1;
            try {
                //System.out.println(targetTable + " before execution -->");
                rowCount = stmt.executeUpdate(stringForExecute);
                System.out.println("row " + rowCount + " updated");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(stringForExecute + " : update fails");
            }
            return rowCount;
        }

        public void disconnect(){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("disconnect fails");
            }
        }




}
