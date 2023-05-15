package View;

import Models.*;
import View.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class index {
    public static void main(String[] args) {                                                                                //---main---
        HashMap<String, Integer> filmName = new HashMap<>();
        HashMap<Integer, Hall> halls = new HashMap<>();

        int cinemaNumber = 5;
        Cinema[] cinemas = new Cinema[cinemaNumber];

        String[] format = {"2D","3D"};                                                                                      //cinema 0
        input.cinemaInput(cinemas, 0, "35mm", "Pokrovka", 3, 2, format);
        double[][] price = { {1, 2, 1}, {1, 2, 1}, {1, 2, 1} };                                                             //hall 0
        input.hallInput(cinemas, 0, 0, 3, 3, price);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 1
        input.hallInput(cinemas, 0, 1, 3, 3, price);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 2
        input.hallInput(cinemas, 0, 2, 3, 3, price);

        format = new String[]{"2D","3D"};                                                                                   //cinema 1
        input.cinemaInput(cinemas, 1, "Secret", "Volochayev", 3, 2, format);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 0
        input.hallInput(cinemas, 1, 0, 3, 3, price);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 1
        input.hallInput(cinemas, 1, 1, 3, 3, price);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 2
        input.hallInput(cinemas, 1, 2, 3, 3, price);

        format = new String[]{"2D","3D"};                                                                                   //cinema 2
        input.cinemaInput(cinemas, 2, "GUM", "Tver", 3, 2, format);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 0
        input.hallInput(cinemas, 2, 0, 3, 3, price);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 1
        input.hallInput(cinemas, 2, 1, 3, 3, price);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 2
        input.hallInput(cinemas, 2, 2, 3, 3, price);

        format = new String[]{"2D","3D"};                                                                                   //cinema 3
        input.cinemaInput(cinemas, 3, "Pioneer", "Kutuzov", 3, 2, format);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 0
        input.hallInput(cinemas, 3, 0, 3, 3, price);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 1
        input.hallInput(cinemas, 3, 1, 3, 3, price);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 2
        input.hallInput(cinemas, 3, 2, 3, 3, price);

        format = new String[]{"2D","3D"};                                                                                   //cinema 4
        input.cinemaInput(cinemas, 4, "Illusion", "Tagan", 3, 2, format);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 0
        input.hallInput(cinemas, 4, 0, 3, 3, price);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 1
        input.hallInput(cinemas, 4, 1, 3, 3, price);
        price = new double[][]{{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};                                                            //hall 2
        input.hallInput(cinemas, 4, 2, 3, 3, price);

        int hallNumber = 0;
        for (int i = 0; i < cinemaNumber; i++) {
            hallNumber += cinemas[i].getCinemaCapacity();
            for (int j = 0; j < cinemas[i].getCinemaCapacity(); j++) {
                halls.put(cinemas[i].getCinemaHall()[j].getHallID(), cinemas[i].getCinemaHall()[j]);
            }
        }

        int filmNumber = 10;
        Film[] films = new Film[filmNumber];
        input.filmInput(films,0,"Black Widow",2021,"Action","2D", 150);                    //film 0
        filmName.put("Black Widow",0);
        input.filmInput(films,1,"Doctor Strange",2016,"Action","2D", 150);                 //film 1
        filmName.put("Doctor Strange",1);
        input.filmInput(films,2,"Your name",2018,"Anime","3D",150);                        //film 2
        filmName.put("Your name",2);
        input.filmInput(films,3,"Dragon Balls",2000,"Anime","2D",150);                     //film 3
        filmName.put("Dragon Balls",3);
        input.filmInput(films,4,"Batman",2008,"Action","2D",150);                          //film 4
        filmName.put("Batman",4);
        input.filmInput(films,5,"Superman",2001,"Action","2D",150);                        //film 5
        filmName.put("Superman",5);
        input.filmInput(films,6,"Masha i medved",2001,"Cartoon","2D",150);                 //film 6
        filmName.put("Masha i medved",6);
        input.filmInput(films,7,"Nu pogodi",1969,"Cartoon","2D",150);                      //film 7
        filmName.put("Nu pogodi",7);
        input.filmInput(films,8,"Titanic",1997,"Romance","2D",150);                        //film 8
        filmName.put("Titanic",8);
        input.filmInput(films,9,"Lalaland",2016,"Romance","2D",150);                       //film 9
        filmName.put("Lalaland",9);

        int sessionNumber = 24;
        Session[] sessions = new Session[sessionNumber];
        input.sessionInput(sessions, 0, 0, 0, 0, 3, "12:00");                  //session 0
        input.sessionInput(sessions, 1, 0, 0, 0, 3, "16:00");                  //session 1
        input.sessionInput(sessions, 2, 0, 0, 0, 3, "22:00");                  //session 2
        input.sessionInput(sessions, 3, 0, 0, 3, 2, "14:00");                  //session 3
        input.sessionInput(sessions, 4, 0, 0, 3, 2, "18:00");                  //session 4
        input.sessionInput(sessions, 5, 0, 1, 5, 3, "13:00");                  //session 5
        input.sessionInput(sessions, 6, 0, 1, 5, 3, "15:30");                  //session 6
        input.sessionInput(sessions, 7, 0, 1, 5, 3, "19:30");                  //session 7
        input.sessionInput(sessions, 8, 0, 2, 0, 2, "08:30");                  //session 8
        input.sessionInput(sessions, 9, 0, 2, 0, 2, "11:00");                  //session 9
        input.sessionInput(sessions, 10, 0, 2, 8, 4, "13:00");                  //session 10
        input.sessionInput(sessions, 11, 0, 2, 8, 4, "15:30");                  //session 11
        input.sessionInput(sessions, 12, 0, 2, 8, 4, "18:00");                  //session 12
        input.sessionInput(sessions, 13, 0, 2, 8, 4, "22:00");                  //session 13
        input.sessionInput(sessions, 14, 1, 0, 1, 5, "12:00");                  //session 14
        input.sessionInput(sessions, 15, 1, 0, 1, 5, "14:00");                  //session 15
        input.sessionInput(sessions, 16, 1, 0, 1, 5, "16:00");                  //session 16
        input.sessionInput(sessions, 17, 1, 0, 1, 5, "18:00");                  //session 17
        input.sessionInput(sessions, 18, 1, 0, 1, 5, "22:00");                  //session 18
        input.sessionInput(sessions, 19, 1, 1, 4, 5, "12:00");                  //session 19
        input.sessionInput(sessions, 20, 1, 1, 4, 5, "14:00");                  //session 20
        input.sessionInput(sessions, 21, 1, 1, 4, 5, "16:00");                  //session 21
        input.sessionInput(sessions, 22, 1, 1, 4, 5, "18:00");                  //session 22
        input.sessionInput(sessions, 23, 1, 1, 4, 5, "22:00");                  //session 23

        Account account = new Account();
        input.accountInput(account, input.clientInput("Minh", "79296706316", "minh@gmail.com",
                        "admin"), "minh456");
        input.accountInput(account, input.clientInput("Thien", "79296706316", "thien@gmail.com",
                        "user"), "thien123");
        input.accountInput(account, input.clientInput("Dang", "79296706316", "dang@gmail.com",
                        "user"), "dangvu");
        input.accountInput(account, input.clientInput("Hung", "79296706316", "hung@gmail.com",
                        "user"), "hung111");

        int ticketNumber = 0;
        int cinTemp;
        int hallTemp;
        int filmTemp;
        int hourTemp;
        int minTemp;
        int ticTemp = 0;
        for (int i = 0; i < sessionNumber; i++) {
            ticketNumber += cinemas[sessions[i].getSessionCinema()].getCinemaHallCapacity()[sessions[i].getSessionHall()];
//            for (int j = 0; j < cinemas[sessions[i].getSessionCinema()].getCinemaHallCapacity()[sessions[i].getSessionHall()]; j++) {
//                ticketNumber +=
//            }
        }
        Ticket[] tickets = new Ticket[ticketNumber];
        for (int i = 0; i < sessionNumber; i++) {
            cinTemp = sessions[i].getSessionCinema();
            hallTemp = sessions[i].getSessionHall();
            filmTemp = sessions[i].getSessionFilmID();
            hourTemp = sessions[i].getSessionHour();
            minTemp = sessions[i].getSessionMinute();
            for (int j = 0; j < cinemas[cinTemp].getCinemaHall()[hallTemp].getLength(); j++) {
                for (int k = 0; k < cinemas[cinTemp].getCinemaHall()[hallTemp].getWidth(); k++) {
                    input.ticketInput(tickets, cinemas, ticTemp, i, cinTemp, hallTemp, filmTemp, hourTemp, minTemp,
                            films[filmTemp].getFilmHour(), films[filmTemp].getFilmMinute(), j, k);
                    ticTemp++;
                }
            }
        }
//        System.out.println(" ");
//        System.out.println(Arrays.toString(tickets));

        function functions = new function();
//        functions.setTimeTable(sessions, films, hallNumber, sessionNumber);

//        int dayNumber = 7;
//        Day[] days = new Day[7];
//        input.dayInput(days, cinemas);

        Scanner scanner = new Scanner(System.in);
        Client client = new Client();
        int chooseNumber;
        double money;
        String accTp;
        String passTp;
        String read;
        boolean check;

//        for (Ticket ticket : tickets) {
//            System.out.print(ticket.getTicketID() + " ");
//        }

        Outer1:
        while (true) {
            System.out.println("Choose your option:");
            System.out.println("1. Login:");
            System.out.println("2. Exit");
            chooseNumber = scanner.nextInt();

            switch (chooseNumber) {
                case 1:
                    System.out.println("Enter your email:");
                    accTp = scanner.next();
                    if (account.checkAccount(accTp)) {
                        check = true;
                        while (check) {
                            System.out.println("1. Enter your password:");
                            System.out.println("2. Back");
                            chooseNumber = scanner.nextInt();
                            if (chooseNumber == 1) {
                                passTp = scanner.next();
                                if (account.comparePassword(accTp, passTp)) {
                                    check = false;
                                    System.out.println("Welcome back!");
                                    client = account.getClientThroughAcc(accTp);
                                }
                                else {
                                    System.out.println("Wrong password. Please try again.");
                                }
                            }
                            else {
                                continue Outer1;
                            }
                        }
                    } else {
                        System.out.println("Your account didn't exist.");
                        continue;
                    }
                    break;
                case 2:
                    break Outer1;
                default:
                    System.out.println("Please choose right number!");
                    continue;
            }

            // Login successfully
            check = true;
            while (check) {
                if (client.getClientPower().equals("admin")) {
                    System.out.println("Choose your option:");
                    System.out.println("1.Add new cinema");
                    System.out.println("2.Add new film + session");
                    System.out.println("3.Total revenue for all sessions");
                    System.out.println("4.Total number of tickets sold");
                    System.out.println("5.Log out");
                    chooseNumber = scanner.nextInt();

                    switch (chooseNumber) {
                        case 1 -> System.out.println("Add new cinema");
                        case 2 -> System.out.println("Add new film + session");
                        case 3 -> {
                            System.out.println("Total revenue for all sessions:");
                            //revenue
                        }
                        case 4 -> {
                            System.out.println("Total number of tickets sold:");
                            //number
                        }
                        case 5 -> check = false;
                        default -> System.out.println("Please choose right number!");
                    }
                }
                else {
                    System.out.println("Choose your option:");
                    System.out.println("1.See budget");
                    System.out.println("2.Recharge budget");
                    System.out.println("3.Buy ticket");
                    System.out.println("4.Log out");
                    chooseNumber = scanner.nextInt();

                    switch (chooseNumber) {
                        case 1 -> System.out.println("Your budget: $" + client.getClientBudget());
                        case 2 -> {
                            System.out.println("Recharge: $");
                            money = scanner.nextDouble();
                            if (money > 0) {
                                client.rechargeMoney(money);
                                System.out.println("Recharge successfully!");
                            }
                            else {
                                System.out.println("Recharge failed! Please put available amount of money!");
                            }
                        }
                        case 3 -> {
                            System.out.println("Choose your option:");
                            System.out.println("1. Choose ticket by time");
                            System.out.println("2. Choose ticket by film");
                            System.out.println("3. Choose ticket by price");
                            System.out.println("4. Back");
                            chooseNumber = scanner.nextInt();

                            int[] chooseArray;
                            switch (chooseNumber) {
                                case 1:
                                    System.out.println("Put time:");
                                    read = scanner.next();
                                    chooseArray = functions.chooseTicketByTime(tickets, read);
                                    if (chooseArray != null) {
                                        if (chooseArray[0] == -1) {
                                            System.out.println("Please write the correct time!");
                                        }
                                        else {
                                            System.out.println("No. | "+"_Name | "+"_Time st | "+"_Time end | "+"_Cinema | "
                                                    +"_Hall No. | "+"_Chair | "+"_Price| ");
                                            for (int i = 0; i < chooseArray.length; i++) {
                                                System.out.println(i+". "+films[tickets[chooseArray[i]].getTicketFilmID()].getFilmTitle()
                                                        +" | "+tickets[chooseArray[i]].getTicketStHour()+":"+tickets[chooseArray[i]].getTicketStMinute()
                                                        +" | "+tickets[chooseArray[i]].getTicketEndHour()+":"+tickets[chooseArray[i]].getTicketEndMinute()
                                                        +" | "+cinemas[tickets[chooseArray[i]].getTicketCinemaID()].getCinemaName()
                                                        +" | "+tickets[chooseArray[i]].getTicketHallID()
                                                        +" | "+tickets[chooseArray[i]].getTicketHallLength()+"-"+tickets[chooseArray[i]].getTicketHallWidth()
                                                        +" | $"+tickets[chooseArray[i]].getTicketPrice());
                                            }
                                            System.out.println("Choose your ticket (-1 to back):");
                                            while (true) {
                                                chooseNumber = scanner.nextInt();
                                                if (chooseNumber >= chooseArray.length) {
                                                    System.out.println("Please choose available ticket!");
                                                    System.out.println("Choose your ticket (-1 to back):");
                                                }
                                                else {
                                                    if (chooseNumber != -1) {
                                                        if (client.getClientBudget() >= tickets[chooseArray[chooseNumber]].getTicketPrice())
                                                        {
                                                            client.purchaseMoney(tickets[chooseArray[chooseNumber]].getTicketPrice());
                                                            tickets[chooseArray[chooseNumber]].buyTicket(client.getClientID());
                                                            System.out.println("Buy successfully!");
                                                        }
                                                        else {
                                                            System.out.println("You don't have enough money!");
                                                        }
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        System.out.println("No tickets available!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Put film:");
                                    read = scanner.nextLine();
                                    if (filmName.containsKey(read)) {
                                        chooseArray = functions.chooseTicketByFilm(tickets, filmName.get(read));
                                        if (chooseArray != null) {
                                            System.out.println("No. | "+"_Name | "+"_Time st | "+"_Time end | "+"_Cinema | "
                                                    +"_Hall No. | "+"_Chair | "+"_Price| ");
                                            for (int i = 0; i < chooseArray.length; i++) {
                                                System.out.println(i+". "+films[tickets[chooseArray[i]].getTicketFilmID()].getFilmTitle()
                                                        +" | "+tickets[chooseArray[i]].getTicketStHour()+":"+tickets[chooseArray[i]].getTicketStMinute()
                                                        +" | "+tickets[chooseArray[i]].getTicketEndHour()+":"+tickets[chooseArray[i]].getTicketEndMinute()
                                                        +" | "+cinemas[tickets[chooseArray[i]].getTicketCinemaID()].getCinemaName()
                                                        +" | "+tickets[chooseArray[i]].getTicketHallID()
                                                        +" | "+tickets[chooseArray[i]].getTicketHallLength()+"-"+tickets[chooseArray[i]].getTicketHallWidth()
                                                        +" | $"+tickets[chooseArray[i]].getTicketPrice());
                                            }
                                            System.out.println("Choose your ticket (-1 to back):");
                                            while (true) {
                                                chooseNumber = scanner.nextInt();
                                                if (chooseNumber >= chooseArray.length) {
                                                    System.out.println("Please choose available ticket!");
                                                    System.out.println("Choose your ticket (-1 to back):");
                                                }
                                                else {
                                                    if (chooseNumber != -1) {
                                                        if (client.getClientBudget() >= tickets[chooseArray[chooseNumber]].getTicketPrice())
                                                        {
                                                            client.purchaseMoney(tickets[chooseArray[chooseNumber]].getTicketPrice());
                                                            tickets[chooseArray[chooseNumber]].buyTicket(client.getClientID());
                                                            System.out.println("Buy successfully!");
                                                        }
                                                        else {
                                                            System.out.println("You don't have enough money!");
                                                        }
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            System.out.println("No tickets available!");
                                        }
                                    }
                                    else {
                                        System.out.println("Please write correct film name!");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Put price:");
                                    money = scanner.nextDouble();
                                    chooseArray = functions.chooseTicketByPrice(tickets, halls, money);
                                    if (chooseArray != null) {
                                        System.out.println("No. | "+"_Name | "+"_Time st | "+"_Time end | "+"_Cinema | "
                                                +"_Hall No. | "+"_Chair | "+"_Price| ");
                                        for (int i = 0; i < chooseArray.length; i++) {
                                            System.out.println(i+". "+films[tickets[chooseArray[i]].getTicketFilmID()].getFilmTitle()
                                                    +" | "+tickets[chooseArray[i]].getTicketStHour()+":"+tickets[chooseArray[i]].getTicketStMinute()
                                                    +" | "+tickets[chooseArray[i]].getTicketEndHour()+":"+tickets[chooseArray[i]].getTicketEndMinute()
                                                    +" | "+cinemas[tickets[chooseArray[i]].getTicketCinemaID()].getCinemaName()
                                                    +" | "+tickets[chooseArray[i]].getTicketHallID()
                                                    +" | "+tickets[chooseArray[i]].getTicketHallLength()+"-"+tickets[chooseArray[i]].getTicketHallWidth()
                                                    +" | $"+tickets[chooseArray[i]].getTicketPrice());
                                        }
                                        System.out.println("Choose your ticket (-1 to back):");
                                        while (true) {
                                            chooseNumber = scanner.nextInt();
                                            if (chooseNumber >= chooseArray.length) {
                                                System.out.println("Please choose available ticket!");
                                                System.out.println("Choose your ticket (-1 to back):");
                                            }
                                            else {
                                                if (chooseNumber != -1) {
                                                    if (client.getClientBudget() >= tickets[chooseArray[chooseNumber]].getTicketPrice())
                                                    {
                                                        client.purchaseMoney(tickets[chooseArray[chooseNumber]].getTicketPrice());
                                                        tickets[chooseArray[chooseNumber]].buyTicket(client.getClientID());
                                                        System.out.println("Buy successfully!");
                                                    }
                                                    else {
                                                        System.out.println("You don't have enough money!");
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    else {
                                        System.out.println("No tickets available!");
                                    }
                                    break;
                                case 4:
                                    break;
                                default:
                                    System.out.println("Please choose right number!");
                            }
                        }
                        case 4 -> check = false;
                        default -> System.out.println("Please choose right number!");
                    }
                }
            }
        }
    }
}
