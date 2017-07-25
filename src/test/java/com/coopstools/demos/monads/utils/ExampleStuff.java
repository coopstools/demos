/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.coopstools.demos.monads.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExampleStuff {

    private static Map<String, Person> personRepo = new HashMap<>();

    static {
        Account accA = new Account(AccountType.CREDIT, 1000L);
        Account accB = new Account(AccountType.SAVINGS, 500L);

        personRepo.put("Alice", new Person("Alice", Optional.empty(), accA));
        personRepo.put("Bob", new Person("Bob", Optional.empty(), accB));
        personRepo.put("Chloe", new Person("Chloe", Optional.empty(), null));
    }

    public ExampleStuff() {
    }

    public static PersonRepo getPersonRepo() {

        return (name) -> {
            if (!personRepo.containsKey(name))
                return null;
            return personRepo.get(name);
        };
    }

    @FunctionalInterface
    public interface PersonRepo {
        Person findOne(final String name);
    }
}
