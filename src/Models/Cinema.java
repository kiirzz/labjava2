package Models;

import Models.Hall;

import java.util.ArrayList;

public class Cinema {
    private String cinemaID;
    private String cinemaName;
    private String cinemaAddress;
    private int cinemaCapacity = 0;
    private int cinemaFormatNumber = 0;
    private final ArrayList<String> cinemaFormat = new ArrayList<>();
    private final ArrayList<Hall> cinemaHall = new ArrayList<>();
    private final ArrayList<Integer> cinemaHallCapacity = new ArrayList<>();

    /**/

    /*setter*/
    public void setCinemaBasicInfo(String id, String cinemaName, String cinemaAddress)
    {
        this.cinemaID = id;
        this.cinemaName = cinemaName;
        this.cinemaAddress = cinemaAddress;
    }

    public void addCinemaFormat(String format) {
        this.cinemaFormat.add(format);
        this.cinemaFormatNumber += 1;
    }

//    public void setCinemaFormat(String[] format) {
//        this.cinemaFormat = format;
//    }

    public void addCinemaHall(Hall hall) {
        this.cinemaHall.add(hall);
        this.cinemaHallCapacity.add(hall.getLength() * hall.getWidth());
        this.cinemaCapacity += 1;
    }

//    public void setCinemaPriceHall(int hallCount, double[][] price) {
//        this.cinemaHall.get(hallCount).setPrice(price);
//        cinemaHallCapacity.get(hallCount) = this.cinemaHall.get(hallCount).getWidth() * this.cinemaHall.get(hallCount).getLength();
//    }

    /*getter*/
    public String getCinemaID() { return cinemaID; }

    public String getCinemaName() { return cinemaName; }

    public int getCinemaCapacity() { return cinemaCapacity; }

    public String getCinemaAddress() { return cinemaAddress; }

    public int getCinemaFormatNumber() { return cinemaFormatNumber; }

    public String getCinemaFormat(int num) { return cinemaFormat.get(num); }

    public ArrayList<String> getCinemaFormat() { return cinemaFormat; }

    public ArrayList<Hall> getCinemaHall() { return cinemaHall; }

    public ArrayList<Integer> getCinemaHallCapacity() { return cinemaHallCapacity; }

}
