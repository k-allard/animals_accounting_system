package com.accounting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final String SPACE_DELIMITER = " ";

    public static ArrayList<List<String>> getAnimalsFromAnimalsCSV() {
        ArrayList<List<String>> animals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("animals.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(SPACE_DELIMITER);
                animals.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return animals;
    }

    public static List<String> readTaskCSV() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("task4.csv"));
        String line = br.readLine();
        String[] values = line.split(SPACE_DELIMITER);
        List<String> task = new ArrayList<String>(Arrays.asList(values));
        return task;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<List<String>> animals = getAnimalsFromAnimalsCSV();
        if (animals.isEmpty()) {
            System.out.println("Нет животных");
        }
        List<List<String>> properties = new ArrayList<>();

        List<String> columns = animals.get(0);
        animals.remove(0);
        int i = 0;
        for (String column: columns) {

            System.out.println("Значения, которые может принимать " + column);
            int finalI = i;
            for (Iterator<String> it = animals.stream().map((a) -> a.get(finalI)).distinct().iterator(); it.hasNext(); ) {
                String str = it.next();
                System.out.println((" - " + str));

            }
            i++;
        }
        List<String> task = readTaskCSV();
        System.out.println("\nЖивотные:");
        System.out.println(animals);
        System.out.println("\nЗадача:");
        System.out.println(task);
    }
}