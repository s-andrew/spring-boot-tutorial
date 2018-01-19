package com.tutorial.tutorial.Controllers;


import com.tutorial.tutorial.Entities.Person;
import com.tutorial.tutorial.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    PersonRepository personRepository;


    @GetMapping("/")
    public Iterable<Person> test(){
        return personRepository.findAll();
    }

}
