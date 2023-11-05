package com.example.DarrensPetitions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class PetitionController {
    static List<Petition> petitions = new ArrayList<>(); // List to store petition objects
    @RequestMapping("/") // Map to root path
    public String index(Model model){
        model.addAttribute("title", "Darren's Petitions");
        model.addAttribute("pageTitle", "Index Page");
        model.addAttribute("petitions", petitions);
        return "index"; // Map to "index.html"
    }

    @RequestMapping(value="create", method = RequestMethod.GET) // Map to create path
    public String create(Model model){
        model.addAttribute("title", "Create a Petition");
        model.addAttribute("pageTitle", "Create Page");
        return "create"; // Map to "create.html"
    }

    @RequestMapping(value="create", method = RequestMethod.POST) // Handle the POST request to create a new petition
    public String processCreate(
            @RequestParam String petitionTitle,
            @RequestParam String petitionDescription,
            @RequestParam String petitionAuthor){
       Petition newPetition = new Petition(); // create a new petition object and populate it with the form data
       newPetition.setPetitionTitle(petitionTitle);
       newPetition.setPetitionDescription(petitionDescription);
       newPetition.setPetitionAuthor(petitionAuthor);
       petitions.add(newPetition); // add the new petition to the list of petitions
       return "redirect:"; // Redirect to index page
    }

    @RequestMapping("/search")
    public String search(Model model){
        model.addAttribute("title", "Search for a Petition");
        model.addAttribute("pageTitle", "Search Page");
        return "search"; // Map to "search.html"
    }

    @RequestMapping("/result/{petitionTitle}")// handle the GET request to display the result of the search
    public String result(@PathVariable String petitionTitle, Model model){
        model.addAttribute("title", "Review Petition Result");
        model.addAttribute("pageTitle", "Result Page");
        Petition isPetition = null;
        for (Petition petition : petitions) { // loop through the list of petitions to find the petition with the matching title
            if (petition.getPetitionTitle().equals(petitionTitle)) {
                isPetition = petition;
                break;
            }
        }
        if (isPetition != null) { // if the petition is found, add it to the model
            model.addAttribute("petition", isPetition);
            return "result"; // Map to "review.html"
        } else {
            // Handle invalid petitionTitle, return to index page
            return "index";
        }

    }
}
