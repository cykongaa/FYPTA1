package hkust.fypta1;

import android.annotation.SuppressLint;
import android.database.CursorJoiner;
import android.database.SQLException;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by kongchingyiii on 5/10/16.
 */

public class ConnectionClass {

    private Connection conn;
    private String ip = "172.17.133.201"; //ip address of server
    private String classes = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String databaseName = "FYPTA1";
    private String destination = null;
    private String userName = "administrator"; // administrator
    private String password = "12345678"; // 12345678

    public ConnectionClass(){
        destination = "jdbc:sqlserver://" + ip + ":1433;DatabaseName=" + databaseName +";instance=SQLSERVER;encrypt=true;TrustServerCertificate=true;";
        Log.d("destination",destination);
    }

    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String usename = "administrator";
            String password = "12345678";
            String destination = "jdbc:jtds:sqlserver://172.17.133.201:1433/FYPTA1;instance=FYPTA1;";

            conn = DriverManager.getConnection(destination, usename, password);
//            conn.executeSQL("SELECT user_name FROM [User] WHERE user_id='U000000001'");
//            ResultSet xre = x.executeQuery();
//            if (xre.next()) {
//                System.out.println("Connect successfully to the database! \n Here is one search result: " +xre.getString("user_name"));
//            }
            conn.setAutoCommit(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Event> executeSQL(String ex) {
        PreparedStatement x = null;
        ArrayList<Event> eventList=new ArrayList<>();
        try {
            x = conn.prepareStatement(ex);
            ResultSet result = x.executeQuery();

            while(result.next()) {
//                    Log.d();
                Log.d("result id",result.getString("event_id"));
                eventList.add(new Event(result.getString("event_id"),result.getString("event_name"), result.getString("event_time"),result.getString("event_organizer"),result.getString("event_address"),result.getString("event_description"),result.getString("event_pic"),result.getString("event_time")));
            }
//            Array array=result.getArray();
            return eventList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                x.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void updateSQL(String ex) {
        PreparedStatement x = null;
        try {
            x = conn.prepareStatement(ex);
            x.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


}
