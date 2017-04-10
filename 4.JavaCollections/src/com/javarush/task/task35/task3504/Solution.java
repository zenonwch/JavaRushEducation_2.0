package com.javarush.task.task35.task3504;

import java.util.HashMap;
import java.util.LinkedHashMap;

/* 
Простой generics
*/
public class Solution<T extends HashMap<K, V>, K, V> {
    private T map;

    public Solution(final T map) {
        this.map = map;
    }

    public T getMap() {
        return map;
    }

    public static void main(final String[] args) {
        final HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("string", 4);
        final Solution solution = new Solution(hashMap);
        final HashMap mapFromSolution = solution.getMap();
        System.out.println(mapFromSolution.getClass());


        final LinkedHashMap<Solution, Solution> hashMap2 = new LinkedHashMap<>();
        hashMap2.put(solution, solution);
        final Solution solution2 = new Solution(hashMap2);
        final LinkedHashMap mapFromSolution2 = (LinkedHashMap)solution2.getMap();   //need to cast  :(
        System.out.println(mapFromSolution2.getClass());
    }
}
