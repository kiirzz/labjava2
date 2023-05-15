package Models;

import Models.Cinema;
import Models.Film;

public class Session {
    private int sessionCinemaID;
    private int sessionID;
    private int sessionFilmID;
    private int sessionHall;
    private int sessionTimeNumber;
    private int sessionHour;
    private int sessionMinute;
    private boolean sessionException;

    /**/
    private int toInt(String str) {
        int num;
        try {
            num = Integer.parseInt(str);
        }
        catch (NumberFormatException ex) {
            num = -1;
        }
        return(num);
    }

    /*setter*/
    public void setSessionBasicInfo(int id, int cinema, int hall, int film, int timeNumber)
    {
        this.sessionID = id;
        this.sessionCinemaID = cinema;
        this.sessionHall = hall;
        this.sessionFilmID = film;
        this.sessionTimeNumber = timeNumber;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionException = sessionTime.length() == 5;

        int hour = 0;
        int minute = 0;

        hour = toInt(sessionTime.substring(0, 2));
        minute = toInt(sessionTime.substring(3, 5));
        if (hour != -1 & hour <= 23) { this.sessionHour = hour; } else { this.sessionException = false; }
        if (minute != -1 & minute <= 59) { this.sessionMinute = minute; } else { this.sessionException = false; }
    }

    /*getter*/
    public int getSessionID() { return sessionID; }

    public int getSessionCinema() { return sessionCinemaID; }

    public int getSessionHall() { return sessionHall; }

    public int getSessionFilmID() { return sessionFilmID; }

    public int getSessionTimeNumber() { return sessionTimeNumber; }

    public int getSessionHour() { return sessionHour; }

    public int getSessionMinute() { return sessionMinute; }

    public boolean getSessionException() { return sessionException; }

}
