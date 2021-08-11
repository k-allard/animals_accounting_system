package com.accounting;

import java.util.Arrays;
import java.util.List;

public class Rule {
    public String rule;
    public List<String> ruleLine;

    public Rule(String rule) {
        this.rule = rule;
        this.ruleLine = Arrays.asList(rule.split(","));
    }
}
