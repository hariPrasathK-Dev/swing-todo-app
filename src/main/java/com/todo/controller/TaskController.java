package com.todo.controller;

import com.todo.dao.TaskDAO;
import com.todo.model.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskController {
    private TaskDAO taskDAO;

    public TaskController() {
        this.taskDAO = new TaskDAO();
    }

    public void addTask(Task task) {
        taskDAO.addTask(task);
    }

    public void deleteTask(int id) {
        taskDAO.deleteTask(id);
    }

    public void updateTask(Task task) {
        taskDAO.updateTask(task);
    }

    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    public List<Task> getTasksByPriority(String priority) {
        return taskDAO.getAllTasks().stream()
                .filter(t -> t.getPriority().equalsIgnoreCase(priority))
                .collect(Collectors.toList());
    }
}
