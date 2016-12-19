package hkust.fypta1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
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
    private LayoutInflater aInflater;

    static class ViewHolder {
        public TextView evtTxt;
        public ImageView imgView;
    }

    public EventListAdapter(Context con, ArrayList<Event> array){
        this.events = array;
        aInflater = LayoutInflater.from(con);

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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if(convertView==null) {

            holder = new ViewHolder();

            convertView = aInflater.inflate(R.layout.eventlistitem, null);

            holder.imgView = (ImageView) convertView.findViewById(R.id.EventImage);
            holder.evtTxt = (TextView) convertView.findViewById(R.id.EventText);

            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }

        String picString=events.get(position).getEventPic();

        Bitmap bmp= null;
        try {
            bmp = new RetrievePicTask().execute(picString).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        imageView.setImageBitmap(bmp);
        holder.imgView.setImageBitmap(bmp);
        holder.evtTxt.setText(events.get(position).getEventName());

        return convertView;
    }

    class RetrievePicTask extends AsyncTask<String, Void, Bitmap> {

        private Exception exception;

        protected Bitmap doInBackground(String... urls) {
            try {


                URL url = new URL(urls[0]);
                Bitmap bmp = null;
                try {
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return bmp;
            } catch (Exception e) {
                this.exception = e;

                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bmp) {
            // TODO: check this.exception
            // TODO: do something with the feed

//            return bmp;
        }
    }


}