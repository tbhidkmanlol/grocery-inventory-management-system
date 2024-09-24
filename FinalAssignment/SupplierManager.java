package FinalAssignment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupplierManager {
    private static final String FILE_PATH = "C:/Users/Lionel Seow/OneDrive/Documents/NetBeansProjects/OOPTFinalCombination/src/FinalAssignment/ReynoldSupplier.txt";
    private Scanner scanner;

    public SupplierManager() {
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nSuppliers Page");
            System.out.println("1. Display Data");
            System.out.println("2. Create Data");
            System.out.println("3. Update Data");
            System.out.println("4. Delete Data");
            System.out.println("5. Main Page");
            System.out.println("6. Exit Program");
            System.out.println("Please select an option:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayData();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    return; // Exit to Main Page
                case 6:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void displayData() {
        System.out.println("Displaying all Suppliers:");
        System.out.println("----------------------------------------------------");
        System.out.printf("%-5s | %-15s | %-20s%n", "ID", "Supplier", "Products");
        System.out.println("----------------------------------------------------");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String id = parts[0];
                    String supplier = parts[1];
                    StringBuilder products = new StringBuilder();
                    for (int i = 2; i < parts.length; i += 2) {
                        if (i > 2) products.append(", ");
                        products.append(parts[i]).append(" (").append(parts[i + 1]).append(")");
                    }
                    System.out.printf("%-5s | %-15s | %-20s%n", id, supplier, products.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        System.out.println("----------------------------------------------------");
    }

    private int generateNextId() {
        int highestId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length > 0) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        if (id > highestId) {
                            highestId = id;
                        }
                    } catch (NumberFormatException e) {
                        // Ignore non-numeric IDs
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return highestId + 1;
    }

    public void addProduct() {
        int newId = generateNextId();
        System.out.println("\nNew Supplier ID: " + newId);
        System.out.print("\nEnter Supplier Name: ");
        String supplier = scanner.nextLine();

        List<String> products = new ArrayList<>();
        String continueAdding;
        do {
            System.out.print("\nEnter Product Name: ");
            String name = scanner.nextLine();
            System.out.print("\nEnter Price: ");
            String price = scanner.nextLine();

            products.add(name + "|" + price);

            System.out.print("\nDo you want to add another product for this supplier? (y/n): ");
            continueAdding = scanner.nextLine().toLowerCase();
        } while (continueAdding.equals("y"));

        try {
            File file = new File(FILE_PATH);
            List<String> lines = new ArrayList<>();

            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                }
            }

            lines.add(newId + "|" + supplier + "|" + String.join("|", products));

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            System.out.println("\nSupplier and products added successfully with ID: " + newId);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void updateProduct() {
        System.out.print("\nEnter Supplier ID to update: ");
        String supplierId = scanner.nextLine();
        
        // Add confirmation step
        System.out.print("Are you sure you want to update this supplier? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (!confirmation.equals("yes")) {
            System.out.println("Update cancelled.");
            return;
        }
        
        try {
            File inputFile = new File(FILE_PATH);
            File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(supplierId)) {
                    System.out.println("\nCurrent data: " + line.replace("|", " | "));
                    System.out.print("\nEnter new Supplier Name: ");
                    String newName = scanner.nextLine();
                    List<String> newProducts = new ArrayList<>();
                    String continueAdding;
                    do {
                        System.out.print("\nEnter Product Name: ");
                        String productName = scanner.nextLine();
                        System.out.print("\nEnter Price: ");
                        String price = scanner.nextLine();
                        newProducts.add(productName + "|" + price);
                        System.out.print("\nDo you want to add another product? (y/n): ");
                        continueAdding = scanner.nextLine().toLowerCase();
                    } while (continueAdding.equals("y"));
                    writer.write(supplierId + "|" + newName + "|" + String.join("|", newProducts));
                    found = true;
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
            writer.close();
            reader.close();

            if (!found) {
                System.out.println("Supplier not found.");
                tempFile.delete();
            } else {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                System.out.println("Supplier and products updated successfully!");
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

    public void deleteProduct() {
        System.out.print("\nEnter Supplier ID to delete: ");
        String supplierId = scanner.nextLine();
        
        // Add confirmation step
        System.out.print("Are you sure you want to delete this supplier? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (!confirmation.equals("yes")) {
            System.out.println("Deletion cancelled.");
            return;
        }
        
        try {
            File inputFile = new File(FILE_PATH);
            File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(supplierId)) {
                    found = true;
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            reader.close();

            if (!found) {
                System.out.println("Supplier not found.");
                tempFile.delete();
            } else {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                System.out.println("Supplier and all associated products deleted successfully!");
            }
        } catch (IOException e) {
            System.out.println("Error deleting from file: " + e.getMessage());
        }
    }
}