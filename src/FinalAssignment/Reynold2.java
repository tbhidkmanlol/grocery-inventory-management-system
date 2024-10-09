package FinalAssignment;

import java.util.Scanner;

public class Reynold2 {
    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        SupplierManager supplierManager = new SupplierManager(); // Create an instance of SupplierManager
        OrderManager orderManager = new OrderManager();

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Manage Orders");
            System.out.println("2. View Suppliers");
            System.out.println("3. Exit");
            System.out.print("Please select an option (1, 2, 3): ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nOrder Management");
                    
                    orderManager.showMenu();
                    break;
                case 2:
                    System.out.println("\nSupplier Management");
                    supplierManager.showMenu();
                    break;
                case 3:
                    System.out.println("\nExiting program...");
                    System.exit(0);
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
