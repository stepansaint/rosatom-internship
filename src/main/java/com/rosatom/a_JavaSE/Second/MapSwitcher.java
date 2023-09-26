package com.rosatom.a_JavaSE.Second;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSwitcher {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(Map.of(
                "One", 1,
                "Two", 2,
                "Three", 3,
                "Four", 4,
                "Five", 5,
                "ThreeAgain", 3
        ));

        Map<Integer, String> switchedMap = swapKeysAndValues(map, true);
        System.out.println(switchedMap);
    }

    /**
     * Swaps keys and values provided by <code>map</code>, replacing, if existing,
     * an old value with a new value (if <code>replaceOldValue</code> is <code>true</code>,
     * otherwise leaves an old value). Returns a new created {@link java.util.Map}.
     *
     * @param map             {@link Map}, at which keys and values should be swapped
     * @param replaceOldValue in case of a collision whether an old value should be replaced with a new value
     * @return a new created {@link java.util.Map} with swapped keys and values
     */
    private static <K, V> Map<V, K> swapKeysAndValues(Map<K, V> map, boolean replaceOldValue) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getValue,
                        Map.Entry::getKey,
                        (oldK, newK) -> replaceOldValue ? oldK : newK)
                );
    }
}
