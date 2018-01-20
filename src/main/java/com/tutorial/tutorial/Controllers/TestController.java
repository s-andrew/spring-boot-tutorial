package com.tutorial.tutorial.Controllers;


import com.tutorial.tutorial.Entities.Person;
import com.tutorial.tutorial.Entities.Phone;
import com.tutorial.tutorial.Repositories.PersonRepository;
import com.tutorial.tutorial.Repositories.PhoneRepository;
import com.tutorial.tutorial.Services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    DemoService service;


    @GetMapping("/persons")
    public Iterable<Person> getPersons(){
        return service.getPersons();
    }

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable long id){
        return service.getPerson(id);
    }

    @GetMapping("/persons/search_by")
    public Iterable<Person> searchPersons(@ModelAttribute(name = "q") String query){
        return service.personSearch(query);
    }

    @GetMapping("/persons/{id}/colleagues")
    public Iterable<Person> getCollegues(@PathVariable long id){
        return service.getColleagues(id);
    }

}
