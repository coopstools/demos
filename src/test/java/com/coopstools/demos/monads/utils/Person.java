/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.coopstools.demos.monads.utils;

import java.util.Optional;

import com.coopstools.demos.monads.utils.Account;
import com.coopstools.demos.monads.utils.AccountType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    private final String name;
    private final Optional<String> address;
    private final Account account;

    @JsonCreator
    public Person(
            @JsonProperty("name") final String name,
            @JsonProperty("address") final String address) {

        this.name = name;
        this.address = Optional.ofNullable(address);
        this.account = new Account(AccountType.CREDIT, 0L);
    }

    public Person(final String name) {

        this.name = name;
        this.address = Optional.empty();
        this.account = new Account(AccountType.CREDIT, 0L);
    }

    public Person(
            final String name,
            final Optional<String> address,
            final Account account) {

        this.name = name;
        this.address = address;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getAddress() {
        return address;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {

        return address
                .map(add -> String.format("%s in %s", name, add))
                .orElse(name);
    }
}
