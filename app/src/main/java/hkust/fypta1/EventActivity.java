package hkust.fypta1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<Event> eventList;
    private ListView evntList;
    private EventListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        evntList = (ListView) findViewById(R.id.listView);

        ArrayList<Event> test = new ArrayList<Event>();
        for(int i = 0; i < 8; i++) {
           Event evt = new Event(i, i);
            test.add(evt);
        }
        eventList = test;
        View view = findViewById(R.id.listView);
        adapter =  new EventListAdapter(this, test);

        evntList.setAdapter(adapter);

    }

    public void onClick(View view) {
        switch (view.getId()) {

            //To control the click event of different clickable items
            case R.id.EventImage:
                //To navigate to Event Detail Page
                break;

            default:
                break;
        }
    }

}
