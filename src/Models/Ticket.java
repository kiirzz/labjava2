package Models;

public class Ticket {
    private int ticketID;
    private int ticketSessionID;
    private int ticketCinemaID;
    private int ticketHallID;
    private int ticketFilmID;
    private int ticketStHour;
    private int ticketStMinute;
    private int ticketEndHour;
    private int ticketENdMinute;
    private int ticketHallLength;
    private int ticketHallWidth;
    private int ticketClientID;
    private double ticketPrice;

    public void setTicketInfo(int id, int session, int cinema, int hall, int film, int stHour, int stMinute,
                              int endHour, int endMinute, int len, int wid, double price)
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
        this.ticketHallLength = len;
        this.ticketHallWidth = wid;
        this.ticketPrice = price;
        this.ticketClientID = -1;
    }

    public void buyTicket(int clientID) {
        this.ticketClientID = clientID;
    }

    public int getTicketID() { return ticketID; }

    public int getTicketSessionID() { return ticketSessionID; }

    public int getTicketCinemaID() { return ticketCinemaID; }

    public int getTicketHallID() { return ticketHallID; }

    public int getTicketFilmID() { return ticketFilmID; }

    public int getTicketStHour() { return ticketStHour; }

    public int getTicketStMinute() { return ticketStMinute; }

    public int getTicketEndHour() { return ticketEndHour; }

    public int getTicketEndMinute() { return ticketENdMinute; }

    public int getTicketHallLength() { return ticketHallLength; }

    public int getTicketHallWidth() { return ticketHallWidth; }

    public int getTicketClientID() { return ticketClientID; }

    public double getTicketPrice() { return ticketPrice; }

}
