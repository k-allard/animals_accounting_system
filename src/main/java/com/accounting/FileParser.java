package com.accounting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileParser {

    static Map<Integer, Rule> parseRuleFile(String fileName) throws IOException {
        Map<Integer, Rule> result = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int id = 1;
            while ((line = br.readLine()) != null) {
                result.put(id, new Rule(line));
                id++;
            }
        }
        return result;
    }

    static ArrayList<List<String>> getLinesFromFile(BufferedReader br, int maxNumOfLines) throws IOException {
        int i = 0;
        String line;
        ArrayList<List<String>> result = new ArrayList<>();
        while (i < maxNumOfLines && (line = br.readLine()) != null) {
            result.add(Arrays.asList(line.split(",")));
            i++;
        }
        return result;
    }
}
