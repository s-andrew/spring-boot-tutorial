package com.tutorial.tutorial.Repositories;


import com.tutorial.tutorial.Entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{
    Iterable<Person> findByFirstnameContainingOrSecondnameContaining(String firstnamePath, String secondnamePath);
}
