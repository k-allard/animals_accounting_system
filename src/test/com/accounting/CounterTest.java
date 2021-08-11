package com.accounting;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CounterTest {

    @Test
    void countAnimals_withNOT_withOR() {
        Map<Integer, Rule> rules = new HashMap<>();
        rules.put(1, new Rule("НЕ МАЛЕНЬКОЕ,ТРАВОЯДНОЕ ИЛИ ПЛОТОЯДНОЕ"));
        try {
            Map<Integer, Integer> result = Counter.countAnimals("test_files/animals.csv", rules, 6, 7);
            assertEquals(12, result.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void countAnimals_withNOT_withNOT() {
        Map<Integer, Rule> rules = new HashMap<>();
        rules.put(1, new Rule("НЕ МАЛЕНЬКОЕ,НЕ ПЛОТОЯДНОЕ"));
        try {
            Map<Integer, Integer> result = Counter.countAnimals("test_files/animals.csv", rules, 6, 7);
            assertEquals(19, result.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void countAnimals_one_criteria() {
        Map<Integer, Rule> rules = new HashMap<>();
        rules.put(1, new Rule("КАРЛИКОВОЕ"));
        try {
            Map<Integer, Integer> result = Counter.countAnimals("test_files/animals.csv", rules, 6, 7);
            assertEquals(1, result.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}