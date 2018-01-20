package com.tutorial.tutorial.Repositories;

import com.tutorial.tutorial.Entities.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long>{
}
