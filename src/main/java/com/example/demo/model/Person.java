package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

public class Person {
    private final UUID id;

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String surname;

    @NotNull
    private final Integer age;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("first_name") String firstName,
                  @JsonProperty("surname") String surname,
                  @JsonProperty("age") Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

//    public Optional<Integer> getOptionalAge() {
//        return Optional.ofNullable(age);
//    }
}
