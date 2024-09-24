package FinalAssignment;

import static FinalAssignment.LinkToAll.PURPLE;
import static FinalAssignment.LinkToAll.BLUE;
import static FinalAssignment.LinkToAll.RESET;
import static FinalAssignment.LinkToAll.RED;

import java.io.*;
import java.util.*;

public class LionelMainPage {

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        LionelInventory inventory = new LionelInventory();
        LionelUsers users = new LionelUsers();
        LionelSupplier suppliers = new LionelSupplier();

        while (true) {
System.out.println(PURPLE +"\nMain Menu"+ RESET);


            System.out.println(BLUE +"\n1. Manage Products"+ RESET);
            System.out.println(BLUE +"2. Manage Suppliers"+ RESET);
            System.out.println(BLUE +"3. Manage Users"+ RESET);
            System.out.println(BLUE +"4. Back To Main Page"+ RESET);
            System.out.println(BLUE +"5. Exit"+ RESET);
            System.out.println(PURPLE + "Please select an option (1, 2, 3, 4 or 5): "+ RESET);

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
System.out.println(BLUE +"  _____  _____   ____  _____  _    _  _____ _______     __  __          _   _          _____ ______ __  __ ______ _   _ _______ "+ RESET);
System.out.println(BLUE +" |  __ \\|  __ \\ / __ \\|  __ \\| |  | |/ ____|__   __|   |  \\/  |   /\\   | \\ | |   /\\   / ____|  ____|  \\/  |  ____| \\ | |__   __|"+ RESET);
System.out.println(BLUE +" | |__) | |__) | |  | | |  | | |  | | |       | |      | \\  / |  /  \\  |  \\| |  /  \\ | |  __| |__  | \\  / | |__  |  \\| |  | |   "+ RESET);
System.out.println(BLUE +" |  ___/|  _  /| |  | | |  | | |  | | |       | |      | |\\/| | / /\\ \\ | . ` | / /\\ \\| | |_ |  __| | |\\/| |  __| | . ` |  | |   "+ RESET);
System.out.println(BLUE +" | |    | | \\ \\| |__| | |__| | |__| | |____   | |      | |  | |/ ____ \\| |\\  |/ ____ \\ |__| | |____| |  | | |____| |\\  |  | |   "+ RESET);
System.out.println(BLUE +" |_|    |_|  \\_\\\\____/|_____/ \\____/ \\_____|  |_|      |_|  |_/_/    \\_\\_| \\_/_/    \\_\\_____|______|_|  |_|______|_| \\_|  |_|   "+ RESET);
System.out.println("                                                                                                                                ");
System.out.println("                                                                                                                                ");

                    inventory.showMenu();
                    break;
                case 2:
                    System.out.println(BLUE +"   _____ _    _ _____  _____  _      _____ ______ _____      __  __          _   _          _____ ______ __  __ ______ _   _ _______ "+ RESET);
System.out.println(BLUE +"  / ____| |  | |  __ \\|  __ \\| |    |_   _|  ____|  __ \\    |  \\/  |   /\\   | \\ | |   /\\   / ____|  ____|  \\/  |  ____| \\ | |__   __|"+ RESET);
System.out.println(BLUE +" | (___ | |  | | |__) | |__) | |      | | | |__  | |__) |   | \\  / |  /  \\  |  \\| |  /  \\ | |  __| |__  | \\  / | |__  |  \\| |  | |   "+ RESET);
System.out.println(BLUE +"  \\___ \\| |  | |  ___/|  ___/| |      | | |  __| |  _  /    | |\\/| | / /\\ \\ | . ` | / /\\ \\| | |_ |  __| | |\\/| |  __| | . ` |  | |   "+ RESET);
System.out.println(BLUE +"  ____) | |__| | |    | |    | |____ _| |_| |____| | \\ \\    | |  | |/ ____ \\| |\\  |/ ____ \\ |__| | |____| |  | | |____| |\\  |  | |   "+ RESET);
System.out.println(BLUE +" |_____/ \\____/|_|    |_|    |______|_____|______|_|  \\_\\   |_|  |_/_/    \\_\\_| \\_/_/    \\_\\_____|______|_|  |_|______|_| \\_|  |_|   "+ RESET);
System.out.println(BLUE +"                                                                                                                                     "+ RESET);
System.out.println("                                                                                                                                     ");

                    suppliers.showMenu();
                    break;
                case 3:
System.out.println(BLUE +"  _    _  _____ ______ _____      __  __          _   _          _____ ______ __  __ ______ _   _ _______ " + RESET);
System.out.println(BLUE +" | |  | |/ ____|  ____|  __ \\    |  \\/  |   /\\   | \\ | |   /\\   / ____|  ____|  \\/  |  ____| \\ | |__   __|"+ RESET);
System.out.println(BLUE +" | |  | | (___ | |__  | |__) |   | \\  / |  /  \\  |  \\| |  /  \\ | |  __| |__  | \\  / | |__  |  \\| |  | |   "+ RESET);
System.out.println(BLUE +" | |  | |\\___ \\|  __| |  _  /    | |\\/| | / /\\ \\ | . ` | / /\\ \\| | |_ |  __| | |\\/| |  __| | . ` |  | |   "+ RESET);
System.out.println(BLUE +" | |__| |____) | |____| | \\ \\    | |  | |/ ____ \\| |\\  |/ ____ \\ |__| | |____| |  | | |____| |\\  |  | |   "+ RESET);
System.out.println(BLUE +"  \\____/|_____/|______|_|  \\_\\   |_|  |_/_/    \\_\\_| \\_/_/    \\_\\_____|______|_|  |_|______|_| \\_|  |_|   "+ RESET);
System.out.println(BLUE +"                                                                                                          "+ RESET);
System.out.println(BLUE +"                                                                                                          "+ RESET);

                    users.showMenu();
                    break;
                case 4:
                    LinkToAll.mainMenu();
                    
                case 5:
                    System.out.println(RED +"\nExiting program..."+ RESET);
                    System.exit(0);
                default:
                    System.out.println(RED +"\nInvalid choice."+ RESET);
            }
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
