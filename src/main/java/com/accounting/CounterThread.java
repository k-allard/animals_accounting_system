package com.accounting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CounterThread implements Runnable {
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
