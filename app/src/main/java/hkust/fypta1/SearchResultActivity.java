package hkust.fypta1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;


public class SearchResultActivity extends AppCompatActivity {

    private ArrayList<Event> searchList;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        list = (ListView) findViewById(R.id.search_list);
        ArrayList<Event> evt = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            Event evts = new Event(i, i);
            evt.add(evts);
        }

        searchList = evt;
        SearchListAdapter adapter = new SearchListAdapter(this , evt);

        list.setAdapter(adapter);
    }

}
