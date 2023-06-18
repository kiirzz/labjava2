package View;

import Models.*;
import Controller.*;

import java.util.ArrayList;
import java.util.Scanner;

public class clientView {
    public void render() {

        int chooseNumber;
        var cinemaFileName = "Cinema.DAT";
        var hallFileName = "Hall.DAT";
        var filmFileName = "Film.DAT";
        var sessionFileName = "Session.DAT";
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
//        var admins = controller.readAdminFromFile(adminFileName);
        var clients = controller.readClientFromFile(clientFileName);
        var tickets = controller.readTicket(util, ticketFileName, sessions, cinemas, halls, films);

        Scanner scanner = new Scanner(System.in);

        ArrayList<Ticket> tic;
        Ticket ticket;
        String mail, password, data;
        double money;
        int intData;

        boolean login = false;

        Outer:
        while (true) {
            System.out.println("gmail Client (0 to back):");
            mail = scanner.next();
            if (mail.equals("0")) {
                System.out.println("______________________________________________");
                break;
            }
            Client client = util.getClient(clients, mail);
            if (client == null) {
                System.out.println("Client is not exist!");
                continue;
            }

            do {
                System.out.println("password:");
                password = scanner.next();
                if (password.equals("0")) { continue Outer; }

                if (client.getPassword().equals(password)) { login = true; }
                else { System.out.println("Wrong password!"); }
            } while (!login);

            while (login) {
                System.out.println("______________________________________________");
                System.out.println("0. Logout");
                System.out.println("1. See budget");
                System.out.println("2. Recharge budget");
                System.out.println("3. Buy ticket");
                System.out.println("4. Cancel ticket");
                System.out.println("5. See tickets bought");

                chooseNumber = scanner.nextInt();

                switch (chooseNumber) {
                    case 0 -> {
                        System.out.println("______________________________________________");
                        login = false;
                    }
                    case 1 -> System.out.println("Your budget: $" + client.getClientBudget());
                    case 2 -> {
                        System.out.println("Recharge: $");
                        money = scanner.nextDouble();
                        if (money > 0) {
                            client.rechargeMoney(money);

                            controller.writeClientToFile(clients, clientFileName);
                            System.out.println("Recharge successfully!");
                        } else {
                            System.out.println("Recharge failed! Please put available amount of money!");
                        }
                    }
                    case 3 -> {
                        System.out.println("Choose your option:");
                        System.out.println("0. Back");
                        System.out.println("1. Choose ticket by time");
                        System.out.println("2. Choose ticket by film");
                        System.out.println("3. Choose ticket by price");
                        chooseNumber = scanner.nextInt();
                        String[] chooseArray;
                        switch (chooseNumber) {
                            case 0 -> System.out.println("______________________________________________");
                            case 1 -> {
                                do {
                                    System.out.println("Put time:");
                                    data = scanner.next();
                                    if (!util.checkTime(data)) {
                                        System.out.println("Please write the correct time!");
                                    }
                                } while (!util.checkTime(data));
                                chooseArray = util.chooseTicketByTime(tickets, data);
                                if (chooseArray != null) {
                                    System.out.println("No. | " + "_Name | " + "_Time st | " + "_Time end | " + "_Cinema | "
                                            + "_Hall No. | " + "_Chair | " + "_Price| ");
                                    for (int i = 0; i < chooseArray.length; i++) {
                                        ticket = util.getTicketFromID(tickets, chooseArray[i]);
                                        System.out.println(i + 1 + ". " + util.getFilmFromID(films, ticket.getTicketFilmID()).getFilmTitle()
                                                + " | " + ticket.getTicketStHour() + ":" + ticket.getTicketStMinute()
                                                + " | " + ticket.getTicketEndHour() + ":" + ticket.getTicketEndMinute()
                                                + " | " + util.getCinemaFromID(cinemas, ticket.getTicketCinemaID()).getCinemaName()
                                                + " | " + util.getHallFromID(halls, ticket.getTicketHallID()).getHallNO()
                                                + " | " + ticket.getTicketHallRow() + "-" + ticket.getTicketHallColumn()
                                                + " | $" + ticket.getTicketPrice());
                                    }
                                    while (true) {
                                        System.out.println("Choose your ticket (0 to back):");
                                        chooseNumber = scanner.nextInt();
                                        if (chooseNumber > chooseArray.length | chooseNumber < 0) {
                                            System.out.println("Please choose available ticket!");
                                        } else {
                                            if (chooseNumber != 0) {
                                                ticket = util.getTicketFromID(tickets, chooseArray[chooseNumber - 1]);
                                                if (client.getClientBudget() >= ticket.getTicketPrice()) {
                                                    client.purchaseMoney(ticket.getTicketPrice());
                                                    ticket.buyTicket(client.getID());
                                                    System.out.println("Buy successfully!");

                                                    controller.writeClientToFile(clients, clientFileName);
                                                    controller.writeTicketToFile(tickets, ticketFileName);
                                                    controller.writeNumberTicketSoldToFile(controller.readNumberTicketSold(numberTSold) + 1, numberTSold);
                                                    controller.writeSessionRevenue(controller.readSessionRevenue(sesRevenue) + ticket.getTicketPrice(), sesRevenue);

                                                    break;
                                                } else {
                                                    System.out.println("You don't have enough money!");
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("No tickets available!");
                                }
                            }
                            case 2 -> {
                                for (int i = 0; i < films.size(); i++) {
                                    System.out.println(i + 1 + ". " + films.get(i).getFilmTitle());
                                }
                                do {
                                    System.out.println("Choose film:");
                                    intData = scanner.nextInt();
                                    if (intData <= 0 | intData > films.size()) {
                                        System.out.println("Please choose available number!");
                                    }
                                } while (intData <= 0 | intData > films.size());
                                chooseArray = util.chooseTicketByFilm(tickets, films.get(intData - 1).getFilmID());
                                if (chooseArray != null) {
                                    System.out.println("No. | " + "_Name | " + "_Time st | " + "_Time end | " + "_Cinema | "
                                            + "_Hall No. | " + "_Chair | " + "_Price| ");
                                    for (int i = 0; i < chooseArray.length; i++) {
                                        ticket = util.getTicketFromID(tickets, chooseArray[i]);
                                        System.out.println(i + 1 + ". " + util.getFilmFromID(films, ticket.getTicketFilmID()).getFilmTitle()
                                                + " | " + ticket.getTicketStHour() + ":" + ticket.getTicketStMinute()
                                                + " | " + ticket.getTicketEndHour() + ":" + ticket.getTicketEndMinute()
                                                + " | " + util.getCinemaFromID(cinemas, ticket.getTicketCinemaID()).getCinemaName()
                                                + " | " + util.getHallFromID(halls, ticket.getTicketHallID()).getHallNO()
                                                + " | " + ticket.getTicketHallRow() + "-" + ticket.getTicketHallColumn()
                                                + " | $" + ticket.getTicketPrice());
                                    }
                                    while (true) {
                                        System.out.println("Choose your ticket (0 to back):");
                                        chooseNumber = scanner.nextInt();
                                        if (chooseNumber > chooseArray.length | chooseNumber < 0) {
                                            System.out.println("Please choose available ticket!");
                                        } else {
                                            if (chooseNumber != 0) {
                                                ticket = util.getTicketFromID(tickets, chooseArray[chooseNumber - 1]);
                                                if (client.getClientBudget() >= ticket.getTicketPrice()) {
                                                    client.purchaseMoney(ticket.getTicketPrice());
                                                    ticket.buyTicket(client.getID());
                                                    System.out.println("Buy successfully!");

                                                    controller.writeClientToFile(clients, clientFileName);
                                                    controller.writeTicketToFile(tickets, ticketFileName);
                                                    controller.writeNumberTicketSoldToFile(controller.readNumberTicketSold(numberTSold) + 1, numberTSold);
                                                    controller.writeSessionRevenue(controller.readSessionRevenue(sesRevenue) + ticket.getTicketPrice(), sesRevenue);

                                                    break;
                                                } else {
                                                    System.out.println("You don't have enough money!");
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("No tickets available!");
                                }
                            }
                            case 3 -> {
                                System.out.println("Put price:");
                                money = scanner.nextDouble();
                                chooseArray = util.chooseTicketByPrice(tickets, money);
                                if (chooseArray != null) {
                                    System.out.println("No. | " + "_Name | " + "_Time st | " + "_Time end | " + "_Cinema | "
                                            + "_Hall No. | " + "_Chair | " + "_Price| ");
                                    for (int i = 0; i < chooseArray.length; i++) {
                                        ticket = util.getTicketFromID(tickets, chooseArray[i]);
                                        System.out.println(i + 1 + ". " + util.getFilmFromID(films, ticket.getTicketFilmID()).getFilmTitle()
                                                + " | " + ticket.getTicketStHour() + ":" + ticket.getTicketStMinute()
                                                + " | " + ticket.getTicketEndHour() + ":" + ticket.getTicketEndMinute()
                                                + " | " + util.getCinemaFromID(cinemas, ticket.getTicketCinemaID()).getCinemaName()
                                                + " | " + util.getHallFromID(halls, ticket.getTicketHallID()).getHallNO()
                                                + " | " + ticket.getTicketHallRow() + "-" + ticket.getTicketHallColumn()
                                                + " | $" + ticket.getTicketPrice());
                                    }
                                    while (true) {
                                        System.out.println("Choose your ticket (0 to back):");
                                        chooseNumber = scanner.nextInt();
                                        if (chooseNumber > chooseArray.length | chooseNumber < 0) {
                                            System.out.println("Please choose available ticket!");
                                        } else {
                                            if (chooseNumber != 0) {
                                                ticket = util.getTicketFromID(tickets, chooseArray[chooseNumber - 1]);
                                                if (client.getClientBudget() >= ticket.getTicketPrice()) {
                                                    client.purchaseMoney(ticket.getTicketPrice());
                                                    ticket.buyTicket(client.getID());
                                                    System.out.println("Buy successfully!");

                                                    controller.writeClientToFile(clients, clientFileName);
                                                    controller.writeTicketToFile(tickets, ticketFileName);
                                                    controller.writeNumberTicketSoldToFile(controller.readNumberTicketSold(numberTSold) + 1, numberTSold);
                                                    controller.writeSessionRevenue(controller.readSessionRevenue(sesRevenue) + ticket.getTicketPrice(), sesRevenue);

                                                    break;
                                                } else {
                                                    System.out.println("You don't have enough money!");
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("No tickets available!");
                                }
                            }
                            default -> System.out.println("Please choose right number!");
                        }
                    }
                    case 4 -> {
                        tic = util.findTicket(tickets, client.getID());
                        if (tic.isEmpty()) {
                            System.out.println("You didn't buy any ticket!");
                        }
                        else {
                            System.out.println("Your tickets: ");
                            for (int i = 0; i < tic.size(); i++) {
                                System.out.println(i + 1 + ". " + util.getFilmFromID(films, tic.get(i).getTicketFilmID()).getFilmTitle()
                                        + " | " + tic.get(i).getTicketStHour() + ":" + tic.get(i).getTicketStMinute()
                                        + " | " + tic.get(i).getTicketEndHour() + ":" + tic.get(i).getTicketEndMinute()
                                        + " | " + util.getCinemaFromID(cinemas, tic.get(i).getTicketCinemaID()).getCinemaName()
                                        + " | " + util.getHallFromID(halls, tic.get(i).getTicketHallID()).getHallNO()
                                        + " | " + tic.get(i).getTicketHallRow() + "-" + tic.get(i).getTicketHallColumn()
                                        + " | $" + tic.get(i).getTicketPrice());
                            }
                        }
                        System.out.println("Choose ticket you want to cancel (0 to back): ");
                        chooseNumber = scanner.nextInt();
                        if (chooseNumber > tic.size() | chooseNumber < 0) {
                            System.out.println("Please choose available ticket!");
                        } else {
                            if (chooseNumber != 0) {
                                ticket = util.getTicketFromID(tickets, tic.get(chooseNumber-1).getTicketID());
                                ticket.cancelTicket();
                                client.rechargeMoney(ticket.getTicketPrice());
                                System.out.println("Cancel successfully!");

                                controller.writeClientToFile(clients, clientFileName);
                                controller.writeTicketToFile(tickets, ticketFileName);
                                controller.writeNumberTicketSoldToFile(controller.readNumberTicketSold(numberTSold) - 1, numberTSold);
                                controller.writeSessionRevenue(controller.readSessionRevenue(sesRevenue) - ticket.getTicketPrice(), sesRevenue);
                            }
                        }
                    }
                    case 5 -> {
                        tic = util.findTicket(tickets, client.getID());
                        if (tic.isEmpty()) {
                            System.out.println("You didn't buy any ticket!");
                        }
                        else {
                            System.out.println("Your tickets: ");
                            for (int i = 0; i < tic.size(); i++) {
                                System.out.println(i + 1 + ". " + util.getFilmFromID(films, tic.get(i).getTicketFilmID()).getFilmTitle()
                                        + " | " + tic.get(i).getTicketStHour() + ":" + tic.get(i).getTicketStMinute()
                                        + " | " + tic.get(i).getTicketEndHour() + ":" + tic.get(i).getTicketEndMinute()
                                        + " | " + util.getCinemaFromID(cinemas, tic.get(i).getTicketCinemaID()).getCinemaName()
                                        + " | " + util.getHallFromID(halls, tic.get(i).getTicketHallID()).getHallNO()
                                        + " | " + tic.get(i).getTicketHallRow() + "-" + tic.get(i).getTicketHallColumn()
                                        + " | $" + tic.get(i).getTicketPrice());
                            }
                        }
                    }
                    default -> System.out.println("Please choose right number!");
                }
            }
        }
    }
}
