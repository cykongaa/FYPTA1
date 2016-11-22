package hkust.fypta1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class SharingActivity extends AppCompatActivity {

    Context thisActivity = this;
    private ViewGroup layoutBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);

        Intent intent = getIntent();
        Follower followerData = (Follower) intent.getExtras().getSerializable("follower");

        ImageView image = (ImageView) findViewById(R.id.sharing1);
        image.setImageResource(R.drawable.flowerexpo);

        /*ImageView image2 = (ImageView) findViewById(R.id.sharing2);
        image2.setImageResource(R.drawable.foodexpo);
*/
        ImageView user_icon = (ImageView) findViewById(R.id.userIcon);
        user_icon.setImageResource(getId(followerData.getUserIcon(),R.drawable.class));

        TextView user_name = (TextView) findViewById(R.id.userName);
        user_name.setText(followerData.getFollowerName());

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
        getMenuInflater().inflate(R.menu.menu_sharing, menu);
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
