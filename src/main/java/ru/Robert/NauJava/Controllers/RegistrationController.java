package ru.Robert.NauJava.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.Robert.NauJava.Entities.Contact;
import ru.Robert.NauJava.Services.ContactDetailsService;

@Controller
public class RegistrationController {
    @Autowired
    ContactDetailsService contactService;

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }
    @PostMapping("/registration")
    public String addContact(Contact contact, Model model)
    {
        if (contactService.addContact(contact))
            return "redirect:/login";
        else {
            model.addAttribute("message", "User exists");
            return "registration";
        }

    }

    @GetMapping("/login")
    public String login(Authentication authentication, Model model) {
        if (authentication != null) {
            return "redirect:/welcome";
        }
        return "login";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcomePage";
    }
}
