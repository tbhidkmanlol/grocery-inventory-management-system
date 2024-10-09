package FinalAssignment;

import java.io.*;
import java.util.*;

public class LinkToAll {
public static final String RESET = "\033[0m";  
    // Text Colors
    public static final String RED = "\033[0;31m";  
    public static final String GREEN = "\033[0;32m";  
    public static final String YELLOW = "\033[0;33m";  
    public static final String BLUE = "\033[0;34m";  
    public static final String PURPLE = "\033[0;35m";  
    public static final String CYAN = "\033[0;36m";
    public static final String DARKBLUE ="\033[0;34m";
    
    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
System.out.println(BLUE +"  __  __       _           __  __                  "+ RESET);
System.out.println(BLUE +" |  \\/  |     (_)         |  \\/  |                 "+ RESET);
System.out.println(BLUE +" | \\  / | __ _ _ _ __     | \\  / | ___ _ __  _   _ "+ RESET);
System.out.println(BLUE +" | |\\/| |/ _` | | '_ \\    | |\\/| |/ _ \\ '_ \\| | | |");
System.out.println(BLUE +" | |  | | (_| | | | | |   | |  | |  __/ | | | |_| |"+ RESET);
System.out.println(BLUE +" |_|  |_|\\__,_|_|_| |_|   |_|  |_|\\___|_| |_|\\__,_|"+ RESET);
System.out.println("                                                   ");
System.out.println("                                                   ");

            System.out.println(BLUE+"1. Database Page"+RESET);
            System.out.println(BLUE+"2. Orders Page"+RESET);
            System.out.println(BLUE+"3. Transactions Page"+RESET);
            System.out.println(BLUE+"4. Exit");
            System.out.println(PURPLE+"Please select an option (1, 2, 3, or 4): "+RESET);

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
System.out.println(GREEN+"  _____       _______       ____           _____ ______     _____        _____ ______ "+ RESET);
System.out.println(GREEN+" |  __ \\   /\\|__   __|/\\   |  _ \\   /\\    / ____|  ____|   |  __ \\ /\\   / ____|  ____|"+ RESET);
System.out.println(GREEN+" | |  | | /  \\  | |  /  \\  | |_) | /  \\  | (___ | |__      | |__) /  \\ | |  __| |__   "+ RESET);
System.out.println(GREEN+" | |  | |/ /\\ \\ | | / /\\ \\ |  _ < / /\\ \\  \\___ \\|  __|     |  ___/ /\\ \\| | |_ |  __|  "+ RESET);
System.out.println(GREEN+" | |__| / ____ \\| |/ ____ \\| |_) / ____ \\ ____) | |____    | |  / ____ \\ |__| | |____ "+ RESET);
System.out.println(GREEN+" |_____/_/    \\_\\_/_/    \\_\\____/_/    \\_\\_____/|______|   |_| /_/    \\_\\_____|______|"+ RESET);

                    LionelMainPage.mainMenu();
                    break;
                case 2:
                    System.out.println("\nOrders Page");
                    Reynold2.mainMenu();
                    break;
                case 3:
                    System.out.println("\nTransactions Page");
                    TransactionModule.mainMenu();
                    break;
                case 4:
                    System.out.println("\nExiting program...");
                    System.exit(0);
                default:
                    System.out.println("\nInvalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
