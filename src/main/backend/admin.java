package main.backend;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Admin extends User {
    private static final String URL = "jdbc:mysql://localhost:3306/laravel_api";
    private static final String DB_USER = "root";
    private static final String PASS = "kd(S(MavJCoLV1";

    public Admin(String username, String password, String firstName, String lastName, String id) {
        super(username, password, firstName, lastName, id);
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Teller createTellerAccount(String user, String pass, String first, String last, String id) {
        String sql = "INSERT INTO tellers (id, username, password, firstName, lastName) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, DB_USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, user);
            pstmt.setString(3, hashPassword(pass));
            pstmt.setString(4, first);
            pstmt.setString(5, last);
            pstmt.executeUpdate();
            return new Teller(user, pass, first, last, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteTellerAccount(String id) {
        String sql = "DELETE FROM tellers WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, DB_USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyTellerAccount(String id, String newUsername, String newPassword) {
        String sql = "UPDATE tellers SET username = ?, password = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, DB_USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newUsername);
            pstmt.setString(2, hashPassword(newPassword));
            pstmt.setString(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewTellerAccount(String id) {
        String sql = "SELECT * FROM tellers WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, DB_USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Teller ID: " + rs.getString("id"));
                // More fields can be processed here
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewCustomerBankAccount(String customerId) {
        String sql = "SELECT account_number, balance FROM accounts WHERE customer_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, DB_USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Account Number: " + rs.getString("account_number"));
                System.out.println("Balance: " + rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
