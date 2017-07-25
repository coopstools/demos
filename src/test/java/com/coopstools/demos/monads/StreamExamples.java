/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.coopstools.demos.monads;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.coopstools.demos.monads.utils.FBTuple;
import com.coopstools.demos.monads.utils.JSONRetriever;
import com.coopstools.demos.monads.utils.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class StreamExamples {

    //PURPOSE:
    //A way of operating on collections of data
    //Procedure vs Transform

    private final List<Person> persons = Arrays.asList(
            new Person("Simon Peter", "The Lake of Galilee"),
            new Person("Levi Matthew", "The Tax Office"),
            new Person("Luke Scrivener", "The Library"),
            new Person("John Loved", "The Island of Patmos"),
            new Person("Saul Newman", "Tarsus"));
























    //Sort

    @Test
    public void testSortinAStream() throws Exception {

        List<String> sortedPersons = persons
                .stream()
                .map(Person::getName) //Method reference vs p -> p.getName()
                .map(String::toLowerCase)
                .sorted(String::compareTo) //comparator method takes 2 arguments
                .collect(Collectors.toList());
    }












    //Terminal and Intermediate operations

    @Test
    public void testIntermediateOps() throws Exception {

        Stream<String> personStream = persons
                .stream()
                .map(Person::toString);

        List<String> personNames = personStream.collect(Collectors.toList());

        personNames.stream().forEach(System.out::println);
        Integer length = personNames.stream()
                .mapToInt(String::length)
                .sum();

        System.out.println(length);
    }

























    //forEach and Peek

    @Test
    public void testTerminalOps() throws Exception {

        Stream<String> personStream = persons
                .stream()
                .map(Person::toString)
                .peek(System.out::println);

        Integer length = personStream
                .mapToInt(String::length)
                .sum();

        System.out.println(length);
    }












    //Lazy evaluation

    @Test
    public void testPeaking() throws Exception {

        Stream<String> personStream = persons
                .stream()
                .map(Person::toString)
                .peek(System.out::println);

        System.out.println("---  TEST LINE  ---");

        personStream.forEach(System.out::println);
    }









    //Iterate

    @Test
    public void FizzBuzz() throws Exception {

        Stream.iterate(1, v -> v + 1)
                .limit(30)
                .map(FBTuple::initNew)
                .map(fbTup -> fbTup.isDivisibleBy(3) ? fbTup.appendValue("Fizz") : fbTup)
                .map(fbTup -> fbTup.isDivisibleBy(5) ? fbTup.appendValue("Buzz") : fbTup)
                .map(fbTup -> "".equals(fbTup.value) ? fbTup.position.toString() : fbTup.value)
                .forEach(System.out::println);
    }












    //Filters and Groupings

    @Test
    public void testFilter() throws Exception {

        Map<Boolean, List<Person>> division = persons.stream()
                .collect(Collectors.groupingBy(
                        person -> isDisciple(person.getName())));

        System.out.println("These are disciples:");
        division.get(true).stream()
                .map(Person::toString)
                .forEach(System.out::println);

        System.out.println("\n\nThese are not disciples:");
        division.get(false).stream()
                .map(Person::toString)
                .forEach(System.out::println);
    }

    private final List<String> discipleNames = Arrays.asList(
            "Peter",
            "James",
            "John",
            "Judas",
            "Andrew",
            "Phillip",
            "Thadeus",
            "Bartholomew",
            "Thomas",
            "Matthew",
            "Simon");

    private Boolean isDisciple(final String name) {

        return discipleNames.stream()
                .filter(name::contains)
                .findFirst()
                .isPresent();
    }

    //flatMap
    //distinct
    //reduce














    //int streams

    @Test
    public void testIntegerStreams() throws Exception {

        Double averageNameLength = persons.stream()
                .map(Person::getName)
                .mapToInt(String::length)
                .average() //Return OptionalDouble
                .getAsDouble();
    }










    @Test
    public void testJson() throws Exception {

        String json = "[{\n"
                + "    \"name\": \"Saul\",\n"
                + "    \"address\": \"Benjamin\",\n"
                + "},\n"
                + "{\n"
                + "    \"name\": \"David\",\n"
                + "    \"address\": \"Bethlehem\"\n"
                + "},\n"
                + "{\n"
                + "    \"name\": \"Solomon\",\n"
                + "    \"address\": \"Jerusalem\"\n"
                + "},\n"
                + "{\n"
                + "    \"name\": \"Rehoboam\",\n"
                + "    \"address\": \"Jerusalem\"\n"
                + "}]\n";

        JSONArray jsonArray = new JSONArray(json);
        JSONRetriever jsonAtIndex = getJsonFromArray(jsonArray);

        Stream.iterate(0, v -> v + 1)
                .limit(jsonArray.length())
                .map(jsonAtIndex::retrieveAtIndex)
                .map(JSONObject::toString)
                .map(this::readJsonIntoPerson)
                .map(Person::getName)
                .forEach(System.out::println);
    }

    //Replace with functional interface as example
    private JSONRetriever getJsonFromArray(final JSONArray jsonArray) {

        return index -> {
            try {
                return jsonArray.getJSONObject(index);
            } catch (JSONException jsonEx) {
                throw new RuntimeException(jsonEx);
            }
        };
    }

    private final ObjectReader reader = new ObjectMapper().readerFor(Person.class);

    private Person readJsonIntoPerson(final String jsonString) {

        try {
            return reader.readValue(jsonString);
        } catch (IOException ioEx) {
            throw new RuntimeException(ioEx);
        }
    }
}
