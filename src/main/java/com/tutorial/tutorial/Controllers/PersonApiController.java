package com.tutorial.tutorial.Controllers;


import com.tutorial.tutorial.Entities.Person;
import com.tutorial.tutorial.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
public class PersonApiController {

    @Autowired
    PersonService service;


    @GetMapping
    public Iterable<Person> getPersons(){
        return service.getPersons();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable long id){
        return service.getPerson(id);
    }

    @GetMapping("/search_by")
    public Iterable<Person> searchPersons(@ModelAttribute(name = "q") String query){
        return service.personSearch(query);
    }

    @GetMapping("/{id}/colleagues")
    public Iterable<Person> getCollegues(@PathVariable long id){
        return service.getColleagues(id);
    }

}
