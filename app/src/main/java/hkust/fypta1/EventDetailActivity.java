package hkust.fypta1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class EventDetailActivity extends AppCompatActivity {

    public Event eventData;

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


        String picString=eventData.getEventPic();
        URL url=null;
        if (picString.length()!=0){
            try {
                url=new URL(picString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else{
            try {
                url =new URL("https://www.google.com.hk/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwi23pX36_fQAhVCKpQKHUzbA9EQjRwIBw&url=http%3A%2F%2Fwww.jsgtlr.com%2F601509033fc68851-event-icon.html&psig=AFQjCNF0eoDI0mOy6s4Q0KRb1_z6vMpO7Q&ust=1481947804213136");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        imageView.setImageBitmap(bmp);
//        holder.imgView.setImageBitmap(bmp);
        ImageView eventPic = (ImageView) findViewById(R.id.eventpic);
        eventPic.setImageBitmap(bmp);


    }
}
