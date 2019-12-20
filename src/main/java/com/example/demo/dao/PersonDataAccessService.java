package com.example.demo.dao;

import com.example.demo.model.Person;
import org.postgresql.core.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;
    private static final String TABLE_NAME = "person";
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String SURNAME = "surname";
    private static final String AGE = "age";

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        String insertPersonFirstName = person.getFirstName();
        String insertPersonSurname = person.getSurname();
//        Integer insertPersonAge = person.etOptionalAge().orElse(null);
        Integer insertPersonAge = person.getAge();
        final String sql = "INSERT INTO" + " " + TABLE_NAME + " " + "(" + ID + " , " + FIRST_NAME
                + " , " + SURNAME + " , " + AGE + ") VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, id, insertPersonFirstName, insertPersonSurname, insertPersonAge);
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT" + " " + ID + " , " + FIRST_NAME + " , " + SURNAME + " , " + AGE + " " + "FROM" + " " + TABLE_NAME; //sql statements will be parsed

        /*Parses sql statement to select the data. Maps the data into Java objects*/
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString(ID)); //input column name
            String firstName = resultSet.getString(FIRST_NAME);
            String surname = resultSet.getString(SURNAME);
//            Integer age = ((Optional<Integer>) resultSet.getObject(AGE)).orElse(null);
            Integer age = (Integer) resultSet.getObject(AGE);
            return new Person(id, firstName, surname, age);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT" + " " + ID + " , " + FIRST_NAME + " , " + SURNAME + " , " + AGE + " " + "FROM"
                + " " + TABLE_NAME + " " + "WHERE" + " " + ID + " = ?"; //note the question mark
        Person person = jdbcTemplate.queryForObject(sql,
                new Object[]{id},
                (resultSet, i) -> {
                    String firstName = resultSet.getString(FIRST_NAME);
                    String surname = resultSet.getString(SURNAME);
//                    Integer age = ((Optional<Integer>) resultSet.getObject(AGE)).orElse(null);
                    Integer age = (Integer) resultSet.getObject(AGE);
                    return new Person(id, firstName, surname, age);
                });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM" + " " + TABLE_NAME + " " + "WHERE" + " " + ID + " = ?";
        jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        final String sql = "UPDATE" + " " + TABLE_NAME + " " + "SET" + " " + FIRST_NAME + " = ? , "
                + SURNAME + " = ? , " + AGE + " = ? " + "WHERE" + " " + ID + " = ?";
        final String newPersonFirstName = person.getFirstName();
        final String newPersonSurname = person.getSurname();
//        final Integer newPersonAge = person.getOptionalAge().orElse(null);
        final Integer newPersonAge = person.getAge();
        jdbcTemplate.update(sql, newPersonFirstName, newPersonSurname, newPersonAge, id);
        return 1;
    }

    @Override
    public int getNumberOfPeopleWithAge(int age) {
        final String sql = "SELECT calculateNumberOfPeopleWithAge(?)";
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName("calculateNumberOfPeopleWithAge");
        SqlParameterSource ageInput = new MapSqlParameterSource().addValue("inputAge", age);
        int number = simpleJdbcCall.executeFunction(Integer.class, ageInput);
        return number;
    }
}
