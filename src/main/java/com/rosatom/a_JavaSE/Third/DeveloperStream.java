package com.rosatom.a_JavaSE.Third;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeveloperStream {
    public static void main(String[] args) {
        Developer dev1 = new Developer("Наташа", Arrays.asList("Java", "C++"));
        Developer dev2 = new Developer("Эрнест", Arrays.asList("Java", "Python"));
        Developer dev3 = new Developer("Элла", Arrays.asList("С#", "Python", "JavaScript"));
        Developer dev4 = new Developer("Bob", Arrays.asList("C", "Python"));
        Developer dev5 = new Developer("Jack", Arrays.asList("C", "Rust"));

        // what about empty List of languages? -> it's discarded
        Developer dev6 = new Developer("Tom", List.of());

        // dev1.getLanguages() == L1
        // O(L1 + L2 + ... + LN) - average linear complexity
        List<Developer> developersWithUniqueLanguages =
                findDevelopersWithUniqueLanguages(Stream.of(dev1, dev2, dev3, dev4, dev5));

        System.out.println(developersWithUniqueLanguages);
    }

    /**
     * Finds developers with unique languages and returns the {@link java.util.List},
     * consisting of them. Developers with no languages will not be included in
     * the resulting {@link java.util.List}.
     *
     * @param developerStream stream of {@link Developer}
     * @return {@link java.util.List}, consisting of developers with unique languages
     */
    private static <T extends Developer> List<T> findDevelopersWithUniqueLanguages(Stream<T> developerStream) {
        Set<String> usedLanguages = new HashSet<>();
        return developerStream
                .filter((dev) ->
                        dev.getLanguages().stream().noneMatch(usedLanguages::contains)
                                && usedLanguages.addAll(dev.getLanguages()))
                .toList();
    }
}

class Developer {
    private String name;
    private List<String> languages;

    public Developer(String name, List<String> languages) {
        this.name = name;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return name;
    }
}