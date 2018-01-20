package com.tutorial.tutorial.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "PHONES")
public class Phone {

    @Id
    @GeneratedValue
    private long id;
    private String number;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    @JsonBackReference
    private Person person;

    public Phone(){}

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
