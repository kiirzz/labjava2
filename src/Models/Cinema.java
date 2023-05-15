package Models;

import Models.Hall;

public class Cinema {
    private String cinemaName;
    private String cinemaAddress;
    private int cinemaCapacity;
    private int cinemaFormatNumber;
    private String[] cinemaFormat;
    private Hall[] cinemaHall;
    private int[] cinemaHallCapacity;

    /**/

    /*setter*/
    public void setCinemaBasicInfo(String cinemaName, String cinemaAddress, int cinemaCapacity, int cinemaFormatNumber)
    {
        this.cinemaName = cinemaName;
        this.cinemaAddress = cinemaAddress;
        this.cinemaCapacity = cinemaCapacity;
        this.cinemaFormatNumber = cinemaFormatNumber;
        cinemaFormat = new String[cinemaFormatNumber];
        cinemaHall = new Hall[cinemaCapacity];
        cinemaHallCapacity = new int[cinemaCapacity];
    }

    public void setCinemaFormat(String format, int formatCount) {
        this.cinemaFormat[formatCount] = format;
    }

    public void setCinemaFormat(String[] format) {
        this.cinemaFormat = format;
    }

    public void setCinemaHall(int hallCount, int len, int wid) {
        this.cinemaHall[hallCount].setSize(len, wid);
        this.cinemaHall[hallCount].setHallID(hallCount);
    }

    public void setCinemaPriceHall(int hallCount, int len, int wid, double price) {
        this.cinemaHall[hallCount].setPrice(len,wid,price);
        cinemaHallCapacity[hallCount] = len * wid;
    }

    public void setCinemaPriceHall(int hallCount, double[][] price) {
        this.cinemaHall[hallCount].setPrice(price);
        cinemaHallCapacity[hallCount] = this.cinemaHall[hallCount].getWidth() * this.cinemaHall[hallCount].getLength();
    }

    /*getter*/
    public String getCinemaName() { return cinemaName; }

    public int getCinemaCapacity() { return cinemaCapacity; }

    public String getCinemaAddress() { return cinemaAddress; }

    public int getCinemaFormatNumber() { return cinemaFormatNumber; }

    public String getCinemaFormat(int formatCount) { return cinemaFormat[formatCount]; }

    public Hall[] getCinemaHall() { return cinemaHall; }

    public int[] getCinemaHallCapacity() { return cinemaHallCapacity; }

}
