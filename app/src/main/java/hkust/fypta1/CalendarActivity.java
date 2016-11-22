package hkust.fypta1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.view.LayoutInflater;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class CalendarActivity extends AppCompatActivity {
    private PopupWindow popup;
    private Typeface face;
    private int year;
    private int month;
    private int weekDay;
    private int dayOfMonth;
    private TextView yearText;
    private TextView monthText;
    private GridView gv_cal;
    public ViewGroup layoutBar;
    public Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Get Current Year and Month When Creating
        Context con = getApplicationContext();
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        c.set(Calendar.DATE, 1);
        face = Typeface.createFromAsset(con.getAssets(), "fonts/KaushanScript-Regular.ttf");
        weekDay = c.get(Calendar.DAY_OF_WEEK);
        dayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //Set The Text View by current year and month
        yearText = (TextView) findViewById(R.id.Year);
        monthText = (TextView) findViewById(R.id.Month);
        TextView txtyr = (TextView) findViewById(R.id.textForYear);
        layoutBar = (ViewGroup) findViewById(R.id.bottomBar);
        setListener(layoutBar);

        setAllAttr(yearText, Integer.toString(year), face, Color.BLACK);
        setAllAttr(monthText, new SimpleDateFormat("MMM").format(c.getTime()), face, Color.BLACK);
        setAllAttr(txtyr, "Year", face, Color.BLACK);

        //Binding the grid view by day images and set the theme image
        gv_cal = (GridView) findViewById(R.id.CalendarGrid);
        //gv_cal.setBackgroundResource(R.mipmap.ic_launcher);
        gv_cal.setAdapter(new TextAdapter(this, year, month, weekDay, 0, dayOfMonth, face));

        gv_cal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                //Method for output parameter and debugging
                if (index - weekDay >= -1 && index - weekDay < dayOfMonth - 1) {
                    showPopUp();
                }
                Toast.makeText(CalendarActivity.this, "" + index + " " + year + " " + month + " " + weekDay,
                        Toast.LENGTH_SHORT).show();
            }
        });
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

    public void switchOnClick(View view) {
        Calendar newMonth = Calendar.getInstance();

        switch (view.getId()) {
            case R.id.forward:
                if (month + 1 > 12) {
                    yearText.setText(Integer.toString(year + 1));
                    month = 1;
                    year += 1;
                } else {
                    month += 1;
                }
                break;
            case R.id.backward:
                if (month - 1 < 1) {
                    yearText.setText(Integer.toString(year - 1));
                    year -= 1;
                    month = 12;
                } else {
                    month -= 1;
                }
                break;
        }

        newMonth.set(year, month - 1, 1);
        monthText.setText(new SimpleDateFormat("MMM").format(newMonth.getTime()));
        weekDay = newMonth.get(Calendar.DAY_OF_WEEK);
        dayOfMonth = newMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        gv_cal.setAdapter(new TextAdapter(this, year, month, weekDay, 0, dayOfMonth, face));
    }

    private void showPopUp() {
        try {
            LayoutInflater inflater = (LayoutInflater) CalendarActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_calendar_detail, (ViewGroup) findViewById(R.id.popupCalendar));
            popup = new PopupWindow(layout, 1300, 1500, true);
            popup.showAtLocation(layout, Gravity.CENTER, 0, 0);
            close = (Button) layout.findViewById(R.id.closeBtn);
            close.setOnClickListener(cancel_button);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private OnClickListener cancel_button = new OnClickListener() {
        public void onClick(View v) {
            popup.dismiss();
        }
    };

    private void setAllAttr(TextView txtview, String text, Typeface face, int color) {
        txtview.setTextColor(color);
        txtview.setText(text);
        txtview.setTypeface(face);
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
