package com.accounting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CounterThread implements Runnable {
    private final Map<Rule, Integer> result;
    private final List<Rule> rules;
    private final List<List<String>> animals;

    public CounterThread(List<Rule> rules, List<List<String>> animals) {
        this.rules = rules;
        this.animals = animals;
        result = new HashMap<>();
    }

    public void run() {
        for (Rule rule : rules) {
            int res = 0;
            for (List<String> animal : animals) {
                if (RuleApplier.applyRulesToAnimal(animal, rule))
                    res++;
            }
            result.put(rule, res);
        }
    }

    public Map<Rule, Integer> getResult() {
        return result;
    }
}
