package hkust.fypta1;

/**
 * Created by huisuiho on 26/9/2016.
 */
public class Event {
    //public String [] name = {"Larry", "Clarence", "Leo", "Derek", "Chris", "Angel", "ABC", "Cat"};
    public String [] name1 = {"家居控注意！AEON 進擊2.0窩心家品最抵買","銀礦灣 沙灘音樂節！梅窩聽Supper Moment、KOLOR、ToNick",
    "推介！ 星球手作","90後 心機小店︳捐窿捐罅尋好物","Halloween 又去老蘭咁悶？ 萬聖節活動 大晒冷","HMV Outlet 開幕! 平掃$10影碟＋$50耳筒|九龍灣好去處|",
    "大澳 靚景民宿│起身看海│聽潮汐漲退","獨食無有怕！一人前創新 帽子火鍋"};
    private String eventTxt;
    private String eventTxt2;
    private String imgURL;

    public Event() {
        eventTxt = "Hello";
        eventTxt2 = "Larry";
        imgURL = "www.yahoo.com.hk";
    }

    public Event(int x, int y) {
        //String newX = name[x];
        String newY = name1[y];
        //eventTxt = newX;
        eventTxt2 = newY;
        imgURL = "www.yahoo.com.hk";
    }

    public String getimgURL() {
        return imgURL;
    }

    public String getEventTxt() {
        return eventTxt;
    }

    public String getEventTxt2() {

        return eventTxt2;
    }
}
