package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Person {
    private final UUID id;

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String surname;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("first_name") String firstName,
                  @JsonProperty("surname") String surname) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
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
}
