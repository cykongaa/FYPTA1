package hkust.fypta1;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


/**
 * Created by clarence on 23/11/2016.
 */

public class SearchListAdapter extends BaseAdapter {
    private ArrayList<Event> searchList = new ArrayList();
    private Integer [] imgList = {R.drawable.band, R.drawable.beach, R.drawable.carnival, R.drawable.concert};
    private Context context;
    private LayoutInflater aInflater;

    private static class ViewHolder {
        public TextView evtName;
        public ImageView imgView;
    }

    public SearchListAdapter(Context con, ArrayList<Event> list) {
        context = con;
        searchList = list;
        aInflater = (LayoutInflater.from(con));
    }

    public int getCount() {
        return searchList.size();
    }

    public Object getItem(int index) {
        return searchList.get(index);
    }

    public long getItemId(int index) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        holder = new ViewHolder();

        convertView = aInflater.inflate(R.layout.search_list_item, null);

        holder.imgView = (ImageView) convertView.findViewById(R.id.evtImg);
        holder.evtName = (TextView) convertView.findViewById(R.id.evtname);
        //((TextView) convertView.findViewById(R.id.evtname)).setGravity(Gravity.CENTER);
        convertView.setTag(holder);

        holder.imgView.setImageResource(imgList[position]);
        holder.evtName.setText(searchList.get(position).getEventTxt2());

        return convertView;
    }
}
