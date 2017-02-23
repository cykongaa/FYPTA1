package hkust.fypta1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.CursorJoiner;
import android.database.SQLException;
import android.os.StrictMode;
import android.util.Log;

import java.io.InputStream;
import java.security.KeyStore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

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
//        destination = "jdbc:sqlserver://" + ip + ":1433;DatabaseName=" + databaseName +";instance=SQLSERVER;encrypt=true;TrustServerCertificate=true;";

    }

    public Connection CONN(Context context) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String usename = "administrator";
            String password = "12345678";
            System.setProperty("javax.net.ssl.keyStore","C:/Program Files/Java/jre1.8.0_66/bin/keystore.jks");
            System.setProperty("javax.net.ssl.keyStorePassword","password");

//            destination = "jdbc:jtds:sqlserver://"+ip+":1433/FYPTA1;instance=FYPTA1;useUnicode=true;characterEncoding=true;";
        destination = "jdbc:sqlserver://"+ip+":1433;databaseName=FYPTA1;useUnicode=true;characterEncoding=true;integratedSecurity=true;" +
                "encrypt=true; trustServerCertificate=false;" +
                "trustStore=C:/Program Files/Java/jre1.8.0_66/bin/keystore.jks;trustStorePassword=password";
            Log.d("destination",destination);
//            SSLContext sslContext = SSLContext.getDefault();
//            KeyStore trustSt= KeyStore.getInstance("JKS");
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            InputStream trustStoreStream = context.getResources().openRawResource(R.raw.keystore);
//            trustSt.load(trustStoreStream, "password".toCharArray());
//            trustManagerFactory.init(trustSt);
//
//            KeyStore keyStore = KeyStore.getInstance("JKS");
//            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//            InputStream keyStoreStream = context.getResources().openRawResource(R.raw.keystore);
//            keyStore.load(keyStoreStream, "password".toCharArray());
//            keyManagerFactory.init(keyStore, "password".toCharArray());
//
//            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
//            conn = DriverManager.getConnection(destination, usename, password);
//            conn.executeSQL("SELECT user_name FROM [User] WHERE user_id='U000000001'");
//            ResultSet xre = x.executeQuery();
//            if (xre.next()) {
//                System.out.println("Connect successfully to the database! \n Here is one search result: " +xre.getString("user_name"));
//            }
//            conn.setAutoCommit(false);

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
                Log.d("result id",result.getString("event_id"));
                eventList.add(new Event(result.getString("event_id"),result.getString("event_name"), result.getString("event_time"),result.getString("event_address"),result.getString("event_organizer"),result.getString("event_description"),result.getString("event_pic"),result.getString("event_time")));
            }
            return eventList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (x!=null) x.close();
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
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


}
