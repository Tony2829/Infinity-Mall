package com.infinitymall.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * User model - Shared across all modules
 */
public class User {
    
    private String userId;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private List<Coupon> couponWallet;
    private ParkingTicket activeParkingTicket;
    private double totalSpent;
    private boolean loggedIn;
    
    // ==================== CONSTRUCTORS ====================
    
    public User() {
        this.couponWallet = new ArrayList<>();
        this.totalSpent = 0.0;
        this.loggedIn = false;
    }
    
    public User(String fullName, String email, String phone, String password) {
        this();
        this.userId = generateUserId();
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    
    private String generateUserId() {
        return "USER" + System.currentTimeMillis() % 100000;
    }
    
    // ==================== GETTERS & SETTERS ====================
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public List<Coupon> getCouponWallet() { return couponWallet; }
    public void setCouponWallet(List<Coupon> couponWallet) { this.couponWallet = couponWallet; }
    
    public ParkingTicket getActiveParkingTicket() { return activeParkingTicket; }
    public void setActiveParkingTicket(ParkingTicket activeParkingTicket) { 
        this.activeParkingTicket = activeParkingTicket; 
    }
    
    public double getTotalSpent() { return totalSpent; }
    public void setTotalSpent(double totalSpent) { this.totalSpent = totalSpent; }
    
    public boolean isLoggedIn() { return loggedIn; }
    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }
    
    // ==================== COUPON METHODS ====================
    
    public void addCoupon(Coupon coupon) {
        if (couponWallet == null) {
            couponWallet = new ArrayList<>();
        }
        couponWallet.add(coupon);
    }
    
    public List<Coupon> getAvailableCoupons(Coupon.CouponType type) {
        List<Coupon> available = new ArrayList<>();
        for (Coupon c : couponWallet) {
            if (!c.isUsed() && !c.isExpired()) {
                if (type == null || c.getType() == Coupon.CouponType.ALL || c.getType() == type) {
                    available.add(c);
                }
            }
        }
        return available;
    }
    
    public boolean useCoupon(String couponCode) {
        for (Coupon c : couponWallet) {
            if (c.getCode().equals(couponCode) && !c.isUsed() && !c.isExpired()) {
                c.setUsed(true);
                return true;
            }
        }
        return false;
    }
    
    public int getActiveCouponCount() {
        int count = 0;
        for (Coupon c : couponWallet) {
            if (!c.isUsed() && !c.isExpired()) count++;
        }
        return count;
    }
    
    // ==================== SPENDING METHODS ====================
    
    public void addSpending(double amount) {
        this.totalSpent += amount;
    }
    
    // ==================== PARKING METHODS ====================
    
    public boolean hasActiveParking() {
        return activeParkingTicket != null && activeParkingTicket.isActive();
    }
    
    public void clearParking() {
        this.activeParkingTicket = null;
    }
    
    // ==================== TO STRING ====================
    
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", coupons=" + couponWallet.size() +
                ", totalSpent=" + totalSpent +
                '}';
    }
}