package FinalAssignment;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TransactionManager {

    // Constant file path
    private static final String FILE_PATH = "C:/Users/Lionel Seow/OneDrive/Documents/NetBeansProjects/OOPTFinalCombination/src/FinalAssignment/ReynoldOrder.txt";
    private List<Transaction> transactions;
    private CompanyAccount companyAccount;

    public TransactionManager(CompanyAccount companyAccount) {
        this.transactions = new ArrayList<>();
        this.companyAccount = companyAccount;
    }

    // Load transactions from file
   public void loadTransactionsFromFile() {
       transactions.clear(); // Clear existing transactions to prevent duplication
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|"); // Use | as delimiter
            if (parts.length == 8) {
                String orderId = parts[0];
                LocalDate expDate = LocalDate.parse(parts[1], formatter);
                String status = parts[2];
                String supplierName = parts[3];
                String prodName = parts[4];
                int quantity = Integer.parseInt(parts[5]);
                double price = Double.parseDouble(parts[6]);
                double totalAmount = Double.parseDouble(parts[7]);

                Transaction transaction;
                if (status.equalsIgnoreCase("Pending")) {
                    transaction = new PendingTransaction(orderId, expDate, supplierName, prodName, quantity, price, totalAmount);
                } else {
                    transaction = new CompletedTransaction(orderId, expDate, supplierName, prodName, quantity, price, totalAmount);
                }

                // Check for duplicates before adding
                boolean exists = transactions.stream()
                    .anyMatch(existing -> existing.getOrderId().equals(orderId));

                if (!exists) {
                    transactions.add(transaction);
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
}


    // Update transaction in file
    private void updateTransactionInFile(Transaction transaction) {
    File inputFile = new File(FILE_PATH);
    File tempFile = new File("TempOrder.txt");

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts[0].equals(transaction.getOrderId())) {
                writer.write(transaction.toFileString()); // Write updated transaction
            } else {
                writer.write(line); // Write original line
            }
            writer.newLine();
        }

    } catch (IOException e) {
        System.out.println("Error updating file: " + e.getMessage());
    }

    // Rename the temp file after the try block
    if (!inputFile.delete()) {
        System.out.println("Could not delete the original file");
        return;
    }
    if (!tempFile.renameTo(inputFile)) {
        System.out.println("Could not rename temp file");
    }
}


    public List<Transaction> getTransactions() {
        return transactions;
    }

public void displayAllTransactions() {
    Set<String> displayedIds = new HashSet<>();

    for (Transaction transaction : transactions) {
        if (displayedIds.add(transaction.getOrderId())) { // This will only add if it's not already in the set
            System.out.println(transaction.toString());
        }
    }
}

public void displayTransactionsBySupplier(String supplierName) {
    for (Transaction transaction : transactions) {
        if (transaction.getSupplierName().equalsIgnoreCase(supplierName)) {
            System.out.println(transaction);
        }
    }
}

    public void displayTransactionById(String orderId) {
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getOrderId().equalsIgnoreCase(orderId)) {
                t.displayDetails();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No transaction found with Order ID: " + orderId);
        }
    }

    public void displayTransactionsByProduct(String prodName) {
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getProdName().equalsIgnoreCase(prodName)) {
                t.displayDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for product: " + prodName);
        }
    }

    public void displayTransactionsByStatus(String status) {
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getStatus().equalsIgnoreCase(status)) {
                t.displayDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found with status: " + status);
        }
    }

   public void completePayment(String orderId) {
    Transaction transaction = findTransactionById(orderId);
    if (transaction != null && transaction instanceof PendingTransaction) {
        PendingTransaction pendingTransaction = (PendingTransaction) transaction;
        
        if (pendingTransaction.getStatus().equalsIgnoreCase("Completed")) {
            System.out.println("Payment already completed for order " + orderId);
            return;
        }
        
        System.out.println("Current Status: " + pendingTransaction.getStatus());
        
        if (companyAccount.transferToSupplier(pendingTransaction.getSupplierName(), pendingTransaction.getTotalAmount())) {
            pendingTransaction.setStatus("Completed");
            System.out.println("New Status: " + pendingTransaction.getStatus());
            updateTransactionInFile(pendingTransaction); // Update the file
            
           // System.out.println("Before Reloading:");
           //displayAllTransactions(); // Check before loading again
            
            loadTransactionsFromFile(); // Reload transactions
            //System.out.println("After Reloading:");
            //displayAllTransactions(); // Check again
            
            System.out.println("Payment completed for order " + orderId);
        } else {
            System.out.println("Payment failed.");
        }
    } else {
        System.out.println("Invalid order ID or order is already completed.");
    }
}




    private Transaction findTransactionById(String orderId) {
        return transactions.stream()
                .filter(t -> t.getOrderId().equalsIgnoreCase(orderId))
                .findFirst()
                .orElse(null);
    }
}
