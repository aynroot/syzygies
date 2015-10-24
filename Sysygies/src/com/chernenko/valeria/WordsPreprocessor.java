package com.chernenko.valeria;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;


public class WordsPreprocessor {
    private HashMap<String, LinkedList<String>> wordsByPrefix = new HashMap<>();
    private HashMap<String, LinkedList<String>> wordsBySuffix = new HashMap<>();

    private void putWord(HashMap<String, LinkedList<String>> container, String key, String word) {
        if (container.containsKey(key)) {
            container.get(key).add(word);
        } else {
            LinkedList<String> words = new LinkedList<>();
            words.push(word);
            container.put(key, words);
        }
    }

    public void processWordsFile(String filePath) {
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
        } catch (FileNotFoundException e) {
            System.out.println(String.format("Specified path (%s) not found.", filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, LinkedList<String>> getWordsByPrefix() {
        return wordsByPrefix;
    }

    public HashMap<String, LinkedList<String>> getWordsBySuffix() {
        return wordsBySuffix;
    }
}
