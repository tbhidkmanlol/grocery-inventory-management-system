package FinalAssignment;

import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import static FinalAssignment.LinkToAll.PURPLE;
import static FinalAssignment.LinkToAll.BLUE;
import static FinalAssignment.LinkToAll.RESET;
import static FinalAssignment.LinkToAll.RED;

public class LionelInventory extends LionelDatabase {
    
    public LionelInventory() {
        super("C:/Users/Lionel Seow/Downloads/sampleData.txt");
    }

    @Override
    protected String generateNextId() {
        int highestId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                if (id > highestId) {
                    highestId = id;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format("%04d", highestId + 1);
    }

    // Method to show the inventory menu
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(PURPLE + "\nInventory Page" + RESET);
            System.out.println(BLUE + "1. Display Data" + RESET);
            System.out.println(BLUE + "2. Create Data" + RESET);
            System.out.println(BLUE + "3. Update Data" + RESET);
            System.out.println(BLUE + "4. Delete Data" + RESET);
            System.out.println(BLUE + "5. Main Page" + RESET);
            System.out.println(BLUE + "6. Exit Program" + RESET);
            System.out.println(PURPLE + "Please select an option:" + RESET);
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
                    System.out.println(RED + "Exiting program..." + RESET);
                    System.exit(0); // Exit the program
                default:
                    System.out.println(RED + "Invalid option. Please try again." + RESET);
            }
        }
    }

    // Method to display all product data
    public void displayData() {
        System.out.println(PURPLE + "\nDisplaying all inventory data:" + RESET);

        // Call readFile() and store the lines
        List<String> inventoryData = readFile();
        
        System.out.println("---------------------------------------------------------------------------------------------------");
        
        // Print the table header
        System.out.printf("|%-15s      | %-20s | %-15s | %-10s | %-10s | %-15s |%n", 
                          BLUE + "Product ID", "Product Name", "Category", "Quantity", "Price", "Expiry Date" + RESET);
        System.out.println("---------------------------------------------------------------------------------------------------");
        
        // Print each line from the inventory data
        for (String line : inventoryData) {
            String[] details = line.split(","); // Assuming data is comma-separated
            System.out.printf("|%-15s | %-20s | %-15s | %-10s | %-10s | %-11s |%n", 
                              details[0], details[1], details[2], details[3], details[4], details[5]);
        }
        
        // Print the bottom line
        System.out.println("---------------------------------------------------------------------------------------------------");
    }

    // Method to add a new product to the inventory
    public void addProduct() {
        String newId = generateNextId();
      
        System.out.println(PURPLE + "\nGenerated Product ID: " + newId + RESET);
        System.out.print(BLUE + "Enter Product Name: " + RESET);
        String name = scanner.nextLine();
        System.out.print(BLUE + "Enter Category: " + RESET);
        String category = scanner.nextLine();
        System.out.print(BLUE + "Enter Quantity: " + RESET);
        int quantity = scanner.nextInt();
        System.out.print(BLUE + "Enter Price: " + RESET);
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline
        System.out.print(BLUE + "Enter Expiry Date (YYYY-MM-DD): " + RESET);
        String expiryDate = scanner.nextLine();

        // Confirmation after details are filled
        System.out.print(BLUE + "\n\nYou entered:\nProduct ID: " + newId + "\nProduct Name: " + name + "\nCategory: " + category +
                         "\nQuantity: " + quantity + "\nPrice: " + price + "\nExpiry Date: " + expiryDate +
                         "\nDo you want to add this product? (y/n): " + RESET);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y")) {
            String data = newId + "," + name + "," + category + "," + quantity + "," + price + "," + expiryDate;
            createData(data);
            System.out.println(PURPLE + "Product added successfully!" + RESET);
        } else {
            System.out.println(RED + "Product creation cancelled." + RESET);
        }
    }

    // Method to update product information
    public void updateProduct() {
        System.out.print(PURPLE + "\nEnter Product ID to update: " + RESET);
        String id = scanner.nextLine();
        System.out.print(BLUE + "Enter new Product Name: " + RESET);
        String name = scanner.nextLine();
        System.out.print(BLUE + "Enter new Category: " + RESET);
        String category = scanner.nextLine();
        System.out.print(BLUE + "Enter new Quantity: " + RESET);
        int quantity = scanner.nextInt();
        System.out.print(BLUE + "Enter new Price: " + RESET);
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline
        System.out.print(BLUE + "Enter new Expiry Date (YYYY-MM-DD): " + RESET);
        String expiryDate = scanner.nextLine();

        // Confirmation after details are filled
        System.out.print(BLUE + "\n\nYou entered:\nProduct ID: " + id + "\nNew Product Name: " + name + "\nNew Category: " + category +
                         "\nNew Quantity: " + quantity + "\nNew Price: " + price + "\nNew Expiry Date: " + expiryDate +
                         "\nDo you want to update this product? (y/n): " + RESET);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y")) {
            String updatedData = id + "," + name + "," + category + "," + quantity + "," + price + "," + expiryDate;
            updateData(id, updatedData);
            System.out.println(PURPLE + "Product updated successfully!" + RESET);
        } else {
            System.out.println(RED + "Product update cancelled." + RESET);
        }
    }

    // Method to delete a product from the inventory
    public void deleteProduct() {
        System.out.print(PURPLE + "\nEnter Product ID to delete: " + RESET);
        String id = scanner.nextLine();

        // Confirmation after user ID is provided
        System.out.print(BLUE + "\n\nAre you sure you want to delete Product ID: " + id + "? (y/n): " + RESET);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y")) {
            deleteData(id);
            System.out.println(RED + "Product deleted successfully!" + RESET);
        } else {
            System.out.println(RED + "Product deletion cancelled." + RESET);
        }
    }
}
