package hkust.fypta1;

import android.content.Context;

import java.util.List;

/**
 * Created by kongchingyiii on 5/10/16.
 */

public class NetworkClientImp implements NetworkClient{

    public void postSharing(String item, Context context) throws Exception{}

    public int postMessage(String message, Context context) throws Exception{return 0;}

    public Follower getFollowerByUserId(String id) throws Exception{return null;}

    public List<Event> getEventsByCat(String category) throws Exception{return null;}

    public List<Event> getEventsByLocation(String GPS) throws Exception{return null;}

    public List<Message> getMessages(String userId, Context context) throws Exception{return null;}

    public String getUserNameById(Long userId) throws Exception{return null;}
}
