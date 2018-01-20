package com.tutorial.tutorial.Controllers;


import com.tutorial.tutorial.Entities.Person;
import com.tutorial.tutorial.Entities.Phone;
import com.tutorial.tutorial.Repositories.PersonRepository;
import com.tutorial.tutorial.Repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PhoneRepository phoneRepository;


    @GetMapping("/persons")
    public Iterable<Person> getPersons(){
        return personRepository.findAll();
    }

    @GetMapping("/phones")
    public Iterable<Phone> getPhones(){
        return phoneRepository.findAll();
    }

}
