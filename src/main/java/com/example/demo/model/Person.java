package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Person {
    private final UUID id;

//    @NotBlank
//    private final String name;

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

//    public Person(@JsonProperty("id") UUID id,
//                  @JsonProperty("name") String name) {
//        this.id = id;
//        this.name = name;
//    }

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("first_name") String firstName,
                  @JsonProperty("last_name") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

//    public String getName() {
//        return name;
//    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
