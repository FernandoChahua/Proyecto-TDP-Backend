package com.softtech.tdp.registration;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
//        TODO: Regex to validate email
        return true;
    }
}
