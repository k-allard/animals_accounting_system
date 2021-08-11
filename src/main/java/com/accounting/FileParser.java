package com.accounting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileParser {

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

//    static void parseAndCountAnimals(String fileName, Map<Integer, Rule> rules) throws IOException {
//        Map<Integer, Integer> result = initMap(rules);
//        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//            ArrayList<List<String>> animals = getLinesFromFile(br);
//            while (!animals.isEmpty()) {
//                ExecutorService executor = Executors.newFixedThreadPool(THREADPOOLSIZE);
//                ConcurrentLinkedQueue<CounterThread> threads = new ConcurrentLinkedQueue<>();
//                while (threads.size() < THREADPOOLSIZE && !animals.isEmpty()) {
//                    CounterThread thread = new CounterThread(rules, animals);
//                    threads.add(thread);
//                    executor.execute(thread);
//                    animals = getLinesFromFile(br);
//                }
//                executor.shutdown();
//                while (!executor.isTerminated()) { }
//                while (threads.size() > 0) {
//                    CounterThread thread = threads.poll();
//                    Map<Integer, Integer> res = thread.getResult();
//                    result = addMapToMap(result, res);
//                }
//            }
//        }
//        for (Integer id : rules.keySet()) {
//            System.out.println(id + ". Правилу " + rules.get(id).rule + " соответствует " + result.get(id) + " животных");
//        }
//    }
}
