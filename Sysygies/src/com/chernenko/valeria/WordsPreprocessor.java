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
    private HashMap<String, LinkedList<String>> childrenByPrefix = new HashMap<>();
    private HashMap<String, LinkedList<String>> childrenBySuffix = new HashMap<>();

    private void putWord(HashMap<String, LinkedList<String>> container, String key, String word) {
        if (container.containsKey(key)) {
            container.get(key).add(word);
        } else {
            LinkedList<String> words = new LinkedList<>();
            words.push(word);
            container.put(key, words);
        }
    }

    public static BufferedReader getBufferedReader(String filePath) {
        try {
            return new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.out.println(String.format("Specified path (%s) not found.", filePath));
            return null;
        }
    }

    public void processWords(BufferedReader bufferedReader) {
        try {
            for(String line; (line = bufferedReader.readLine()) != null;) {
                String word = line.trim().toLowerCase();
                if (word.length() < 2)
                    continue;
                String prefix = word.substring(0, 2);
                String suffix = word.substring(word.length() - 2, word.length());
                putWord(wordsByPrefix, prefix, word);
                putWord(wordsBySuffix, suffix, word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String prefix: wordsByPrefix.keySet()) {
            LinkedList<String> words = wordsByPrefix.get(prefix);
            childrenBySuffix.put(prefix, words);
        }
        for (String suffix: wordsBySuffix.keySet()) {
            LinkedList<String> words = wordsBySuffix.get(suffix);
            childrenByPrefix.put(suffix, words);
        }
    }

    public HashMap<String, LinkedList<String>> getChildrenByPrefix() {
        return childrenByPrefix;
    }

    public HashMap<String, LinkedList<String>> getChildrenBySuffix() {
        return childrenBySuffix;
    }
}
