package Models;

public class Film {
    private String filmTitle;
    private int filmYear;
    private String filmGenre;
    private String filmFormat;
    private int filmDuration;
    private int filmID;
    private int filmHour;
    private int filmMinute;

    /**/

    /*setter*/
    public void setFilmTitle(String filmTitle) { this.filmTitle = filmTitle; }

    public void setFilmInfo(int id, int filmYear, String filmGenre, String filmFormat, int filmDuration)
    {
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

    public int getFilmID() { return filmID; }

    public int getFilmHour() { return filmHour; }

    public int getFilmMinute() { return filmMinute; }

}
