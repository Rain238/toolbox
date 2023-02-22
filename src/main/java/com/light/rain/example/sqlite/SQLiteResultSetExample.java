package com.light.rain.example.sqlite;

import java.sql.*;

/**
 * 使用 ResultSet 处理查询结果
 */
public class SQLiteResultSetExample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:mydatabase.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

            String sql = "SELECT * FROM employees WHERE age >= ? AND age <= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 18);
            pstmt.setInt(2, 60);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double salary = rs.getDouble("salary");
                System.out.println("Employee: " + id + " " + name + " " + age + " " + salary);
            }
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
