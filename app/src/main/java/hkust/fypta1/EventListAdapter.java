package hkust.fypta1;

import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;

import java.lang.reflect.Field;
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
    private String[] imgResource = {"R.drawable.band","R.drawable.beach","R.drawable.cinema", "R.drawable.carnival", "R.drawable.concert", "R.drawable.donate", "R.drawable.shopping","R.drawable.service"};
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

    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
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

        holder.imgView.setImageResource(getId(imgResource[position],R.drawable.class));
        holder.evtTxt.setText(events.get(position).getEventTxt());
        holder.evtTxt2.setText(events.get(position).getEventTxt2());

        return convertView;
    }


}