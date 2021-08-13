package com.accounting;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Counter {

    private static Map<Rule, Integer> addMapToMap(Map<Rule, Integer> one, Map<Rule, Integer> two) {
        Map<Rule, Integer> overallResult = new HashMap<>();
        for (Map.Entry<Rule, Integer> oneResult : one.entrySet()) {
            Rule keyRule = oneResult.getKey();
            overallResult.put(keyRule, one.get(keyRule) + two.get(keyRule));
        }
        return overallResult;
    }

    private static Map<Rule, Integer> initMap(List<Rule> rules) {
        Map<Rule, Integer> res = new HashMap<>();
        for (Rule rule : rules) {
            res.put(rule, 0);
        }
        return res;
    }

    static Map<Rule, Integer> countAnimals(
            String fileName, List<Rule> rules, int threadPoolSize, int maxNumOfLines
    ) throws IOException {
        Map<Rule, Integer> result = initMap(rules);
        try (
                FileReader fileReader = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fileReader)
        ) {
            List<List<String>> animals = FileParser.getLinesFromFile(br, maxNumOfLines);
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
                    Map<Rule, Integer> res = thread.getResult();
                    result = addMapToMap(result, res);
                }
            }
        }
        return result;
    }
}
