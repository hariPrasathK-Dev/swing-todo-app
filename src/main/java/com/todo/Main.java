package com.todo;

import com.todo.db.DatabaseManager;
import com.todo.ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Initialize Database (Skipped due to missing driver)
        // DatabaseManager.createNewTable();

        // Setup System Look and Feel (Native UI) - Fixes "FlatDarkLaf" error
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        // Launch UI
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
