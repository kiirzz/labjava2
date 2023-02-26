package Data;

import Data.Hall;

public class Cinema {
    private String cinemaName;
    private String cinemaAddress;
    private int cinemaCapacity;
    private int cinemaFormatNumber;
    private String[] cinemaFormat = new String[cinemaFormatNumber];
    private int cinemaHallNumber;
    private Hall[] cinemaHall = new Hall[cinemaHallNumber];

    /**/

    /*setter*/
    public void setCinemaBasicInfo(String cinemaName, String cinemaAddress, int cinemaCapacity, int cinemaHallNumber,
                                   int cinemaFormatNumber)
    {
        this.cinemaName = cinemaName;
        this.cinemaAddress = cinemaAddress;
        this.cinemaCapacity = cinemaCapacity;
        this.cinemaHallNumber = cinemaHallNumber;
        this.cinemaFormatNumber = cinemaFormatNumber;
    }

    public void setCinemaFormat(String format, int formatCount) {
        this.cinemaFormat[formatCount] = format;
    }

    public void setCinemaHall(int hallCount, int len, int wid) {
        this.cinemaHall[hallCount].setSize(len, wid);
    }

    public void setCinemaPriceHall(int hallCount, int len, int wid, double price) {
        this.cinemaHall[hallCount].setPrice(len,wid,price);
    }

    /*getter*/
    public String getCinemaName() { return cinemaName; }

    public int getCinemaCapacity() { return cinemaCapacity; }

    public String getCinemaAddress() { return cinemaAddress; }

    public int getCinemaFormatNumber() { return cinemaFormatNumber; }

    public String getCinemaFormat(int formatCount) { return cinemaFormat[formatCount]; }

    public int getCinemaHallNumber() { return cinemaHallNumber; }

    public Hall getCinemaHall(int hallCount) { return cinemaHall[hallCount]; }

    public String[] getCinemaFormatArr() { return cinemaFormat; }

}
