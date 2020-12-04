package com.dylanlonglands.springbootdemo.dao;

import com.dylanlonglands.springbootdemo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> db = new ArrayList<>();

    @Override
    public String insertPerson(UUID id, Person person) {
        db.add(new Person(id, person.getName()));
        return "User successfully saved";
    }

    @Override
    public String deletePerson(UUID id) {
        Optional<Person> person = selectPersonById(id);
        if (person.isPresent()) {
            db.remove(person.get());
            return "User successfullly removed";
        }
        return "User does not exist";
    }

    @Override
    public String updatePerson(UUID id, Person newPerson) {
        return selectPersonById(id)
                .map(currentPerson -> {
                    int indexOfPersonToUpdate = db.indexOf(currentPerson);
                    if (indexOfPersonToUpdate >= 0) {
                        db.set(indexOfPersonToUpdate, newPerson);
                        return "User successfully updated";
                    }
                    return "User not found";
                })
                .orElse("User not found");
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return db.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Person> getPeople() {
        return db;
    }
}
