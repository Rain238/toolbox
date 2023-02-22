package com.light.rain.example.sqlite;

import java.sql.*;

public class SQLiteExample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:mydatabase.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

            // 检查表是否存在，如果不存在则创建表
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='employees'");
            if (!rs.next()) {
                String sql = "CREATE TABLE employees ("
                        + " id integer PRIMARY KEY AUTOINCREMENT,"
                        + " name text NOT NULL,"
                        + " age integer,"
                        + " salary real"
                        + ");";
                stmt.executeUpdate(sql);
                System.out.println("Table created successfully.");
            }

            // 插入一条数据
            String insertSql = "INSERT INTO employees (name, age, salary) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, "Alice");
            pstmt.setInt(2, 25);
            pstmt.setDouble(3, 50000.0);
            pstmt.executeUpdate();
            System.out.println("Data inserted successfully.");

            // 查询数据
            String selectSql = "SELECT id, name, age, salary FROM employees";
            Statement selectStmt = conn.createStatement();
            rs = selectStmt.executeQuery(selectSql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double salary = rs.getDouble("salary");
                System.out.println("ID = " + id + ", NAME = " + name + ", AGE = " + age + ", SALARY = " + salary);
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
