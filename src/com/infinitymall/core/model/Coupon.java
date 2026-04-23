package com.infinitymall.core.model;

import java.time.LocalDateTime;

/**
 * Coupon model - Won from games or given as promotions
 */
public class Coupon {
    
    public enum CouponType {
        FOOD("🍔", "Food"),
        SHOPPING("🛍️", "Shopping"),
        MOVIE("🎬", "Movie"),
        PARKING("🚗", "Parking"),
        ALL("🎟️", "All");
        
        private final String emoji;
        private final String displayName;
        
        CouponType(String emoji, String displayName) {
            this.emoji = emoji;
            this.displayName = displayName;
        }
        
        public String getEmoji() { return emoji; }
        public String getDisplayName() { return displayName; }
    }
    
    private String code;
    private String name;
    private double discountValue;
    private boolean isPercentage;  // true for %, false for flat amount
    private CouponType type;
    private boolean isUsed;
    private LocalDateTime expiryDate;
    private String source;  // Where it came from (game name, promo, etc.)
    private double minPurchase;  // Minimum purchase required
    
    // ==================== CONSTRUCTORS ====================
    
    public Coupon() {
        this.isUsed = false;
        this.isPercentage = false;
        this.minPurchase = 0.0;
    }
    
    public Coupon(String code, double discountValue, CouponType type) {
        this();
        this.code = code;
        this.discountValue = discountValue;
        this.type = type;
        this.expiryDate = LocalDateTime.now().plusDays(7);
    }
    
    // ==================== GETTERS & SETTERS ====================
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getDiscountValue() { return discountValue; }
    public void setDiscountValue(double discountValue) { this.discountValue = discountValue; }
    
    public boolean isPercentage() { return isPercentage; }
    public void setPercentage(boolean percentage) { isPercentage = percentage; }
    
    public CouponType getType() { return type; }
    public void setType(CouponType type) { this.type = type; }
    
    public boolean isUsed() { return isUsed; }
    public void setUsed(boolean used) { isUsed = used; }
    
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    
    public double getMinPurchase() { return minPurchase; }
    public void setMinPurchase(double minPurchase) { this.minPurchase = minPurchase; }
    
    // ==================== UTILITY METHODS ====================
    
    public boolean isExpired() {
        return expiryDate != null && LocalDateTime.now().isAfter(expiryDate);
    }
    
    public String getDisplayValue() {
        if (isPercentage) {
            return (int) discountValue + "% OFF";
        } else {
            return "₹" + (int) discountValue + " OFF";
        }
    }
    
    public String getEmoji() {
        return type != null ? type.getEmoji() : "🎟️";
    }
    
    public String getStatus() {
        if (isUsed) return "USED";
        if (isExpired()) return "EXPIRED";
        return "ACTIVE";
    }
    
    public String getDaysLeft() {
        if (expiryDate == null) return "No expiry";
        if (isExpired()) return "Expired";
        
        long days = java.time.Duration.between(LocalDateTime.now(), expiryDate).toDays();
        if (days == 0) return "Expires today";
        if (days == 1) return "Expires tomorrow";
        return "Expires in " + days + " days";
    }
    
    // ==================== TO STRING ====================
    
    @Override
    public String toString() {
        return String.format("%s %s - %s (%s) [%s]", 
                getEmoji(), code, getDisplayValue(), 
                type != null ? type.getDisplayName() : "ALL", 
                getStatus());
    }
}