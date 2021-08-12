package com.accounting;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static final int THREADPOOLSIZE = 5;
    public static final int MAXLINE = 1000;

    public static void main(String[] args) throws IllegalArgumentException, IOException {
        long startTime = System.currentTimeMillis();
        Map<Integer, Rule> rules = FileParser.parseRuleFile("task.csv");
        Map<Integer, Integer> result = Counter.countAnimals("animals.1000000.csv", rules, THREADPOOLSIZE, MAXLINE);
        long endTime = System.currentTimeMillis();
        for (Map.Entry<Integer, Rule> ruleEntry: rules.entrySet()) {
            System.out.println(ruleEntry.getKey() + ". Правилу " + ruleEntry.getValue().rule +
                    " соответствует " + result.get(ruleEntry.getKey()) + " животных");
        }
        System.out.println("Время: [" + (endTime - startTime) + "] миллисекунд");
    }
}
