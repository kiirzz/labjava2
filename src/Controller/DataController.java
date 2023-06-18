package Controller;

import Models.*;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataController {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    public void openFileToWrite(String fileName) {
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFileToRewrite(String fileName) {
        try {
            File file = new File("/U:/russian/k4/java/lab/lab3/src/Data/"+fileName);
            fileWriter = new FileWriter(file.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterWrite() {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openFileToRead(String fileName) {
        try {
            File file = new File("/U:/russian/k4/java/lab/lab3/src/Data/"+fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            scanner = new Scanner(Paths.get(URI.create("file:///U:/russian/k4/java/lab/lab3/src/Data/"+fileName)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterRead() {
        try {
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//---------------------------------------------READ---------------------------------------------------------------------

    public ArrayList<Hall> readHallFromFile(String fileName) {
        openFileToRead(fileName);

        ArrayList<Hall> halls = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Hall hall = creatHallFromData(data);
            for (int i = 0; i < hall.getLength(); i++) {
                String[] rowData = scanner.nextLine().split("\\|");
                for (int j = 0; j < rowData.length; j++) {
                    hall.setPrice(i, j, Double.parseDouble(rowData[j]));
                }
            }
            halls.add(hall);
        }

        closeFileAfterRead();

        return halls;
    }

    public Hall creatHallFromData(String data) {
        String[] datas = data.split("\\|");

        Hall hall = new Hall();
        hall.setInfo(datas[0], Integer.parseInt(datas[1]), Integer.parseInt(datas[2]));

        return hall;
    }

    public ArrayList<Cinema> readCinemaFromFile(String fileName) {
        var halls = readHallFromFile("Hall.DAT");

        openFileToRead(fileName);
        ArrayList<Cinema> cinemas = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Cinema cinema = createCinemaFromData(data, halls);

            cinemas.add(cinema);
        }

        closeFileAfterRead();
        return cinemas;
    }

    public Cinema createCinemaFromData(String data, ArrayList<Hall> halls) {
        String[] datas = data.split("\\|");

        Cinema cinema = new Cinema();
        cinema.setCinemaBasicInfo(datas[0], datas[1], datas[2]);

        for (int i = 3; i < datas.length; i++) {
            if (datas[i].length() == 8) {
                cinema.addCinemaHall(getHall(halls, datas[i]));
            }
            else {
                cinema.addCinemaFormat(datas[i]);
            }
        }

        return cinema;
    }

    public static Hall getHall(ArrayList<Hall> halls, String hallID) {
        for (Hall hall : halls) {
            if (hall.getHallID().equals(hallID)) {
                return hall;
            }
        }
        return null;
    }

    public ArrayList<Film> readFilmFromFile(String fileName) {
        openFileToRead(fileName);

        ArrayList<Film> films = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();

            Film film = createFilmFromData(data);
            films.add(film);
        }

        closeFileAfterRead();
        return films;
    }

    public Film createFilmFromData(String data) {
        String[] datas = data.split("\\|");

        Film film = new Film();
        film.setFilmInfo(datas[0], datas[1], Integer.parseInt(datas[2]), datas[3], datas[4],
                Integer.parseInt(datas[5]));

        return film;
    }

    public ArrayList<Session> readSessionFromFile(String fileName) {
        openFileToRead(fileName);

        ArrayList<Session> sessions = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();

            Session session = createSessionFromData(data);
            sessions.add(session);
        }

        closeFileAfterRead();
        return sessions;
    }

    public Session createSessionFromData(String data) {
        String[] datas = data.split("\\|");

        Session session = new Session();
        session.setSessionBasicInfo(datas[0], datas[1], datas[2], datas[3]);
        for (int i = 4; i < datas.length; i++) {
            session.addSessionTime(datas[i]);
        }

        return session;
    }

    public ArrayList<Client> readClientFromFile(String fileName) {
        openFileToRead(fileName);

        ArrayList<Client> clients = new ArrayList<>();

        while(scanner.hasNextLine()) {
            String datas = scanner.nextLine();
            Client client = createClientFromData(datas);

            clients.add(client);
        }

        closeFileAfterRead();
        return clients;
    }

    public Client createClientFromData(String datas) {
        String[] data = datas.split("\\|");
        return new Client(data[0], data[1], data[2], data[3], data[4], Double.parseDouble(data[5]));
    }

    public ArrayList<Admin> readAdminFromFile(String fileName) {
        openFileToRead(fileName);

        ArrayList<Admin> admins = new ArrayList<>();

        while(scanner.hasNextLine()) {
            String datas = scanner.nextLine();
            Admin admin = createAdminFromData(datas);

            admins.add(admin);
        }

        closeFileAfterRead();
        return admins;
    }

    public Admin createAdminFromData(String datas) {
        String[] data = datas.split("\\|");
        return new Admin(data[0], data[1], data[2], data[3], data[4]);
    }

    public ArrayList<Ticket> readTicket(DataUtil util, String fileName, ArrayList<Session> sessions,
                                        ArrayList<Cinema> cinemas, ArrayList<Hall> halls, ArrayList<Film> films)
    {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            File file = new File("/U:/russian/k4/java/lab/lab3/src/Data/"+fileName);
            if (!file.exists()) {
                file.createNewFile();
                tickets = util.createTicketsFromData(sessions, cinemas, halls, films);
                writeTicketToFile(tickets, fileName);
            }
            else {
                scanner = new Scanner(Paths.get(URI.create("file:///U:/russian/k4/java/lab/lab3/src/Data/"+fileName)), StandardCharsets.UTF_8);
                while (scanner.hasNextLine()) {
                    String datas = scanner.nextLine();
                    Ticket ticket = createTicketFromData(datas);

                    tickets.add(ticket);
                }
                closeFileAfterRead();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public Ticket createTicketFromData(String datas) {
        String[] data = datas.split("\\|");
        return new Ticket(data[0], data[1], data[2], data[3], data[4],
                Integer.parseInt(data[5]), Integer.parseInt(data[6]),
                Integer.parseInt(data[7]), Integer.parseInt(data[8]),
                Integer.parseInt(data[9]), Integer.parseInt(data[10]), Double.parseDouble(data[11]),
                data[12]);
    }

    public long readNumberTicketSold(String fileName) {
        openFileToRead(fileName);

        long res = 0;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            try {
                res = Long.parseLong(data);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        closeFileAfterRead();
        return res;
    }

    public double readSessionRevenue(String fileName) {
        openFileToRead(fileName);

        double res = 0;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            try {
                res = Double.parseDouble(data);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        closeFileAfterRead();
        return res;
    }

//---------------------------------------------WRITE--------------------------------------------------------------------

    public void writeHallToFile(ArrayList<Hall> list, String fileName) {
        openFileToRewrite(fileName);

        for (Hall hall : list) {
            printWriter.println(hall.getHallID() + "|" + hall.getLength() + "|" + hall.getWidth());
            for (int i = 0; i < hall.getLength(); i++) {
                for (int j = 0; j < hall.getWidth(); j++) {
                    if (j != 0) { printWriter.print("|"); }
                    printWriter.print(hall.getPrice(i, j));
                }
                printWriter.print("\n");
            }
        }

        closeFileAfterWrite();
    }

    public void writeCinemaToFile(ArrayList<Cinema> list, String fileName) {
        openFileToRewrite(fileName);

        for (Cinema cinema : list) {
            printWriter.print(cinema.getCinemaID() + "|" + cinema.getCinemaName() + "|" + cinema.getCinemaAddress());
            for (int i = 0; i < cinema.getCinemaFormatNumber(); i++) {
                printWriter.print("|" + cinema.getCinemaFormat(i));
            }
            for (int i = 0; i < cinema.getCinemaCapacity(); i++) {
                printWriter.print("|" + cinema.getCinemaHall().get(i).getHallID());
            }
            printWriter.print("\n");
        }

        closeFileAfterWrite();
    }

    public void writeFilmToFile(ArrayList<Film> list, String fileName) {
        openFileToRewrite(fileName);

        for (Film film : list) {
            printWriter.print(film.getFilmID() + "|" + film.getFilmTitle() + "|" + film.getFilmYear() + "|" +
                    film.getFilmGenre() + "|" + film.getFilmFormat() + "|" + film.getFilmDuration());
            printWriter.print("\n");
        }

        closeFileAfterWrite();
    }

    public void writeSessionToFile(ArrayList<Session> list, String fileName) {
        openFileToRewrite(fileName);

        String hour, min;

        for (Session session : list) {
            printWriter.print(session.getSessionID() + "|" + session.getSessionCinema() + "|" + session.getSessionHall()
                + "|" + session.getSessionFilmID());
            for (int i = 0; i < session.getSessionTimeNumber(); i++) {
                hour = Integer.toString(session.getSessionHour().get(i));
                min = Integer.toString(session.getSessionMinute().get(i));
                if (hour.length() == 1) { hour = "0".concat(hour); }
                if (min.length() == 1) { min = "0".concat(min); }
                printWriter.print("|" + hour + ":" + min);
            }
            printWriter.print("\n");
        }

        closeFileAfterWrite();
    }

    public void writeTicketToFile(ArrayList<Ticket> list, String fileName) {
        openFileToRewrite(fileName);

        for (Ticket ticket : list) {
            printWriter.print(ticket.getTicketID() + "|" + ticket.getTicketSessionID() + "|" + ticket.getTicketFilmID()
                    + "|" + ticket.getTicketCinemaID() + "|" + ticket.getTicketHallID()
                    + "|" + ticket.getTicketStHour() + "|" + ticket.getTicketStMinute()
                    + "|" + ticket.getTicketEndHour() + "|" + ticket.getTicketEndMinute()
                    + "|" + ticket.getTicketHallRow() + "|" + ticket.getTicketHallColumn() + "|" + ticket.getTicketPrice()
                    + "|" + ticket.getTicketClientID());
            printWriter.print("\n");
        }

        closeFileAfterWrite();
    }

    public void writeClientToFile(ArrayList<Client> list, String fileName) {
        openFileToRewrite(fileName);

        for (Client client : list) {
            printWriter.print(client.getID() + "|" + client.getName() + "|" + client.getPhoneNumber()
                    + "|" + client.getMail() + "|" + client.getPassword() + "|" + client.getClientBudget());
            printWriter.print("\n");
        }

        closeFileAfterWrite();
    }

    public void writeAdminToFile(ArrayList<Admin> list, String fileName) {
        openFileToRewrite(fileName);

        for (Admin admin : list) {
            printWriter.print(admin.getID() + "|" + admin.getName() + "|" + admin.getPhoneNumber()
                    + "|" + admin.getMail() + "|" + admin.getPassword());
            printWriter.print("\n");
        }

        closeFileAfterWrite();
    }

    public void writeNumberTicketSoldToFile(long numberTSold,String fileName) {
        openFileToRewrite(fileName);

        printWriter.print(numberTSold);

        closeFileAfterWrite();
    }

    public void writeSessionRevenue(double sessionRevenue,String fileName) {
        openFileToRewrite(fileName);

        printWriter.print(sessionRevenue);

        closeFileAfterWrite();
    }

//----------------------------------------------------------------------------------------------------------------------

    public double[][] readPriceHallFromTerminal(Scanner sc, int len, int wid) {
        double[][] price = new double[len][wid];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < wid; j++) {
                price[i][j] = sc.nextDouble();
            }
        }

        return price;
    }
}
