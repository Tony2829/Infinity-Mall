package com.infinitymall;

import com.infinitymall.core.model.*;
import com.infinitymall.core.service.*;
import com.infinitymall.core.ui.*;
import com.infinitymall.core.util.*;

import java.util.Map; 

/**
 * INFINITY MALL - Main Entry Point
 * Premium Console Shopping & Entertainment System
 */
public class InfinityMallMain {
    
    private static User currentUser = null;
    
    public static void main(String[] args) {
        // Show splash screen
        showSplashScreen();
        
        // Authentication loop
        while (currentUser == null) {
            currentUser = AuthService.showAuthMenu();
        }
        
        // Main mall loop
        boolean running = true;
        while (running) {
            int choice = showMainMenu();
            
            switch (choice) {
                case 1:
                    // Multiplex - Team Member 1
                    UIHelper.showInfo("🎬 Multiplex module loading...");
                    UIHelper.sleep(1000);
                    // MultiplexModule.start(currentUser);
                    showComingSoon("Multiplex");
                    break;
                    
                case 2:
                    // Food Court - Team Member 2
                    UIHelper.showInfo("🍔 Food Court module loading...");
                    UIHelper.sleep(1000);
                    // FoodCourtModule.start(currentUser);
                    showComingSoon("Food Court");
                    break;
                    
                case 3:
                    // Shopping Zone - Team Member 3
                    UIHelper.showInfo("🛍️ Shopping Zone module loading...");
                    UIHelper.sleep(1000);
                    // ShoppingModule.start(currentUser);
                    showComingSoon("Shopping Zone");
                    break;
                    
                case 4:
                    // Games Zone - Team Member 4
                    UIHelper.showInfo("🎮 Games Zone module loading...");
                    UIHelper.sleep(1000);
                    // GamesModule.start(currentUser);
                    showComingSoon("Games Zone");
                    break;
                    
                case 5:
                    // Parking - Team Member 5
                    UIHelper.showInfo("🚗 Parking module loading...");
                    UIHelper.sleep(1000);
                    // ParkingModule.start(currentUser);
                    showComingSoon("Parking");
                    break;
                    
                case 6:
                    // Profile - Team Member 7
                    showProfile();
                    break;
                    
                case 7:
                    // Admin - Team Member 6
                    showAdmin();
                    break;
                    
                case 8:
                    // Logout
                    if (UIHelper.confirm("Are you sure you want to logout?")) {
                        DataStore.getInstance().removeActiveSession(currentUser.getUserId());
                        currentUser.setLoggedIn(false);
                        currentUser = null;
                        UIHelper.typeWriteCentered("👋 Thank you for visiting Infinity Mall!", 
                                                  BrandColors.CYAN, 40);
                        UIHelper.sleep(1500);
                    }
                    break;
                    
                case 9:
                    // Exit
                    if (UIHelper.confirm("Are you sure you want to exit?")) {
                        running = false;
                        UIHelper.clearScreen();
                        UIHelper.typeWriteCentered("🏢 Infinity Mall - See You Again Soon! 🏢", 
                                                  BrandColors.INFINITY_GOLD, 50);
                        UIHelper.sleep(1500);
                    }
                    break;
                    
                default:
                    UIHelper.showError("Invalid choice!");
            }
        }
        
        System.exit(0);
    }
    
    /**
     * Show splash screen
     */
    private static void showSplashScreen() {
        UIHelper.clearScreen();
        
        String[] splash = {
            "",
            BrandColors.INFINITY_GOLD + BrandColors.BOLD,
            "    ██╗███╗   ██╗███████╗██╗███╗   ██╗██╗████████╗██╗   ██╗",
            "    ██║████╗  ██║██╔════╝██║████╗  ██║██║╚══██╔══╝╚██╗ ██╔╝",
            "    ██║██╔██╗ ██║█████╗  ██║██╔██╗ ██║██║   ██║    ╚████╔╝ ",
            "    ██║██║╚██╗██║██╔══╝  ██║██║╚██╗██║██║   ██║     ╚██╔╝  ",
            "    ██║██║ ╚████║██║     ██║██║ ╚████║██║   ██║      ██║   ",
            "    ╚═╝╚═╝  ╚═══╝╚═╝     ╚═╝╚═╝  ╚═══╝╚═╝   ╚═╝      ╚═╝   ",
            "",
            "    ███╗   ███╗ █████╗ ██╗     ██╗",
            "    ████╗ ████║██╔══██╗██║     ██║",
            "    ██╔████╔██║███████║██║     ██║",
            "    ██║╚██╔╝██║██╔══██║██║     ██║",
            "     ██║ ╚═╝ ██║██║  ██║███████╗███████╗",
            "     ╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝",
            "",
            BrandColors.RESET
        };
        
        for (String line : splash) {
            UIHelper.printCentered(line, BrandColors.RESET);
            UIHelper.sleep(40);
        }
        
        System.out.println();
        UIHelper.printCentered(BrandColors.CYAN + "═══════════════════════════════════════════════════════" + 
                             BrandColors.RESET, BrandColors.RESET);
        UIHelper.printCentered(BrandColors.YELLOW + "🏢 Your Premium Shopping & Entertainment Destination 🏢" + 
                             BrandColors.RESET, BrandColors.RESET);
        UIHelper.printCentered(BrandColors.CYAN + "═══════════════════════════════════════════════════════" + 
                             BrandColors.RESET, BrandColors.RESET);
        
        System.out.println();
        UIHelper.loadingBar("🏢 Loading Infinity Mall", 2000);
    }
    
    /**
     * Show main menu
     */
    private static int showMainMenu() {
        UIHelper.clearScreen();
        UIHelper.printHeader("INFINITY MALL - MAIN ATRIUM", "🏢", BrandColors.INFINITY_GOLD);
        
        // User status
        UIHelper.printUserHeader(currentUser);
        System.out.println();
        
        // Main menu grid
        String[] mainOptions = {
            "🎬 MULTIPLEX - Book Movie Tickets",
            "🍔 FOOD COURT - Delicious Food",
            "🛍️ SHOPPING ZONE - Top Brands",
            "🎮 GAMES ZONE - Play & Win",
            "🚗 PARKING - Vehicle Parking"
        };
        
        String[] secondaryOptions = {
            "👤 MY PROFILE - Wallet & History",
            "👨‍💼 ADMIN PANEL - Staff Only",
            "🚪 LOGOUT",
            "❌ EXIT"
        };
        
        // Display main options as cards
        System.out.println();
        for (int i = 0; i < mainOptions.length; i++) {
            String option = mainOptions[i];
            String card = "╭─────────────────────────────────────────────────────╮\n" +
                         "│                                                     │\n" +
                         "│   [" + (i + 1) + "] " + option + "\n" +
                         "│                                                     │\n" +
                         "╰─────────────────────────────────────────────────────╯";
            
            String[] lines = card.split("\n");
            for (String line : lines) {
                UIHelper.printCentered(line, BrandColors.INFINITY_BLUE);
            }
            System.out.println();
        }
        
        // Secondary options
        UIHelper.printDivider("MORE OPTIONS", BrandColors.INFINITY_GOLD);
        for (int i = 0; i < secondaryOptions.length; i++) {
            String num = "[" + (mainOptions.length + i + 1) + "]";
            System.out.println("    " + BrandColors.GREEN + num + BrandColors.RESET + " " + secondaryOptions[i]);
        }
        
        UIHelper.printSeparator(BrandColors.INFINITY_GOLD);
        
        return UIHelper.getIntInput("Enter your choice", 1, mainOptions.length + secondaryOptions.length);
    }
    
    /**
     * Show profile (temporary until Team Member 7 completes)
     */
    private static void showProfile() {
        UIHelper.clearScreen();
        UIHelper.printHeader("MY PROFILE", "👤", BrandColors.INFINITY_GOLD);
        
        String[] info = {
            "👤 Name: " + currentUser.getFullName(),
            "📧 Email: " + currentUser.getEmail(),
            "📱 Phone: " + currentUser.getPhone(),
            "💰 Total Spent: " + BillingEngine.formatAmount(currentUser.getTotalSpent()),
            "🎟️ Active Coupons: " + currentUser.getActiveCouponCount()
        };
        
        UIHelper.printBox("ACCOUNT DETAILS", info, BrandColors.INFINITY_BLUE);
        
        // Show active parking if any
        if (currentUser.hasActiveParking()) {
            System.out.println();
            String[] parkingInfo = {
                currentUser.getActiveParkingTicket().toString()
            };
            UIHelper.printBox("🚗 ACTIVE PARKING", parkingInfo, BrandColors.YELLOW);
        }
        
        UIHelper.pressEnterToContinue();
    }
    
    /**
     * Show admin panel (temporary until Team Member 6 completes)
     */
    private static void showAdmin() {
        UIHelper.clearScreen();
        UIHelper.printHeader("ADMIN PANEL", "👨‍💼", BrandColors.INFINITY_PURPLE);
        
        String password = UIHelper.getPassword("Enter admin password");
        
        if (password.equals("admin123")) {
            DataStore store = DataStore.getInstance();
            
            String[] stats = {
                "👥 Total Users: " + store.getTotalUsers(),
                "🟢 Active Sessions: " + store.getActiveSessions(),
                "💰 Total Revenue: " + BillingEngine.formatAmount(store.getTotalRevenue()),
                "🎟️ Coupons Issued: " + store.getTotalCouponsIssued(),
                "✅ Coupons Redeemed: " + store.getTotalCouponsRedeemed()
            };
            
            UIHelper.printBox("SYSTEM STATISTICS", stats, BrandColors.GREEN);
            
            System.out.println();
            UIHelper.printDivider("MODULE REVENUE", BrandColors.INFINITY_GOLD);
            for (Map.Entry<String, Double> entry : store.getAllModuleRevenue().entrySet()) {
                System.out.println("    " + entry.getKey() + ": " + BillingEngine.formatAmount(entry.getValue()));
            }
        } else {
            UIHelper.showError("Invalid admin password!");
        }
        
        UIHelper.pressEnterToContinue();
    }
    
    /**
     * Show coming soon for incomplete modules
     */
    private static void showComingSoon(String moduleName) {
        UIHelper.clearScreen();
        UIHelper.printHeader(moduleName.toUpperCase(), "🚧", BrandColors.YELLOW);
        
        String[] message = {
            "",
            "🚧 This module is under construction",
            "",
            "👷 Team member is working hard to",
            "   bring you an amazing experience!",
            "",
            "📅 Coming soon...",
            ""
        };
        
        UIHelper.printBox("UNDER CONSTRUCTION", message, BrandColors.YELLOW);
        UIHelper.pressEnterToContinue();
    }
}