package hkust.fypta1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {
    public ArrayList<Event> eventData;
    public ListView evntList;
    Context thisActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();
        String searchkeyword = intent.getStringExtra("searchTxt");
        evntList = (ListView) findViewById(R.id.searchlistView);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.usermainicon);
        setTitle("Search Results");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AED6F1")));
        getSupportActionBar().setIcon(drawable);

        new ConnectTask().execute("SELECT event_id,event_name,event_date,event_address,event_organizer,event_description,event_pic,event_time FROM [Event] WHERE event_name LIKE '%" + searchkeyword +"%'");
    }

    class ConnectTask extends AsyncTask<String,Void,ArrayList<Event>> {


        protected ArrayList<Event> doInBackground(String... k) {
            try {

                ConnectionClass connectionClass = new ConnectionClass();
                connectionClass.CONN();

                eventData = connectionClass.executeSQL(k[0]);
                Log.d("Finish executing query","hi");


                return eventData;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate() {

        }


        protected void onPostExecute(ArrayList<Event> event_list2) {
            // TODO: check this.exception
            // TODO: do something with the feed

            evntList.setAdapter( new EventListAdapter(SearchResultActivity.this, eventData));
            evntList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = evntList.getItemAtPosition(position);
                    Event eventData = (Event) o;

                    Intent intent = new Intent(thisActivity, EventDetailActivity.class);
                    intent.putExtra("event",eventData);
                    startActivity(intent);

                }
            });

        }
    }
}
