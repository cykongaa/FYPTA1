package hkust.fypta1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {


    public ListView lv1;
    Context thisActivity=this;
    private ViewGroup layoutBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.usermainicon);
        setTitle("Chat Room");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4B0082")));
        getSupportActionBar().setIcon(drawable);

        layoutBar = (ViewGroup) findViewById(R.id.bottomBar);
        setListener(layoutBar);

        ArrayList chat_list = getListData();
        lv1 = (ListView) findViewById(R.id.chat_list);

        lv1.setAdapter(new ChatListAdapter(this, chat_list));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Chat chatData = (Chat) o;


            }
        });
    }

    private ArrayList getListData() {
        ArrayList<Chat> results = new ArrayList<Chat>();

        Chat chatData = new Chat(createUserList(),createMsgList("1"),"C000000001","Chat1","chaticon1");
        results.add(chatData);

        Chat chatData2 = new Chat(createUserList(),createMsgList("2"),"C000000002","Chat2","chaticon2");
        results.add(chatData2);

        Chat chatData3 = new Chat(createUserList(),createMsgList("3"),"C000000003","Chat3","chaticon3");
        results.add(chatData3);

        Chat chatData4 = new Chat(createUserList(),createMsgList("4"),"C000000004","Chat4","chaticon4");
        results.add(chatData4);

        Chat chatData5 = new Chat(createUserList(),createMsgList("5"),"C000000005","Chat5","chaticon5");
        results.add(chatData5);


        // Add some more dummy data for testing
        return results;
    }

    private ArrayList createUserList(){
        ArrayList<User> results = new ArrayList<User>();
        for (int i=1; i<=30; i++){
            String user_id = "U00000000" + i;
            String user_name = "abc" + i;
            String user_password = "abcd" + i;
            boolean authorized = false;
            String user_icon = "usericon" + i;

            User userData =new User(user_id,user_name,user_password,authorized,user_icon);
            results.add(userData);
        }
        return results;
    }

    private ArrayList createMsgList(String j){
        ArrayList<Message> results = new ArrayList<Message>();
        for (int i=1; i<=30; i++){
            String message_id = "M00000000" + i;
            String message_text = "This is chat no." +j;
            String chat_id = "C00000000"+i;
            String user_id = "U00000000" + i;
            Timestamp created_at = null;

            Message msgData = new Message(message_id,message_text,chat_id, user_id, created_at);
            results.add(msgData);
        }
        return results;
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
        Context con = getApplicationContext();
        NavigationBar btnListener = new NavigationBar(this, con);
        int count = view.getChildCount();
        for (int i = 0; i < count; i++) {
            view.getChildAt(i).setOnClickListener(btnListener);
        }
    }
}
