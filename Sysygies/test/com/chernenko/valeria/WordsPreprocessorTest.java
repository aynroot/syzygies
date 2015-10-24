package com.chernenko.valeria;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class WordsPreprocessorTest {

    @Test
    public void testProcessWordsFile() throws Exception {
        WordsPreprocessor wordsPreprocessor = new WordsPreprocessor();
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(bufferedReader.readLine()).thenReturn(
              "white", "teamwork", "team", "a", "enumerate", "green", null
        );
        HashMap<String, LinkedList<String>> expectedByPrefix = new HashMap<>();
        expectedByPrefix.put("te", new LinkedList<>(Arrays.asList("teamwork", "team")));
        expectedByPrefix.put("en", new LinkedList<>(Collections.singletonList("enumerate")));
        expectedByPrefix.put("wh", new LinkedList<>(Collections.singletonList("white")));
        expectedByPrefix.put("gr", new LinkedList<>(Collections.singletonList("green")));

        HashMap<String, LinkedList<String>> expectedBySuffix = new HashMap<>();
        expectedBySuffix.put("te", new LinkedList<>(Arrays.asList("white", "enumerate")));
        expectedBySuffix.put("rk", new LinkedList<>(Collections.singletonList("teamwork")));
        expectedBySuffix.put("am", new LinkedList<>(Collections.singletonList("team")));
        expectedBySuffix.put("en", new LinkedList<>(Collections.singletonList("green")));

        wordsPreprocessor.processWords(bufferedReader);
        Assert.assertEquals(expectedByPrefix, wordsPreprocessor.getWordsByPrefix());
        Assert.assertEquals(expectedBySuffix, wordsPreprocessor.getWordsBySuffix());
    }
}