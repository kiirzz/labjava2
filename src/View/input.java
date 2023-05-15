package View;

import Models.*;

public class input {
    public static void cinemaInput(Cinema[] cinema, int number, String name, String address, int capacity,                  //input cinema
                                   int formatNumber, String[] format) {
        cinema[number] = new Cinema();
        cinema[number].setCinemaBasicInfo(name, address, capacity, formatNumber);
        cinema[number].setCinemaFormat(format);
    }

    public static void hallInput(Cinema[] cinema, int number, int hallID, int len, int wid, double[][] price)           //input hall
    {
        cinema[number].getCinemaHall()[hallID] = new Hall();
        cinema[number].setCinemaHall(hallID, len, wid);
        cinema[number].setCinemaPriceHall(hallID, price);
    }

    public static void filmInput(Film[] film, int number, String title, int year, String genre, String format,
                                 int duration) {
        film[number] = new Film();
        film[number].setFilmTitle(title);
        film[number].setFilmInfo(number, year, genre, format, duration);
    }

    public static void sessionInput(Session[] session, int number, int cinemaID, int hallID, int filmID,
                                    int timeNumber, String time) {
        session[number] = new Session();
        session[number].setSessionBasicInfo(number, cinemaID, hallID, filmID, timeNumber);
        session[number].setSessionTime(time);
    }

    public static void dayInput(Day[] day, Cinema[] cinema) {
        for (Day item : day) {
            for (Cinema value : cinema) {
                for (int i = 0; i < value.getCinemaCapacity(); i++) {
                    item.setHallCheck(value.getCinemaHall()[i]);
                }
            }
        }
    }

    public static Client clientInput(String name, String phoneNum, String mail, String power) {
        Client client = new Client();
        client.setClientInfo(name, phoneNum, mail, power);

        return client;
    }

    public static void accountInput(Account account, Client client, String pass) {
        account.addNewAccount(client, pass);
    }

    public static void ticketInput(Ticket[] ticket, Cinema[] cinema, int id, int sessionID, int cinemaID, int hallID,
                                   int filmID, int stHour, int stMinute, int filmHour, int filmMinute, int length,
                                   int width) {
        ticket[id] = new Ticket();
        int endMinute = stMinute + filmMinute - 1;
        int endHour = stHour + filmHour;
        if (endMinute >= 60) {
            endMinute -= 60;
            endHour += 1;
        }
        if (endMinute < 0) {
            endMinute += 60;
            endHour -= 1;
        }
        ticket[id].setTicketInfo(id, sessionID, cinemaID, hallID, filmID, stHour, stMinute, endHour, endMinute,
                length, width, cinema[cinemaID].getCinemaHall()[hallID].getPrice(length, width));
    }
}

//    public static void addTimeToTable(int[][][] table, int hallID, int sessionID, int startHour, int startMinute,
//                                      int filmHour, int filmMinute)
//    {
//        int hour;
//        int minute;
//        for (int j = 0; j < 60 - startMinute; j++) {
//            minute = j + startMinute;
//            table[hallID][startHour][minute] = sessionID;
//        }
//        hour = startHour + filmHour;
//        for (int j = 0; j < filmMinute + 1; j++) {
//            table[hallID][hour][j] = sessionID;
//        }
//        if (filmHour > 1) {
//            for (int i = 1; i < filmHour; i++) {
//                hour = i + startHour;
//                for (int j = 0; j < 60; j++) {
//                    table[hallID][hour][j] = sessionID;
//                }
//            }
//        }
//    }

