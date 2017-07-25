/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.coopstools.demos.monads.utils;

public class Account {

    private AccountType type;
    private Long balance;

    public Account(final AccountType type, final Long balance) {

        this.type = type;
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public Long getBalance() {
        return balance;
    }

    public void addToBalance(final Long balance) {
        this.balance += balance;
    }
}
