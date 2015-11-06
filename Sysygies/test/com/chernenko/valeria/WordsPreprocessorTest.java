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
        expectedByPrefix.put("te", new LinkedList<>(Arrays.asList("white", "enumerate")));
        expectedByPrefix.put("en", new LinkedList<>(Collections.singletonList("green")));
        expectedByPrefix.put("rk", new LinkedList<>(Collections.singletonList("teamwork")));
        expectedByPrefix.put("am", new LinkedList<>(Collections.singletonList("team")));

        HashMap<String, LinkedList<String>> expectedBySuffix = new HashMap<>();
        expectedBySuffix.put("wh", new LinkedList<>(Collections.singletonList("white")));
        expectedBySuffix.put("te", new LinkedList<>(Arrays.asList("teamwork", "team")));
        expectedBySuffix.put("en", new LinkedList<>(Collections.singletonList("enumerate")));
        expectedBySuffix.put("gr", new LinkedList<>(Collections.singletonList("green")));

        wordsPreprocessor.processWords(bufferedReader);
        Assert.assertEquals(expectedByPrefix, wordsPreprocessor.getChildrenByPrefix());
        Assert.assertEquals(expectedBySuffix, wordsPreprocessor.getChildrenBySuffix());
    }
}