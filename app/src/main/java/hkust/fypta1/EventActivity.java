package hkust.fypta1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity  {


    private ArrayList<Event> eventList;
    public ListView evntList;
    private ViewGroup layoutBar;
    Context thisActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        evntList = (ListView) findViewById(R.id.listView);


        new ConnectTask().execute("SELECT event_id,event_name,event_date,event_address,event_organizer,event_description,event_pic,event_time FROM [Event] ");

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.usermainicon);
        setTitle("ToGather");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#EF5350")));
        getSupportActionBar().setIcon(drawable);



        layoutBar = (ViewGroup) findViewById(R.id.bottomBar);
        setListener(layoutBar);

    }

//    private ArrayList  getListData() {
//
//        ArrayList<Event> eventList=new ArrayList<>();
//
//        Event eventData = new Event("Newly Opened AEON market","10am-10pm","AEON",	"新界沙田銀城街2號置富第一城‧樂薈地下G112-G118號舖","除了AEON SUPERMARKET禾輋店，AEON旗下的Living PLAZA by AEON經已進駐沙田第一城。新店佔地超過2,000平方呎，貨品種類超過6,100款。\n" +
//                "\n" +
//                "Living PLAZA由即日起至12月26日，推出多款聖誕節商品。大家可以考慮買入一些聖誕節精品及裝飾，為家增添聖誕氣氛。想買得更抵，記得逢周三掃貨！",R.drawable.aeon);
//        eventList.add(eventData);
//        Event eventData2 = new Event("Beach Music Festival by Supper Moment,KOLOR,ToNick","2016年10月21日-23日","Unknown",	"大嶼山梅窩銀礦灣海灘","沙灘上睇band show，成件事好熱血！一眾音樂發燒友不用再葡萄台灣人有「春吶」了，銀礦灣沙灘音樂節將於本周末回歸！",R.drawable.mfestival);
//        eventList.add(eventData2);
//
//        Event eventData3 = new Event("Last Season for JCCAC Handmade Market","2016年11月26日 - 27日","JCCAC",	"九龍石硤尾白田街30號","每季都會舉行一次的JCCAC手作市集來到本年度的最後一場。這次市集是「JCCAC藝術節」的頭炮節目，除一如以往有過百檔手作攤檔外，還有多項文藝活動，以下為大家介紹4個完全免費的活動，部份活動需於網上預先報名，打算到市集的朋友可以留意一下。",R.drawable.diy);
//
//        eventList.add(eventData3);
//
//        Event eventData4 = new Event("Uppacase JapaneseXKorean Store","1pm - 8pm","Uppacase","觀塘成業街19-21號成業工業大廈14樓14室","日系文具控絕不能錯過！Uppacase 12月新開的觀塘店內設有日本紙膠帶的專賣區，各品牌如maste、Cute Model、F:chocalo等的特別設計，款式齊全而且全集中在一起，預留半天時間沈醉在膠帶的夢幻世界吧！觀塘店的面積較大，除了日韓文具和精品外，新店擬增設北歐雜貨，給大家更多元化的選擇。",R.drawable.goodstuff);
//
//        eventList.add(eventData4);
//        Event eventData5 = new Event("Calbee Newly released Churros","2016年9月19日 至另推通知","Calbee","灣仔皇后大道東200號利東街地下G14-15號","最近卡樂B Plus零食店新推出了薯仔Churros ，由西班牙甜品Churros油條改良而成，改用新鮮薯仔造成，口感獨特又美味。",R.drawable.churro);
//
//        eventList.add(eventData5);
//
//        Event eventData6= new Event("HMV FIRST outlet","10am-10pm","HMV","九龍灣展貿徑1號九龍灣國際展貿中心地下shop 72B","九展名店倉除了名牌衫褲鞋袋之外，就連hmv Outlet都有！Outlet雖然面積不大，但勝在貨品陳列清楚。一排排VCD、DVD排滿貨架，還有Blu-ray和多個品牌的耳筒發售。",R.drawable.hmv_outlet);
//
//        eventList.add(eventData6);
//
//        Event eventData7= new Event("638 one night! TaiO romantic hostel","10am-10pm","海悠居","大澳石仔埗街63-69號大澳花園B座2樓","假期不少香港人都會選擇到大澳感受一下漁村風貌，難得去到想出海睇白海豚，又想順道去埋昂坪市集和大佛等鄰近景點的話，一日真的不夠玩！想在大澳住一晚，除了大澳文物酒店，亦有一個平價之選，就是新開張的大澳民宿海悠居，海悠居有三款不同的套房出租，最平的山景套房只需＄638就能住一晚，黃昏和晚上還可以在海悠居露台或天台看潮汐和觀星，一流享受！",R.drawable.hotel);
//
//        eventList.add(eventData7);
//
//        return eventList;
//
//    }

//    public void onClick(View view) {
//        switch (view.getId()) {
//
//            //To control the click event of different clickable items
//            case R.id.EventImage:
//                //To navigate to Event Detail Page
//                break;
//
//            default:
//                break;
//        }
//    }


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
        switch (item.getItemId()) {
            case R.id.IconPage:
                // User chose the "Settings" item, show the app settings UI...
//                return true;
                break;

            case R.id.MySharingPage:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                break;

            case R.id.SettingPage:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                break;

            case R.id.LoginPage:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                break;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);
    }


    private void setListener(ViewGroup view) {
        Context con = getApplicationContext();
        NavigationBar btnListener = new NavigationBar(this, con);
        int count = view.getChildCount();
        for (int i = 0; i < count; i++) {
            view.getChildAt(i).setOnClickListener(btnListener);
        }
    }


    class ConnectTask extends AsyncTask<String,Void,ArrayList<Event>> {


        protected ArrayList<Event> doInBackground(String... k) {
            try {

                ConnectionClass connectionClass = new ConnectionClass();
                connectionClass.CONN();

                eventList = connectionClass.executeSQL(k[0]);
                Log.d("Finish executing query","hi");


                return eventList;

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

            evntList.setAdapter( new EventListAdapter(EventActivity.this, eventList));
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