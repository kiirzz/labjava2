package View;

import java.util.Scanner;

public class index {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        clientView client = new clientView();
        adminView admin = new adminView();

        int chooseNumber;

        do {
            System.out.println("You are (0 to exit):");
            System.out.println("1. Admin");
            System.out.println("2. Client");
            chooseNumber = scanner.nextInt();

            switch (chooseNumber) {
                case 0:
                    break;
                case 1:
                    admin.render();
                    break;
                case 2:
                    client.render();
                    break;
                default:
                    System.out.println("Please choose right number!");
                    break;
            }
        } while (chooseNumber != 0);
    }
}

//----------------------------------------------------------------------------------------------------------------------


