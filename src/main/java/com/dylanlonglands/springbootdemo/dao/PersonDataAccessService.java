package com.dylanlonglands.springbootdemo.dao;

import com.dylanlonglands.springbootdemo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String insertPerson(UUID id, Person person) {
        return "";
    }

    @Override
    public String deletePerson(UUID id) {
        return "";
    }

    @Override
    public String updatePerson(UUID id, Person person) {
        return "";
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String selectQuery = "SELECT id, name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(
                selectQuery,
                new Object[]{id},
                new int[] {Types.OTHER},
                (resultSet, i) -> {
            final UUID personId = UUID.fromString(resultSet.getString("id"));
            final String name = resultSet.getString("name");
            return new Person(personId, name);
        });
        return Optional.ofNullable(person);
    }

    @Override
    public List<Person> getPeople() {
        final String selectQuery = "SELECT id, name FROM person;";
        return jdbcTemplate.query(selectQuery, (resultSet, i) -> {
            final UUID id = UUID.fromString(resultSet.getString("id"));
            final String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public List<Person> getPeopleByName(String name) {
        return null;
    }
}
