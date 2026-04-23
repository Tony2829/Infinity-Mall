package com.infinitymall.core.ui;

/**
 * Brand-specific colors for consistent theming across all modules
 * All colors are Java 8 compatible ANSI escape codes
 */
public class BrandColors {
    
    // ==================== RESET ====================
    public static final String RESET = "\u001B[0m";
    
    // ==================== MALL BRANDING ====================
    public static final String INFINITY_GOLD = "\u001B[38;5;220m";
    public static final String INFINITY_PURPLE = "\u001B[38;5;129m";
    public static final String INFINITY_BLUE = "\u001B[38;5;33m";
    
    // ==================== CINEMA BRANDS ====================
    public static final String PVR_RED = "\u001B[38;5;196m";
    public static final String PVR_RED_BG = "\u001B[48;5;196m";
    public static final String INOX_BLUE = "\u001B[38;5;27m";
    public static final String CINEPOLIS_ORANGE = "\u001B[38;5;202m";
    
    // ==================== FOOD BRANDS ====================
    // KFC - Red theme
    public static final String KFC_RED = "\u001B[38;5;196m";
    public static final String KFC_RED_BG = "\u001B[48;5;196m";
    public static final String KFC_WHITE = "\u001B[97m";
    
    // McDonald's - Yellow/Red theme
    public static final String MCD_YELLOW = "\u001B[38;5;226m";
    public static final String MCD_YELLOW_BG = "\u001B[48;5;226m";
    public static final String MCD_RED = "\u001B[38;5;196m";
    
    // Starbucks - Green theme
    public static final String SBUX_GREEN = "\u001B[38;5;34m";
    public static final String SBUX_GREEN_BG = "\u001B[48;5;34m";
    public static final String SBUX_WHITE = "\u001B[97m";
    
    // Paradise Biryani - Orange theme
    public static final String PARADISE_ORANGE = "\u001B[38;5;208m";
    public static final String PARADISE_ORANGE_BG = "\u001B[48;5;208m";
    
    // Haldiram's - Orange/Yellow theme
    public static final String HALDIRAM_ORANGE = "\u001B[38;5;214m";
    public static final String HALDIRAM_YELLOW = "\u001B[38;5;220m";
    
    // ==================== SHOPPING BRANDS ====================
    // Nike - Orange/Black
    public static final String NIKE_ORANGE = "\u001B[38;5;202m";
    public static final String NIKE_ORANGE_BG = "\u001B[48;5;202m";
    public static final String NIKE_BLACK = "\u001B[30m";
    
    // Zara - Black/White
    public static final String ZARA_BLACK = "\u001B[30m";
    public static final String ZARA_BLACK_BG = "\u001B[40m";
    public static final String ZARA_WHITE = "\u001B[97m";
    
    // Croma - Blue
    public static final String CROMA_BLUE = "\u001B[38;5;33m";
    public static final String CROMA_BLUE_BG = "\u001B[48;5;33m";
    
    // Tanishq - Purple/Gold
    public static final String TANISHQ_PURPLE = "\u001B[38;5;129m";
    public static final String TANISHQ_PURPLE_BG = "\u001B[48;5;129m";
    public static final String TANISHQ_GOLD = "\u001B[38;5;220m";
    
    // Decathlon - Blue/White
    public static final String DECATHLON_BLUE = "\u001B[38;5;27m";
    public static final String DECATHLON_BLUE_BG = "\u001B[48;5;27m";
    
    // ==================== STANDARD ANSI COLORS ====================
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    // ==================== BRIGHT COLORS ====================
    public static final String BLACK_BRIGHT = "\u001B[90m";
    public static final String RED_BRIGHT = "\u001B[91m";
    public static final String GREEN_BRIGHT = "\u001B[92m";
    public static final String YELLOW_BRIGHT = "\u001B[93m";
    public static final String BLUE_BRIGHT = "\u001B[94m";
    public static final String MAGENTA_BRIGHT = "\u001B[95m";
    public static final String CYAN_BRIGHT = "\u001B[96m";
    public static final String WHITE_BRIGHT = "\u001B[97m";
    
    // ==================== BACKGROUND COLORS ====================
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_MAGENTA = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";
    
    // ==================== BRIGHT BACKGROUNDS ====================
    public static final String BG_BLACK_BRIGHT = "\u001B[100m";
    public static final String BG_RED_BRIGHT = "\u001B[101m";
    public static final String BG_GREEN_BRIGHT = "\u001B[102m";
    public static final String BG_YELLOW_BRIGHT = "\u001B[103m";
    public static final String BG_BLUE_BRIGHT = "\u001B[104m";
    public static final String BG_MAGENTA_BRIGHT = "\u001B[105m";
    public static final String BG_CYAN_BRIGHT = "\u001B[106m";
    public static final String BG_WHITE_BRIGHT = "\u001B[107m";
    
    // ==================== TEXT EFFECTS ====================
    public static final String BOLD = "\u001B[1m";
    public static final String DIM = "\u001B[2m";
    public static final String ITALIC = "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String BLINK = "\u001B[5m";
    public static final String REVERSE = "\u001B[7m";
    public static final String HIDDEN = "\u001B[8m";
    public static final String STRIKETHROUGH = "\u001B[9m";
    
    // ==================== RESET SPECIFIC ====================
    public static final String RESET_BOLD = "\u001B[22m";
    public static final String RESET_ITALIC = "\u001B[23m";
    public static final String RESET_UNDERLINE = "\u001B[24m";
    public static final String RESET_BLINK = "\u001B[25m";
    public static final String RESET_REVERSE = "\u001B[27m";
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Get color for a specific restaurant name
     */
    public static String getRestaurantColor(String restaurantName) {
        String name = restaurantName.toLowerCase();
        if (name.contains("kfc")) return KFC_RED;
        if (name.contains("mcdonald") || name.contains("mc donald")) return MCD_YELLOW;
        if (name.contains("starbucks")) return SBUX_GREEN;
        if (name.contains("paradise")) return PARADISE_ORANGE;
        if (name.contains("haldiram")) return HALDIRAM_ORANGE;
        return INFINITY_GOLD;
    }
    
    /**
     * Get background color for a specific restaurant
     */
    public static String getRestaurantBackground(String restaurantName) {
        String name = restaurantName.toLowerCase();
        if (name.contains("kfc")) return KFC_RED_BG;
        if (name.contains("mcdonald")) return MCD_YELLOW_BG;
        if (name.contains("starbucks")) return SBUX_GREEN_BG;
        if (name.contains("paradise")) return PARADISE_ORANGE_BG;
        return BG_BLACK;
    }
}