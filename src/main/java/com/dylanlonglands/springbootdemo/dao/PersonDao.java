package com.dylanlonglands.springbootdemo.dao;

import com.dylanlonglands.springbootdemo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    String insertPerson(UUID id, Person person);

    String deletePerson(UUID id);

    String updatePerson(UUID id, Person person);

    Optional<Person> selectPersonById(UUID id);

    List<Person> getPeople();

    List<Person> getPeopleByName(String name);

    default String insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
}
