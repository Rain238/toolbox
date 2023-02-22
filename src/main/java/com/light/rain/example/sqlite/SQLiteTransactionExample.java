package com.light.rain.example.sqlite;

import java.sql.*;

/**
 * 处理事务
 *
 * 在这个示例中，我们通过将 Connection 的 setAutoCommit() 方法设置为 false 来禁用自动提交，
 * 并在多个 SQL 语句中执行事务。如果所有操作都成功，我们就提交事务；否则，我们就回滚事务。
 */
public class SQLiteTransactionExample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:mydatabase.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

            conn.setAutoCommit(false);  // 禁用自动提交

            // 在事务中执行多个 SQL 语句
            String sql1 = "UPDATE employees SET salary = 80000 WHERE age > 35";
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            int count1 = pstmt1.executeUpdate();

            String sql2 = "UPDATE employees SET salary = 50000 WHERE age <= 35";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            int count2 = pstmt2.executeUpdate();

            conn.commit();  // 提交事务
            System.out.println("Transaction is committed. Rows updated: " + (count1 + count2));
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();  // 回滚事务
                    System.out.println("Transaction is rolled back.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

