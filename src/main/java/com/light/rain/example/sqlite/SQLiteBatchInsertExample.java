package com.light.rain.example.sqlite;

import java.sql.*;

/**
 *
 * 在这个示例中，我们使用了 PreparedStatement 的 addBatch() 和 executeBatch() 方法来批量插入数据。
 * 我们首先关闭了自动提交模式，以便在插入数据后手动提交。
 * 然后，我们循环10000次，每次将一条记录添加到批处理中，最后执行批处理并提交更改
 */
public class SQLiteBatchInsertExample {
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

            // 批量插入数据
            conn.setAutoCommit(false);
            String insertSql = "INSERT INTO employees (name, age, salary) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            for (int i = 0; i < 10000; i++) {
                pstmt.setString(1, "Employee " + i);
                pstmt.setInt(2, 25 + i % 10);
                pstmt.setDouble(3, 50000.0 + i * 1000);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            conn.commit();
            System.out.println("Data inserted successfully.");
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
