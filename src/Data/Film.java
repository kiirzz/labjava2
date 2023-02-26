package Data;

public class Film {
    private String filmTitle;
    private int filmYear;
    private String filmGenre;
    private int filmDuration;
    private String filmFormat;

    /*setter*/
    public void setFilmTitle(String filmTitle, int filmYear, String filmGenre, int filmDuration, String filmFormat)
    {
        this.filmTitle = filmTitle;
        this.filmYear = filmYear;
        this.filmGenre = filmGenre;
        this.filmDuration = filmDuration;
        this.filmFormat = filmFormat;
    }

    /*getter*/
    public String getFilmTitle() { return filmTitle; }

    public int getFilmYear() { return filmYear; }

    public String getFilmGenre() { return filmGenre; }

    public int getFilmDuration() { return filmDuration; }

    public String getFilmFormat() { return filmFormat; }

    /**/

}
