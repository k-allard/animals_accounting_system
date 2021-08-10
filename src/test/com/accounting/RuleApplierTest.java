package com.accounting;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RuleApplierTest {

    private static final List<String> animal = new ArrayList<>();
    private static final List<String> rulesSet = new ArrayList<>();

    @BeforeAll
    static void init() {
        animal.add("ВОДОПЛАВАЮЩЕЕ");
        animal.add("ПОЛОСАТОЕ");
        animal.add("КОРОТКОХВОСТОЕ");
        rulesSet.add("НЕ КОРОТКОХВОСТОЕ");
    }

    @Test
    void applyRulesSet() {
        boolean result = RuleApplier.applyRulesSet(animal, rulesSet);
        assertFalse(result);
    }
}