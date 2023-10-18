package com.rosatom.a_javaSE.b_collections_exceptions_reflection;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

public class CollectionsResearch {
    public static void main(String[] args) {
        final List<Integer> arrayList = new ArrayList<>();
        final List<Integer> linkedList = new LinkedList<>();
        final List<Collection<Integer>> collections = new ArrayList<>(
                List.of(arrayList,
                        linkedList,
                        new TreeSet<>(),
                        new HashSet<>())
        );

        final int size = 10_000_000;
        final int last = size - 1;

        warmUpCollections(collections);

        fillCollections(collections, size);

        // 'last' element is used to achieve the worst case
        searchElementInCollections(collections, last);
        removeElementInCollections(collections, last);

        /* special cases: java.util.List#add() */

        // first index
        System.out.println("\n=============SPECIAL CASES=============\n");
        addAtIndex(arrayList, 0);
        addAtIndex(linkedList, 0);
        System.out.println();
        // middle index
        addAtIndex(arrayList, size / 2);
        addAtIndex(linkedList, size / 2);
        System.out.println();
        // last index
        addAtIndex(arrayList, last);
        addAtIndex(linkedList, last);
        System.out.println();
    }

    private static void warmUpCollections(List<Collection<Integer>> collections) {
        PrintStream standardOut = System.out;

        System.setOut(new PrintStream(OutputStream.nullOutputStream()));
        collections.forEach((collection) -> {
            int warmUpSize = 1_000;

            fillCollections(collections, warmUpSize);
            searchElementInCollections(collections, warmUpSize);
            removeElementInCollections(collections, warmUpSize);
        });

        System.setOut(standardOut);
    }

    private static void fillCollections(List<Collection<Integer>> collections, int size) {
        collections.forEach((collection) -> {
            long start = System.currentTimeMillis();

            for (int i = 0; i < size; i++) {
                collection.add(i);
            }

            long end = System.currentTimeMillis();
            System.out.printf("%-14s: filled with %,d elements in %d ms.\n",
                    collection.getClass().getSimpleName(), size, (end - start));
        });

        System.out.println("\n");
    }

    private static void searchElementInCollections(List<Collection<Integer>> collections, int element) {
        collections.forEach((collection) -> {
            long start = System.currentTimeMillis();

            collection.contains(element);

            long end = System.currentTimeMillis();
            System.out.printf("%-14s: found element %,d in %d ms.\n",
                    collection.getClass().getSimpleName(), element, (end - start));
        });

        System.out.println("\n");
    }

    private static void removeElementInCollections(List<Collection<Integer>> collections, int element) {
        collections.forEach((collection) -> {
            long start = System.currentTimeMillis();
            collection.remove(element);
            long end = System.currentTimeMillis();
            System.out.printf("%-14s: removed element %,d in %d ms.\n",
                    collection.getClass().getSimpleName(), element, (end - start));
        });
    }

    private static void addAtIndex(List<Integer> list, int index) {
        long start = System.currentTimeMillis();

        list.add(index, -1);

        long end = System.currentTimeMillis();

        System.out.printf("%-14s: added at %,d index in %d ms.\n",
                list.getClass().getSimpleName(), index, (end - start));
    }
}
