package com.infinitymall.core.service;

import com.infinitymall.core.model.*;
import com.infinitymall.core.ui.*;
import com.infinitymall.core.util.DataStore;
import java.util.Random;

/**
 * Payment Gateway - Simulates UPI/Card payments
 */
public class PaymentGateway {
    
    private static final Random RANDOM = new Random();
    private static final double SUCCESS_RATE = 0.90; // 90% success rate
    
    /**
     * Process payment with user context
     */
    public static boolean process(double amount, String module, User user) {
        UIHelper.clearScreen();
        UIHelper.printHeader("PAYMENT GATEWAY", "💳", BrandColors.INFINITY_GOLD);
        
        System.out.println();
        UIHelper.printCentered(BrandColors.CYAN + "💰 AMOUNT TO PAY: " + 
                             BrandColors.YELLOW + BrandColors.BOLD + 
                             "₹" + String.format("%.2f", amount) + 
                             BrandColors.RESET, BrandColors.RESET);
        System.out.println();
        
        // Payment method selection
        UIHelper.printDivider("SELECT PAYMENT METHOD", BrandColors.INFINITY_GOLD);
        System.out.println();
        
        String[] methods = {
            "💳 Credit/Debit Card",
            "📱 UPI (Google Pay / PhonePe / Paytm)",
            "🏧 Net Banking",
            "❌ Cancel Payment"
        };
        
        UIHelper.printMenuOptions(methods, BrandColors.GREEN);
        
        int choice = UIHelper.getIntInput("Select payment method", 1, 4);
        
        if (choice == 4) {
            UIHelper.showWarning("Payment cancelled");
            return false;
        }
        
        // Simulate payment processing
        System.out.println();
        UIHelper.loadingBar("Processing payment", 2000);
        
        // For UPI, ask for PIN
        if (choice == 2) {
            System.out.println();
            String pin = UIHelper.getPassword("Enter UPI PIN");
            // Any 4-6 digit PIN works for demo
        }
        
        // Random success/failure
        boolean success = RANDOM.nextDouble() < SUCCESS_RATE;
        
        if (success) {
            UIHelper.showSuccess("Payment Successful!");
            
            // Record transaction
            if (user != null) {
                Transaction txn = new Transaction(
                    user.getUserId(), 
                    module, 
                    amount, 
                    "Payment for " + module
                );
                DataStore.getInstance().addTransaction(txn);
                
                // Update user's total spent
                user.addSpending(amount);
            }
            
            // Show success animation
            System.out.println();
            String[] successArt = {
                "✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅",
                "     TRANSACTION COMPLETE     ",
                "✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅ ✅"
            };
            for (String line : successArt) {
                UIHelper.printCentered(line, BrandColors.GREEN);
                UIHelper.sleep(200);
            }
            
            UIHelper.sleep(1000);
            return true;
            
        } else {
            UIHelper.showError("Payment Failed! Please try again.");
            System.out.println();
            UIHelper.printCentered(BrandColors.YELLOW + "💡 Tip: Check your internet connection or try another method" + 
                                 BrandColors.RESET, BrandColors.RESET);
            UIHelper.sleep(2000);
            return false;
        }
    }
    
    /**
     * Process payment without user context
     */
    public static boolean process(double amount, String module) {
        return process(amount, module, null);
    }
    
    /**
     * Quick payment (skips method selection for demo)
     */
    public static boolean quickProcess(double amount, String module, User user) {
        UIHelper.loadingAnimation("💳 Processing payment");
        UIHelper.sleep(500);
        
        boolean success = RANDOM.nextDouble() < SUCCESS_RATE;
        
        if (success && user != null) {
            Transaction txn = new Transaction(
                user.getUserId(), 
                module, 
                amount, 
                "Payment for " + module
            );
            DataStore.getInstance().addTransaction(txn);
            user.addSpending(amount);
        }
        
        return success;
    }
}