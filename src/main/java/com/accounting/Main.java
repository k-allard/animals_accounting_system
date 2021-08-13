package com.accounting;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static final int THREADPOOLSIZE = Runtime.getRuntime().availableProcessors();
    public static final int MAXLINE = 1000;

    public static void main(String[] args) throws IllegalArgumentException, IOException {

        long startTime = System.currentTimeMillis();
        List<Rule> rules = FileParser.parseRuleFile("task.csv");
        Map<Rule, Integer> resultMap = Counter.countAnimals("animals.1000000.csv", rules, THREADPOOLSIZE, MAXLINE);
        long endTime = System.currentTimeMillis();
        for (Map.Entry<Rule, Integer> result : resultMap.entrySet()) {
            System.out.println(
                    "Правилу " + result.getKey().rule + " соответствует " + result.getValue() + " животных"
            );
        }
        System.out.println("Время: [" + (endTime - startTime) + "] миллисекунд");
    }
}
