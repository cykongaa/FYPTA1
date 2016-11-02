package hkust.fypta1;

/**
 * Created by kongchingyiii on 12/10/16.
 */

public class Sharing {

    private String userName;
    private String content;
    private String sharingPhoto;

    public Sharing(String userName,String content, String sharingPhoto){
        this.userName=userName;
        this.content=content;
        this.sharingPhoto=sharingPhoto;
    }

    public Sharing(String userName,String content){
        this.userName=userName;
        this.content=content;
        this.sharingPhoto=null;
    }

    public void setPhoto(String sharingPhoto){
        this.sharingPhoto=sharingPhoto;
    }
    public String getPhoto(){return this.sharingPhoto;}

    public String getUserName(){return this.userName;}
    public String getContent() {
        return this.content;
    }

}
