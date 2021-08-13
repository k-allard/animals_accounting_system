package com.accounting;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class FileParser {

    static List<Rule> parseRuleFile(String fileName) throws IOException {
        List<Rule> ruleList = new ArrayList<>();
        try (
                FileReader fileReader = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fileReader)
        ) {
            while (br.ready()) {
                ruleList.add(new Rule(br.readLine()));
            }
        }
        return ruleList;
    }

    static List<List<String>> getLinesFromFile(BufferedReader br, int maxNumOfLines) throws IOException {
        int i = 0;
        List<List<String>> result = new ArrayList<>();
        while (i < maxNumOfLines && br.ready()) {
            result.add(Arrays.asList(br.readLine().split(",")));
            i++;
        }
        return result;
    }
}
