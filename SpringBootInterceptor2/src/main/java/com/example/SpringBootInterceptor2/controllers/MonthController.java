package com.example.SpringBootInterceptor2.controllers;

import com.example.SpringBootInterceptor2.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/month")
public class MonthController {
    @GetMapping
    public Month get(HttpServletRequest request) {
        return (Month) request.getAttribute("month");
    }
}
