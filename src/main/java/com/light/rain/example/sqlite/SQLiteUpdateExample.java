package com.light.rain.example.sqlite;

import java.sql.*;

public class SQLiteUpdateExample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:mydatabase.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

            // 更新单个记录
            String updateSql = "UPDATE employees SET salary = ? WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateSql);
            pstmt.setDouble(1, 70000.0);
            pstmt.setString(2, "Employee 5");
            int rowCount = pstmt.executeUpdate();
            System.out.println(rowCount + " rows updated.");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

