package ru.Robert.NauJava.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/testik")
public class TestController {
    @GetMapping(value = "/html", produces = {"text/html"})
    public String get() {
        return "contactList.html";
    }
}
