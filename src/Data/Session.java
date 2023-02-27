package Data;

import Data.Cinema;
import Data.Film;

public class Session {
    private Cinema sessionCinema;
    private Film sessionFilm;
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

        if (sessionTime.length() != 5) {
            this.sessionException = false;
        }
        else {
            int hour = toInt(sessionTime.substring(0, 1));
            int minute = toInt(sessionTime.substring(3, 4));
            if (hour != -1) { this.sessionHour = hour; } else { this.sessionException = false; }
            if (minute != -1) { this.sessionMinute = minute; } else { this.sessionException = false; }
        }
    }

    /*getter*/
    public Cinema getSessionCinema() { return sessionCinema; }

    public Film getSessionFilm() { return sessionFilm; }

    public int getSessionHour() { return sessionHour; }

    public int getSessionMinute() { return sessionMinute; }

    public boolean getSessionException() { return sessionException; }

}
