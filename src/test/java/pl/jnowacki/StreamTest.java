package pl.jnowacki;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StreamTest {

    private List<Integer> numbers = Arrays.asList(1, 6, 76, 15, 34, -6);
    private List<String> names = Arrays.asList("Marek", "Ania", "Darek", "Piotr", "Asia");

    @Test
    public void testPrintNumbers() {
//        tradycyjnie - foreach
        for (int number : numbers) {
            System.out.println(number);
        }

//        za pomocą klasy anonimowej
        numbers.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

//        za pomocą lambdy
        numbers.forEach(integer -> {
            System.out.println(integer);
        });

//        jeśli mamy 1 instrukcję, to nawaisy są zbędne
        numbers.forEach(integer -> System.out.println(integer));

//        możemy wykorzystać referencję do metody
        numbers.forEach(System.out::println);
    }

    @Test
    public void testFilterNumbers() {
        numbers.stream()
                .filter(number -> number > 5)
                .forEach(System.out::println);
    }

    @Test
    public void testMapNumbers() {

        numbers.stream()
                .map(number -> number > 5 ? ">5" : "<5")
                .forEach(System.out::println);
    }


    @Test
    public void testFilterNames() {
        List<String> namesBeginWithA;
        List<String> namesDoNotBeginWithA;

        namesBeginWithA = names.stream()
                .filter(name -> name.charAt(0) == 'A')
                .collect(Collectors.toList());

        namesDoNotBeginWithA = names.stream()
                .filter(name -> name.charAt(0) != 'A')
                .collect(Collectors.toList());

        System.out.println("begins with A");
        System.out.println(namesBeginWithA);
        System.out.println("does not begin with A");
        System.out.println(namesDoNotBeginWithA);
    }

    @Test
    public void testMapNames() {
        names.stream().map(String::length).forEach(System.out::println);
    }

    @Test
    public void testPeopleAge() {
        List<Person> people = names.stream()
                .map(name -> new Person(name, getRandomNumberInRange(0, 99)))
                .collect(Collectors.toList());

        System.out.println("All people:");
        System.out.println(people);

        System.out.println("All people above 18 yo:");
        people.stream()
                .filter(person -> person.getAge() >= 18)
                .forEach(System.out::println);
    }

    public void trenaryOperator() {
        int a = 6;

//        to
        String aEval = a > 5 ? ">5" : "<5";

//        jest równe temu:
        if (a > 5) {
            aEval = ">5";
        } else {
            aEval = "<5";
        }
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
