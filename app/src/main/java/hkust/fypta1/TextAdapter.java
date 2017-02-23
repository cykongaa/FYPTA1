package hkust.fypta1;

import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.graphics.Typeface;


/**
 * Created by clarence on 8/9/2016.
 */

public class TextAdapter extends BaseAdapter
{
    private Context imgContent;
    private int Month;
    private int Year;
    private int WeekDay;
    private int imgsetting;
    private int dayOfMonth;
    private Typeface typef;
    private Integer [] imgResource = {R.mipmap.cat2};

    public TextAdapter (Context c, int year, int month, int weekDay, int setting, int dayofmon, Typeface face) {
        imgContent = c;
        this.Month = month;
        this.Year = year;
        this.WeekDay = weekDay;
        this.imgsetting = setting;
        this.typef = face;
        this.dayOfMonth = dayofmon;
    }

    public int getCount(){
        return dayOfMonth + WeekDay - 1;
    }

    public long getItemId(int id) {
        return 0;
    }

    public Object getItem(int id){
        return null;
    }

    @Override
    public View getView(int index, View ContextView, ViewGroup parent){
        TextView textview;
        textview = new TextView(imgContent);
        textview.setPadding(8, 8, 8, 8);
        textview.setTextSize(15);

        try {
            textview.setTypeface(typef);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        textview.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 150));
        textview.setGravity(Gravity.LEFT & Gravity.TOP);
        textview.setTextColor(Color.BLACK);
        //textview.setLayoutParams(new GridView.LayoutParams(96, 96));
        if (index + 1 >= WeekDay && index < dayOfMonth + WeekDay - 1) {
            textview.setBackgroundResource(imgResource[imgsetting]);
//            textview.setHeight(10);
            textview.setText(Integer.toString(index + 2 - WeekDay));
        } else {
            textview.setBackgroundResource(0);
            textview.setClickable(false);
        }
        return textview;
    }
}
