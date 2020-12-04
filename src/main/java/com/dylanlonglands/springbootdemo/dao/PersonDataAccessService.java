package com.dylanlonglands.springbootdemo.dao;

import com.dylanlonglands.springbootdemo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public int deletePerson(UUID id) {
        return 0;
    }

    @Override
    public int updatePerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Person> getPeople() {
        Person postgres = new Person(UUID.randomUUID(), "FROM POSTGRES DB");
        List<Person> list = new ArrayList<>();
        list.add(postgres);
        return list;
    }
}
