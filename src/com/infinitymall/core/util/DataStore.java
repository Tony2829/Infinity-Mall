package com.infinitymall.core.util;

import com.infinitymall.core.model.*;
import java.util.*;

/**
 * DataStore - In-memory database (Singleton)
 * EVERY module uses this to store and retrieve data
 */
public class DataStore {
    
    private static DataStore instance;
    
    // ==================== STORAGE ====================
    private Map<String, User> users;
    private List<Transaction> allTransactions;
    private Map<String, List<Transaction>> userTransactions;
    private Map<String, Double> moduleRevenue;
    private Map<String, Integer> itemSalesCount;
    private Set<String> activeSessions;
    
    // Counters
    private int totalCouponsIssued;
    private int totalCouponsRedeemed;
    
    // ==================== CONSTRUCTOR ====================
    
    private DataStore() {
        users = new HashMap<>();
        allTransactions = new ArrayList<>();
        userTransactions = new HashMap<>();
        moduleRevenue = new HashMap<>();
        itemSalesCount = new HashMap<>();
        activeSessions = new HashSet<>();
        
        totalCouponsIssued = 0;
        totalCouponsRedeemed = 0;
        
        // Initialize module revenue
        moduleRevenue.put("MULTIPLEX", 0.0);
        moduleRevenue.put("FOOD", 0.0);
        moduleRevenue.put("SHOPPING", 0.0);
        moduleRevenue.put("PARKING", 0.0);
        moduleRevenue.put("GAMES", 0.0);
        
        // Create default admin user
        User admin = new User("Administrator", "admin@infinitymall.com", "9999999999", "admin123");
        admin.setUserId("ADMIN001");
        users.put("admin@infinitymall.com", admin);
        userTransactions.put("ADMIN001", new ArrayList<>());
        
        // Add some sample users for testing
        User john = new User("John Doe", "john@example.com", "9876543210", "john123");
        john.setUserId("USER001");
        users.put("john@example.com", john);
        userTransactions.put("USER001", new ArrayList<>());
        
        User jane = new User("Jane Smith", "jane@example.com", "9876543211", "jane123");
        jane.setUserId("USER002");
        users.put("jane@example.com", jane);
        userTransactions.put("USER002", new ArrayList<>());
    }
    
    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }
    
    // ==================== USER OPERATIONS ====================
    
    public boolean addUser(User user) {
        if (users.containsKey(user.getEmail())) {
            return false;
        }
        users.put(user.getEmail(), user);
        userTransactions.put(user.getUserId(), new ArrayList<>());
        return true;
    }
    
    public User getUserByEmail(String email) {
        return users.get(email);
    }
    
    public User getUserByPhone(String phone) {
        for (User u : users.values()) {
            if (u.getPhone().equals(phone)) {
                return u;
            }
        }
        return null;
    }
    
    public User getUserById(String userId) {
        for (User u : users.values()) {
            if (u.getUserId().equals(userId)) {
                return u;
            }
        }
        return null;
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    public int getTotalUsers() {
        return users.size();
    }
    
    // ==================== SESSION OPERATIONS ====================
    
    public void addActiveSession(String userId) {
        activeSessions.add(userId);
    }
    
    public void removeActiveSession(String userId) {
        activeSessions.remove(userId);
    }
    
    public int getActiveSessions() {
        return activeSessions.size();
    }
    
    // ==================== TRANSACTION OPERATIONS ====================
    
    public void addTransaction(Transaction transaction) {
        allTransactions.add(transaction);
        
        List<Transaction> userTxns = userTransactions.get(transaction.getUserId());
        if (userTxns != null) {
            userTxns.add(transaction);
        }
        
        // Update module revenue
        String module = transaction.getModule();
        double current = moduleRevenue.getOrDefault(module, 0.0);
        moduleRevenue.put(module, current + transaction.getAmount());
    }
    
    public List<Transaction> getAllTransactions() {
        return allTransactions;
    }
    
    public List<Transaction> getUserTransactions(String userId) {
        return userTransactions.getOrDefault(userId, new ArrayList<>());
    }
    
    public List<Transaction> getTransactionsByModule(String module) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : allTransactions) {
            if (t.getModule().equals(module)) {
                result.add(t);
            }
        }
        return result;
    }
    
    // ==================== REVENUE OPERATIONS ====================
    
    public double getModuleRevenue(String module) {
        return moduleRevenue.getOrDefault(module, 0.0);
    }
    
    public double getTotalRevenue() {
        double total = 0.0;
        for (Double revenue : moduleRevenue.values()) {
            total += revenue;
        }
        return total;
    }
    
    public Map<String, Double> getAllModuleRevenue() {
        return new HashMap<>(moduleRevenue);
    }
    
    // ==================== ITEM SALES TRACKING ====================
    
    public void recordItemSale(String itemName, int quantity) {
        int current = itemSalesCount.getOrDefault(itemName, 0);
        itemSalesCount.put(itemName, current + quantity);
    }
    
    public void recordItemSale(String itemName) {
        recordItemSale(itemName, 1);
    }
    
    public Map<String, Integer> getItemSalesCount() {
        return new HashMap<>(itemSalesCount);
    }
    
    public String[][] getTopSellingItems(int limit) {
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(itemSalesCount.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        int size = Math.min(limit, sorted.size());
        String[][] result = new String[size][2];
        for (int i = 0; i < size; i++) {
            result[i][0] = sorted.get(i).getKey();
            result[i][1] = String.valueOf(sorted.get(i).getValue());
        }
        return result;
    }
    
    // ==================== COUPON TRACKING ====================
    
    public void incrementCouponsIssued() {
        totalCouponsIssued++;
    }
    
    public void incrementCouponsRedeemed() {
        totalCouponsRedeemed++;
    }
    
    public int getTotalCouponsIssued() {
        return totalCouponsIssued;
    }
    
    public int getTotalCouponsRedeemed() {
        return totalCouponsRedeemed;
    }
    
    // ==================== DASHBOARD STATS ====================
    
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", getTotalUsers());
        stats.put("activeSessions", getActiveSessions());
        stats.put("totalRevenue", getTotalRevenue());
        stats.put("moduleRevenue", getAllModuleRevenue());
        stats.put("totalTransactions", allTransactions.size());
        stats.put("couponsIssued", totalCouponsIssued);
        stats.put("couponsRedeemed", totalCouponsRedeemed);
        stats.put("topItems", getTopSellingItems(5));
        return stats;
    }
    
    // ==================== UTILITY ====================
    
    public void reset() {
        users.clear();
        allTransactions.clear();
        userTransactions.clear();
        moduleRevenue.clear();
        itemSalesCount.clear();
        activeSessions.clear();
        totalCouponsIssued = 0;
        totalCouponsRedeemed = 0;
    }
}