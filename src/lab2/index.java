package lab2;

import Data.Cinema;
import Data.Hall;
import Data.Film;
import Data.Session;
import Data.Client;
import java.util.Scanner;

public class index {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*Cinema input*/
        System.out.print("Put number of Cinema: ");
        int cinemaNumber = sc.nextInt();
        Cinema[] cinema = new Cinema[cinemaNumber];

        for (int i = 0; i < cinemaNumber; i++) {
            cinema[i] = new Cinema();
            System.out.println("Put cinema " + i + 1 + " name, address, capacity, number of hall, number of format: ");
            cinema[i].setCinemaBasicInfo(sc.next(), sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt());

            System.out.println("Put cinema " + i + 1 + " formats: ");
            for (int j = 0; j < cinema[i].getCinemaFormatNumber(); j++) {
                cinema[i].getCinemaFormatArr() = new
                cinema[i].setCinemaFormat(sc.next(), j);
            }

            for (int j = 0; j < cinema[i].getCinemaHallNumber(); j++) {
                System.out.println("Put cinema " + i + 1 + " hall " + j + 1 + " length and width: ");
                cinema[i].setCinemaHall(j, sc.nextInt(), sc.nextInt());
                System.out.println("Put cinema " + i + 1 + " hall " + j + 1 + " price per place: ");
                for (int k = 0; k < cinema[i].getCinemaHall(j).getLength(); k++) {
                    for (int m = 0; m < cinema[i].getCinemaHall(j).getWidth(); m++) {
                        cinema[i].setCinemaPriceHall(j, k, m, sc.nextDouble());
                    }
                }
            }
        }

        /*test*/
        System.out.println(cinema[0].getCinemaName());
        System.out.println(cinema[0].getCinemaCapacity());
        System.out.println(cinema[0].getCinemaAddress());
        for (int i = 0; i < cinema[0].getCinemaFormatNumber(); i++) {
            System.out.println(cinema[0].getCinemaFormat(i));
        }
    }
}
