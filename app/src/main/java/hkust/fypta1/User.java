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
