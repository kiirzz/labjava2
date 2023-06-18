package Models;

public class Hall {
    private double[][] ticketPrice;
    private int hallLength;
    private int hallWidth;
    private int hallSize;
    private String hallID;
    private int hallNO;

    /*setter*/

    public void setInfo(String hallID, int hallLength, int hallWidth) {
        this.hallID = hallID;
        this.hallLength = hallLength;
        this.hallWidth = hallWidth;
        this.hallSize = hallLength * hallWidth;
        ticketPrice = new double[hallLength][hallWidth];
    }

    public void setPrice(int len, int wid, double price) {
        if (len < this.hallLength & wid < this.hallWidth) {
            this.ticketPrice[len][wid] = price;
        }
    }

    public void setPrice(double[][] price) {
        this.ticketPrice = price;
    }

    public void addHallCinema(Cinema cinema) { this.hallNO = cinema.getCinemaCapacity() + 1; }

    /*getter*/
    public int getLength() { return hallLength; }

    public int getWidth() { return hallWidth; }

    public double getPrice(int len, int wid) {
        if (len < this.hallLength & wid < this.hallWidth) {
            return ticketPrice[len][wid];
        }
        return(-1);
    }

    public String getHallID() { return hallID; }

    public int getHallSize() { return hallSize; }

    public int getHallNO() { return hallNO; }


    /**/

}
