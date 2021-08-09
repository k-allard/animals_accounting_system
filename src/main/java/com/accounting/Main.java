package com.accounting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        final ArrayList<List<String>> animals = CSVParser.parseFile("animals.csv");
        if (animals.isEmpty()) {
            System.out.println("Нет животных");
        }
        ArrayList<List<String>> tasks = CSVParser.parseFile("task.csv");
        System.out.println("\nЖивотные:");
        System.out.println(animals);
        System.out.println("\nЗадачи:");
        System.out.println(tasks);
    }
}