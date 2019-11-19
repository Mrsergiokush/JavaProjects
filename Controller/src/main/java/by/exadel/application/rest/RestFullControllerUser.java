package by.exadel.application.rest;

import by.exadel.application.model.User;
import by.exadel.application.service.IServiceUser;
import by.exadel.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestFullControllerUser {

    private final IServiceUser userService;

    @Autowired
    public RestFullControllerUser(IServiceUser userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{from}", method = RequestMethod.GET)
    public List<User> getAllUsers(@PathVariable Integer from) throws Exception {
        return userService.getAll(from);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) throws Exception {
        User user = new User();
        user.setId(id);
        userService.delete(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@ModelAttribute("user") User user) throws Exception {
        userService.update(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public User add(@ModelAttribute("user") User user) throws Exception {
        if (userService.add(user) == null)
            return null;
        else return user;
    }
}
