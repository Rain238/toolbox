package com.light.rain.example.sqlite;

import java.sql.*;

/**
 * 在这个示例中，我们首先建立了一个 SQLite 数据库连接，
 * 然后创建了 employees 表并插入了一些数据。接下来，
 * 我们查询了年龄在 18 到 60 岁之间的员工，
 * 使用批量更新语句将他们的年龄增加 10 年，
 * 最后删除了年龄超过 30 岁的员工记录。
 *
 */
public class SQLiteExample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // 创建 SQLite 数据库连接
            String url = "jdbc:sqlite:mydatabase.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

            // 创建 departments 表
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS departments (id INTEGER PRIMARY KEY, name TEXT)");
            stmt.execute("INSERT INTO departments (id, name) SELECT 1, 'Sales' WHERE NOT EXISTS (SELECT * FROM departments WHERE id = 1)");
            stmt.execute("INSERT INTO departments (id, name) SELECT 2, 'Marketing' WHERE NOT EXISTS (SELECT * FROM departments WHERE id = 2)");
            stmt.execute("INSERT INTO departments (id, name) SELECT 3, 'Engineering' WHERE NOT EXISTS (SELECT * FROM departments WHERE id = 3)");

            // 创建 employees 表
            stmt.execute("CREATE TABLE IF NOT EXISTS employees (id INTEGER PRIMARY KEY, name TEXT, age INT, department_id INT)");

            // 插入数据
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO employees (id, name, age, department_id) VALUES (?, ?, ?, ?)");
            for (int i = 1; i <= 10; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2, "Employee " + i);
                pstmt.setInt(3, 20 + i);
                pstmt.setInt(4, i % 3 + 1);
                pstmt.executeUpdate();
            }

            // 查询数据
            PreparedStatement selectStmt = conn.prepareStatement("SELECT e.name, d.name AS department FROM employees e INNER JOIN departments d ON e.department_id = d.id WHERE e.age >= ? AND e.age <= ?");
            selectStmt.setInt(1, 18);
            selectStmt.setInt(2, 60);
            ResultSet rs = selectStmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String department = rs.getString("department");
                System.out.println(name + " works in the " + department + " department.");
            }

            // 批量更新数据
            PreparedStatement updateStmt = conn.prepareStatement("UPDATE employees SET age = ? WHERE id = ?");
            for (int i = 1; i <= 10; i++) {
                updateStmt.setInt(1, 30 + i);
                updateStmt.setInt(2, i);
                updateStmt.addBatch();
            }
            int[] updateCounts = updateStmt.executeBatch();
            System.out.println("Updated " + updateCounts.length + " rows.");

            // 删除数据
            PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM employees WHERE age >= ?");
            deleteStmt.setInt(1, 30);
            int deleteCount = deleteStmt.executeUpdate();
            System.out.println("Deleted " + deleteCount + " rows.");

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


