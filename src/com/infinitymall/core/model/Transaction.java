package com.infinitymall.core.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Transaction model - Records every payment
 */
public class Transaction {
    
    private String transactionId;
    private String userId;
    private String module;
    private double amount;
    private String description;
    private Date timestamp;
    private String paymentMethod;
    private String status;
    
    private static final SimpleDateFormat DATE_FORMAT = 
        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    // ==================== CONSTRUCTORS ====================
    
    public Transaction() {
        this.transactionId = generateTransactionId();
        this.timestamp = new Date();
        this.status = "SUCCESS";
        this.paymentMethod = "UPI";
    }
    
    public Transaction(String userId, String module, double amount, String description) {
        this();
        this.userId = userId;
        this.module = module;
        this.amount = amount;
        this.description = description;
    }
    
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis();
    }
    
    // ==================== GETTERS & SETTERS ====================
    
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    // ==================== UTILITY METHODS ====================
    
    public String getFormattedDate() {
        return DATE_FORMAT.format(timestamp);
    }
    
    public String getFormattedTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(timestamp);
    }
    
    public String getModuleEmoji() {
        if (module == null) return "💰";
        switch (module.toUpperCase()) {
            case "MULTIPLEX": return "🎬";
            case "FOOD": return "🍔";
            case "SHOPPING": return "🛍️";
            case "PARKING": return "🚗";
            case "GAMES": return "🎮";
            default: return "💰";
        }
    }
    
    // ==================== TO STRING ====================
    
    @Override
    public String toString() {
        return String.format("%s %s %s - ₹%.2f (%s)", 
                getModuleEmoji(), getFormattedTime(), description, amount, transactionId);
    }
    
    public String toDetailedString() {
        return String.format("[%s] %s | %s | ₹%.2f | %s | %s",
                transactionId, getFormattedDate(), module, amount, description, status);
    }
}