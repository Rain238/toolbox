package com.light.rain.example.sqlite;

import java.sql.*;

/**
 * 批量更新操作
 */
public class SQLiteBatchUpdateExample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:mydatabase.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

            // 更新数据
            conn.setAutoCommit(false);
            String updateSql = "UPDATE employees SET salary = ? WHERE age > ?";
            PreparedStatement pstmt = conn.prepareStatement(updateSql);
            for (int i = 0; i < 10; i++) {
                pstmt.setDouble(1, 60000.0 + i * 1000);
                pstmt.setInt(2, 25 + i % 10);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            conn.commit();
            System.out.println("Data updated successfully.");
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
