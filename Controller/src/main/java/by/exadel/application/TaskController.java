package by.exadel.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.exadel.application.aspect.SecurityContext;
import by.exadel.application.model.Task;
import by.exadel.application.service.IServiceTask;
import by.exadel.application.service.IServiceUser;

@Controller
@RequestMapping("user/{id}/task")
public class TaskController {

    @Autowired
    private IServiceTask taskService;
    @Autowired
    private IServiceUser userService;

    @SecurityContext
    @RequestMapping(value = "/{from}", method = RequestMethod.GET)
    public String getAllUsers(@PathVariable Integer id, @PathVariable Integer from, Model model) throws Exception {
        List<Task> tasks = taskService.getAll(id, from);
        model.addAttribute("size", taskService.getSize());
        model.addAttribute("from", from);
        model.addAttribute("taskList", tasks);
        return "task";
    }

    @SecurityContext
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addNewTaskPage(@PathVariable Integer id) {
        return "addNewTask";
    }

    @SecurityContext
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTask(@PathVariable Integer id, @ModelAttribute("task") Task task, @RequestParam(value = "isDone") String isDone) throws Exception {
        if (isDone.equals("Done"))
            task.setDone(true);
        else
            task.setDone(false);
        task.setUser(userService.getById(id));
        if (taskService.add(task) == null)
            return "ErrorAddTask";
        else
            return "redirect:0";
    }

    @SecurityContext
    @RequestMapping(value = "{taskId}", method = RequestMethod.DELETE)
    public String deleteTask(@PathVariable Integer id, @PathVariable Integer taskId) throws Exception {
        Task task = new Task();
        task.setId(taskId);
        taskService.delete(task);
        return "redirect:0";
    }

    @SecurityContext
    @RequestMapping(value = "{taskId}/edit")
    public String editForm(@PathVariable Integer id, @PathVariable Integer taskId, Model model) throws Exception {
        Task task = taskService.getById(taskId);
        model.addAttribute("task", task);
        return "taskEditForm";
    }

    @SecurityContext
    @RequestMapping(value = "{taskId}", method = RequestMethod.PUT)
    public String save(@PathVariable Integer id, @PathVariable Integer taskId, @ModelAttribute("task") Task task, @RequestParam(value = "isDone") String isDone) throws Exception {
        task.setId(taskId);
        if (isDone.equals("Done")) {
            task.setDone(true);
        } else {
            task.setDone(false);
        }
        task.setUser(userService.getById(id));
        ////TODO JSPs only permit GET POST or HEAD???
        if (!taskService.update(task))
            return "ErrorAddTask";
        return "redirect:0";
    }
}
