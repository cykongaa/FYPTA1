package hkust.fypta1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FollowerActivity extends Activity {

    public ListView lv1;
    private ViewGroup layoutBar;
    Context thisActivity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower);

        ArrayList follower_list = getListData();
        lv1 = (ListView) findViewById(R.id.custom_list);

        lv1.setAdapter(new CustomListAdapter(this, follower_list));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Follower followerData = (Follower) o;

                Intent intent = new Intent(thisActivity, SharingActivity.class);
                intent.putExtra("follower", followerData);
                startActivity(intent);

            }
        });

        layoutBar = (ViewGroup) findViewById(R.id.bottomBar);
        setListener(layoutBar);
    }

    private ArrayList getListData() {
        ArrayList<Follower> results = new ArrayList<Follower>();
        Follower followerData = new Follower("cat1","kate_100");
        results.add(followerData);
        Follower followerData2 = new Follower("dog1","bpp1220");
        results.add(followerData2);
        Follower followerData3 = new Follower("flower1","haha005");
        results.add(followerData3);
        Follower followerData4 = new Follower("flower2","ktk1222");
        results.add(followerData4);
        Follower followerData5 = new Follower("girl1","kelvin2");
        results.add(followerData5);
        Follower followerData6 = new Follower("sea1","aaa1220");
        results.add(followerData6);
        Follower followerData7 = new Follower("orange","yuyur05");
        results.add(followerData7);
        Follower followerData8 = new Follower("apple1","pann22");
        results.add(followerData8);
        Follower followerData9= new Follower("banana","katy9000");
        results.add(followerData9);
        Follower followerData10 = new Follower("sea2","yakk50");
        results.add(followerData10);
        Follower followerData11 = new Follower("girl2","yeah");
        results.add(followerData11);

        // Add some more dummy data for testing
        return results;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_follower, menu);
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

    private void setListener(ViewGroup view) {
        NavigationBar btnListener = new NavigationBar(this, thisActivity);
        int count = view.getChildCount();
        for (int i = 0; i < count; i++) {
            view.getChildAt(i).setOnClickListener(btnListener);
        }
    }
}
