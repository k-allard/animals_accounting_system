package com.accounting;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CounterTest {

    @Test
    void countAnimals_withNOT_withOR() throws IOException {
        List<Rule> rules = new ArrayList<>();
        Rule rule = new Rule("НЕ МАЛЕНЬКОЕ,ТРАВОЯДНОЕ ИЛИ ПЛОТОЯДНОЕ");
        rules.add(rule);

        Map<Rule, Integer> result = Counter.countAnimals("test_files/animals.csv", rules, 6, 7);
        assertEquals(12, result.get(rule));
    }

    @Test
    void countAnimals_withNOT_withNOT() throws IOException {
        List<Rule> rules = new ArrayList<>();
        Rule rule = new Rule("НЕ МАЛЕНЬКОЕ,НЕ ПЛОТОЯДНОЕ");
        rules.add(rule);

        Map<Rule, Integer> result = Counter.countAnimals("test_files/animals.csv", rules, 6, 7);
        assertEquals(19, result.get(rule));

    }

    @Test
    void countAnimals_one_criteria() throws IOException {
        List<Rule> rules = new ArrayList<>();
        Rule rule = new Rule("КАРЛИКОВОЕ");
        rules.add(rule);

        Map<Rule, Integer> result = Counter.countAnimals("test_files/animals.csv", rules, 6, 7);
        assertEquals(1, result.get(rule));
    }
}