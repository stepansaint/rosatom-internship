package com.rosatom.a_javaSE.c_io_serialization_generics;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PeopleSerialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Path serializationFile = Path.of("src/main/java/com/rosatom/a_JavaSE/Third/serialization.bin");
        final List<Person> people = List.of(
                new Person("Alex", 43, "job"),
                new Person("Bob", 3, "kindergarten"),
                new Person("Mary", 21, "university"),
                new Person("Maksim", 79, "retired"),
                new Person("Oleg", 18, "school"),
                new Person("Charles", 1, "home"));


        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(serializationFile));
             ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(serializationFile))) {

            oos.writeObject(people);
            System.out.println(ois.readObject());
        }
    }
}

/**
 * Represents a person.
 */
class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final int age;
    private transient String occupation;

    public Person(String name, int age, String occupation) {
        this.name = name;
        this.age = age;
        this.occupation = occupation;
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        occupation = calculateOccupation(age);
    }

    private String calculateOccupation(int age) {
        if (age >= 0 && age < 3) {
            return "home";
        } else if (age >= 3 && age < 7) {
            return "kindergarten";
        } else if (age >= 7 && age < 18) {
            return "school";
        } else if (age >= 18 && age < 23) {
            return "university";
        } else if (age >= 23 && age < 65) {
            return "job";
        } else {
            return "retired";
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
