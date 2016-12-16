package hkust.fypta1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;

import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SharingActivity extends AppCompatActivity {

    Context thisActivity = this;

    public Follower followerData = null;
    public ListView lv1;
    public ArrayList<Sharing> results = new ArrayList<>();

    private ViewGroup layoutBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);

//        ImageButton eventButton=(ImageButton)findViewById(R.id.Event);
//        eventButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                startActivity(new Intent(getApplicationContext(),EventActivity.class));
//           /* if you want to finish the first activity then just call
//            finish(); */
//            }
//        });
//
//        ImageButton calendarButton=(ImageButton)findViewById(R.id.Calendar);
//        calendarButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                startActivity(new Intent(getApplicationContext(),CalendarActivity.class));
//           /* if you want to finish the first activity then just call
//            finish(); */
//            }
//        });
//
//        ImageButton searchButton=(ImageButton)findViewById(R.id.Search);
//        searchButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                startActivity(new Intent(getApplicationContext(),SearchActivity.class));
//           /* if you want to finish the first activity then just call
//            finish(); */
//            }
//        });
//
//        ImageButton followerButton=(ImageButton)findViewById(R.id.Follower);
//        followerButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                startActivity(new Intent(getApplicationContext(),FollowerActivity.class));
//           /* if you want to finish the first activity then just call
//            finish(); */
//            }
//        });
//
//        ImageButton chatButton=(ImageButton)findViewById(R.id.chatRoom);
//        chatButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                startActivity(new Intent(getApplicationContext(),ChatRoomActivity.class));
//           /* if you want to finish the first activity then just call
//            finish(); */
//            }
//        });

        Intent intent = getIntent();
        followerData = (Follower) intent.getExtras().getSerializable("follower");
        getListData();


//        ImageView image = (ImageView) findViewById(R.id.sharing1);
//        image.setImageResource(R.drawable.flowerexpo);
//
//        /*ImageView image2 = (ImageView) findViewById(R.id.sharing2);
//        image2.setImageResource(R.drawable.foodexpo);
//*/
        ImageView user_icon = (ImageView) findViewById(R.id.userIcon);
        user_icon.setImageResource(getId(followerData.getUserIcon(),R.drawable.class));
//
        TextView user_name = (TextView) findViewById(R.id.userName);
        user_name.setText(followerData.getFollowerName());

        ArrayList sharing_list = getSpecificFollowerActivity(followerData.getFollowerName());
        lv1 = (ListView) findViewById(R.id.sharing_list);

        if (!sharing_list.isEmpty()){
        lv1.setAdapter(new SharingListAdapter(this, sharing_list));}
        else {Toast toast = Toast.makeText(this.getApplicationContext(), "No Sharing Yet", Toast.LENGTH_SHORT);
        toast.show();}
//        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//                Object o = lv1.getItemAtPosition(position);
//                Sharing sharingData = (Sharing) o;
//
//
//            }
//        });

        Button back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisActivity, FollowerActivity.class);

                startActivity(intent);
            }
        });

        layoutBar = (ViewGroup) findViewById(R.id.bottomBar);
        setListener(layoutBar);
    }

    private ArrayList getSpecificFollowerActivity(String followerName){

        ArrayList<Sharing> specificList = new ArrayList<>();
        for (Sharing s:results){
            if (s.getUserName().equals(followerName)){
                specificList.add(s);
            }
        }

        return specificList;
    }

    private void getListData() {

        Sharing sharingData = new Sharing("ktk1222", "Today we went to the flower expo yeah!","flowerexpo");
        results.add(sharingData);
        Sharing sharingData2 = new Sharing("ktk1222", "Today we went to the beach yeah!","sea2");
        results.add(sharingData2);
        Sharing sharingData3 = new Sharing("ktk1222", "Today we went to the food expo WoW!","foodexpo1");
        results.add(sharingData3);
        Sharing sharingData5 = new Sharing("ktk1222", "Today we went to the concert so exciting!","concert");
        results.add(sharingData5);
        Sharing sharingData6 = new Sharing("aaa1220", "Today we went to the hiking so exhausting!","hiking");
        results.add(sharingData6);
        Sharing sharingData7 = new Sharing("yuyur05", "Today we went to the carnival yo!","carnival");
        results.add(sharingData7);
        Sharing sharingData8 = new Sharing("pann22", "Today we went to the museum, so gorgeous!","museum");
        results.add(sharingData8);
        Sharing sharingData9 = new Sharing("yakk50", "Today we went to donate blood, let's help others together!","donate");
        results.add(sharingData9);
        Sharing sharingData10 = new Sharing("pann22", "Today we went to shopping together haha!","shopping");
        results.add(sharingData10);
        Sharing sharingData11 = new Sharing("bpp1220", "Today we went to do some voluntary services, so meaningful!","service");
        results.add(sharingData11);
        Sharing sharingData12 = new Sharing("haha005", "Today we went to watch the band show, the music was really great!","band");
        results.add(sharingData12);
        Sharing sharingData13 = new Sharing("kelvin2", "Today we went to a seminar, with lots of inspiration in return:)!", "seminar");
        results.add(sharingData13);
        Sharing sharingData14 = new Sharing("kate_100", "Today we went cycling, healthy lifestyle :D","cycling");
        results.add(sharingData14);
        Sharing sharingData15 = new Sharing("katy9000", "Today we went to an open concert, bravo!", "openconcert");
        results.add(sharingData15);

    }


    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_item, menu);
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
