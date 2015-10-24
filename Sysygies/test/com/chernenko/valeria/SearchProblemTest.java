package com.chernenko.valeria;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import sun.plugin.javascript.navig.Link;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class SearchProblemTest {

    @Test
    public void testGetChildren() throws Exception {
        WordsPreprocessor wordsPreprocessor = new WordsPreprocessor();
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(bufferedReader.readLine()).thenReturn(
                "white", "teamwork", "team", "a", "enumerate", "green", null
        );

        wordsPreprocessor.processWords(bufferedReader);
        SearchProblem problem = new SearchProblem("white", "green", true,
                wordsPreprocessor.getWordsByPrefix(), wordsPreprocessor.getWordsBySuffix());
        List<String> children = problem.getChildren();
        Assert.assertEquals(new LinkedList<>(Arrays.asList("teamwork", "team")), children);

        problem = new SearchProblem("teamwork", "green", true,
                wordsPreprocessor.getWordsByPrefix(), wordsPreprocessor.getWordsBySuffix());
        children = problem.getChildren();
        Assert.assertEquals(new LinkedList<>(), children);

        problem = new SearchProblem("white", "green", false,
                wordsPreprocessor.getWordsByPrefix(), wordsPreprocessor.getWordsBySuffix());
        children = problem.getChildren();
        Assert.assertEquals(new LinkedList<>(), children);

        problem = new SearchProblem("enumerate", "green", false,
                wordsPreprocessor.getWordsByPrefix(), wordsPreprocessor.getWordsBySuffix());
        children = problem.getChildren();
        Assert.assertEquals(Collections.singletonList("green"), children);
    }
}