package com.javamentor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class StartController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap map) {
        map.addAttribute("message", "It's first page!");
        return "index";
    }
}
