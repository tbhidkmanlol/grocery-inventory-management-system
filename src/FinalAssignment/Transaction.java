/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalAssignment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Transaction {

    private String orderId;
    private LocalDate expDate;
    private String status;
    private String supplierName;
    private String prodName;
    private int quantity;
    private double price;
    private double totalAmount;

    public Transaction(String orderId, LocalDate expDate, String status, String supplierName,
            String prodName, int quantity, double price, double totalAmount) {
        this.orderId = orderId;
        this.expDate = expDate;
        this.status = status;
        this.supplierName = supplierName;
        this.prodName = prodName;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
    }

    public void displayDetails() {
        System.out.printf("Order ID: %s, Exp Date: %s, Status: %s, Supplier: %s, Product: %s, Quantity: %d, Price: $%.2f, Total: $%.2f%n",
                orderId, expDate, status, supplierName, prodName, quantity, price, totalAmount);
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public String getStatus() {
        return status;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getProdName() {
        return prodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    public String toFileString() {
    return String.join("|", orderId, expDate.toString(), status, supplierName, prodName, String.valueOf(quantity), String.valueOf(price), String.valueOf(totalAmount));
    }
    
     @Override
    public String toString() {
        return String.format("Order ID: %s, Supplier: %s, Product: %s, Quantity: %d, Price: RM%.2f, Total: RM%.2f", 
                             orderId, supplierName, prodName, quantity, price, totalAmount);
    }
}
