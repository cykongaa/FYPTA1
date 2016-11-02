package hkust.fypta1;

/**
 * Created by kongchingyiii on 7/9/16.
 */
public class User {

    private String user_id;
    private String user_name;
    private String user_password;
    private boolean user_authorized;
    private String user_icon;

    public User(String user_id,String user_name, String user_password, boolean authorized, String user_icon){
        this.user_id=user_id;
        this.user_name=user_name;
        this.user_password=user_password;
        this.user_authorized=authorized;
        this.user_icon=user_icon;

    }
    public String getUserName() {
        return user_name;
    }

    public boolean isUserAuthorized() {
        return user_authorized;
    }

    public String getUserIcon() {
        return user_icon;
    }


}
