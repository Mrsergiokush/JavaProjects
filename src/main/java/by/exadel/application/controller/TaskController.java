package by.exadel.application.controller;


import by.exadel.application.model.Task;
import by.exadel.application.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("user/{userId}/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/{from}", method = RequestMethod.GET)
    public String getAllUsers(@PathVariable Integer from, @PathVariable Integer userId, Model model) throws Exception {
        List<Task> tasks = taskService.getAll(userId, from);
        model.addAttribute("size", taskService.getSize());
        model.addAttribute("from", from);
        model.addAttribute("taskList", tasks);
        return "task";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addNewTaskPage() {
        return "addNewTask";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTask(@PathVariable Integer userId, @RequestParam(value = "taskName") String taskName,
                          @RequestParam(value = "deadLine") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate deadLine,
                          @RequestParam(value = "isDone") String isDone,
                          @RequestParam(value = "priority") String priority) throws Exception {
        Task task = new Task();
        task.setTaskName(taskName);

        if (isDone.equals("Done"))
            task.setDone(true);
        else
            task.setDone(false);

        task.setDeadline(deadLine);
        task.setPriority(priority);
        task.setUserId(userId);

        if (taskService.add(task) == null)
            return "ErrorAddTask";
        else
            return "redirect:0";
    }

    @RequestMapping(value = "{taskId}", method = RequestMethod.DELETE)
    public String deleteTask(@PathVariable Integer taskId) throws Exception {
        Task task = new Task();
        task.setTaskId(taskId);
        taskService.delete(task);
        return "redirect:0";
    }

    @RequestMapping(value = "{taskId}/edit")
    public String editForm(@PathVariable Integer taskId, Model model) throws Exception {
        Task task = taskService.getById(taskId);
        model.addAttribute("task", task);
        return "taskEditForm";
    }

    @RequestMapping(value = "{taskId}", method = RequestMethod.PUT)
    public String save(@ModelAttribute("task") Task task, @RequestParam(value = "isDone") String isDone) throws Exception {

        if (isDone.equals("Done"))
            task.setDone(true);
        else
            task.setDone(false);

        taskService.update(task);
        return "redirect:0";
    }
}