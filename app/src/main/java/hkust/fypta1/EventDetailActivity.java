package hkust.fypta1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        if(!eventData.getEventName().equals("")) {
            eventName.setText(eventData.getEventName());
        }else{
            eventName.setText("Event");
        }

        TextView eventTime = (TextView) findViewById(R.id.event_time);
        if(!eventData.getEventTime().equals("")) {
            eventTime.setText(eventData.getEventTime());
        }else{
            eventTime.setText("Not Specified");
        }

        TextView eventOrganizer = (TextView) findViewById(R.id.event_organizer);
        if(!eventData.getEventOrganizer().equals("")) {
            eventOrganizer.setText(eventData.getEventOrganizer());
        }else{
            eventOrganizer.setText("Not Specified");
        }

        TextView eventAddress = (TextView) findViewById(R.id.event_address);
        if(!eventData.getEventAddress().equals("")) {
            eventAddress.setText(eventData.getEventAddress());
        }else{
            eventAddress.setText("Not Specified");
        }

        TextView eventDescription = (TextView) findViewById(R.id.event_description);
        if(!eventData.getEventDescription().equals("")) {
            eventDescription.setText(eventData.getEventDescription());
        }else{
            eventDescription.setText("Not Specified");
        }
//        layoutBar = (ViewGroup) findViewById(R.id.bottomBar);
//        setListener(layoutBar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.usermainicon);
        setTitle("Event details");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#EF5350")));
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

        Button directionButton = (Button) findViewById(R.id.directionButton);
        directionButton.setOnClickListener(e->{// Create a Uri from an intent string. Use the result to create an Intent.
            Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q="+ eventData.getEventAddress());

            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            // Make the Intent explicit by setting the Google Maps package
                        mapIntent.setPackage("com.google.android.apps.maps");

            // Attempt to start an activity that can handle the Intent
                        startActivity(mapIntent);});

        Button addToCalendarButton = (Button) findViewById(R.id.addCalendarButton);
        addToCalendarButton.setOnClickListener(e->{

            new AddCalendarTask().execute("INSERT INTO [Participant] VALUES ('U000000001',0,'" + eventData.getEventId() +"')");

        });


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

    class AddCalendarTask extends AsyncTask<String, Void, Boolean> {

        private Exception exception;


        protected Boolean doInBackground(String... urls) {
            try {

                ConnectionClass connectionClass = new ConnectionClass();
                connectionClass.CONN();

                connectionClass.updateSQL(urls[0]);
                return true;

            } catch (Exception e) {
                this.exception = e;

                return false;
            }


        }

        @Override
        protected void onPostExecute(Boolean success) {
            // TODO: check this.exception
            // TODO: do something with the feed

            if(success){
                Toast.makeText(EventDetailActivity.this, "Successfully added to your calendar.",
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(EventDetailActivity.this, "Failed to add this event to your calendar, Please try again later." ,
                        Toast.LENGTH_SHORT).show();
            }

//            return bmp;
        }
    }
}
