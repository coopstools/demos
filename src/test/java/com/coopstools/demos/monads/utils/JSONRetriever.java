/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.coopstools.demos.monads.utils;

import org.json.JSONObject;

@FunctionalInterface
public interface JSONRetriever {

    JSONObject retrieveAtIndex(final Integer index);
}
