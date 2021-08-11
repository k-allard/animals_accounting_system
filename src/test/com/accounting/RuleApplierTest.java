package com.accounting;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RuleApplierTest {

    private static final List<String> animal = new ArrayList<>();

    @BeforeAll
    static void init() {
        animal.add("ВОДОПЛАВАЮЩЕЕ");
        animal.add("ПОЛОСАТОЕ");
        animal.add("КОРОТКОХВОСТОЕ");
    }

    @Test
    void applyRulesSet_withNOT_TRUE() {
        Rule rule = new Rule("ПОЛОСАТОЕ,НЕ ДЛИННОХВОСТОЕ");
        assertTrue(RuleApplier.applyRulesSet(animal, rule));
    }

    @Test
    void applyRulesSet_withOR_withNOT() {
        Rule rule = new Rule("КОРОТКОХВОСТОЕ ИЛИ БЕСХВОСТОЕ,НЕ СУХОПУТНОЕ");
        assertTrue(RuleApplier.applyRulesSet(animal, rule));
    }

    @Test
    void applyRulesSet_withOR() {
        Rule rule = new Rule("ПОЛОСАТОЕ ИЛИ ОДНОТОННОЕ");
        assertTrue(RuleApplier.applyRulesSet(animal, rule));
    }

    @Test
    void applyRulesSet_withNOT_FALSE() {
        Rule rule = new Rule("ВОДОПЛАВАЮЩЕЕ,НЕ ПОЛОСАТОЕ");
        assertFalse(RuleApplier.applyRulesSet(animal, rule));
    }
}
