package ru.Robert.NauJava.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        model.addAttribute("exceptionMessage", e.getMessage());
        return "exception";
    }
}
