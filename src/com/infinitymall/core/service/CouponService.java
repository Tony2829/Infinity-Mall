package com.infinitymall.core.service;

import com.infinitymall.core.model.*;
import com.infinitymall.core.util.DataStore;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Service for generating and managing coupons
 */
public class CouponService {
    
    private static final Random RANDOM = new Random();
    
    /**
     * Generate a new coupon
     */
    public static Coupon generateCoupon(String source, double value, 
                                        boolean isPercentage, Coupon.CouponType type) {
        Coupon coupon = new Coupon();
        coupon.setCode(generateCode(source));
        coupon.setName(source + " Coupon");
        coupon.setDiscountValue(value);
        coupon.setPercentage(isPercentage);
        coupon.setType(type);
        coupon.setUsed(false);
        coupon.setExpiryDate(LocalDateTime.now().plusDays(7));
        coupon.setSource(source);
        coupon.setMinPurchase(0.0);
        
        DataStore.getInstance().incrementCouponsIssued();
        
        return coupon;
    }
    
    /**
     * Generate a coupon with minimum purchase requirement
     */
    public static Coupon generateCoupon(String source, double value, 
                                        boolean isPercentage, Coupon.CouponType type,
                                        double minPurchase) {
        Coupon coupon = generateCoupon(source, value, isPercentage, type);
        coupon.setMinPurchase(minPurchase);
        return coupon;
    }
    
    /**
     * Generate random coupon from prize pool
     */
    public static Coupon generateRandomCoupon(String source) {
        int prize = RANDOM.nextInt(100);
        
        if (prize < 30) {
            // 30% chance: ₹50 off
            return generateCoupon(source, 50, false, Coupon.CouponType.ALL);
        } else if (prize < 55) {
            // 25% chance: ₹100 off
            return generateCoupon(source, 100, false, Coupon.CouponType.ALL);
        } else if (prize < 70) {
            // 15% chance: 10% off
            return generateCoupon(source, 10, true, Coupon.CouponType.ALL);
        } else if (prize < 82) {
            // 12% chance: ₹200 off
            return generateCoupon(source, 200, false, Coupon.CouponType.ALL);
        } else if (prize < 92) {
            // 10% chance: 20% off
            return generateCoupon(source, 20, true, Coupon.CouponType.ALL);
        } else if (prize < 97) {
            // 5% chance: ₹500 off
            return generateCoupon(source, 500, false, Coupon.CouponType.ALL);
        } else {
            // 3% chance: ₹1000 off
            return generateCoupon(source, 1000, false, Coupon.CouponType.ALL);
        }
    }
    
    /**
     * Generate coupon code
     */
    private static String generateCode(String prefix) {
        String shortPrefix = prefix.length() > 4 ? prefix.substring(0, 4) : prefix;
        return shortPrefix.toUpperCase() + (1000 + RANDOM.nextInt(9000));
    }
    
    /**
     * Validate if coupon can be applied
     */
    public static boolean validateCoupon(Coupon coupon, double subtotal) {
        if (coupon == null) return false;
        if (coupon.isUsed()) return false;
        if (coupon.isExpired()) return false;
        if (subtotal < coupon.getMinPurchase()) return false;
        return true;
    }
    
    /**
     * Apply coupon to user's wallet
     */
    public static boolean applyCouponToUser(User user, String couponCode) {
        if (user == null || couponCode == null) return false;
        return user.useCoupon(couponCode);
    }
    
    /**
     * Get coupon types as array for display
     */
    public static String[] getCouponTypeNames() {
        Coupon.CouponType[] types = Coupon.CouponType.values();
        String[] names = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            names[i] = types[i].getEmoji() + " " + types[i].getDisplayName();
        }
        return names;
    }
}