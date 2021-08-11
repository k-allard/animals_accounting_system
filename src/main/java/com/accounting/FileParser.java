package com.accounting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CSVParser {

    private static final int THREADPOOLSIZE = 6;
    private static final int MAXLINE = 25;

    static Map<Integer, Rule> parseRuleFile(String fileName) throws IOException {
        Map<Integer, Rule> result = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int id = 1;
            while ((line = br.readLine()) != null) {
                result.put(id, new Rule(line));
                id++;
            }
        }
        return result;
    }

    static class CounterThread implements Runnable {
        private Map<Integer, Integer> result;
        Map<Integer, Rule> rules;
        ArrayList<List<String>> animals;

        public CounterThread(Map<Integer, Rule> rules, ArrayList<List<String>> animals) {
            this.rules = rules;
            this.animals = animals;
            result = new HashMap<>();
        }

        public void run() {
            for (Integer ruleId : rules.keySet()) {
                int res = 0;
                for (List<String> animal : animals) {
                    if (RuleApplier.applyRulesSet(animal, rules.get(ruleId)))
                        res++;
                }
                result.put(ruleId, res);
            }
        }

        public Map<Integer, Integer> getResult() {
            return this.result;
        }
    }

    static ArrayList<List<String>> getLinesFromFile(BufferedReader br) throws IOException {
        int i = 0;
        String line;
        ArrayList<List<String>> result = new ArrayList<>();
        while (i < MAXLINE && (line = br.readLine()) != null) {
            result.add(Arrays.asList(line.split(",")));
            i++;
        }
        return result;
    }

    static Map<Integer, Integer> addMapToMap(Map<Integer, Integer> one, Map<Integer, Integer> two) {
        Map<Integer, Integer> res = new HashMap<>();
        for (Integer key1 : one.keySet()) {
            res.put(key1, one.get(key1) + two.get(key1));
        }
        return res;
    }

    static Map<Integer, Integer> initMap(Map<Integer, Rule> rules) {
        Map<Integer, Integer> res = new HashMap<>();
        for (Integer key : rules.keySet()) {
            res.put(key, 0);
        }
        return res;
    }

    static void parseAndCountAnimals(String fileName, Map<Integer, Rule> rules) throws IOException {
        Map<Integer, Integer> result = initMap(rules);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            ArrayList<List<String>> animals = getLinesFromFile(br);
            while (!animals.isEmpty()) {
                ExecutorService executor = Executors.newFixedThreadPool(THREADPOOLSIZE);
                ConcurrentLinkedQueue<CounterThread> threads = new ConcurrentLinkedQueue<>();
                while (threads.size() < THREADPOOLSIZE && !animals.isEmpty()) {
                    CounterThread thread = new CounterThread(rules, animals);
                    threads.add(thread);
                    executor.execute(thread);
                    animals = getLinesFromFile(br);
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
        for (Integer id : rules.keySet()) {
            System.out.println(id + ". Правилу " + rules.get(id).rule + " соответствует " + result.get(id) + " животных");
        }
    }
}