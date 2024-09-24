package FinalAssignment;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static FinalAssignment.LinkToAll.PURPLE;
import static FinalAssignment.LinkToAll.BLUE;
import static FinalAssignment.LinkToAll.RESET;
import static FinalAssignment.LinkToAll.RED;
import static FinalAssignment.LinkToAll.GREEN;

public class LionelSupplier extends LionelDatabase {

    public LionelSupplier() {
        super("C:/Users/Lionel Seow/Downloads/sampleSupplier.txt");
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

    // Method to show the supplier management menu
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(PURPLE + "\nSupplier Page" + RESET);
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
                    addSupplier();
                    break;
                case 3:
                    updateSupplier();
                    break;
                case 4:
                    deleteSupplier();
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

    // Method to display all supplier data
    public void displayData() {
        System.out.println(PURPLE + "\nDisplaying all supplier data:" + RESET);

        // Call readFile() and store the lines
        List<String> inventoryData = readFile();
        
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        
        // Print the table header
        System.out.printf("|%-12s  | %-20s | %-20s | %-15s | %-25s     |%n", 
                          BLUE + "Supplier ID", "Supplier Name", "Contact Name", "Phone Number", "Address" + RESET);
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        
        // Print each line from the inventory data
        for (String line : inventoryData) {
            String[] details = line.split(","); // Assuming data is comma-separated
            System.out.printf("|%-12s | %-20s | %-20s | %-15s | %-25s |%n", 
                              details[0], details[1], details[2], details[3], details[4]);
        }
        
        // Print the bottom line
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }
    
    // Method to add a new supplier
    public void addSupplier() {
        String newId = generateNextId();
        
        System.out.println(PURPLE + "\nGenerated Supplier ID: " + newId + RESET);
        System.out.print(BLUE + "Enter Supplier Name: " + RESET);
        String name = scanner.nextLine();
        System.out.print(BLUE + "Enter Contact Person: " + RESET);
        String contact = scanner.nextLine();
        System.out.print(BLUE + "Enter Phone Number: " + RESET);
        String phone = scanner.nextLine();
        System.out.print(BLUE + "Enter Address: " + RESET);
        String address = scanner.nextLine();

        // Confirmation after details are filled
        System.out.print(BLUE + "\n\nYou entered:\nSupplier ID: " + newId + "\nSupplier Name: " + name + 
                         "\nContact Person: " + contact + "\nPhone Number: " + phone + 
                         "\nAddress: " + address + "\nDo you want to add this supplier? (y/n): " + RESET);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y")) {
            String data = newId + "," + name + "," + contact + "," + phone + "," + address;
            createData(data);
            System.out.println(GREEN + "Supplier added successfully!" + RESET);
        } else {
            System.out.println(RED + "Supplier creation cancelled." + RESET);
        }
    }

    // Method to update supplier information
    public void updateSupplier() {
        System.out.print(BLUE + "Enter Supplier ID to update: " + RESET);
        String id = scanner.nextLine();
        System.out.print(BLUE + "Enter new Supplier Name: " + RESET);
        String name = scanner.nextLine();
        System.out.print(BLUE + "Enter new Contact Person: " + RESET);
        String contact = scanner.nextLine();
        System.out.print(BLUE + "Enter new Phone Number: " + RESET);
        String phone = scanner.nextLine();
        System.out.print(BLUE + "Enter new Address: " + RESET);
        String address = scanner.nextLine();

        // Confirmation after details are filled
        System.out.print(BLUE + "\n\nYou entered:\nSupplier ID: " + id + "\nNew Supplier Name: " + name + 
                         "\nNew Contact Person: " + contact + "\nNew Phone Number: " + phone + 
                         "\nNew Address: " + address + "\nDo you want to update this supplier? (y/n): " + RESET);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y")) {
            String updatedData = id + "," + name + "," + contact + "," + phone + "," + address;
            updateData(id, updatedData);
            System.out.println(GREEN + "Supplier updated successfully!" + RESET);
        } else {
            System.out.println(RED + "Supplier update cancelled." + RESET);
        }
    }

    // Method to delete a supplier from the database
    public void deleteSupplier() {
        System.out.print(RED + "Enter Supplier ID to delete: " + RESET);
        String id = scanner.nextLine();

        // Confirmation after user ID is provided
        System.out.print(BLUE + "\n\nAre you sure you want to delete Supplier ID: " + id + "? (y/n): " + RESET);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y")) {
            deleteData(id);
            System.out.println(RED + "Supplier deleted successfully!" + RESET);
        } else {
            System.out.println(RED + "Supplier deletion cancelled." + RESET);
        }
    }
}
