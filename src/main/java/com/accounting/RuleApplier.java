package com.accounting;

import java.util.List;

public class RuleApplier {
    private final static String OR_DELIMITER = " ИЛИ ";
    private final static String INVERSION = "НЕ ";
    private final static int INVERSION_SUBSTRING_COUNT = INVERSION.length();

    static boolean applyRulesToAnimal(List<String> animal, Rule ruleObj) {
        boolean result = false;
        for (String rule : ruleObj.ruleLine) {
            result = isMultipleRule(rule)
                    ? applyMultipleRule(animal, rule)
                    : applySingleRule(animal, rule);
            if (!result)
                break;
        }
        return result;
    }

    static boolean applyMultipleRule(List<String> animal, String rule) {
        String[] twoRulesArray = rule.split(OR_DELIMITER);
        return applySingleRule(animal, twoRulesArray[0]) || applySingleRule(animal, twoRulesArray[1]);
    }

    private static boolean applySingleRule(List<String> animal, String rule) {
        return containsInversion(rule)
                ? !apply(animal, rule.substring(INVERSION_SUBSTRING_COUNT))
                : apply(animal, rule);
    }

    static boolean apply(List<String> animal, String rule) {
        for (String animalFeature : animal) {
            if (animalFeature.equals(rule))
                return true;
        }
        return false;
    }

    static boolean containsInversion(String rule) {
        return rule.startsWith(INVERSION);
    }

    static boolean isMultipleRule(String rule) {
        return rule.contains(OR_DELIMITER);
    }
}
