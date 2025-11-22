package com.todo.ui;

import com.todo.model.Task;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TaskCellRenderer extends JPanel implements ListCellRenderer<Task> {
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JLabel priorityLabel;
    private JCheckBox completedCheckBox;

    public TaskCellRenderer() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setOpaque(true);

        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateLabel.setForeground(Color.GRAY);

        priorityLabel = new JLabel();
        priorityLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        priorityLabel.setOpaque(true);
        priorityLabel.setBorder(new EmptyBorder(2, 8, 2, 8));

        completedCheckBox = new JCheckBox();

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.add(titleLabel);
        textPanel.add(dateLabel);

        add(completedCheckBox, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);
        add(priorityLabel, BorderLayout.EAST);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Task> list, Task task, int index, boolean isSelected,
            boolean cellHasFocus) {
        titleLabel.setText(task.getTitle());
        dateLabel.setText(task.getDueDate() != null ? "Due: " + task.getDueDate().toString() : "No Due Date");

        // Priority Styling
        String priority = task.getPriority();
        priorityLabel.setText(priority);
        if ("High".equalsIgnoreCase(priority)) {
            priorityLabel.setBackground(new Color(231, 76, 60)); // Red
            priorityLabel.setForeground(Color.WHITE);
        } else if ("Medium".equalsIgnoreCase(priority)) {
            priorityLabel.setBackground(new Color(241, 196, 15)); // Yellow
            priorityLabel.setForeground(Color.BLACK);
        } else {
            priorityLabel.setBackground(new Color(46, 204, 113)); // Green
            priorityLabel.setForeground(Color.WHITE);
        }

        // Completed Styling
        completedCheckBox.setSelected(task.isCompleted());
        if (task.isCompleted()) {
            titleLabel.setForeground(Color.GRAY);
            // Strikethrough effect could be added here with HTML or Font attributes
        } else {
            titleLabel.setForeground(list.getForeground());
        }

        // Selection Styling
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
