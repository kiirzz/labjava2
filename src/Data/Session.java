package Data;

import Data.Cinema;
import Data.Film;

public class Session {
    private Cinema sessionCinema;
    private Film sessionFilm;
    private int sessionYear;
    private int sessionMonth;
    private int sessionDate;
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
    public void setSessionCinema(Cinema cinema) {
        this.sessionCinema = cinema;
    }

    public void setSessionFilm(Film film) {
        this.sessionFilm = film;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionException = true;

        if (sessionTime.length() != 16) {
            this.sessionException = false;
        }
        else {
            int date = toInt(sessionTime.substring(0, 1));
            int month = toInt(sessionTime.substring(3, 4));
            int year = toInt(sessionTime.substring(6, 9));
            int hour = toInt(sessionTime.substring(11, 12));
            int minute = toInt(sessionTime.substring(14, 15));
            if (date != -1) { this.sessionDate = date; } else { this.sessionException = false; }
            if (month != -1) { this.sessionMonth = month; } else { this.sessionException = false; }
            if (year != -1) { this.sessionYear = year; } else { this.sessionException = false; }
            if (hour != -1) { this.sessionHour = hour; } else { this.sessionException = false; }
            if (minute != -1) { this.sessionMinute = minute; } else { this.sessionException = false; }
        }
    }

    /*getter*/
    public Cinema getSessionCinema() { return sessionCinema; }

    public Film getSessionFilm() { return sessionFilm; }

    public int getSessionYear() { return sessionYear; }

    public int getSessionMonth() { return sessionMonth; }

    public int getSessionDate() { return sessionDate; }

    public int getSessionHour() { return sessionHour; }

    public int getSessionMinute() { return sessionMinute; }

}
