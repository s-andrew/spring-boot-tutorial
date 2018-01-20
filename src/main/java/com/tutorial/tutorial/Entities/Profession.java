package com.tutorial.tutorial.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PROFESSIONS")
public class Profession {
    @Id
    @GeneratedValue
    private long id;
    private String title;

    @ManyToMany
    @JoinTable(name = "PERSONS_PROFESSIONS",
            joinColumns = @JoinColumn(name = "PROFESSION_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERSON_ID"))
    @JsonBackReference
    private Set<Person> persons;

    public Profession(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
