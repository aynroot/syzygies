package com.chernenko.valeria;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SearchProblem {
    private final String start;
    private final String goal;
    private final Boolean prefixMode;
    private final HashMap<String, LinkedList<String>> wordsByPrefix;
    private final HashMap<String, LinkedList<String>> wordsBySuffix;
    private SearchSolution solution = null;

    public SearchProblem(String start, String goal, Boolean prefixMode,
                         HashMap<String, LinkedList<String>> wordsByPrefix,
                         HashMap<String, LinkedList<String>> wordsBySuffix) {
        this.start = start;
        this.goal = goal;
        this.prefixMode = prefixMode;
        this.wordsByPrefix = wordsByPrefix;
        this.wordsBySuffix = wordsBySuffix;
    }

    public SearchProblem(String start, SearchProblem parentProblem) {
        this.start = start;
        this.goal = parentProblem.goal;
        this.prefixMode = !parentProblem.prefixMode;
        this.wordsByPrefix = parentProblem.wordsByPrefix;
        this.wordsBySuffix = parentProblem.wordsBySuffix;
    }

    public String getGoal() {
        return goal;
    }

    public String getStart() {
        return start;
    }

    public List<String> getChildren() {
        HashMap<String, LinkedList<String>> words = prefixMode ? wordsByPrefix : wordsBySuffix;
        String key = prefixMode ? start.substring(start.length() - 2, start.length()) : start.substring(0, 2);
        return words.getOrDefault(key, new LinkedList<>());
    }

    public SearchSolution getSolution() {
        return solution;
    }

    public void setSolution(SearchSolution solution) {
        this.solution = solution;
    }
}
