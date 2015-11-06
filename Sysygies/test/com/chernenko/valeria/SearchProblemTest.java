package com.chernenko.valeria;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SearchProblemTest {

    @Test
    public void testGetChildren() throws Exception {
        WordsPreprocessor wordsPreprocessor = new WordsPreprocessor();
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(bufferedReader.readLine()).thenReturn(
                "white", "teamwork", "team", "a", "enumerate", "green", null
        );

        wordsPreprocessor.processWords(bufferedReader);
        SearchProblem problem = new SearchProblem("white", "green",
                wordsPreprocessor.getChildrenByPrefix(), wordsPreprocessor.getChildrenBySuffix());
        List<String> children = problem.getChildren();
        Assert.assertEquals(new LinkedList<>(Arrays.asList("teamwork", "team")), children);

        problem = new SearchProblem("teamwork", "green",
                wordsPreprocessor.getChildrenByPrefix(), wordsPreprocessor.getChildrenBySuffix());
        children = problem.getChildren();
        Assert.assertEquals(new LinkedList<>(Arrays.asList("white", "enumerate")), children);

        problem = new SearchProblem("enumerate", "green",
                wordsPreprocessor.getChildrenByPrefix(), wordsPreprocessor.getChildrenBySuffix());
        children = problem.getChildren();
        Assert.assertEquals(new LinkedList<>(Arrays.asList("green", "teamwork", "team")), children);
    }
}