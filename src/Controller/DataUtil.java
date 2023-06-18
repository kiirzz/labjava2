package Controller;

import Models.*;

import java.util.ArrayList;
import java.util.Random;

public class DataUtil {

    public boolean checkTime(String time) {
        if (time.length() != 5) {
            return false;
        }
        try {
            Integer.parseInt(time.split(":")[0]);
            Integer.parseInt(time.split(":")[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public Cinema addNewCinema(int cinemaNumber, String datas) {
        String[] data = datas.split("\\|");
        String[] date = java.time.LocalDate.now().toString().split("-");
        String idNO = Integer.toString(cinemaNumber + 1);
        String id;
        if (cinemaNumber < 9) {
            id = date[2].concat(date[1]).concat("0").concat(idNO);
        }
        else {
            id = date[2].concat(date[1]).concat(idNO);
        }


        Cinema cinema = new Cinema();
        cinema.setCinemaBasicInfo(id, data[0], data[1]);
        for (int i = 2; i < data.length; i++) {
            cinema.addCinemaFormat(data[i]);
        }

        return cinema;
    }

    public Hall addNewHall(Cinema cinema, String datas, double[][] price) {
        String[] data = datas.split("\\|");
        String idNO = Integer.toString(cinema.getCinemaCapacity() + 1);
        String id;
        if (cinema.getCinemaCapacity() < 9) {
            id = cinema.getCinemaID().concat("0").concat(idNO);
        } else {
            id = cinema.getCinemaID().concat(idNO);
        }

        Hall hall = new Hall();
        hall.setInfo(id, Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        hall.setPrice(price);

        return hall;
    }

    public Film addNewFilm(int filmNumber, String datas) {
        String[] data = datas.split("\\|");
        String idNO = Integer.toString(filmNumber + 1);
        String id = data[1].concat(idNO);

        Film film = new Film();
        film.setFilmInfo(id, data[0], Integer.parseInt(data[1]), data[2], data[3], Integer.parseInt(data[4]));

        return film;
    }

    public Session addNewSession(Cinema cinema, Hall hall, Film film, int hour, int min) {
        String id = cinema.getCinemaID().substring(cinema.getCinemaID().length() - 2).concat(
                    hall.getHallID().substring(hall.getHallID().length() - 2)).concat(
                    film.getFilmID().substring(film.getFilmID().length() - 2));

        Session session = new Session();
        session.setSessionBasicInfo(id, cinema.getCinemaID(), hall.getHallID(), film.getFilmID());
        session.addSessionTime(hour, min);

        return session;
    }

    public Admin addNewAdmin(int adminNumber, String datas) {
        Random random = new Random();
        String[] data = datas.split("\\|");
        String idNO = Integer.toString(adminNumber + 1);
        while (idNO.length() < 4) {
            idNO = "0".concat(idNO);
        }
        String id = Integer.toString(random.nextInt(9000) + 1000).concat(idNO);

        return new Admin(id, data[0], data[1], data[2], data[3]);
    }

    public Client addNewClient(int clientNumber, String datas) {
        Random random = new Random();
        String[] data = datas.split("\\|");
        String idNO = Integer.toString(clientNumber + 1);
        while (idNO.length() < 4) {
            idNO = "0".concat(idNO);
        }
        String id = Integer.toString(random.nextInt(9000) + 1000).concat(idNO);

        return new Client(id, data[0], data[1], data[2], data[3], 0);
    }

    public ArrayList<Ticket> createTicketsFromData(ArrayList<Session> sessions, ArrayList<Cinema> cinemas,
                                                   ArrayList<Hall> halls, ArrayList<Film> films) {
        var hall = new Hall();
        var cinema = new Cinema();
        var film = new Film();
        String id;
        int stHour, stMin, endHour, endMin;

        ArrayList<Ticket> tickets = new ArrayList<>();
        for (Session session : sessions) {
            hall = getHallFromID(halls, session.getSessionHall());
            cinema = getCinemaFromID(cinemas, session.getSessionCinema());
            film = getFilmFromID(films, session.getSessionFilmID());
            if (hall != null & cinema != null & film != null) {
                for (int t = 0; t < session.getSessionTimeNumber(); t++) {
                    stHour = session.getSessionHour().get(t);
                    stMin = session.getSessionMinute().get(t);

                    //setup hour, minute
                    endMin = stMin + film.getFilmMinute() - 1;
                    endHour = stHour + film.getFilmHour();
                    if (endMin >= 60) {
                        endMin -= 60;
                        endHour += 1;
                    }
                    if (endMin < 0) {
                        endMin += 60;
                        endHour -= 1;
                    }

                    for (int j = 0; j < hall.getLength(); j++) {
                        for (int k = 0; k < hall.getWidth(); k++) {

                            //create id
                            id = session.getSessionID().concat(Integer.toString(j + 1)).concat(Integer.toString(k + 1))
                                    .concat(Integer.toString(stHour)).concat(Integer.toString(stMin));

                            //create ticket
                            Ticket ticket = new Ticket(id, session.getSessionID(), session.getSessionFilmID(),
                                    session.getSessionCinema(), session.getSessionHall(), stHour, stMin, endHour, endMin,
                                    j, k, hall.getPrice(j, k), "0");

                            tickets.add(ticket);
                        }
                    }
                }
            }
        }

        return tickets;
    }

//--------------------------------------------choose-ticket-------------------------------------------------------------

    public String[] chooseTicketByTime(ArrayList<Ticket> tickets, String time) {
        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);

        ArrayList<String> ticketList = new ArrayList<>();

        for (Ticket ticket : tickets) {
            if (ticket.getTicketClientID().equals("0")) {
                if (((ticket.getTicketStHour() < hour) && (ticket.getTicketEndHour() > hour)) ||
                        ((ticket.getTicketStHour() == hour) && (ticket.getTicketStMinute() <= minute)) ||
                        ((ticket.getTicketEndHour() == hour) && (ticket.getTicketEndMinute() >= minute)))
                {
                    ticketList.add(ticket.getTicketID());
                }
            }
        }
        if (ticketList.size() == 0) {
            return null;
        }

        String[] result = new String[ticketList.size()];
        for (int i = 0; i < ticketList.size(); i++) {
            result[i] = ticketList.get(i);
        }

        return result;
    }

    public String[] chooseTicketByFilm(ArrayList<Ticket> tickets, String filmID) {
        ArrayList<String> ticketList = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getTicketFilmID().equals(filmID) & ticket.getTicketClientID().equals("0")) {
                ticketList.add(ticket.getTicketID());
            }
        }

        if (ticketList.size() == 0) {
            return null;
        }

        String[] result = new String[ticketList.size()];
        for (int i = 0; i < ticketList.size(); i++) {
            result[i] = ticketList.get(i);
        }

        return result;
    }

    public String[] chooseTicketByPrice(ArrayList<Ticket> tickets, double money) {
        ArrayList<String> ticketList = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getTicketPrice() <= money & ticket.getTicketClientID().equals("0")) {
                ticketList.add(ticket.getTicketID());
            }
        }

        if (ticketList.size() == 0) {
            return null;
        }

        String[] result = new String[ticketList.size()];
        for (int i = 0; i < ticketList.size(); i++) {
            result[i] = ticketList.get(i);
        }

        return result;
    }

//--------------------------------------------get-model-----------------------------------------------------------------

    public Cinema getCinemaFromID(ArrayList<Cinema> cinemas, String id) {
        for (Cinema cinema : cinemas) {
            if (cinema.getCinemaID().equals(id)) {
                return cinema;
            }
        }
        return null;
    }

    public Hall getHallFromID(ArrayList<Hall> halls, String id) {
        for (Hall hall : halls) {
            if (hall.getHallID().equals(id)) {
                return hall;
            }
        }
        return null;
    }

    public Film getFilmFromID(ArrayList<Film> films, String id) {
        for (Film film : films) {
            if (film.getFilmID().equals(id)) {
                return film;
            }
        }
        return null;
    }

    public Session getSessionFromID(ArrayList<Session> sessions, String id) {
        for (Session session : sessions) {
            if (session.getSessionID().equals(id)) {
                return session;
            }
        }
        return null;
    }

    public Ticket getTicketFromID(ArrayList<Ticket> tickets, String id) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketID().equals(id)) {
                return ticket;
            }
        }
        return null;
    }

    public Client getClientFromID(ArrayList<Client> clients, String id) {
        for (Client client : clients) {
            if (client.getID().equals(id)) {
                return client;
            }
        }
        return null;
    }

    public Admin getAdmin(ArrayList<Admin> admins, String mail) {
        for (Admin admin : admins) {
            if (admin.getMail().equals(mail)) {
                return admin;
            }
        }
        return null;
    }

    public Client getClient(ArrayList<Client> clients, String mail) {
        for (Client client : clients) {
            if (client.getMail().equals(mail)) {
                return client;
            }
        }
        return null;
    }

    public Cinema getCinemaFromName(ArrayList<Cinema> cinemas, String name) {
        for (Cinema cinema : cinemas) {
            if (cinema.getCinemaName().equals(name)) {
                return cinema;
            }
        }
        return null;
    }

    public ArrayList<Ticket> findTicket(ArrayList<Ticket> tickets, String clientID) {
        ArrayList<Ticket> tic = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getTicketClientID().equals(clientID)) {
                tic.add(ticket);
            }
        }

        return tic;
    }

    public Session getSessionFromInfo(ArrayList<Session> sessions, String filmID, String cinemaID, String hallID) {
        for (Session session : sessions) {
            if (session.getSessionFilmID().equals(filmID) & session.getSessionCinema().equals(cinemaID) &
            session.getSessionHall().equals(hallID)) {
                return session;
            }
        }
        return null;
    }

//----------------------------------------------------------------------------------------------------------------------

    public ArrayList<ArrayList<Integer>> timeTable(ArrayList<Ticket> tickets, Film film, Cinema cinema, Hall hall) {
        int[][] timeTable = new int[24][60];
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j++) {
                timeTable[i][j] = 0;
            }
        }

        //add time to table
        for (Ticket ticket : tickets) {
            if (ticket.getTicketCinemaID().equals(cinema.getCinemaID()) & ticket.getTicketHallID().equals(hall.getHallID())) {
                if (ticket.getTicketStHour() < ticket.getTicketEndHour()) {
                    for (int j = ticket.getTicketStMinute(); j < 60; j++) {
                        timeTable[ticket.getTicketStHour()][j] = Integer.parseInt(ticket.getTicketFilmID().substring(ticket.getTicketFilmID().length() - 2));
                    }
                    for (int j = 0; j < ticket.getTicketEndMinute() + 1; j++) {
                        timeTable[ticket.getTicketEndHour()][j] = Integer.parseInt(ticket.getTicketFilmID().substring(ticket.getTicketFilmID().length() - 2));
                    }
                    for (int i = ticket.getTicketStHour() + 1; i < ticket.getTicketEndHour(); i++) {
                        for (int j = 0; j < 60; j++) {
                            timeTable[i][j] = Integer.parseInt(ticket.getTicketFilmID().substring(ticket.getTicketFilmID().length() - 2));
                        }
                    }
                }
                else {
                    if (ticket.getTicketStHour() == ticket.getTicketEndHour()) {
                        for (int j = ticket.getTicketStMinute(); j < ticket.getTicketEndMinute() + 1; j++) {
                            timeTable[ticket.getTicketStHour()][j] = Integer.parseInt(ticket.getTicketFilmID().substring(ticket.getTicketFilmID().length() - 2));
                        }
                    }
                }
            }
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int endHour, endMin;
        boolean check;

        for (int i = 7; i < 24; i++) {
            for (int j = 0; j < 60; j++) {

                //setup check
                check = true;

                //setup virtual end time
                endMin = j + film.getFilmMinute() - 1;
                endHour = i + film.getFilmHour();
                if (endMin >= 60) {
                    endMin -= 60;
                    endHour += 1;
                }
                if (endMin < 0) {
                    endMin += 60;
                    endHour -= 1;
                }

                if (endHour >= 24) { continue; }

                //check
                if (i < endHour) {
                    for (int jj = j; jj < 60; jj++) {
                        if (timeTable[i][jj] != 0) {
                            check = false;
                            break;
                        }
                    }
                    if (check) {
                        for (int jj = 0; jj < endMin + 1; jj++) {
                            if (timeTable[endHour][jj] != 0) {
                                check = false;
                                break;
                            }
                        }
                    }
                    if (check) {
                        for (int ii = i + 1; ii < endHour; ii++) {
                            for (int jj = 0; jj < 60; jj++) {
                                if (timeTable[ii][jj] != 0) {
                                    check = false;
                                    break;
                                }
                            }
                        }
                    }
                }
                else {
                    if (i == endHour) {
                        for (int jj = j; jj < endMin + 1; jj++) {
                            if (timeTable[i][jj] == 1) {
                                check = false;
                                break;
                            }
                        }
                    }
                }

                //get result
                if (check) {
                    result.add(new ArrayList<>());
                    result.get(result.size()-1).add(i);
                    result.get(result.size()-1).add(j);
                }
            }
        }

        return result;
    }

    public void addNewTicketFromSession(ArrayList<Ticket> tickets, Session session, Hall hall, Film film,
                                        int stHour, int stMin)
    {
        String id;

        int endMin = stMin + film.getFilmMinute() - 1;
        int endHour = stHour + film.getFilmHour();
        if (endMin >= 60) {
            endMin -= 60;
            endHour += 1;
        }
        if (endMin < 0) {
            endMin += 60;
            endHour -= 1;
        }

        for (int j = 0; j < hall.getLength(); j++) {
            for (int k = 0; k < hall.getWidth(); k++) {
                id = session.getSessionID().concat(Integer.toString(j + 1)).concat(Integer.toString(k + 1))
                        .concat(Integer.toString(stHour)).concat(Integer.toString(stMin));

                tickets.add(new Ticket(id, session.getSessionID(), session.getSessionFilmID(),
                        session.getSessionCinema(), session.getSessionHall(), stHour, stMin, endHour, endMin,
                        j, k, hall.getPrice(j, k), "0"));
            }
        }
    }
}
