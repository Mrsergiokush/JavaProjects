package by.exadel.application;

import by.exadel.application.model.Filter;
import by.exadel.application.model.User;
import by.exadel.application.service.UserDetailServiceImpl;
import by.exadel.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{from}", method = RequestMethod.GET)
    public String getAllUsers(@PathVariable Integer from, Model model) throws Exception {
        List<User> users = userService.getAll(from);
        model.addAttribute("size", userService.getSize());
        model.addAttribute("from", from);
        model.addAttribute("userList", users);
        return "user";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addNewUserPage() {
        return "addNewUser";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewUser(@RequestParam(value = "userName") String userName
            , @RequestParam(value = "age") Integer age
            , @RequestParam(value = "email") String email) throws Exception {
        User user = new User();
        user.setUsername(userName);
        user.setEmail(email);
        user.setAge(age);
        if (userService.add(user) == null)
            return "ErrorAddUser";
        else
            return "redirect:0";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable Integer id) throws Exception {
        User user = new User();
        user.setId(id);
        userService.delete(user);
        return "redirect:0";
    }

    @RequestMapping(value = "{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "userEditForm";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String save(@ModelAttribute("user") User user) throws Exception {
        userService.update(user);
        return "redirect:/user/0";
    }

    @RequestMapping(value = "/{from}", method = RequestMethod.POST) //filter
    public String filter(@PathVariable Integer from, Filter filter, Model model) throws Exception {
        List<User> users = userService.getByFilter(filter, from);
        model.addAttribute("userList", users);
        model.addAttribute("from", from);
        return "user";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public String getCurrentUser() {
        UserDetailServiceImpl userDetailService = new UserDetailServiceImpl();
        String userEmail = userDetailService.getEmailOfCurrentUser();
        Integer id = userService.getByEmail(userEmail).getId();
        return "redirect:"+id.toString()+"/task/0";
    }

    boolean isHasPermission(String email){
            UserDetailServiceImpl userDetailService = new UserDetailServiceImpl();
        return email.equals(userDetailService.getEmailOfCurrentUser());
    }
}
