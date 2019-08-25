package by.exadel.application.controller.rest;

import by.exadel.application.model.Task;
import by.exadel.application.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/{userId}/task")
public class RestFullControllerTask {

    private final TaskService taskService;

    @Autowired
    public RestFullControllerTask(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/{from}", method = RequestMethod.GET)
    public List<Task> getAll(@PathVariable Integer userId, @PathVariable Integer from) throws Exception {
        return taskService.getAll(userId, from);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) throws Exception {
        Task task = new Task();
        task.setId(id);
        taskService.delete(task);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@ModelAttribute("task") Task task) throws Exception {
        taskService.update(task);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Task add(@ModelAttribute("task") Task task) throws Exception {
        if (taskService.add(task) == null)
            return null;
        else return task;
    }


}
