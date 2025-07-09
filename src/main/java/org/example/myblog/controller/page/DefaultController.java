package org.example.myblog.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping({"/index", "", "/", "/home"})
    public String page() { return "home"; }
}
