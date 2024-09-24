/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package FinalAssignment;

import java.util.Scanner;

public class TransactionModule {
    public static void mainMenu() {
        CompanyAccount companyAccount = new CompanyAccount(10000); // Initial balance
        TransactionManager transactionManager = new TransactionManager(companyAccount);
        
        // Load transactions from file
        transactionManager.loadTransactionsFromFile();
        
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n1. Display Company Account");
            System.out.println("2. Display All Transactions");
            System.out.println("3. Display Transactions by Supplier");
            System.out.println("4. Display Transactions by Product");
            System.out.println("5. Display Transactions by Status");
            System.out.println("6. Display Transaction by Order ID");
            System.out.println("7. Complete Payment of an Order");
            System.out.println("8. Generate Expense Report");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    companyAccount.displayAccount(transactionManager.getTransactions());
                    break;
                case 2:
                    transactionManager.displayAllTransactions();
                    break;
                case 3:
                    System.out.print("Enter supplier name: ");
                    String supplier = scanner.nextLine();
                    transactionManager.displayTransactionsBySupplier(supplier);
                    break;
                case 4:
                    System.out.print("Enter product name: ");
                    String product = scanner.nextLine();
                    transactionManager.displayTransactionsByProduct(product);
                    break;
                case 5:
                    System.out.print("Enter status (Completed/Pending): ");
                    String status = scanner.nextLine();
                    transactionManager.displayTransactionsByStatus(status);
                    break;
                case 6:
                    System.out.print("Enter Order ID: ");
                    String orderId = scanner.nextLine();
                    transactionManager.displayTransactionById(orderId);
                    break;
                case 7:
                    System.out.print("Enter Order ID to complete payment: ");
                    String paymentOrderId = scanner.nextLine();
                    System.out.print("Confirm payment (y/n): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("y")) {
                        transactionManager.completePayment(paymentOrderId);
                    } else {
                        System.out.println("Payment cancelled.");
                    }
                    break;
                case 8:
                    companyAccount.generateExpenseReport(transactionManager.getTransactions());
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);
        
        scanner.close();
    }
}
