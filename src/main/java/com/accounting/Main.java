package com.accounting;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        final ArrayList<List<String>> animals = CSVParser.parseFile("animals.csv");
        if (animals.isEmpty()) {
            System.out.println("animals.csv: Нет животных");
            System.exit(1);
        }
        ArrayList<List<String>> rules = CSVParser.parseFile("task.csv");

        System.out.println("\nЖивотные:");
        System.out.println(animals);
        System.out.println("\nЗадачи:");
        System.out.println(rules);
        System.out.println();

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