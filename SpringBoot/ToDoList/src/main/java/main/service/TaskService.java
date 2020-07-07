package main.service;

import main.controller.EntityNotFoundException;
import main.model.Task;
import main.model.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService
{
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository)
    {
        this.taskRepository = taskRepository;
    }

    public List<Task> list()
    {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Task task : taskIterable)
        {
            tasks.add(task);
        }
        return tasks;
    }

    public void add(Task task)
    {
        taskRepository.save(task);
    }

    public Task get(int id)
    {
        Task optionalTask = taskRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException.createWithMessage("Задача не найдена ID: " + id));
        return optionalTask;
    }

    public void done(int id)
    {
        Task optionalTask = taskRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException.createWithMessage("Задача не найдена ID: " + id));
        optionalTask.setDone(true);
        taskRepository.save(optionalTask);
    }

    public Task edit(int id, String name, boolean done)
    {
        Task optionalTask = taskRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException.createWithMessage("Задача не найдена ID: " + id));
        optionalTask.setName(name);
        optionalTask.setDone(done);
        return taskRepository.save(optionalTask);
    }

    public void delete(int id)
    {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException.createWithMessage("Задача не найдена ID: " + id));
        if (task != null)
        {
            taskRepository.deleteById(id);
        }
    }
}
