package hkust.fypta1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kongchingyiii on 7/9/16.
 */
public class Event implements Serializable {

    private String event_id;
    private String event_name;
    private String event_date;
    private String event_time;
    private String event_address;
    private String event_organizer;
    private String event_description;
    private String event_pic;
    private String event_GPS;

    //public String [] name = {"Larry", "Clarence", "Leo", "Derek", "Chris", "Angel", "ABC", "Cat"};
//    public String [] name1 = {"多一間選擇！AEON $12店進駐沙田","銀礦灣 沙灘音樂節！梅窩聽Supper Moment、KOLOR、ToNick",
//            "最後一季！JCCAC手作市集4大免費節目","Uppacase日韓小物專賣店（觀塘店）","即叫即炸！卡樂B新出Churros薯棒","hmv首開outlet！$10起平價電影劇集",
//            "$638一晚！大澳新民宿浪漫睇海"};
//    public String [] name2 = {"Newly Opened AEON market","Beach Music Festival by Supper Moment,KOLOR,ToNick",
//            "Last Season for JCCAC Handmade Market","Uppacase JapaneseXKorean Store","Calbee Churros","hmv FIRST outlet",
//            "$638 one night! TaiO romantic hostel"};
    private String eventTxt;
    private String imgURL;

    public Event(String id,String name, String date, String address, String organizer, String description, String pic,String time) {
        this.event_id=id;
        this.event_name=name;
        this.event_date=date;
        this.event_address=address;
        this.event_organizer=organizer;
        this.event_description=description;
        this.event_pic=pic;
//        this.event_pic="https://s29.postimg.org/g2xl6im07/defaulteventpic.png";
        this.event_time=time;
    }

//    public Event(int x, int y) {
//        this.event_name = name2[y];
//        imgURL = "www.yahoo.com.hk";
//    }


    public String getEventId(){return event_id;}

    public String getEventName() {
        return event_name;
    }

    public String getEventAddress(){return event_address;}

    public String getEventDate(){ return event_date;}

    public String getEventTime(){return event_time;}

    public String getEventOrganizer(){return event_organizer;}

    public String getEventDescription(){
        return event_description;
    }

    public String getEventPic(){ return event_pic; }

    public static Event parseFromJSON(JSONObject jsonObject) throws JSONException {

        String event_name=jsonObject.getString("eventName");

        String event_address=jsonObject.getString("eventLocation");

        String event_date=jsonObject.getString("eventDate");

        String event_id=jsonObject.getString("eventId");

        String event_time=jsonObject.getString("eventTime");

        String event_category=jsonObject.getString("eventCategory");

        String event_organizer=jsonObject.getString("eventOrganizer");

        String event_description=jsonObject.getString("eventDescript");

        String event_pic=jsonObject.getString("eventPic");



        return new Event(event_id,event_name, event_date, event_address, event_organizer, event_description, event_pic,event_time);


    }



}
