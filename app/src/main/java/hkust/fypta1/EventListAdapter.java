package hkust.fypta1;

import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import java.util.ArrayList;
import java.util.zip.Inflater;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by huisuiho on 26/9/2016.
 */
public class EventListAdapter extends BaseAdapter {
    private ArrayList<Event> events = new ArrayList();
    private final Context context;
    private LayoutInflater aInflater;
    private Integer[] imgResource = {R.drawable.aeon,R.drawable.mfestival,R.drawable.diy,
            R.drawable.good_stuff,R.drawable.halloween,R.drawable.hmv_outlet,R.drawable.hotel,R.drawable.hotpot };
    private static class ViewHolder {
        public TextView evtTxt;
        public TextView evtTxt2;
        public ImageView imgView;
    }

    public EventListAdapter(Context con, ArrayList<Event> array){
        this.context = con;
        this.events = array;
        aInflater = (LayoutInflater.from(con));

    }

    public long getItemId(int id) {
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public int getCount() {
        return events.size();
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if(convertView==null) {

            holder = new ViewHolder();

            convertView = aInflater.inflate(R.layout.eventlistitem, null);

            holder.imgView = (ImageView) convertView.findViewById(R.id.EventImage);
            holder.evtTxt = (TextView) convertView.findViewById(R.id.EventText);
            holder.evtTxt2 = (TextView) convertView.findViewById(R.id.EventText2);

            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }

        holder.imgView.setBackgroundResource(imgResource[position]);
        holder.evtTxt.setText(events.get(position).getEventTxt());
        holder.evtTxt2.setText(events.get(position).getEventTxt2());

        return convertView;
    }


}
