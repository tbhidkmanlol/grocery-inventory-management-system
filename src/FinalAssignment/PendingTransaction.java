package FinalAssignment;

import java.time.LocalDate;

public class PendingTransaction extends Transaction {

    public PendingTransaction(String orderId, LocalDate expDate, String supplierName,
            String prodName, int quantity, double price, double totalAmount) {
        super(orderId, expDate, "Pending", supplierName, prodName, quantity, price, totalAmount);
    }

    @Override
    public void displayDetails() {
        System.out.printf("Pending Transaction - Order ID: %s, Exp Date: %s, Supplier: %s, Product: %s, Quantity: %d, Price: RM%.2f, Total: RM%.2f%n",
                getOrderId(), getExpDate(), getSupplierName(), getProdName(), getQuantity(), getPrice(), getTotalAmount());
    }

    @Override
    public String toString() {
        return "Pending " + super.toString();
    }
}
