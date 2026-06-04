package com.devops.taskapp;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @PostMapping
    public Task createTask(@RequestBody Map<String, String> body) {
        Task task = new Task(nextId++, body.get("title"));
        tasks.add(task);
        return task;
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
        return "Deleted";
    }
}