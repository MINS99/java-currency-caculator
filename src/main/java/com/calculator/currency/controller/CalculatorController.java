package com.calculator.currency.controller;

import com.calculator.currency.application.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalculatorController {

    private final CountryService countryService;

    public CalculatorController(final CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("countryList", countryService.findAll());
        return "index";
    }
}
