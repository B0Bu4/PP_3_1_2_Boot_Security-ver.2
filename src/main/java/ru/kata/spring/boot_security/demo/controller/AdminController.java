package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11);

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String showAllUsers(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "users-table";
    }

    @GetMapping("/users-add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", new ArrayList<Role>());
        return "users-add";
    }

    @PostMapping("/users-add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(value = "role") String[] roles) {
        Set<Role> roleSet = userService.getRoleToSet(roles);
        roleService.saveAll(roleSet);
        user.setRoleSet(roleSet);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.save(user);


        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "users-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "role") String[] roles) {
        Set<Role> roleSet = userService.getRoleToSet(roles);
        roleService.saveAll(roleSet);
        user.setRoleSet(roleSet);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveAndFlush(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
