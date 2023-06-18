package Models;

import Models.Cinema;
import Models.Film;

import java.util.ArrayList;

public class Session {
    private String sessionCinemaID;
    private String sessionID;
    private String sessionFilmID;
    private String sessionHallID;
    private int sessionTimeNumber;
    private final ArrayList<Integer> sessionHour = new ArrayList<>();
    private final ArrayList<Integer> sessionMinute = new ArrayList<>();

    /**/
//    private int toInt(String str) {
//        int num;
//        try {
//            num = Integer.parseInt(str);
//        }
//        catch (NumberFormatException ex) {
//            num = -1;
//        }
//        return(num);
//    }

    /*setter*/
    public void setSessionBasicInfo(String id, String cinema, String hall, String film)
    {
        this.sessionID = id;
        this.sessionCinemaID = cinema;
        this.sessionHallID = hall;
        this.sessionFilmID = film;
        this.sessionTimeNumber = 0;
    }

    public void addSessionTime(String sessionTime) {
        String[] data = sessionTime.split(":");
        int hour = Integer.parseInt(data[0]);
        int minute = Integer.parseInt(data[1]);

        this.sessionHour.add(hour);
        this.sessionMinute.add(minute);
        this.sessionTimeNumber += 1;
    }

    public void addSessionTime(int hour, int minute) {
        this.sessionHour.add(hour);
        this.sessionMinute.add(minute);
        this.sessionTimeNumber += 1;
    }

    /*getter*/
    public String getSessionID() { return sessionID; }

    public String getSessionCinema() { return sessionCinemaID; }

    public String getSessionHall() { return sessionHallID; }

    public String getSessionFilmID() { return sessionFilmID; }

    public int getSessionTimeNumber() { return sessionTimeNumber; }

    public ArrayList<Integer> getSessionHour() { return sessionHour; }

    public ArrayList<Integer> getSessionMinute() { return sessionMinute; }

}
