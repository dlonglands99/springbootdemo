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
        final String sql = "INSERT INTO person (id, name) VALUES(?, ?)";
        jdbcTemplate.update(sql, new Object[]{id, person.getName()});
        return "User successfully added to postgres db";
    }

    @Override
    public String deletePerson(UUID id) {
        final String sql = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{id});
        return "User successfully deleted";
    }

    @Override
    public String updatePerson(UUID id, Person person) {
        final String sql = "UPDATE person SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{person.getName(), id});
        return "User successfully updated";
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(
                sql,
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
        final String sql = "SELECT id, name FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            final UUID id = UUID.fromString(resultSet.getString("id"));
            final String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }

    //TODO: implement this method
    @Override
    public List<Person> getPeopleByName(String name) {
        final String sql = "SELECT id, name FROM person WHERE name LIKE ?";
        final List<Person> peopleWithName = new ArrayList<>();
        return peopleWithName;
    }
}
