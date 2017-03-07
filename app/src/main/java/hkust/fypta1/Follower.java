package hkust.fypta1;

import java.io.Serializable;

/**
 * Created by kongchingyiii on 7/9/16.
 */
public class Follower implements Serializable {

        private String follower_id;
        private String user_name;
        private String user_icon;
        private String follower_name;

        public Follower(String user_name, String follower_name, String user_icon){
            this.user_name=user_name;
            this.user_icon=user_icon;
            this.follower_name=follower_name;
        }

        public String getUserName() {
            return user_name;
        }

        public String getUserIcon(){
            return user_icon;
        }


        public String getFollowerName() {
            return follower_name;
        }


}
