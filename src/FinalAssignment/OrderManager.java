package FinalAssignment;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class OrderManager {
    private static final String ORDER_FILE_PATH = "C:/Users/Lionel Seow/OneDrive/Documents/NetBeansProjects/OOPTFinalCombination/src/FinalAssignment/ReynoldOrder.txt";
    private static final String SUPPLIER_FILE_PATH = "\"C:\\Users\\Lionel Seow\\OneDrive\\Documents\\NetBeansProjects\\OOPTFinalCombination\\src\\FinalAssignment\\ReynoldSupplier.txt\"";
    private Scanner scanner;

    public OrderManager() {
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nOrder Page");
            System.out.println("1. Display Orders");
            System.out.println("2. Create Order");
            System.out.println("3. Update Order");
            System.out.println("4. Delete Order");
            System.out.println("5. Main Page");
            System.out.println("6. Exit Program");
            System.out.println("Please select an option:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    createOrder();
                    break;
                case 3:
                    updateOrder();
                    break;
                case 4:
                    deleteOrder();
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
    
 public void displayOrders() {
        System.out.println("\nDisplaying all orders:");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-10s | %-8s | %-15s | %-15s | %-8s | %-8s | %-10s%n", 
                          "Order ID", "Date", "Status", "Supplier", "Product", "Quantity", "Price", "Total");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 8) {
                    System.out.printf("%-10s | %-10s | %-8s | %-15s | %-15s | %-8s | %-8s | %-10s%n", 
                                      parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }

    public void createOrder() {
        try {
            // Display all suppliers
            List<String[]> suppliers = readSuppliers();
            System.out.println("Available suppliers:");
            for (String[] supplier : suppliers) {
                if (supplier.length > 1) {
                    System.out.println(supplier[0] + ". " + supplier[1]);
                } else {
                    System.out.println("Warning: Supplier data is incomplete: " + supplier[0]);
                }
            }

            // Select supplier
            System.out.print("\nEnter supplier number: ");
            int supplierIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (supplierIndex < 0 || supplierIndex >= suppliers.size()) {
                System.out.println("Invalid supplier number.");
                return;
            }
            String[] selectedSupplier = suppliers.get(supplierIndex);

            if (selectedSupplier.length < 2) {
                System.out.println("Error: Selected supplier data is incomplete.");
                return;
            }

            // Display products for the selected supplier
            System.out.println("Products sold by " + selectedSupplier[1] + ":");
            for (int i = 2; i < selectedSupplier.length; i += 2) {
                if (i + 1 < selectedSupplier.length) {
                    System.out.println((i/2) + ". " + selectedSupplier[i] + " - $" + selectedSupplier[i+1]);
                } else {
                    System.out.println("Warning: Product data is incomplete for index " + i);
                }
            }

            // Select product
            System.out.print("\nEnter product number: ");
            int productIndex = Integer.parseInt(scanner.nextLine());
            if (productIndex < 1 || productIndex > (selectedSupplier.length - 2) / 2) {
                System.out.println("\nInvalid product number.");
                return;
            }
            String productName = selectedSupplier[productIndex * 2];
            double price = Double.parseDouble(selectedSupplier[productIndex * 2 + 1]);

            // Enter quantity
            System.out.print("\nEnter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            // Generate order ID
            String orderId = generateOrderId();

            // Get current date
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            // Calculate total price
            double totalPrice = price * quantity;

            // Write to order file
            String orderLine = String.format("%s|%s|Pending|%s|%s|%d|%.2f|%.2f",
                    orderId, date, selectedSupplier[1], productName, quantity, price, totalPrice);
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE_PATH, true))) {
                writer.write(orderLine);
                writer.newLine();
            }

            System.out.println("\nOrder created successfully: " + orderLine.replace("|", " | "));

        } catch (IOException e) {
            System.out.println("Error reading supplier file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<String[]> readSuppliers() throws IOException {
        List<String[]> suppliers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SUPPLIER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;  // Skip empty lines silently
                }
                String[] supplierData = line.split("\\|");
                if (supplierData.length < 2) {
                    System.out.println("Warning: Skipping invalid supplier data: " + line);
                    continue;
                }
                suppliers.add(supplierData);
            }
        }
        return suppliers;
    }
    
    private String generateOrderId() {
        int highestId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length > 0) {
                    int id = Integer.parseInt(parts[0].substring(3));
                    if (id > highestId) {
                        highestId = id;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return String.format("ORD%03d", highestId + 1);
    }

    private List<String[]> readOrders() throws IOException {
        List<String[]> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] orderData = line.split("\\|");
                orders.add(orderData);
            }
        }
        return orders;
    }

    private void saveOrders(List<String[]> orders) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE_PATH, false))) {
            for (String[] order : orders) {
                writer.write(String.join("|", order));
                writer.newLine();
            }
        }
    }

    public void updateOrder() {
        try {
            // Display all orders
            List<String[]> orders = readOrders();
            System.out.println("Available orders:");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println((i + 1) + ". " + String.join(" | ", orders.get(i)));
            }

            // Select the order to update
            System.out.print("\nEnter order number to update: ");
            int orderIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (orderIndex < 0 || orderIndex >= orders.size()) {
                System.out.println("Invalid order number.");
                return;
            }

            String[] selectedOrder = orders.get(orderIndex);
            System.out.println("Selected order: " + String.join(" | ", selectedOrder));

            // Confirm update
            System.out.print("Are you sure you want to update this order? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if (!confirmation.equals("yes")) {
                System.out.println("Update cancelled.");
                return;
            }

            // Allow the user to modify each field
            System.out.print("\nEnter new supplier (or press Enter to keep current: " + selectedOrder[2] + "): ");
            String newSupplier = scanner.nextLine();
            if (!newSupplier.trim().isEmpty()) {
                selectedOrder[2] = newSupplier;
            }

            System.out.print("\nEnter new product (or press Enter to keep current: " + selectedOrder[3] + "): ");
            String newProduct = scanner.nextLine();
            if (!newProduct.trim().isEmpty()) {
                selectedOrder[3] = newProduct;
            }

            System.out.print("\nEnter new price (or press Enter to keep current: " + selectedOrder[4] + "): ");
            String newPriceInput = scanner.nextLine();
            if (!newPriceInput.trim().isEmpty()) {
                selectedOrder[4] = newPriceInput;
            }

            System.out.print("\nEnter new quantity (or press Enter to keep current: " + selectedOrder[5] + "): ");
            String newQuantityInput = scanner.nextLine();
            if (!newQuantityInput.trim().isEmpty()) {
                selectedOrder[5] = newQuantityInput;
            }

            // Recalculate total price
            double newPrice = Double.parseDouble(selectedOrder[4]);
            int newQuantity = Integer.parseInt(selectedOrder[5]);
            selectedOrder[6] = String.format("%.2f", newPrice * newQuantity);

            // Save the updated orders list back to the file
            saveOrders(orders);
            System.out.println("Order updated successfully!");

        } catch (IOException e) {
            System.out.println("Error reading or writing order file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        }
    }

    public void deleteOrder() {
        try {
            // Display all orders
            List<String[]> orders = readOrders();
            System.out.println("Available orders:");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println((i + 1) + ". " + String.join(" | ", orders.get(i)));
            }

            // Select the order to delete
            System.out.print("\nEnter order number to delete: ");
            int orderIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (orderIndex < 0 || orderIndex >= orders.size()) {
                System.out.println("Invalid order number.");
                return;
            }

            // Confirm deletion
            System.out.print("\nAre you sure you want to delete this order? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if (confirmation.equals("yes")) {
                orders.remove(orderIndex);
                saveOrders(orders);
                System.out.println("Order deleted successfully.");
            } else {
                System.out.println("Order deletion canceled.");
            }

        } catch (IOException e) {
            System.out.println("Error reading or writing order file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}