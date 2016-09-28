package hkust.fypta1;

import java.sql.Timestamp;

/**
 * Created by kongchingyiii on 7/9/16.
 */
public class Message {

    private String message_id;
    private String message_text;
    private String chat_id;
    private String user_id;
    private Timestamp created_at;

    public String getMessageText() {
        return message_text;
    }


    public Timestamp getCreationTime() {
        return created_at;
    }


}
