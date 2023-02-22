package com.light.rain.example.sqlite;

import java.sql.*;

public class SQLiteSelectExample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:mydatabase.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

            // 查询数据
            String selectSql = "SELECT * FROM employees WHERE age > ?";
            PreparedStatement pstmt = conn.prepareStatement(selectSql);
            pstmt.setInt(1, 30);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double salary = rs.getDouble("salary");
                System.out.println("ID = " + id + ", Name = " + name + ", Age = " + age + ", Salary = " + salary);
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

