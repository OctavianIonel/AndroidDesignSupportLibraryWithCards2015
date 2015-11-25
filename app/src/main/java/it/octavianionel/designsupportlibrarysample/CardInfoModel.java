package it.octavianionel.designsupportlibrarysample;

/**
 * Created by octavian on 7/28/15.
 */
public class CardInfoModel extends CardModel {

    private int status      =1; //1 for "blue", 2 for "red" and 3 for "yellow"
    private String date;
    private String timetable;
    private String room;
    private String address1;
    private String address2;

    public CardInfoModel(int identifier, String title) {
        super(identifier, title);
    }

    public CardInfoModel(int identifier, String title, String date, String timetable, String room, String address1, String address2) {
        this(identifier, title);
        this.date=date;
        this.timetable =timetable;
        this.room =room;
        this.address1 =address1;
        this.address2 =address2;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
