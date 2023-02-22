package com.light.rain.example.sqlite;

import java.sql.*;

public class SQLiteDeleteExample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:mydatabase.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

            // 删除数据
            String deleteSql = "DELETE FROM employees WHERE age < ?";
            PreparedStatement pstmt = conn.prepareStatement(deleteSql);
            pstmt.setInt(1, 30);
            int rowCount = pstmt.executeUpdate();
            System.out.println(rowCount + " rows deleted.");
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

