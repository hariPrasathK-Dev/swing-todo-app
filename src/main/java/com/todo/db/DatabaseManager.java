package com.todo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // ⚠️ REPLACE THESE WITH YOUR FREE CLOUD DATABASE CREDENTIALS ⚠️
    // Recommended: Supabase, Neon, or ElephantSQL (Free Tier)
    private static final String DB_URL = "jdbc:postgresql://db.zjsiaeeuouuhosbrgjjz.supabase.co:5432/postgres?user=postgres&password=Karankaruna@08426";
    private static final String USER = "postgres";
    private static final String PASS = "Karankaruna@08426";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("❌ Database Connection Failed!");
            System.err.println("Error: " + e.getMessage());
            System.err.println(
                    "💡 Tip: Make sure you have updated DatabaseManager.java with your actual Cloud DB credentials.");
        }
        return conn;
    }

    public static void createNewTable() {
        // PostgreSQL Syntax
        String sql = "CREATE TABLE IF NOT EXISTS tasks (\n"
                + " id SERIAL PRIMARY KEY,\n"
                + " title TEXT NOT NULL,\n"
                + " description TEXT,\n"
                + " due_date TEXT,\n"
                + " priority TEXT,\n"
                + " is_completed BOOLEAN DEFAULT FALSE\n"
                + ");";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            if (conn != null) {
                stmt.execute(sql);
                System.out.println("✅ Connected to Cloud Database & Verified Table.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
