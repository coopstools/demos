/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.coopstools.demos.monads.structure;

import java.util.List;

public class Parent {

    private String name;
    private List<Child> child;

    public Parent(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Child> getChild() {
        return child;
    }

    public void setChild(List<Child> child) {
        this.child = child;
    }
}
