package com.infinitymall.core.ui;

import com.infinitymall.core.model.User;
import java.util.Scanner;

/**
 * Central UI Helper - ALL console output must go through this class
 * Provides modern, premium console experience with colors, animations, and boxes
 * Java 8 Compatible
 */
public class UIHelper {
    
    public static final Scanner SCANNER = new Scanner(System.in);
    private static final int CONSOLE_WIDTH = 120;
    private static final int DEFAULT_BOX_WIDTH = 80;
    
    // ==================== SCREEN CONTROL ====================
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // ==================== CENTERED TEXT ====================
    
    public static String centerText(String text, int width) {
        if (text == null) text = "";
        int textLength = text.length();
        if (textLength >= width) return text;
        
        int padding = (width - textLength) / 2;
        int extra = (width - textLength) % 2;
        
        StringBuilder centered = new StringBuilder();
        for (int i = 0; i < padding; i++) centered.append(" ");
        centered.append(text);
        for (int i = 0; i < padding + extra; i++) centered.append(" ");
        
        return centered.toString();
    }
    
    public static void printCentered(String text) {
        String centered = centerText(text, CONSOLE_WIDTH);
        System.out.println(centered);
    }
    
    public static void printCentered(String text, String color) {
        String centered = centerText(text, CONSOLE_WIDTH);
        System.out.println(color + centered + BrandColors.RESET);
    }
    
    public static void printCentered(String text, String color, int width) {
        String centered = centerText(text, width);
        System.out.println(color + centered + BrandColors.RESET);
    }
    
    // ==================== LOADING ANIMATIONS ====================
    
    public static void loadingAnimation(String message) {
        System.out.print(BrandColors.CYAN + message + " ");
        
        String[] spinner = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};
        
        for (int i = 0; i < 15; i++) {
            System.out.print("\r" + BrandColors.CYAN + message + " " + 
                           BrandColors.YELLOW + spinner[i % spinner.length] + 
                           BrandColors.RESET);
            sleep(80);
        }
        
        System.out.println("\r" + BrandColors.GREEN + message + " ✓ DONE!" + 
                         BrandColors.RESET + "                    ");
        sleep(300);
    }
    
    public static void loadingBar(String message, int durationMillis) {
        int steps = 20;
        int stepDelay = durationMillis / steps;
        
        System.out.print(BrandColors.CYAN + message + "\n\n");
        
        for (int i = 0; i <= steps; i++) {
            int percent = (i * 100) / steps;
            String bar = EmojiConstants.getProgressBar(percent, 40);
            
            System.out.print("\r    " + BrandColors.YELLOW + "[" + bar + "] " + 
                           percent + "%" + BrandColors.RESET);
            sleep(stepDelay);
        }
        
        System.out.println("\n");
    }
    
    public static void typeWrite(String text, String color, int delayPerChar) {
        System.out.print(color);
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(delayPerChar);
        }
        System.out.println(BrandColors.RESET);
    }
    
    public static void typeWriteCentered(String text, String color, int delayPerChar) {
        int padding = (CONSOLE_WIDTH - text.length()) / 2;
        System.out.print(color);
        for (int i = 0; i < padding; i++) System.out.print(" ");
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(delayPerChar);
        }
        System.out.println(BrandColors.RESET);
    }
    
    // ==================== BOX DRAWING ====================
    
    public static void printHeader(String title, String emoji, String borderColor) {
        clearScreen();
        String fullTitle = emoji + " " + title + " " + emoji;
        int boxWidth = Math.min(DEFAULT_BOX_WIDTH, fullTitle.length() + 10);
        
        String topBottom = borderColor + "╔" + repeatChar('═', boxWidth - 2) + "╗" + BrandColors.RESET;
        String middle = borderColor + "║" + BrandColors.RESET + 
                       centerText(fullTitle, boxWidth - 2) + 
                       borderColor + "║" + BrandColors.RESET;
        String bottom = borderColor + "╚" + repeatChar('═', boxWidth - 2) + "╝" + BrandColors.RESET;
        
        System.out.println();
        System.out.println(centerText(topBottom, CONSOLE_WIDTH));
        System.out.println(centerText(middle, CONSOLE_WIDTH));
        System.out.println(centerText(bottom, CONSOLE_WIDTH));
        System.out.println();
    }
    
    public static void printBox(String title, String[] content, String borderColor) {
        int boxWidth = DEFAULT_BOX_WIDTH;
        
        String top = borderColor + "┌" + repeatChar('─', boxWidth - 2) + "┐" + BrandColors.RESET;
        System.out.println(centerText(top, CONSOLE_WIDTH));
        
        if (title != null && !title.isEmpty()) {
            String titleLine = borderColor + "│" + BrandColors.RESET + 
                              centerText(BrandColors.BOLD + title + BrandColors.RESET, boxWidth - 2) + 
                              borderColor + "│" + BrandColors.RESET;
            System.out.println(centerText(titleLine, CONSOLE_WIDTH));
            
            String separator = borderColor + "├" + repeatChar('─', boxWidth - 2) + "┤" + BrandColors.RESET;
            System.out.println(centerText(separator, CONSOLE_WIDTH));
        }
        
        if (content != null) {
            for (String line : content) {
                String contentLine = borderColor + "│" + BrandColors.RESET + 
                                   " " + line + 
                                   repeatChar(' ', boxWidth - line.length() - 3) + 
                                   borderColor + "│" + BrandColors.RESET;
                System.out.println(centerText(contentLine, CONSOLE_WIDTH));
            }
        }
        
        String bottom = borderColor + "└" + repeatChar('─', boxWidth - 2) + "┘" + BrandColors.RESET;
        System.out.println(centerText(bottom, CONSOLE_WIDTH));
    }
    
    public static void printDivider(String text, String color) {
        int textLength = text.length();
        int remaining = DEFAULT_BOX_WIDTH - textLength - 4;
        int leftDashes = remaining / 2;
        int rightDashes = remaining - leftDashes;
        
        String divider = color + repeatChar('─', leftDashes) + 
                        " " + BrandColors.BOLD + text + BrandColors.RESET + color + 
                        " " + repeatChar('─', rightDashes) + BrandColors.RESET;
        System.out.println(centerText(divider, CONSOLE_WIDTH));
    }
    
    public static void printSeparator(String color) {
        String line = repeatChar('─', DEFAULT_BOX_WIDTH);
        System.out.println(centerText(color + line + BrandColors.RESET, CONSOLE_WIDTH));
    }
    
    // ==================== MENU DISPLAY ====================
    
    public static void printMenuOptions(String[] options, String highlightColor) {
        for (int i = 0; i < options.length; i++) {
            String number = "[" + (i + 1) + "]";
            String line = BrandColors.GREEN + number + BrandColors.RESET + " " + options[i];
            System.out.println("    " + line);
        }
    }
    
    public static void printStatusBar(String leftText, String centerText, String rightText) {
        int thirdWidth = CONSOLE_WIDTH / 3;
        
        String left = String.format("%-" + thirdWidth + "s", leftText);
        String center = centerText(centerText, thirdWidth);
        String right = String.format("%" + thirdWidth + "s", rightText);
        
        String statusBar = left + center + right;
        String separator = BrandColors.INFINITY_GOLD + repeatChar('─', CONSOLE_WIDTH) + BrandColors.RESET;
        
        System.out.println(separator);
        System.out.println(BrandColors.CYAN + statusBar + BrandColors.RESET);
        System.out.println(separator);
    }
    
    public static void printUserHeader(User user) {
        int availableCoupons = 0;
        if (user != null && user.getCouponWallet() != null) {
            availableCoupons = user.getAvailableCoupons(null).size();
        }
        
        String left = EmojiConstants.PROFILE + " " + (user != null ? user.getFullName() : "Guest");
        String center = EmojiConstants.TICKET_WIN + " " + availableCoupons + " Coupons";
        String right = EmojiConstants.MONEY_BAG + " Spent: " + EmojiConstants.RUPEE + 
                      String.format("%.2f", user != null ? user.getTotalSpent() : 0.0);
        
        printStatusBar(left, center, right);
    }
    
    // ==================== USER INPUT ====================
    
    public static String getInput(String prompt) {
        System.out.print(BrandColors.MAGENTA + "\n👉 " + prompt + ": " + BrandColors.RESET);
        return SCANNER.nextLine();
    }
    
    public static String getPassword(String prompt) {
        System.out.print(BrandColors.MAGENTA + "\n👉 " + prompt + ": " + BrandColors.RESET);
        return SCANNER.nextLine();
    }
    
    public static int getIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(BrandColors.MAGENTA + "\n👉 " + prompt + ": " + BrandColors.RESET);
                int value = Integer.parseInt(SCANNER.nextLine());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println(BrandColors.RED + "⚠️ Please enter between " + min + " and " + max + BrandColors.RESET);
            } catch (NumberFormatException e) {
                System.out.println(BrandColors.RED + "⚠️ Please enter a valid number" + BrandColors.RESET);
            }
        }
    }
    
    public static boolean confirm(String message) {
        System.out.print(BrandColors.YELLOW + "\n❓ " + message + " (Y/N): " + BrandColors.RESET);
        String response = SCANNER.nextLine().trim().toUpperCase();
        return response.equals("Y") || response.equals("YES");
    }
    
    public static void pressEnterToContinue() {
        System.out.print(BrandColors.CYAN + "\n⏎ Press ENTER to continue..." + BrandColors.RESET);
        SCANNER.nextLine();
    }
    
    public static void pressAnyKeyToContinue() {
        System.out.print(BrandColors.CYAN + "\n⌨️ Press any key to continue..." + BrandColors.RESET);
        SCANNER.nextLine();
    }
    
    // ==================== NOTIFICATIONS ====================
    
    public static void showSuccess(String message) {
        System.out.println(BrandColors.GREEN + "\n✅ " + message + BrandColors.RESET);
        sleep(1000);
    }
    
    public static void showError(String message) {
        System.out.println(BrandColors.RED + "\n❌ " + message + BrandColors.RESET);
        sleep(1500);
    }
    
    public static void showWarning(String message) {
        System.out.println(BrandColors.YELLOW + "\n⚠️ " + message + BrandColors.RESET);
        sleep(1000);
    }
    
    public static void showInfo(String message) {
        System.out.println(BrandColors.CYAN + "\nℹ️ " + message + BrandColors.RESET);
        sleep(800);
    }
    
    // ==================== UTILITY ====================
    
    private static String repeatChar(char c, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}