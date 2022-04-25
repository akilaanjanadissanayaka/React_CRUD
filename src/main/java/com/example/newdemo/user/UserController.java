package com.example.newdemo.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/users")
    public String ShowUserList(Model model){
        List<User> listUsers=service.listAll();
        model.addAttribute("listUsers",listUsers);

        return "users";
    }


    @GetMapping("/Users2")
    public String test(Model model){
        List<User> listUsers=service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "Users2";
    }

    @GetMapping("/Users2/new")
    public String ShowNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add New User");
        return "user_form";
    }

    @PostMapping("Users2/save")
    public String SaveUser(User user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message","Successfully added");
        return "redirect:/Users2";
    }
    @GetMapping("/Users2/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model,RedirectAttributes ra){
        try {
            User user= service.get(id);
            model.addAttribute("user" , user);
            model.addAttribute("pageTitle"," Edit User "+id);
            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","Successfully added");
            return "redirect:/Users2";
        }
    }
}
