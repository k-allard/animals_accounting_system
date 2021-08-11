package com.accounting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Counter {

    private static Map<Integer, Integer> addMapToMap(Map<Integer, Integer> one, Map<Integer, Integer> two) {
        Map<Integer, Integer> res = new HashMap<>();
        for (Integer key1 : one.keySet()) {
            res.put(key1, one.get(key1) + two.get(key1));
        }
        return res;
    }

    private static Map<Integer, Integer> initMap(Map<Integer, Rule> rules) {
        Map<Integer, Integer> res = new HashMap<>();
        for (Integer key : rules.keySet()) {
            res.put(key, 0);
        }
        return res;
    }

    static Map<Integer, Integer> countAnimals(String fileName, Map<Integer, Rule> rules,
                                              int threadPoolSize, int maxNumOfLines) throws IOException {
        Map<Integer, Integer> result = initMap(rules);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            ArrayList<List<String>> animals = FileParser.getLinesFromFile(br, maxNumOfLines);
            while (!animals.isEmpty()) {
                ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
                ConcurrentLinkedQueue<CounterThread> threads = new ConcurrentLinkedQueue<>();
                while (threads.size() < threadPoolSize && !animals.isEmpty()) {
                    CounterThread thread = new CounterThread(rules, animals);
                    threads.add(thread);
                    executor.execute(thread);
                    animals = FileParser.getLinesFromFile(br, maxNumOfLines);
                }
                executor.shutdown();
                while (!executor.isTerminated()) { }
                while (threads.size() > 0) {
                    CounterThread thread = threads.poll();
                    Map<Integer, Integer> res = thread.getResult();
                    result = addMapToMap(result, res);
                }
            }
        }
        return result;
    }
}
