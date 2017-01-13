package hkust.fypta1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

public class NavigationBar implements View.OnClickListener {
    private Activity act;
    private Context con;

    public NavigationBar (Activity Act, Context Con){
        this.act = Act;
        this.con = Con;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Event:
                //Intent intent = new Intent(con, EventActivity.class);
                //act.startActivity(intent);
                break;
            case R.id.Calendar:
                Intent intent2 = new Intent(con, CalendarActivity.class);
                act.startActivity(intent2);
                break;
            case R.id.Search:
                Intent intent3 = new Intent(con, SearchActivity.class);
                con.startActivity(intent3);
                break;
            case R.id.Follower:
                Intent intent4 = new Intent(con, FollowerActivity.class);
                act.startActivity(intent4);
                break;
            case R.id.chatRoom:
                break;
        }
    }
}
