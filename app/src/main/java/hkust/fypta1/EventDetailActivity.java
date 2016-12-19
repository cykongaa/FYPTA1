package hkust.fypta1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class EventDetailActivity extends AppCompatActivity {

    public Event eventData;

//    private ViewGroup layoutBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Intent intent = getIntent();
        eventData = (Event) intent.getExtras().getSerializable("event");

        TextView eventName = (TextView) findViewById(R.id.EventName);
        eventName.setText(eventData.getEventName());

        TextView eventTime = (TextView) findViewById(R.id.event_time);
        eventTime.setText(eventData.getEventTime());

        TextView eventOrganizer = (TextView) findViewById(R.id.event_organizer);
        eventOrganizer.setText(eventData.getEventOrganizer());

        TextView eventAddress = (TextView) findViewById(R.id.event_address);
        eventAddress.setText(eventData.getEventAddress());

        TextView eventDescription = (TextView) findViewById(R.id.event_description);
        eventDescription.setText(eventData.getEventDescription());

//        layoutBar = (ViewGroup) findViewById(R.id.bottomBar);
//        setListener(layoutBar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.usermainicon);
        setTitle("Event details");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AED6F1")));
        getSupportActionBar().setIcon(drawable);

        String picString=eventData.getEventPic();

        Bitmap bmp= null;
        try {
            bmp = new RetrievePicTask().execute(picString).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ImageView eventPic = (ImageView) findViewById(R.id.eventpic);
        eventPic.setImageBitmap(bmp);


    }

    private void setListener(ViewGroup view) {
        Context con = this.getApplicationContext();
        NavigationBar btnListener = new NavigationBar(this, con);
        int count = view.getChildCount();
        for (int i = 0; i < count; i++) {
            view.getChildAt(i).setOnClickListener(btnListener);
        }
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
