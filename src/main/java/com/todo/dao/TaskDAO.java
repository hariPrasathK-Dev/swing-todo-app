package com.todo.dao;

import com.todo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskDAO {
    // In-memory storage (Fallback since Driver is missing)
    private static final List<Task> tasks = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public void addTask(Task task) {
        task.setId(idGenerator.getAndIncrement());
        tasks.add(task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks); // Return copy
    }

    public void deleteTask(int id) {
        tasks.removeIf(t -> t.getId() == id);
    }

    public void updateTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == task.getId()) {
                tasks.set(i, task);
                return;
            }
        }
    }
}
