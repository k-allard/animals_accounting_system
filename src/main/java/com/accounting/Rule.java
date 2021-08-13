package com.accounting;

import java.util.Objects;
import java.util.Arrays;
import java.util.List;

public class Rule {
    public String rule;
    public List<String> ruleLine;

    public Rule(String rule) {
        this.rule = rule;
        this.ruleLine = Arrays.asList(rule.split(","));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule1 = (Rule) o;
        return Objects.equals(rule, rule1.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rule);
    }
}
