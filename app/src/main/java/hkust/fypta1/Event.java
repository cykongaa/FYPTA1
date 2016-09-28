package hkust.fypta1;

import java.sql.Timestamp;

/**
 * Created by kongchingyiii on 7/9/16.
 */
public class Event {

    private String event_id;
    private String event_name;
    private Timestamp event_time;
    private String event_address;
    private String event_organizer;
    private String event_description;
    private String event_pic;
    private String event_GPS;

    public String getEventName() {
        return event_name;
    }


    public Timestamp getEventTime() {
        return event_time;
    }

    public String getEventAddress() {
        return event_address;
    }

    public String getEventOrganizer(){
        return event_organizer;
    }

    public String getEventDescription(){
        return event_description;
    }

    public String getEventPic() {
        return event_pic;
    }

    public String getEventGPS() {
        return event_GPS;
    }
}
