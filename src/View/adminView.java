package View;

import Models.*;
import Controller.*;

import java.util.ArrayList;
import java.util.Scanner;

public class adminView {
    public void render() {

        int chooseNumber;
        var cinemaFileName = "Cinema.DAT";
        var hallFileName = "Hall.DAT";
        var filmFileName = "Film.DAT";
        var sessionFileName = "Session.DAT";
        var adminFileName = "Admin.DAT";
        var clientFileName = "Client.DAT";
        var ticketFileName = "Ticket.DAT";
        var numberTSold = "Number_ticket_sold.DAT";
        var sesRevenue = "Sessions_revenue.DAT";

        var controller = new DataController();
        var util = new DataUtil();

        var halls = controller.readHallFromFile(hallFileName);
        var cinemas = controller.readCinemaFromFile(cinemaFileName);
        var films = controller.readFilmFromFile(filmFileName);
        var sessions = controller.readSessionFromFile(sessionFileName);
        var admins = controller.readAdminFromFile(adminFileName);
        var clients = controller.readClientFromFile(clientFileName);
        var tickets = controller.readTicket(util, ticketFileName, sessions, cinemas, halls, films);

        Scanner scanner = new Scanner(System.in);

        String mail, password, data;
        int intData;

        boolean login = false, check;

        Outer:
        while (true) {
            System.out.println("gmail Admin (0 to back):");
            mail = scanner.next();
            if (mail.equals("0")) {
                System.out.println("______________________________________________");
                break;
            }
            Admin admin = util.getAdmin(admins, mail);
            if (admin == null) {
                System.out.println("Admin is not exist!");
                continue;
            }

            do {
                System.out.println("password:");
                password = scanner.next();
                if (password.equals("0")) { continue Outer; }

                if (admin.getPassword().equals(password)) { login = true; }
                else { System.out.println("Wrong password!"); }
            } while (!login);

            while (login) {
                System.out.println("______________________________________________");
                System.out.println("0. Logout");
                System.out.println("1. Add new Cinema");
                System.out.println("2. Add new Film");
                System.out.println("3. Add new Hall");
                System.out.println("4. Add new Session");
                System.out.println("5. Add new Admin");
                System.out.println("6. Add new Client");
                System.out.println("7.Total revenue for all sessions");
                System.out.println("8.Total number of tickets sold");

                chooseNumber = scanner.nextInt();

                switch (chooseNumber) {
                    case 0 -> {
                        System.out.println("______________________________________________");
                        login = false;
                    }
                    case 1 -> {
                        System.out.println("Put cinema's name|address|formats (0 to back):");
                        data = scanner.next();
                        if (!data.equals("0")) {
                            cinemas.add(util.addNewCinema(cinemas.size(), data));

                            System.out.println("Add new Cinema successfully!");

                            controller.writeCinemaToFile(cinemas, cinemaFileName);
                        }
                    }
                    case 2 -> {
                        System.out.println("Put film's title|year|genre|format|duration (0 to back):");
                        data = scanner.next();
                        if (!data.equals("0")) {
                            films.add(util.addNewFilm(films.size(), data));

                            System.out.println("Add new Film successfully!");

                            controller.writeFilmToFile(films, filmFileName);
                        }
                    }
                    case 3 -> {
                        for (Cinema cinema : cinemas) {
                            System.out.println(cinema.getCinemaName());
                        }
                        System.out.println("Put Cinema's name|hall's length|hall's width (0 to back):");
                        data = scanner.next();
                        if (!data.equals("0")) {
                            String[] datas = data.split("\\|");
                            System.out.println("Put hall's price:");
                            double[][] price = controller.readPriceHallFromTerminal(
                                    scanner, Integer.parseInt(datas[1]), Integer.parseInt(datas[2]));

                            Cinema cin = util.getCinemaFromName(cinemas, datas[0]);
                            Hall hall = util.addNewHall(cin, data, price);
                            cin.addCinemaHall(hall);
                            hall.addHallCinema(cin);
                            halls.add(hall);
                            System.out.println("Add new Hall successfully!");

                            controller.writeHallToFile(halls, hallFileName);
                            controller.writeCinemaToFile(cinemas, cinemaFileName);
                        }
                    }
                    case 4 -> {
                        for (int i = 0; i < films.size(); i++) {
                            System.out.print(i + 1 + ". " + films.get(i).getFilmTitle() + "|");
                        }
                        System.out.print("\n");
                        for (int i = 0; i < cinemas.size(); i++) {
                            System.out.print(i + 1 + ". " + cinemas.get(i).getCinemaName() + "|");
                        }
                        System.out.print("\n");

                        Outer1:
                        while (true) {
                            System.out.println("Choose Film|Cinema (0 to back):");
                            data = scanner.next();
                            if (data.equals("0")) {
                                break;
                            }
                            else {
                                String[] datas = data.split("\\|");
                                if (Integer.parseInt(datas[0]) > films.size() | Integer.parseInt(datas[1]) > cinemas.size()) {
                                    System.out.println("Please choose available number!");
                                } else {
                                    Film filmData = films.get(Integer.parseInt(datas[0]) - 1);
                                    Cinema cinData = cinemas.get(Integer.parseInt(datas[1]) - 1);

                                    // check format
                                    check = false;
                                    for (int i = 0; i < cinData.getCinemaFormatNumber(); i++) {
                                        if (cinData.getCinemaFormat().get(i).equals(filmData.getFilmFormat())) {
                                            check = true;
                                            break;
                                        }
                                    }

                                    //format ok
                                    if (check) {
                                        Hall hallData;
                                        System.out.println(cinData.getCinemaName() + "'s Halls :");
                                        for (int i = 0; i < cinData.getCinemaCapacity(); i++) {
                                            System.out.print(i + 1 + ". " + cinData.getCinemaHall().get(i).getLength()
                                                    + "-" + cinData.getCinemaHall().get(i).getWidth() + "|");
                                        }
                                        System.out.print("\n");

                                        //choose Hall
                                        while (true) {
                                            System.out.println("Choose Hall (0 to back):");
                                            intData = scanner.nextInt();
                                            if (intData == 0) {
                                                System.out.println("______________________________________________");
                                                continue Outer1;
                                            }
                                            if (intData < 0 | intData > cinData.getCinemaCapacity()) {
                                                System.out.println("Please choose available number!");
                                                System.out.println("______________________________________________");
                                            }
                                            if (intData > 0 & intData <= cinData.getCinemaCapacity()) {
                                                hallData = cinData.getCinemaHall().get(intData - 1);
                                                break;
                                            }
                                        }

                                        //find time
                                        ArrayList<ArrayList<Integer>> result = util.timeTable(tickets, filmData, cinData, hallData);
                                        if (result.size() == 0) {
                                            System.out.println("No time available!");
                                            break;
                                        }
                                        else {
                                            System.out.println(result.size());
                                            int temp = 0;
                                            for (int i = 0; i < result.size(); i++) {
                                                System.out.print(i+1 + ". " + result.get(i).get(0) + ":" + result.get(i).get(1) + "\t\t");
                                                temp += 1;
                                                if (temp == 12) {
                                                    System.out.print("\n");
                                                    temp = 0;
                                                }
                                            }
                                            if (temp != 0) { System.out.print("\n"); }
                                            System.out.println("Choose time (0 to back):");
                                            chooseNumber = scanner.nextInt();
                                            if (chooseNumber != 0) {
                                                if (chooseNumber < 0 | chooseNumber > result.size()) {
                                                    System.out.println("Please choose available number!");
                                                }
                                                else {
                                                    Session sesData = util.getSessionFromInfo(sessions, filmData.getFilmID(),
                                                            cinData.getCinemaID(), hallData.getHallID());
                                                    if (sesData == null) {
                                                        sessions.add(util.addNewSession(cinData, hallData, filmData,
                                                                result.get(chooseNumber-1).get(0), result.get(chooseNumber-1).get(1)));
                                                    }
                                                    else {
                                                        sesData.addSessionTime(result.get(chooseNumber-1).get(0), result.get(chooseNumber-1).get(1));
                                                    }
                                                    System.out.println("Add new Session successfully!");

                                                    controller.writeSessionToFile(sessions, sessionFileName);
                                                    util.addNewTicketFromSession(tickets, sesData, hallData, filmData,
                                                            result.get(chooseNumber-1).get(0), result.get(chooseNumber-1).get(1));
                                                    controller.writeTicketToFile(tickets, ticketFileName);
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    // format not ok
                                    else {
                                        System.out.println("Cinema cannot display Film's format!");
                                    }
                                }
                            }
                        }
                    }
                    case 5 -> {
                        System.out.println("Put Admin's name|phone number|mail|new password (0 to back):");
                        data = scanner.next();
                        if (!data.equals("0")) {
                            String[] datas = data.split("\\|");
                            if (util.getAdmin(admins, datas[0]) != null) {
                                System.out.println("Admin is already exist!");
                            } else {
                                admins.add(util.addNewAdmin(admins.size(), data));
                                controller.writeAdminToFile(admins, adminFileName);
                                admins = controller.readAdminFromFile(adminFileName);

                                System.out.println("Add new Admin successfully!");

                                controller.writeAdminToFile(admins, adminFileName);
                            }
                        }
                    }
                    case 6 -> {
                        System.out.println("Put Client's name|phone number|mail|new password (0 to back):");
                        data = scanner.next();
                        if (!data.equals("0")) {
                            String[] datas = data.split("\\|");
                            if (util.getClient(clients, datas[0]) != null) {
                                System.out.println("Client is already exist!");
                            } else {
                                clients.add(util.addNewClient(clients.size(), data));
                                controller.writeClientToFile(clients, adminFileName);
                                clients = controller.readClientFromFile(clientFileName);

                                System.out.println("Add new Client successfully!");

                                controller.writeClientToFile(clients, clientFileName);
                            }
                        }
                    }
                    case 7 -> System.out.println("Number tickets sold: " + controller.readNumberTicketSold(numberTSold));
                    case 8 -> System.out.println("Revenue: " + controller.readSessionRevenue(sesRevenue));
                    default -> System.out.println("Please choose right number!");
                }
            }
        }
    }
}
