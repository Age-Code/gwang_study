package org.example.myblog.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/permission")
@Controller
public class PermissionController {

    @RequestMapping("/{page}")
    public String page(@PathVariable String page) { return "permission/" + page; }
    @RequestMapping("/{page}/{id}")
    public String page(@PathVariable String page, @PathVariable String id) { return "permission/" + page; }
}
