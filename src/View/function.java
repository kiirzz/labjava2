package View;

import Models.*;

import View.input;

import java.util.ArrayList;
import java.util.HashMap;

public class function {
//    private int[][][] timeTable;
    private boolean systemException;

    private int toInt(String str) {
        int num;
        try {
            num = Integer.parseInt(str);
        }
        catch (NumberFormatException ex) {
            num = -1;
        }
        return(num);
    }

//    public void setTimeTable(Session[] sessions, Film[] films, int hallNum, int sessionNum) {
//        this.timeTable = new int[hallNum][24][60];
//        for (int i = 0; i < hallNum; i++) {
//            for (int j = 0; j < 24; j++) {
//                for (int k = 0; k < 60; k++) {
//                    this.timeTable[i][j][k] = -1;
//                }
//            }
//        }
//
//        for (int i = 0; i < sessionNum; i++) {
//            input.addTimeToTable(timeTable, sessions[i].getSessionHall(), sessions[i].getSessionID(),
//                    sessions[i].getSessionHour(),
//                    sessions[i].getSessionMinute(),
//                    films[sessions[i].getSessionFilmID()].getFilmHour(),
//                    films[sessions[i].getSessionFilmID()].getFilmMinute());
//        }
//    }

//    public int[][][] getTimeTable() { return timeTable; }

    public int[] chooseTicketByTime(Ticket[] tickets, String time) {
        this.systemException = time.length() == 5;

        ArrayList<Integer> ticketList = new ArrayList<Integer>();

        int hour = toInt(time.substring(0, 2));
        int minute = toInt(time.substring(3, 5));
        if (hour == -1 | hour >= 24) { this.systemException = false; }
        if (minute == -1 | minute >= 60) { this.systemException = false; }

        if (!this.systemException) { return new int[]{-1}; }

        for (Ticket ticket: tickets) {
            if (ticket.getTicketClientID() == -1) {
                if (((ticket.getTicketStHour() < hour) && (ticket.getTicketEndHour() > hour)) ||
                        ((ticket.getTicketStHour() == hour) && (ticket.getTicketStMinute() <= minute)) ||
                        ((ticket.getTicketEndHour() == hour) && (ticket.getTicketEndMinute() >= minute)))
                {
                    ticketList.add(ticket.getTicketID());
                }
            }
        }

        if (ticketList.size() == 0) { return null; }

        int[] result = new int[ticketList.size()];
        for (int i = 0; i < ticketList.size(); i++) {
            if (!this.systemException) {
                result[i] = -1;
            }
            else {
                result[i] = ticketList.get(i);
            }
        }

        return result;
    }

    public int[] chooseTicketByFilm(Ticket[] tickets, int filmID) {
        ArrayList<Integer> ticketList = new ArrayList<Integer>();
        for (Ticket ticket : tickets) {
            if (ticket.getTicketFilmID() == filmID & ticket.getTicketClientID() == -1) {
                ticketList.add(ticket.getTicketID());
            }
        }

        if (ticketList.size() == 0) { return null; }

        int[] result = new int[ticketList.size()];
        for (int i = 0; i < ticketList.size(); i++) {
            result[i] = ticketList.get(i);
        }

        return result;
    }

    public int[] chooseTicketByPrice(Ticket[] tickets, HashMap<Integer, Hall> halls, double money) {
        ArrayList<Integer> ticketList = new ArrayList<Integer>();
        for (Ticket ticket : tickets) {
            if (ticket.getTicketPrice() <= money & ticket.getTicketClientID() == -1) {
                ticketList.add(ticket.getTicketID());
            }
        }

        if (ticketList.size() == 0) { return null; }

        int[] result = new int[ticketList.size()];
        for (int i = 0; i < ticketList.size(); i++) {
            result[i] = ticketList.get(i);
        }

        return result;
    }

    public boolean getSystemException() { return systemException; }
}
