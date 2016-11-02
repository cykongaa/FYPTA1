package hkust.fypta1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ImageButton eventButton=(ImageButton)findViewById(R.id.Event);
        eventButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),EventActivity.class));
           /* if you want to finish the first activity then just call
            finish(); */
            }
        });

        ImageButton calendarButton=(ImageButton)findViewById(R.id.Calendar);
        calendarButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),CalendarActivity.class));
           /* if you want to finish the first activity then just call
            finish(); */
            }
        });

        ImageButton searchButton=(ImageButton)findViewById(R.id.Search);
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),SearchActivity.class));
           /* if you want to finish the first activity then just call
            finish(); */
            }
        });

        ImageButton followerButton=(ImageButton)findViewById(R.id.Follower);
        followerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),FollowerActivity.class));
           /* if you want to finish the first activity then just call
            finish(); */
            }
        });

        ImageButton chatButton=(ImageButton)findViewById(R.id.chatRoom);
        chatButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),ChatRoomActivity.class));
           /* if you want to finish the first activity then just call
            finish(); */
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
