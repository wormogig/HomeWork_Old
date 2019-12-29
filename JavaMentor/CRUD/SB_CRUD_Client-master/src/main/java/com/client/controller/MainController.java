package com.client.controller;

import com.client.model.User;
import com.client.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class MainController {

    private UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:main");
        return modelAndView;
    }

    @GetMapping(value = "/main")
    public ModelAndView startPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            modelAndView.setViewName("redirect:/admin/list");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            modelAndView.setViewName("redirect:/list");
        }
        return modelAndView;
    }

    @GetMapping(value = "/list")
    public ModelAndView listPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.allUsers();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("list_users");
        return modelAndView;
    }

}
