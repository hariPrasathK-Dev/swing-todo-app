package com.todo.ui;

import com.todo.controller.TaskController;
import com.todo.model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainFrame extends JFrame {
    private TaskController controller;
    private DefaultListModel<Task> listModel;
    private JList<Task> taskList;
    private JPanel sidebar;

    public MainFrame() {
        controller = new TaskController();
        initUI();
        refreshTaskList();
    }

    private void initUI() {
        setTitle("Professional To-Do App");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar for Filters
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidebar.setPreferredSize(new Dimension(200, 0));
        // sidebar.setBackground(new Color(44, 62, 80)); // Dark sidebar if needed

        addSidebarButton("All Tasks", e -> refreshTaskList());
        addSidebarButton("High Priority", e -> filterTasks("High"));
        addSidebarButton("Medium Priority", e -> filterTasks("Medium"));
        addSidebarButton("Low Priority", e -> filterTasks("Low"));

        add(sidebar, BorderLayout.WEST);

        // Main Task List
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setCellRenderer(new TaskCellRenderer());
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Double click to toggle completion
        taskList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Task selected = taskList.getSelectedValue();
                    if (selected != null) {
                        selected.setCompleted(!selected.isCompleted());
                        controller.updateTask(selected);
                        taskList.repaint(); // Refresh UI
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Toolbar
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Task");

        // Styling buttons
        addButton.setBackground(new Color(52, 152, 219));
        addButton.setForeground(Color.WHITE);

        addButton.addActionListener(e -> showAddTaskDialog());
        deleteButton.addActionListener(e -> deleteTask());

        toolbar.add(deleteButton);
        toolbar.add(addButton);
        add(toolbar, BorderLayout.SOUTH);
    }

    private void addSidebarButton(String text, java.awt.event.ActionListener action) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(180, 40));
        btn.addActionListener(action);
        sidebar.add(btn);
        sidebar.add(Box.createVerticalStrut(10));
    }

    private void showAddTaskDialog() {
        TaskDialog dialog = new TaskDialog(this);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Task newTask = dialog.getTask();
            controller.addTask(newTask);
            refreshTaskList();
        }
    }

    private void deleteTask() {
        Task selected = taskList.getSelectedValue();
        if (selected != null) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this task?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteTask(selected.getId());
                refreshTaskList();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.");
        }
    }

    private void refreshTaskList() {
        listModel.clear();
        List<Task> tasks = controller.getAllTasks();
        for (Task t : tasks) {
            listModel.addElement(t);
        }
    }

    private void filterTasks(String priority) {
        listModel.clear();
        List<Task> tasks = controller.getTasksByPriority(priority);
        for (Task t : tasks) {
            listModel.addElement(t);
        }
    }
}
