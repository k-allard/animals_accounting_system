package com.accounting;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IllegalArgumentException {
        final ArrayList<List<String>> animals = CSVParser.parseFile("animals.csv");
        if (animals.isEmpty()) {
            throw new IllegalArgumentException("animals.csv file is empty");
        }
        ArrayList<List<String>> rules = CSVParser.parseFile("task.csv");
        for (List<String> ruleSet : rules) {
            int result = 0;
            for (List<String> animal : animals) {
                if (RuleApplier.applyRulesSet(animal, ruleSet))
                    result++;
            }
            System.out.println("Правилу " + ruleSet + " соответствует " + result + " животных");
        }
    }
}