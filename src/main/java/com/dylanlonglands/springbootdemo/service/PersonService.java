package com.dylanlonglands.springbootdemo.service;

import com.dylanlonglands.springbootdemo.dao.PersonDao;
import com.dylanlonglands.springbootdemo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public String insertPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getPeople() {
        return personDao.getPeople();
    }

    public Optional<Person> selectPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public String deletePerson(UUID id) {
        return personDao.deletePerson(id);
    }

    public String updatePerson(UUID id, Person person) {
        return personDao.updatePerson(id, person);
    }
}
