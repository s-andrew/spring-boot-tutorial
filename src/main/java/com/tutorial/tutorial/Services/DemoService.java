package com.tutorial.tutorial.Services;

import com.tutorial.tutorial.Entities.Person;
import com.tutorial.tutorial.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DemoService {
    @Autowired
    PersonRepository personRepository;

    public Person getPerson(long id){
        return personRepository.findOne(id);
    }

    public Iterable getPersons(){
        return personRepository.findAll();
    }

    public Iterable<Person> personSearch(String query){
        return personRepository.findByFirstnameContainingOrSecondnameContaining(query, query);
    }

    public Iterable<Person> getColleagues(long id){
        return personRepository.findOne(id).getProfessions().stream()
                .map(profession -> profession.getPersons())
                .flatMap(people -> people.stream())
                .filter(person -> person.getId() != id)
                .collect(Collectors.toSet());
    }

}
