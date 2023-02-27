package Data;

public class Hall {
    private double[][] ticketPrice;
    private int hallLength;
    private int hallWidth;

    /*setter*/
    public void setSize(int hallLength, int hallWidth) {
        this.hallLength = hallLength;
        this.hallWidth = hallWidth;
        ticketPrice = new double[hallLength][hallWidth];
    }

    public void setPrice(int len, int wid, double price) {
        if (len < this.hallLength & wid < this.hallWidth) {
            this.ticketPrice[len][wid] = price;
        }
    }

    /*getter*/
    public int getLength() { return hallLength; }

    public int getWidth() {
        return hallWidth;
    }

    public double getPrice(int len, int wid) {
        if (len < this.hallLength & wid < this.hallWidth) {
            return ticketPrice[len][wid];
        }
        return(-1);
    }

    /**/

}
