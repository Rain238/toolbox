package com.light.rain.example.sqlite;

import java.sql.*;

/**
 *
 *
 * 在这个示例中，我们查询了所有年龄在 18 到 60 岁之间的员工所在的部门。
 * 使用 INNER JOIN 将 employees 和 departments 表联合起来，只返回两个表中都存在的记录。
 * 注意，这个示例中使用了别名（AS）来给表和列起别名，这样可以让查询语句更加易读。
 * 如果这两个表不存在，则会创建这两个表。
 * 同时，需要注意，创建表时需要指定表中的列名和数据类型，否则可能会导致后续的操作出现异常。
 */
import java.sql.*;

public class SQLiteJoinExample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:mydatabase.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

            Statement stmt = conn.createStatement();
            // 如果 departments 表不存在则创建
            stmt.execute("CREATE TABLE IF NOT EXISTS departments (id INTEGER PRIMARY KEY, name TEXT)");
            // 如果 employees 表不存在则创建
            stmt.execute("CREATE TABLE IF NOT EXISTS employees (id INTEGER PRIMARY KEY, name TEXT, age INT, department_id INT)");

            String sql = "SELECT e.name, d.name AS department " +
                    "FROM employees e " +
                    "INNER JOIN departments d ON e.department_id = d.id " +
                    "WHERE e.age >= ? AND e.age <= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 18);
            pstmt.setInt(2, 60);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String department = rs.getString("department");
                System.out.println(name + " works in the " + department + " department.");
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
