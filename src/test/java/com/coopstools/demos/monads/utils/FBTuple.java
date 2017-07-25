/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.coopstools.demos.monads.utils;

public class FBTuple {

    public Integer position;
    public String value;

    public static FBTuple initNew(final Integer position) {
        return new FBTuple(position, "");
    }

    public FBTuple(Integer position, String value) {
        this.position = position;
        this.value = value;
    }

    public Boolean isDivisibleBy(final Integer number) {
        return (position % number) == 0;
    }

    public FBTuple appendValue(final String appendingValue) {
        return new FBTuple(position, value + appendingValue);
    }
}
