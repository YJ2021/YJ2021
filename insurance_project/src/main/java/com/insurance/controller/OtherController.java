package com.insurance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class OtherController {

    @RequestMapping(value = "/swagger")
    public String swagger() {
        return "redirect:swagger-ui.html";
    }
}
