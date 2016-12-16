package hkust.fypta1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {
    Context thisActivity = this;
    private ConnectionClass connectionClass;
    private ViewGroup layoutBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        connectionClass = new ConnectionClass();
//
//       connectionClass.CONN();

//        Intent intent = new Intent(this, FollowerActivity.class);
//
//        startActivity(intent);
        layoutBar = (ViewGroup) findViewById(R.id.bottomBar);
        setListener(layoutBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//
//    class ConnectTask extends AsyncTask<String,void,ConnectionClass> {
//
//        protected ConnectionClass doInBackground(String k) {
//            try {
//
//                connectionClass = new ConnectionClass();
//
//                return connectionClass;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//
//        protected void onPostExecute(ConnectionClass c) {
//            // TODO: check this.exception
//            // TODO: do something with the feed
//            connectionClass.CONN();
//        }
//    }

    private void setListener(ViewGroup view) {
        NavigationBar btnListener = new NavigationBar(this, thisActivity);
        int count = view.getChildCount();
        for (int i = 0; i < count; i++) {
            view.getChildAt(i).setOnClickListener(btnListener);
        }
    }



}
