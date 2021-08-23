package com.softtech.tdp.registration;


import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}