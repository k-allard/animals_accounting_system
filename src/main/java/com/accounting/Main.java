package com.accounting;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IllegalArgumentException, IOException {
        Map<Integer, Rule> rules = FileParser.parseRuleFile("task.csv");
        AnimalCounter.countAnimals("animals.csv", rules);
    }
}
