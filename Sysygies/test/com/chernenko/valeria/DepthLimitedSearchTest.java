package com.chernenko.valeria;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;

public class DepthLimitedSearchTest {

    @Test
    public void testRunSuccess() throws Exception {
        WordsPreprocessor wordsPreprocessor = new WordsPreprocessor();
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(bufferedReader.readLine()).thenReturn(
                "white", "teamwork", "team", "a", "enumerate", "green", null
        );

        wordsPreprocessor.processWords(bufferedReader);
        SearchProblem problem = new SearchProblem("white", "green",
                wordsPreprocessor.getWordsByPrefix(), wordsPreprocessor.getWordsBySuffix());
        DepthLimitedSearch depthLimitedSearch = new DepthLimitedSearch(problem, 1);
        SearchResult result = depthLimitedSearch.run();
        Assert.assertEquals(result, SearchResult.CUTOFF);

        depthLimitedSearch = new DepthLimitedSearch(problem, 2);
        result = depthLimitedSearch.run();
        Assert.assertEquals(result, SearchResult.CUTOFF);

        depthLimitedSearch = new DepthLimitedSearch(problem, 3);
        result = depthLimitedSearch.run();
        Assert.assertEquals(result, SearchResult.SUCCESS);

        depthLimitedSearch = new DepthLimitedSearch(problem, 4);
        result = depthLimitedSearch.run();
        Assert.assertEquals(result, SearchResult.SUCCESS);
    }

    @Test
    public void testRunFailure() throws Exception {
        WordsPreprocessor wordsPreprocessor = new WordsPreprocessor();
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(bufferedReader.readLine()).thenReturn(
                "white", "teamwork", "team", "a", "enumerate", null
        );

        wordsPreprocessor.processWords(bufferedReader);
        SearchProblem problem = new SearchProblem("white", "green",
                wordsPreprocessor.getWordsByPrefix(), wordsPreprocessor.getWordsBySuffix());
        DepthLimitedSearch depthLimitedSearch = new DepthLimitedSearch(problem, 1);
        SearchResult result = depthLimitedSearch.run();
        Assert.assertEquals(result, SearchResult.CUTOFF);

        depthLimitedSearch = new DepthLimitedSearch(problem, 2);
        result = depthLimitedSearch.run();
        Assert.assertEquals(result, SearchResult.CUTOFF);

        depthLimitedSearch = new DepthLimitedSearch(problem, 3);
        result = depthLimitedSearch.run();
        Assert.assertEquals(result, SearchResult.CUTOFF);

        depthLimitedSearch = new DepthLimitedSearch(problem, 4);
        result = depthLimitedSearch.run();
        Assert.assertEquals(result, SearchResult.FAILURE);
        // TODO: make a set of visited nodes, unless we get a infinite loop
    }
}