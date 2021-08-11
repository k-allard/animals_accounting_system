package com.accounting;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static final int THREADPOOLSIZE = 6;
    public static final int MAXLINE = 25;

    public static void main(String[] args) throws IllegalArgumentException, IOException {
        Map<Integer, Rule> rules = FileParser.parseRuleFile("task.csv");
        Map<Integer, Integer> result = Counter.countAnimals("animals.csv", rules, THREADPOOLSIZE, MAXLINE);
        for (Integer id : rules.keySet()) {
            System.out.println(id + ". Правилу " + rules.get(id).rule + " соответствует " + result.get(id) + " животных");
        }
    }
}
