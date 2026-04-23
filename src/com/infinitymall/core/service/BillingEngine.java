package com.infinitymall.core.service;

import com.infinitymall.core.model.*;
import com.infinitymall.core.util.DataStore;

/**
 * Standard billing engine - EVERY module MUST use this for calculations
 */
public class BillingEngine {
    
    private static final double GST_RATE = 0.05; // 5%
    
    /**
     * Calculate final bill with GST and discount
     * @param subtotal Total before discount
     * @param coupon Coupon to apply (can be null)
     * @return Bill object with all calculations
     */
    public static Bill calculate(double subtotal, Coupon coupon) {
        Bill bill = new Bill();
        bill.setSubtotal(subtotal);
        
        double discount = 0.0;
        String couponCode = "NONE";
        
        // Apply coupon if valid
        if (coupon != null && !coupon.isUsed() && !coupon.isExpired()) {
            // Check minimum purchase requirement
            if (subtotal >= coupon.getMinPurchase()) {
                if (coupon.isPercentage()) {
                    discount = subtotal * (coupon.getDiscountValue() / 100.0);
                } else {
                    discount = Math.min(coupon.getDiscountValue(), subtotal);
                }
                coupon.setUsed(true);
                couponCode = coupon.getCode();
                
                // Track coupon redemption
                DataStore.getInstance().incrementCouponsRedeemed();
            }
        }
        
        bill.setDiscount(discount);
        bill.setCouponUsed(couponCode);
        
        // Calculate GST on amount after discount
        double taxableAmount = Math.max(0, subtotal - discount);
        double gst = taxableAmount * GST_RATE;
        bill.setGst(gst);
        
        // Final amount
        double finalAmount = taxableAmount + gst;
        bill.setFinalAmount(finalAmount);
        
        return bill;
    }
    
    /**
     * Calculate without coupon
     */
    public static Bill calculate(double subtotal) {
        return calculate(subtotal, null);
    }
    
    /**
     * Calculate discount amount for display
     */
    public static double calculateDiscount(double subtotal, Coupon coupon) {
        if (coupon == null || coupon.isUsed() || coupon.isExpired()) {
            return 0.0;
        }
        if (subtotal < coupon.getMinPurchase()) {
            return 0.0;
        }
        if (coupon.isPercentage()) {
            return subtotal * (coupon.getDiscountValue() / 100.0);
        }
        return Math.min(coupon.getDiscountValue(), subtotal);
    }
    
    /**
     * Format amount with currency symbol
     */
    public static String formatAmount(double amount) {
        return "₹" + String.format("%.2f", amount);
    }
}