package by.exadel.application;

import by.exadel.application.model.Task;
import by.exadel.application.service.TaskService;
import by.exadel.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("user/{id}/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{from}", method = RequestMethod.GET)
    public String getAllUsers(@PathVariable Integer from, @PathVariable Integer id, Model model) throws Exception {
        List<Task> tasks = taskService.getAll(id, from);
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
    public String addTask(@PathVariable Integer id, @RequestParam(value = "taskName") String taskName,
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
        task.setUser(userService.getById(id));

        if (taskService.add(task) == null)
            return "ErrorAddTask";
        else
            return "redirect:0";
    }

    @RequestMapping(value = "{taskId}", method = RequestMethod.DELETE)
    public String deleteTask(@PathVariable Integer taskId) throws Exception {
        Task task = new Task();
        task.setId(taskId);
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
    public String save(@PathVariable Integer id, @PathVariable Integer taskId, @ModelAttribute("task") Task task, @RequestParam(value = "isDone") String isDone) throws Exception {
        task.setId(taskId);
        if (isDone.equals("Done"))
            task.setDone(true);
        else
            task.setDone(false);
        task.setUser(userService.getById(id));
        taskService.update(task);
        return "redirect:0";
    }
}
