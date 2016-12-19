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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class SearchActivity extends AppCompatActivity {

    private ViewGroup layoutBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        layoutBar = (ViewGroup) findViewById(R.id.bottomBar);
        setListener(layoutBar);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.usermainicon);
        setTitle("Search");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AED6F1")));
        getSupportActionBar().setIcon(drawable);
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

    public void searchEvt(View view) {
        String searchtxt = ((TextView) findViewById(R.id.Criteria)).getText().toString();
        Intent toSearch = new Intent(this, SearchResultActivity.class);
        if (!searchtxt.trim().equals("")) {
            toSearch.putExtra("searchTxt", searchtxt);
            startActivity(toSearch);
        }
        else {
            Toast t = new Toast(this.getApplication().getApplicationContext());
            t.setDuration(Toast.LENGTH_LONG);
            t.setText("Please enter your search keyword!");
            t.show();
        }
    }

    private void setListener(ViewGroup view) {
        Context con = this.getApplicationContext();
        NavigationBar btnListener = new NavigationBar(this, con);
        int count = view.getChildCount();
        for (int i = 0; i < count; i++) {
            view.getChildAt(i).setOnClickListener(btnListener);
        }
    }
}
