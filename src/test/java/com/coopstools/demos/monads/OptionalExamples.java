/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.coopstools.demos.monads;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;

import com.coopstools.demos.monads.utils.Account;
import com.coopstools.demos.monads.utils.AccountType;
import com.coopstools.demos.monads.utils.ExampleStuff;
import com.coopstools.demos.monads.utils.Person;
import com.coopstools.demos.monads.utils.PotentialHeroes;

@Ignore
public class OptionalExamples {

    final private static String LEADER = "Captain";
    final private static String ASSASIN = "Widow";

    //Creation
    //get
    //present v absent

    @Test
    public void testBasic() throws Exception {

        Optional<String> maybeLeader = Optional.of(LEADER);

        System.out.println(maybeLeader.get());
    }



    //Optional.empty().get()
    //Should avvoid isPresent and get










    private final static Optional<Person> MAYBE_LEADER =
            Optional.of(new Person(LEADER, "Triskalion"));
    private final static Optional<Person> MAYBE_ASSASSIN =
            Optional.of(new Person(ASSASIN));

    //map
    //flatmap
    @Test
    public void testMap() throws Exception {

        System.out.println(MAYBE_ASSASSIN
                .map(Person::getName)
                .get());

        Integer leadersNamesLength = MAYBE_LEADER
                .map(Person::getName)
                .map(String::length)
                .get();
        System.out.println(leadersNamesLength);

        System.out.println(MAYBE_LEADER
                .map(Person::getName)
                .get());
    }




    @Test
    public void testFlatMap() throws Exception {

        System.out.println(MAYBE_LEADER
                .flatMap(Person::getAddress)
                .get());

        System.out.println(MAYBE_ASSASSIN
                .flatMap(Person::getAddress)
                .get());
    }

    //isPresent
    //ifPresent

    @Test
    public void testIsPresent() throws Exception {

        ouputLivingArrangements(MAYBE_LEADER);
        ouputLivingArrangements(MAYBE_ASSASSIN);
    }

    private void ouputLivingArrangements(final Optional<Person> person) {

        Optional<String> personAddress = person.flatMap(Person::getAddress);
        if (personAddress.isPresent()) {
            System.out.println(String.format("%s lives at %s",
                    person.map(Person::getName).get(),
                    personAddress.get()));
        } else {
            System.out.println(String.format("%s needs not the bonds of comfort",
                    person.map(Person::getName).get()));
        }
    }


    //orElse
    //orElseThrow
    //orElseGet <------

    @Test
    public void testIfPresent() throws Exception {

        Optional<String> maybeAddress = MAYBE_ASSASSIN.flatMap(Person::getAddress);

        maybeAddress.ifPresent(address -> System.out.println(
                String.format("%s lives at %s",
                        MAYBE_ASSASSIN.map(Person::getName).get(),
                        address)));

        maybeAddress.orElseThrow(() -> new RuntimeException(
                MAYBE_ASSASSIN.map(Person::getName).get() +
                        " is tied to no place"));
    }

    @Test
    public void testOrElse() throws Exception {

        System.out.println(String.format("%s lives at %s",
                MAYBE_ASSASSIN.map(Person::getName).get(),
                MAYBE_ASSASSIN.flatMap(Person::getAddress).orElseGet(() -> " the whim of desire")));
    }






    //equals
    //filter

    @Test
    public void testCombined() throws Exception {

        List<String> availableHelp = new LinkedList<>();
        for (Optional<Person> potentialHero: getPotentialHeroes()) {
            potentialHero
                    .filter(person -> person.getAddress().isPresent())
                    .map(Person::getName)
                    .ifPresent(availableHelp::add);
        }

        availableHelp.forEach(System.out::println);
    }

    private PotentialHeroes getPotentialHeroes() {

        PotentialHeroes heroes = new PotentialHeroes();
        heroes.addAll(Arrays.asList(
                Optional.empty(),
                Optional.of(new Person("The Hulk")),
                Optional.of(new Person("Hawkeye", "Triskalion")),
                Optional.of(new Person("Black Widow")),
                Optional.of(new Person("Thor", "Asgard")),
                Optional.of(new Person("Captain America", "Triskalion")),
                Optional.of(new Person("Iron Man", "Triskalion")),
                Optional.of(new Person("Spider-man", "Brooklyn"))
        ));

        return heroes;
    }













    private void stateHowMuchPersonHas(final String name) {

        Optional<Long> maybeBalance = Optional.ofNullable(ExampleStuff.getPersonRepo().findOne(name))
                .map(Person::getAccount)
                .filter(account -> AccountType.SAVINGS.equals(account.getType()))
                .map(Account::getBalance)
                .filter(balance -> balance >= 0L);

        maybeBalance.ifPresent(balance -> System.out.println(name + " has $" + balance.toString()));

        if (!maybeBalance.isPresent()) {
            System.out.println(name + " has no money");
        }
    }

    @Test
    public void testCombined2() {

        stateHowMuchPersonHas("Alice");
        stateHowMuchPersonHas("Bob");
        stateHowMuchPersonHas("Charlie");
        stateHowMuchPersonHas("David");
    }

    //Java 9 Optional -> stream
    //                   ifPresentOrElse
    //                   or
}
