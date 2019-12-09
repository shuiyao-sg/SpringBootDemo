package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;
    private static final String TABLE_NAME = "person";

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        String insertPersonName = person.getName();
        final String sql = "INSERT INTO" + " " + TABLE_NAME + " " + "(id, name) VALUES (?,?)";
        jdbcTemplate.update(sql, id, insertPersonName); // bug: id is not string but UUID for table input
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name FROM" + " " + TABLE_NAME; //sql statements will be parsed

        /*Parses sql statement to select the data. Maps the data into Java objects*/
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id")); //input column name
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name FROM" + " " + TABLE_NAME + " " + "WHERE id = ?"; //note the question mark
        Person person = jdbcTemplate.queryForObject(sql,
                new Object[]{id},
                (resultSet, i) -> {
                    String name = resultSet.getString("name");
                    return new Person(id, name);
                });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM" + " " + TABLE_NAME + " " + "WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        final String sql = "UPDATE" + " " + TABLE_NAME + " " + "SET name = ? WHERE id = ?";
        final String newPersonName = person.getName();
        jdbcTemplate.update(sql, newPersonName, id);
        return 1;
    }
}
