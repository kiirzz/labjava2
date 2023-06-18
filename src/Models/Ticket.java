package Models;

public class Ticket {
    private String ticketID;
    private String ticketSessionID;
    private String ticketCinemaID;
    private String ticketHallID;
    private String ticketFilmID;
    private String ticketClientID;
    private int ticketStHour;
    private int ticketStMinute;
    private int ticketEndHour;
    private int ticketENdMinute;
    private int ticketHallRow;
    private int ticketHallColumn;
    private double ticketPrice;

    public Ticket(String id, String session, String film, String cinema, String hall, int stHour, int stMinute,
                              int endHour, int endMinute, int row, int col, double price, String client)
    {
        this.ticketID = id;
        this.ticketSessionID = session;
        this.ticketCinemaID = cinema;
        this.ticketHallID = hall;
        this.ticketFilmID = film;
        this.ticketStHour = stHour;
        this.ticketStMinute = stMinute;
        this.ticketEndHour = endHour;
        this.ticketENdMinute = endMinute;
        this.ticketHallRow = row;
        this.ticketHallColumn = col;
        this.ticketPrice = price;
        this.ticketClientID = client;
    }

    public void buyTicket(String clientID) {
        this.ticketClientID = clientID;
    }

    public void cancelTicket() {
        this.ticketClientID = "0";
    }

    public String getTicketID() { return ticketID; }

    public String getTicketSessionID() { return ticketSessionID; }

    public String getTicketCinemaID() { return ticketCinemaID; }

    public String getTicketHallID() { return ticketHallID; }

    public String getTicketFilmID() { return ticketFilmID; }

    public int getTicketStHour() { return ticketStHour; }

    public int getTicketStMinute() { return ticketStMinute; }

    public int getTicketEndHour() { return ticketEndHour; }

    public int getTicketEndMinute() { return ticketENdMinute; }

    public int getTicketHallRow() { return ticketHallRow; }

    public int getTicketHallColumn() { return ticketHallColumn; }

    public String getTicketClientID() { return ticketClientID; }

    public double getTicketPrice() { return ticketPrice; }

}
