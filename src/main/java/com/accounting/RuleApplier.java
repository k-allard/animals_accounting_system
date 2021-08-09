package com.accounting;

import java.util.List;

public class RuleApplier {

    static boolean applySingleRule(List<String> animal, String rule) {
        for (String animalFeature : animal) {
            if (animalFeature.equals(rule))
                return true;
        }
        return false;
    }

    static boolean applyRulesSet(List<String> animal, List<String> rulesSet) {
        boolean result = false;
        for (String rule : rulesSet) {
            if (!rule.contains(" ")) {
                result = applySingleRule(animal, rule);
            } else if (rule.startsWith("НЕ ")) {
                result = !applySingleRule(animal, rule.substring(3));
            } else if (rule.contains(" ИЛИ ")) {
                String[] twoRulesArray = rule.split(" ");
                result = (applySingleRule(animal, twoRulesArray[0]) || applySingleRule(animal, twoRulesArray[2]));
            } else {
                System.out.println("task.csv: Некорректное правило");
                System.exit(1);
            }
            if (!result)
                break;
        }
        return result;
    }
}
