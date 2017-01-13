package hkust.fypta1;

import android.annotation.SuppressLint;
import android.database.SQLException;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by kongchingyiii on 5/10/16.
 */

public class ConnectionClass {

    private Connection conn;
    private String ip = "10.89.121.180"; //ip address of server
    private String classes = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String databaseName = "FYPTA1";
    private String destination = null;
    private String userName = "administrator"; // administrator
    private String password = "12345678"; // 12345678

    public ConnectionClass(){
        destination = "jdbc:sqlserver://" + ip + ":1433;DatabaseName=" + databaseName +";instance=SQLSERVER;integratedSecurity=true;encrypt=true;TrustServerCertificate=true;";
        Log.d("destination",destination);
    }

    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String usename = "administrator";
            String password = "12345678";
            String destination = "jdbc:sqlserver://10.89.121.180:1433;DatabaseName=FYPTA1;instance=SQLSERVER;encrypt=true;TrustServerCertificate=true;";
            conn = DriverManager.getConnection(destination, usename, password);
            PreparedStatement x = conn.prepareStatement("SELECT user_name FROM [User] WHERE user_id='U000000001'");
            ResultSet xre = x.executeQuery();
            if (xre.next()) {
                System.out.println(xre.getString("user_name"));
            }
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

    public ResultSet executeSQL(String ex) {
        PreparedStatement x = null;
        try {
            x = conn.prepareStatement(ex);
            return x.executeQuery();
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
