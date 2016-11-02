package hkust.fypta1;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by kongchingyiii on 7/9/16.
 */
public class Chat {

    private ArrayList<String> user_id = new ArrayList<>();
    private String chat_id;
    private String chat_icon;
    private String chat_name;
    private ArrayList<Message> message_list = new ArrayList<>();

    public Chat(ArrayList<String> userList, ArrayList<Message> msgList, String chatId, String chatName, String icon){
        this.chat_icon=icon;
        this.user_id=userList;
        this.chat_id=chatId;
        this.chat_name = chatName;
        this.message_list=msgList;
    }

    public String getChatName(){
        return this.chat_name;
    }
    public ArrayList<String> getUserId() {
        return user_id;
    }

    public ArrayList<Message> getMsgList(){
        return message_list;
    }


    public void setChatIcon(String iconString) { this.chat_icon=iconString;}
    public String getChatIcon() {
        return chat_icon;
    }


}
