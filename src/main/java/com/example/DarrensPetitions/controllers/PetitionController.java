package com.example.DarrensPetitions.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class PetitionController {
    static List<Petition> petitions = new ArrayList<>(); // List to store petition objects
    @RequestMapping("/") // Map to root path
    public String index(Model model){
        model.addAttribute("title", "Darren's Petitions");
        model.addAttribute("pageTitle", "View Petitions");

        // Get a list of petition titles and add it to the model
        List<String> petitionTitles = petitions.stream()
                .map(Petition::getPetitionTitle)
                .toList();
        model.addAttribute("petitions", petitions);
        model.addAttribute("petitionTitles", petitionTitles);
        return "index"; // Map to "index.html"
    }

    @RequestMapping(value="create", method = RequestMethod.GET) // Map to create path
    public String create(Model model){
        model.addAttribute("title", "Create a Petition");
        model.addAttribute("pageTitle", "Create Petition");
        return "create"; // Map to "create.html"
    }

    @PostMapping("/create") // Handle the POST request to create a new petition
    public String processCreate(
            @RequestParam String petitionTitle,
            @RequestParam String petitionDescription,
            @RequestParam String petitionAuthor){
        Petition petition = new Petition(petitionTitle, petitionDescription, petitionAuthor);
        petitions.add(petition); // Add the petition to the list of petitions
       return "redirect:"; // Redirect to index page
    }

    @GetMapping("/result/{petitionTitle}")// handle the GET request to display the result of the search
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
            return "result"; // Map to "result.html"
        } else {
            // Handle invalid petitionTitle - return to index page
            return "index";
        }

    }

    @PostMapping("/result/{petitionTitle}") // Handle the POST request to sign a petition
    public String processSignature(@PathVariable String petitionTitle, @ModelAttribute("petition") Petition petition) {
        for (Petition p : petitions) {
            if (p.getPetitionTitle().equals(petitionTitle)) {
                // Create a new Signature object
                Signature signature = new Signature(petition.getSignupSignature(), petition.getSignupEmail());
                p.addSignature(signature); // Add the signature to the signatures list & increment the number of signatures

                return "redirect:/result/" + petitionTitle;
            }
        }
        return "index"; // Error handling - return to index page
    }

    @GetMapping("/search") // Handle the initial search page request
    public String searchPage(Model model) {
        model.addAttribute("title", "Search for a Petition");
        model.addAttribute("pageTitle", "Search Petitions");
        return "search"; // Map to "search.html"
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<List<Petition>> getFilteredPetitions(@PathVariable String searchTerm) {
        // Perform filtering and return filtered petitions as JSON
        List<Petition> searchResults = petitions.stream()
                .filter(petition -> petition.getPetitionTitle().contains(searchTerm) || petition.getPetitionDescription().contains(searchTerm))
                .collect(Collectors.toList());
        return ResponseEntity.ok(searchResults);
    }



}
