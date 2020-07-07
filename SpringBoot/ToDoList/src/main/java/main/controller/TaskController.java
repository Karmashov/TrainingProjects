package main.controller;

import main.model.Task;
import main.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController
{
    private final TaskService taskService;

    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/")
    public List<Task> list()
    {
        return taskService.list();
    }

    @PostMapping("/tasks/")
    public void add(Task task)
    {
        taskService.add(task);
    }

    @GetMapping("/tasks/{id}")
    public Task get(@PathVariable int id)
    {
        return taskService.get(id);
    }

    @PatchMapping("/tasks/{id}")
    public void done(@PathVariable int id)
    {
        taskService.done(id);
    }

    @PostMapping("/tasks/{id}")
    public Task edit(@PathVariable int id, @RequestParam String name, @RequestParam boolean done)
    {
        return taskService.edit(id, name, done);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable int id)
    {
        taskService.delete(id);
    }
}
