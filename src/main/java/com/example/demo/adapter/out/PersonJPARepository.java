package com.example.demo.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.person.Person;
import com.example.demo.domain.person.PersonId;
import com.example.demo.domain.person.PersonRepository;

@Repository
public interface PersonJPARepository extends JpaRepository<Person, PersonId>, PersonRepository {
    default void persist(Person aPerson) {
		this.save(aPerson);
	}
}