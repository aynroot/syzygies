package com.chernenko.valeria;


import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;


public class WordsPreprocessor {
    HashMap<String, LinkedList<String>> wordsByPrefix = new HashMap<>();
    HashMap<String, LinkedList<String>> wordsBySuffix = new HashMap<>();

    public static void main(String[] args) {
        String wordsFilePath = args[0];

        WordsPreprocessor wp = new WordsPreprocessor();
        wp.processWordsFile(wordsFilePath);
    }

    private void putWord(HashMap<String, LinkedList<String>> container, String key, String word) {
        if (container.containsKey(key)) {
            container.get(key).add(word);
        } else {
            LinkedList<String> words = new LinkedList<>();
            words.push(word);
            container.put(key, words);
        }
    }

    private void processWordsFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            for(String line; (line = br.readLine()) != null;) {
                String word = line.trim().toLowerCase();
                if (word.length() < 2)
                    continue;
                String prefix = word.substring(0, 2);
                String suffix = word.substring(word.length() - 2, word.length());
                putWord(wordsByPrefix, prefix, word);
                putWord(wordsBySuffix, suffix, word);
            }

            System.out.println(String.format("Number of prefixes: %d", wordsByPrefix.size()));
            System.out.println(String.format("Number of suffixes: %d", wordsBySuffix.size()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
