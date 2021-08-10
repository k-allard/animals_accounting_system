package com.accounting;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RuleApplierTest {

    private static final List<String> animal = new ArrayList<>();
    private static List<String> rulesSet = new ArrayList<>();

    @BeforeAll
    static void init() {
        animal.add("ВОДОПЛАВАЮЩЕЕ");
        animal.add("ПОЛОСАТОЕ");
        animal.add("КОРОТКОХВОСТОЕ");
    }

    @Test
    void applyRulesSet() {
        rulesSet.add("ПОЛОСАТОЕ");
        rulesSet.add("НЕ КОРОТКОХВОСТОЕ");
        assertFalse(RuleApplier.applyRulesSet(animal, rulesSet));
        rulesSet.clear();
        rulesSet.add("КОРОТКОХВОСТОЕ ИЛИ БЕСХВОСТОЕ");
        rulesSet.add("НЕ СУХОПУТНОЕ");
        assertTrue(RuleApplier.applyRulesSet(animal, rulesSet));
        rulesSet.clear();
        rulesSet.add("ПОЛОСАТОЕ ИЛИ ОДНОТОННОЕ");
        assertTrue(RuleApplier.applyRulesSet(animal, rulesSet));
        rulesSet.clear();
        rulesSet.add("ВОДОПЛАВАЮЩЕЕ");
        rulesSet.add("НЕ ПОЛОСАТОЕ");
        assertFalse(RuleApplier.applyRulesSet(animal, rulesSet));
    }
}
