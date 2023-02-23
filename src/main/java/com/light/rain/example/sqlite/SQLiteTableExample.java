package com.light.rain.example.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 以下是关于SQLite表的查询、创建、修改和删除操作的例子，包括一些较为常见的SQL语句
 */
public class SQLiteTableExample {
    private static final String DB_URL = "jdbc:sqlite:test.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // 创建表
            createTable(conn);
            System.out.println("Table created.");

            // 查询表
            System.out.println("Employees:");
            selectAllFromTable(conn);

            // 修改表
            addColumnToTable(conn);
            System.out.println("Table modified.");

            // 删除表
            dropTable(conn);
            System.out.println("Table dropped.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS employees (\n"
                + "  id INTEGER PRIMARY KEY,\n"
                + "  name TEXT NOT NULL,\n"
                + "  age INTEGER NOT NULL,\n"
                + "  salary REAL NOT NULL\n"
                + ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void selectAllFromTable(Connection conn) throws SQLException {
        String sql = "SELECT id, name, age, salary FROM employees;";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Age: %d, Salary: %.2f\n",
                        rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getDouble("salary"));
            }
        }
    }

    private static void addColumnToTable(Connection conn) throws SQLException {
        String sql = "ALTER TABLE employees ADD COLUMN email TEXT;";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void dropTable(Connection conn) throws SQLException {
        String sql = "DROP TABLE IF EXISTS employees;";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
}
