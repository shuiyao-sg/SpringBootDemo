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
    private static final String FIRST_FIELD = "id";
    private static final String SECOND_FIELD = "first_name";
    private static final String THIRD_FIELD = "last_name";

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        String insertPersonFirstName = person.getFirstName();
        String insertPersonLastName = person.getLastName();
        final String sql = "INSERT INTO" + " " + TABLE_NAME + " " + "(" + FIRST_FIELD + " , " + SECOND_FIELD + " , "
                + THIRD_FIELD + ") VALUES (?,?,?)";
        jdbcTemplate.update(sql, id, insertPersonFirstName, insertPersonLastName);
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT" + " " + FIRST_FIELD + " , " + SECOND_FIELD + " , " + THIRD_FIELD + " " + "FROM" + " " + TABLE_NAME; //sql statements will be parsed

        /*Parses sql statement to select the data. Maps the data into Java objects*/
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString(FIRST_FIELD)); //input column name
            String firstName = resultSet.getString(SECOND_FIELD);
            String lastName = resultSet.getString(THIRD_FIELD);
            return new Person(id, firstName, lastName);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT" + " " + FIRST_FIELD + " , " + SECOND_FIELD + " , " + THIRD_FIELD + " " + "FROM"
                + " " + TABLE_NAME + " " + "WHERE" + " " + FIRST_FIELD + " = ?"; //note the question mark
        Person person = jdbcTemplate.queryForObject(sql,
                new Object[]{id},
                (resultSet, i) -> {
                    String firstName = resultSet.getString(SECOND_FIELD);
                    String lastName = resultSet.getString(THIRD_FIELD);
                    return new Person(id, firstName, lastName);
                });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM" + " " + TABLE_NAME + " " + "WHERE" + " " + FIRST_FIELD + " = ?";
        jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        final String sql = "UPDATE" + " " + TABLE_NAME + " " + "SET" + " " + SECOND_FIELD + " = ? , "
                + THIRD_FIELD + " = ? " + "WHERE" + " " + FIRST_FIELD + " = ?";
        final String newPersonFirstName = person.getFirstName();
        final String newPersonLastName = person.getLastName();
        jdbcTemplate.update(sql, newPersonFirstName, newPersonLastName, id);
        return 1;
    }
}
