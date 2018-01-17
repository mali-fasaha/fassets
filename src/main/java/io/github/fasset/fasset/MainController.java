package io.github.fasset.fasset;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/categories")
    public String categories(){

        return "forms/category";
    }
}
