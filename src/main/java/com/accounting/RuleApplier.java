package com.accounting;

import java.util.List;

public class RuleApplier {

    private static boolean applySingleRule(List<String> animal, String rule) {
        for (String animalFeature : animal) {
            if (animalFeature.equals(rule))
                return true;
        }
        return false;
    }

    static boolean applyRulesSet(List<String> animal, Rule ruleObj) throws IllegalArgumentException {
        boolean result = false;
        for (String rule : ruleObj.ruleLine) {
            if (!rule.contains(" ")) {
                result = applySingleRule(animal, rule);
            } else if (rule.startsWith("НЕ ")) {
                result = !applySingleRule(animal, rule.substring(3));
            } else if (rule.contains(" ИЛИ ")) {
                String[] twoRulesArray = rule.split(" ");
                result = (applySingleRule(animal, twoRulesArray[0]) || applySingleRule(animal, twoRulesArray[2]));
            } else {
                throw new IllegalArgumentException("task.csv: Некорректное правило :" + rule);
            }
            if (!result)
                break;
        }
        return result;
    }
}
