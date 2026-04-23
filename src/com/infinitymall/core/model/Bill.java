package com.infinitymall.core.model;

/**
 * Bill model - Result of billing calculation
 */
public class Bill {
    
    private double subtotal;
    private double discount;
    private double gst;
    private double finalAmount;
    private String couponUsed;
    private String billId;
    private String module;
    
    // GST Rate
    public static final double GST_RATE = 0.05; // 5%
    
    // ==================== CONSTRUCTORS ====================
    
    public Bill() {
        this.couponUsed = "NONE";
        this.billId = generateBillId();
    }
    
    public Bill(double subtotal, double discount, double gst, double finalAmount) {
        this();
        this.subtotal = subtotal;
        this.discount = discount;
        this.gst = gst;
        this.finalAmount = finalAmount;
    }
    
    public Bill(double subtotal, double discount, double gst, double finalAmount, String couponUsed) {
        this(subtotal, discount, gst, finalAmount);
        this.couponUsed = couponUsed;
    }
    
    private String generateBillId() {
        return "BILL" + System.currentTimeMillis() % 100000;
    }
    
    // ==================== GETTERS & SETTERS ====================
    
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    
    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
    
    public double getGst() { return gst; }
    public void setGst(double gst) { this.gst = gst; }
    
    public double getFinalAmount() { return finalAmount; }
    public void setFinalAmount(double finalAmount) { this.finalAmount = finalAmount; }
    
    public String getCouponUsed() { return couponUsed; }
    public void setCouponUsed(String couponUsed) { this.couponUsed = couponUsed; }
    
    public String getBillId() { return billId; }
    public void setBillId(String billId) { this.billId = billId; }
    
    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }
    
    // ==================== UTILITY METHODS ====================
    
    public double getSavings() {
        return discount;
    }
    
    public String[] getReceiptLines() {
        return new String[] {
            "Bill ID: " + billId,
            "─────────────────────────────────",
            String.format("Subtotal:        ₹%.2f", subtotal),
            String.format("Discount:       -₹%.2f", discount),
            String.format("GST (5%%):       +₹%.2f", gst),
            "─────────────────────────────────",
            String.format("TOTAL:          ₹%.2f", finalAmount),
            "",
            "Coupon Used: " + couponUsed
        };
    }
    
    // ==================== TO STRING ====================
    
    @Override
    public String toString() {
        return String.format("Bill[%s] Subtotal: ₹%.2f, Discount: ₹%.2f, GST: ₹%.2f, Total: ₹%.2f",
                billId, subtotal, discount, gst, finalAmount);
    }
}