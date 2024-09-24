/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalAssignment;

import java.util.List;
import java.io.*;

public class CompanyAccount {
    private double balance;

    public CompanyAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void displayAccount(List<Transaction> transactions) {
        System.out.println("Company Account Balance: RM" + String.format("%.2f", balance));
        double totalOrderCost = calculateTotalOrderCost(transactions);
        System.out.println("Total Order Cost: RM" + String.format("%.2f", totalOrderCost));
        System.out.println("Remaining Balance: RM" + String.format("%.2f", (balance - totalOrderCost)));
    }

    public double calculateTotalOrderCost(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getTotalAmount).sum();
    }

    public void updateBalance(double amount) {
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public boolean transferToSupplier(String supplierId, double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Transferred RM" + String.format("%.2f", amount) + " to supplier " + supplierId);
            return true;
        } else {
            System.out.println("Insufficient funds for transfer.");
            return false;
        }
    }

    public void generateExpenseReport(List<Transaction> transactions) {
        System.out.println("\nCompany Expense Report");
        System.out.println("----------------------");
        
        double totalExpenses = calculateTotalOrderCost(transactions);
        System.out.println("Total Expenses: RM" + String.format("%.2f", totalExpenses));
        
        // Group transactions by product and calculate total spent on each
        transactions.stream()
            .collect(java.util.stream.Collectors.groupingBy(
                Transaction::getProdName,
                java.util.stream.Collectors.summingDouble(Transaction::getTotalAmount)
            ))
            .forEach((product, amount) -> 
                System.out.println(product + ": RM" + String.format("%.2f", amount))
            );
        
        // Simple budgeting technique suggestion
        System.out.println("\nBudgeting Suggestion:");
        System.out.println("Consider setting a monthly budget cap of RM" + 
                           String.format("%.2f", totalExpenses * 1.1) + 
                           " (10% above current expenses)");
        
        // Identify potential items to buy less
        System.out.println("\nItems to consider buying less:");
        transactions.stream()
            .collect(java.util.stream.Collectors.groupingBy(
                Transaction::getProdName,
                java.util.stream.Collectors.summingDouble(Transaction::getTotalAmount)
            ))
            .entrySet().stream()
            .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
            .limit(3)
            .forEach(e -> System.out.println(e.getKey() + " (Current spend: RM" + 
                                             String.format("%.2f", e.getValue()) + ")"));
    }
}