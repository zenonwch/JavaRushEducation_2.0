package com.javarush.task.task35.task3504;

import java.util.HashMap;
import java.util.LinkedHashMap;

/* 
Простой generics
*/
public class Solution<T extends HashMap<K, V>, K, V> {
    private final T map;

    public Solution(final T map) {
        this.map = map;
    }

    public T getMap() {
        return map;
    }

    public static void main(final String[] args) {
        final HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("string", 4);
        final Solution<HashMap<String, Integer>, String, Integer> solution = new Solution<>(hashMap);
        final HashMap<String, Integer> mapFromSolution = solution.getMap();
        System.out.println(mapFromSolution.getClass());


        final LinkedHashMap<Solution<HashMap<String, Integer>, String, Integer>, Solution<HashMap<String, Integer>, String, Integer>> hashMap2
                = new LinkedHashMap<>();
        hashMap2.put(solution, solution);
        final Solution<LinkedHashMap<Solution<HashMap<String, Integer>, String, Integer>, Solution<HashMap<String, Integer>, String, Integer>>,
                Solution<HashMap<String, Integer>, String, Integer>, Solution<HashMap<String, Integer>, String, Integer>> solution2
                = new Solution<>(hashMap2);
        final LinkedHashMap<Solution<HashMap<String, Integer>, String, Integer>, Solution<HashMap<String, Integer>, String, Integer>> mapFromSolution2
                = solution2.getMap();   //need to cast  :(
        System.out.println(mapFromSolution2.getClass());
    }
}
