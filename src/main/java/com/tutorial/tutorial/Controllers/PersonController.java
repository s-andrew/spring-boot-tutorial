package com.tutorial.tutorial.Controllers;


import com.tutorial.tutorial.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    PersonService service;

    @GetMapping
    public String getPersons(ModelMap model){
        model.addAttribute("persons", service.getPersons());
        return "persons";

    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable long id, ModelMap model){
        model.addAttribute("person", service.getPerson(id));
        return "person";
    }
}
