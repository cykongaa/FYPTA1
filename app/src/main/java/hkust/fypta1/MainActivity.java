package hkust.fypta1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ConnectionClass connectionClass;
    private Context thisActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionClass = new ConnectionClass();

        //connectionClass.CONN();
//        Intent intent = new Intent(this, FollowerActivity.class);
//
//        startActivity(intent);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.eventPage:
                                Intent intent = new Intent(thisActivity, MainActivity.class);
                                thisActivity.startActivity(intent);
                                break;
                            case R.id.CalendarPage:
                                Intent intent2 = new Intent(thisActivity, CalendarActivity.class);
                                thisActivity.startActivity(intent2);
                                break;
                            case R.id.SearchPage:
                                Intent intent3 = new Intent(thisActivity, SearchActivity.class);
                                thisActivity.startActivity(intent3);
                                break;
                            case R.id.FollowerPage:
                                Intent intent4 = new Intent(thisActivity, FollowerActivity.class);
                                thisActivity.startActivity(intent4);
                                break;
                            case R.id.ChatRoomPage:
                                break;
                        }
                        return false;
                    }
                });
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
}
