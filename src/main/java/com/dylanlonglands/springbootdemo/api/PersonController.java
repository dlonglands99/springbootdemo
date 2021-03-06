package com.dylanlonglands.springbootdemo.api;

import com.dylanlonglands.springbootdemo.model.Person;
import com.dylanlonglands.springbootdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public String addPerson(@Valid @NonNull @RequestBody Person person) {
        return personService.insertPerson(person);
    }

    @GetMapping
    public List<Person> getPeople() {
        return personService.getPeople();
    }

    @GetMapping(path = "/{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.selectPersonById(id).orElse(null);
    }

    @GetMapping("/withName/{name}")
    public List<Person> getPeopleByName(@PathVariable("name") String name) {
        return personService.getPeopleByName(name);
    }

    @DeleteMapping(path = "/{id}")
    public String deletePerson(@PathVariable("id") UUID id) {
        return personService.deletePerson(id);
    }

    @PutMapping(path = "/{id}")
    public String updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }
 }
