package com.infinitymall.core.model;

import java.time.LocalDateTime;
import java.time.Duration;

/**
 * Parking Ticket model - For active parking sessions
 */
public class ParkingTicket {
    
    private String ticketId;
    private String vehicleType;
    private String vehicleNumber;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean isActive;
    private double ratePerHour;
    
    // ==================== CONSTRUCTORS ====================
    
    public ParkingTicket() {
        this.ticketId = generateTicketId();
        this.entryTime = LocalDateTime.now();
        this.isActive = true;
    }
    
    public ParkingTicket(String vehicleType, String vehicleNumber, double ratePerHour) {
        this();
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.ratePerHour = ratePerHour;
    }
    
    private String generateTicketId() {
        return "PARK" + System.currentTimeMillis() % 100000;
    }
    
    // ==================== GETTERS & SETTERS ====================
    
    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }
    
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    
    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }
    
    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
    
    public boolean isActive() { return isActive && exitTime == null; }
    public void setActive(boolean active) { isActive = active; }
    
    public double getRatePerHour() { return ratePerHour; }
    public void setRatePerHour(double ratePerHour) { this.ratePerHour = ratePerHour; }
    
    // ==================== CALCULATION METHODS ====================
    
    public double calculateHours() {
        LocalDateTime endTime = (exitTime != null) ? exitTime : LocalDateTime.now();
        long minutes = Duration.between(entryTime, endTime).toMinutes();
        return minutes / 60.0;
    }
    
    public double calculateAmount() {
        return calculateHours() * ratePerHour;
    }
    
    public String getElapsedHours() {
        double hours = calculateHours();
        if (hours < 1) {
            return String.format("%.0f mins", hours * 60);
        }
        return String.format("%.1f hrs", hours);
    }
    
    public String getVehicleEmoji() {
        if (vehicleType == null) return "🚗";
        switch (vehicleType.toUpperCase()) {
            case "BIKE": return "🏍️";
            case "CAR": return "🚗";
            case "SUV": return "🚙";
            default: return "🚗";
        }
    }
    
    // ==================== TO STRING ====================
    
    @Override
    public String toString() {
        return String.format("%s %s | Entry: %s | Duration: %s | ₹%.2f",
                getVehicleEmoji(), vehicleNumber, 
                entryTime.toString(), getElapsedHours(), calculateAmount());
    }
}