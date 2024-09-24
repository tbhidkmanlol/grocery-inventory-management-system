package FinalAssignment;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static FinalAssignment.LinkToAll.PURPLE;
import static FinalAssignment.LinkToAll.BLUE;
import static FinalAssignment.LinkToAll.RESET;
import static FinalAssignment.LinkToAll.RED;
import static FinalAssignment.LinkToAll.GREEN;

public class LionelUsers extends LionelDatabase {

    public LionelUsers() {
        super("C:/Users/Lionel Seow/Downloads/sampleUsers.txt");
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

    // Method to show the user management menu
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(PURPLE + "\nUsers Page" + RESET);
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
                    addUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
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

    // Method to display all user data
    public void displayData() {
        System.out.println(PURPLE + "\nDisplaying all inventory data:" + RESET);

        // Call readFile() and store the lines
        List<String> inventoryData = readFile();

        System.out.println("-----------------------------------------------------------");

        // Print the table header
        System.out.printf("|%-15s | %-20s | %-15s            |%n",
                BLUE + "User ID", "User Name", "Password" + RESET);
        System.out.println("-----------------------------------------------------------");

        // Print each line from the inventory data
        for (String line : inventoryData) {
            String[] details = line.split(","); // Assuming data is comma-separated
            System.out.printf("|%-15s | %-20s | %-15s |%n",
                    details[0], details[1], details[2]);
        }

        // Print the bottom line
        System.out.println("-----------------------------------------------------------");
    }

    // Method to add a new user
    public void addUser() {
        Scanner scanner = new Scanner(System.in);
        String newId = generateNextId();

        System.out.println(PURPLE + "\nGenerated User ID: " + newId + RESET);
        System.out.print(BLUE + "Enter Username: " + RESET);
        String username = scanner.nextLine();
        System.out.print(BLUE + "Enter Password: " + RESET);
        String password = scanner.nextLine();

        String data = newId + "," + username + "," + password;

        // Confirmation after details are filled
        System.out.print(BLUE + "\n\nYou entered:\nUser ID: " + newId + "\nUser Name: " + username + "\nPassword: " + password + "\nDo you want to add this user? (y/n): " + RESET);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y")) {
            createData(data);
            System.out.println(BLUE + "User added successfully!" + RESET);
        } else {
            System.out.println(RED + "User creation cancelled." + RESET);
        }
    }

    // Method to update user information
    public void updateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(BLUE + "\nEnter User ID to update: " + RESET);
        String id = scanner.nextLine();
        System.out.print(BLUE + "Enter new Username: " + RESET);
        String username = scanner.nextLine();
        System.out.print(BLUE + "Enter new Password: " + RESET);
        String password = scanner.nextLine();

        String updatedData = id + "," + username + "," + password;

        // Confirmation after details are filled
        System.out.print(BLUE + "\n\nYou entered:\nUser ID: " + id + "\nNew User Name: " + username + "\nNew Password: " + password + "\nDo you want to update this user? (y/n): " + RESET);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y")) {
            updateData(id, updatedData);
            System.out.println(BLUE + "User updated successfully!" + RESET);
        } else {
            System.out.println(RED + "User update cancelled." + RESET);
        }
    }

    // Method to delete a user from the database
    public void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(BLUE + "\nEnter User ID to delete: " + RESET);
        String id = scanner.nextLine();

        // Confirmation after user ID is provided
        System.out.print(BLUE + "\n\nAre you sure you want to delete User ID: " + id + "? (y/n): " + RESET);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y")) {
            deleteData(id);
            System.out.println(RED + "User deleted successfully!" + RESET);
        } else {
            System.out.println(RED + "User deletion cancelled." + RESET);
        }
    }
}
