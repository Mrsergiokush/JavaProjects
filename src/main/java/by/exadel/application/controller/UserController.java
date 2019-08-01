package by.exadel.application.controller;

import by.exadel.application.model.User;
import by.exadel.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
    public String addNewUser(@RequestParam(value = "userName") String userName, @RequestParam(value = "age") Integer age, @RequestParam(value = "email") String email) throws Exception {
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setAge(age);
        if (userService.add(user) == null)
            return "ErrorAddUser";
        else
            return "redirect:0";
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable Integer userId) throws Exception {
        User user = new User();
        user.setUserId(userId);
        userService.delete(user);
        return "redirect:0";
    }

    @RequestMapping(value = "{userId}/edit")
    public String editForm(@PathVariable Integer userId, Model model) throws Exception {
        User user = userService.getById(userId);
        model.addAttribute("user", user);
        return "userEditForm";
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.PUT)
    public String save(@ModelAttribute("user") User user) throws Exception {
        userService.update(user);
        return "redirect:/user/0";
    }
}
