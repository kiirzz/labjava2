package Models;

public class Film {
    private String filmTitle;
    private int filmYear;
    private String filmGenre;
    private String filmFormat;
    private int filmDuration;
    private String filmID;
    private int filmHour;
    private int filmMinute;

    /**/

    /*setter*/
    public void setFilmInfo(String id, String filmTitle, int filmYear, String filmGenre, String filmFormat, int filmDuration)
    {
        this.filmTitle = filmTitle;
        this.filmYear = filmYear;
        this.filmGenre = filmGenre;
        this.filmFormat = filmFormat;
        this.filmDuration = filmDuration;
        this.filmID = id;
        this.filmHour = filmDuration / 60;
        this.filmMinute = filmDuration % 60;
    }

    /*getter*/
    public String getFilmTitle() { return filmTitle; }

    public int getFilmYear() { return filmYear; }

    public String getFilmGenre() { return filmGenre; }

    public String getFilmFormat() { return filmFormat; }

    public int getFilmDuration() { return filmDuration; }

    public String getFilmID() { return filmID; }

    public int getFilmHour() { return filmHour; }

    public int getFilmMinute() { return filmMinute; }

}
