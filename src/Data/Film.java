package Data;

public class Film {
    private String filmTitle;
    private int filmYear;
    private String filmGenre;
    private String filmDuration;
    private String filmFormat;
    private int[] filmHour;
    private int[] filmMinute;
    private int filmDurationNumber;
    private boolean filmTimeException;

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
    public void setFilmTitle(String filmTitle) { this.filmTitle = filmTitle; }

    public void setFilmInfo(int filmYear, String filmGenre, String filmFormat, int filmDurationNumber)
    {
        this.filmYear = filmYear;
        this.filmGenre = filmGenre;
        this.filmFormat = filmFormat;
        this.filmDurationNumber = filmDurationNumber;
        filmHour = new int[filmDurationNumber];
        filmMinute = new int[filmDurationNumber];
    }

    public void setFilmDuration(String filmDuration) {
        this.filmTimeException = true;
        int j = 0;
        int hour = 0;
        int minute = 0;
        for (int i=0; i<this.filmDurationNumber; i++) {
            j= i*6;
            hour = toInt(filmDuration.substring(j, j + 1));
            minute = toInt(filmDuration.substring(j, j + 1));
            if (hour != -1) { this.filmHour[i] = hour; } else { this.filmTimeException = false; }
            if (minute != -1) { this.filmMinute[i] = minute; } else { this.filmTimeException = false; }
        }
    }

    public void setFilmTimeException(boolean filmTimeException) { this.filmTimeException = filmTimeException; }

    /*getter*/
    public String getFilmTitle() { return filmTitle; }

    public int getFilmYear() { return filmYear; }

    public String getFilmGenre() { return filmGenre; }

    public String getFilmFormat() { return filmFormat; }

    public boolean getFilmTimeException() { return filmTimeException; }

    /**/

}
