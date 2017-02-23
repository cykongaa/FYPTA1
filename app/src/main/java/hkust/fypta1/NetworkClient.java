package hkust.fypta1;

/**
 * Created by kongchingyiii on 5/10/16.
 */

import android.content.Context;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * A client object to the server
 */

public interface NetworkClient {

//    void postSharing(String item, Context context) throws Exception;
//
//    int postMessage(String message, Context context) throws Exception;
//
//    Follower getFollowerByUserId(String id) throws Exception;
//
//    List<Event> getEventsByCat(String category) throws Exception;
//
//    List<Event> getEventsByLocation(String GPS) throws Exception;
//
//    List<Message> getMessages(String userId, Context context) throws Exception;
//
//    String getUserNameById(Long userId) throws Exception;

    Event fetchEvent() throws Exception;

    public HttpURLConnection connection(URL url) throws IOException ;

    public String getEvent(HttpURLConnection conn) throws IOException ;

}
