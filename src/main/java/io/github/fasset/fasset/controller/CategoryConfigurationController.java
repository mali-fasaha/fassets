package io.github.fasset.fasset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryConfigurationController {

    @GetMapping("/categories")
    public String categories(){

        return "forms/category";
    }
}
