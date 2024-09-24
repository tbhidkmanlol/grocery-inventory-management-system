/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalAssignment;

import java.time.LocalDate;
import java.time.LocalDate;

public class CompletedTransaction extends Transaction {

    public CompletedTransaction(String orderId, LocalDate expDate, String supplierName,
            String prodName, int quantity, double price, double totalAmount) {
        // Pass the parameters to the Transaction constructor
        super(orderId, expDate, "Completed", supplierName, prodName, quantity, price, totalAmount);
    }

    @Override
    public void displayDetails() {
        System.out.println("Completed Transaction - Order ID: " + getOrderId()
                + ", Total Amount: RM" + getTotalAmount()
                + ", Supplier: " + getSupplierName());
    }

    @Override
    public String toString() {
        return "Completed " + super.toString();
    }
}
