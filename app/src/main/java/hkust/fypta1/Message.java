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

    public Message(String message_id,String message_text, String chat_id, String user_id, Timestamp created_at){
        this.message_id=message_id;
        this.message_text=message_text;
        this.chat_id=chat_id;
        this.user_id=user_id;
        this.created_at=created_at;
    }

    public String getMessageText() {
        return message_text;
    }

    public String getUserId(){
        return this.user_id;
    }

    public Timestamp getCreationTime() {
        return created_at;
    }


}
