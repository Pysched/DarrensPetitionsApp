package com.example.DarrensPetitions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PetitionController {
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Darren's Petitions");
        model.addAttribute("pageTitle", "Index Page");
        return "index";
    }

    @RequestMapping("/create")
    public String create(Model model){
        model.addAttribute("title", "Create a Petition");
        model.addAttribute("pageTitle", "Create Page");
        return "create";
    }

    @RequestMapping("/search")
    public String search(Model model){
        model.addAttribute("title", "Search for a Petition");
        model.addAttribute("pageTitle", "Search Page");
        return "search";
    }

    @RequestMapping("/result")
    public String result(Model model){
        model.addAttribute("title", "Review Petition Result");
        model.addAttribute("pageTitle", "Result Page");
        return "result";
    }
}
