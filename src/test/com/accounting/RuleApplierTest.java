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
        List<String> rulesSet = new ArrayList<>();
        rulesSet.add("ПОЛОСАТОЕ");
        rulesSet.add("НЕ ДЛИННОХВОСТОЕ");
        assertTrue(RuleApplier.applyRulesSet(animal, rulesSet));
    }

    @Test
    void applyRulesSet_withOR_withNOT() {
        List<String> rulesSet = new ArrayList<>();
        rulesSet.add("КОРОТКОХВОСТОЕ ИЛИ БЕСХВОСТОЕ");
        rulesSet.add("НЕ СУХОПУТНОЕ");
        assertTrue(RuleApplier.applyRulesSet(animal, rulesSet));
    }

    @Test
    void applyRulesSet_withOR() {
        List<String> rulesSet = new ArrayList<>();
        rulesSet.add("ПОЛОСАТОЕ ИЛИ ОДНОТОННОЕ");
        assertTrue(RuleApplier.applyRulesSet(animal, rulesSet));
    }

    @Test
    void applyRulesSet_withNOT_FALSE() {
        List<String> rulesSet = new ArrayList<>();
        rulesSet.add("ВОДОПЛАВАЮЩЕЕ");
        rulesSet.add("НЕ ПОЛОСАТОЕ");
        assertFalse(RuleApplier.applyRulesSet(animal, rulesSet));
    }
}
