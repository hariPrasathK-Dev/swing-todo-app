package com.todo.ui;

import com.todo.model.Task;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TaskDialog extends JDialog {
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JComboBox<String> priorityBox;
    private JTextField dateField; // Simple text field for now, could be a DatePicker library
    private boolean confirmed = false;

    public TaskDialog(Frame owner) {
        super(owner, "Add New Task", true);
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        titleField = new JTextField();
        descriptionArea = new JTextArea(3, 20);
        priorityBox = new JComboBox<>(new String[] { "High", "Medium", "Low" });
        dateField = new JTextField(LocalDate.now().toString()); // Default to today

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(new JScrollPane(descriptionArea));
        formPanel.add(new JLabel("Priority:"));
        formPanel.add(priorityBox);
        formPanel.add(new JLabel("Due Date (YYYY-MM-DD):"));
        formPanel.add(dateField);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            if (titleField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title is required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            confirmed = true;
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getOwner());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Task getTask() {
        String title = titleField.getText().trim();
        String description = descriptionArea.getText().trim();
        String priority = (String) priorityBox.getSelectedItem();
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateField.getText().trim());
        } catch (Exception e) {
            // Ignore invalid date for now, or handle better
        }
        return new Task(title, description, date, priority);
    }
}
