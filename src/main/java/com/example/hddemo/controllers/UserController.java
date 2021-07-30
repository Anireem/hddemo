package com.example.hddemo.controllers;

import com.example.hddemo.models.Role;
import com.example.hddemo.models.User;
import com.example.hddemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user-all")
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user-all";
    }

    // Edit
    @GetMapping("/user-all/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseGet(null);

        HashMap<Role, Boolean> roles = user.getAllRolesLeftJoinUserRoles();

        ArrayList<String> allRoles = new ArrayList<>();
        for (Role value : Role.values()) {
            allRoles.add(value.name());
        }

        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        model.addAttribute("allRoles", allRoles);
        return "user";
    }

    @PostMapping("/user-all/{id}/addRole")
    public String addRole(@ModelAttribute User user, @RequestParam Map<String, String> form) {
        User userById = userRepository.getById(user.getId());
        for (Map.Entry<String, String> entry : form.entrySet()) {
            if (entry.getKey().equals("roleName")) {
                String roleName = entry.getValue();
                if (!roleName.equals("0"))
                    userById.addRole(Role.valueOf(roleName));
            }
        }
        userRepository.save(userById);
        return "redirect:/user-all/" + user.getId();
    }

    @PostMapping("/user-all/{id}/removeRole")
    public String removeRole(@ModelAttribute User user, @RequestParam Map<String, String> form) {
        User userById = userRepository.getById(user.getId());
        for (Map.Entry<String, String> entry : form.entrySet()) {
            if (entry.getKey().equals("roleName")) {
                String roleName = entry.getValue();
                if (!roleName.equals("0"))
                    userById.removeRole(Role.valueOf(roleName));
            }
        }
        userRepository.save(userById);
        return "redirect:/user-all/" + user.getId();
    }

    // Save after edit
    @PostMapping("/user-all/{id}/save")
    public String saveUser(@RequestParam Map<String, String> form, @ModelAttribute User user) {
        System.out.println();
        return "redirect:/user-all";
    }



}
