package com.chernenko.valeria;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SearchProblem {
    private final String start;
    private final String goal;
    private final HashMap<String, LinkedList<String>> wordsByPrefix;
    private final HashMap<String, LinkedList<String>> wordsBySuffix;
    private SearchSolution solution = null;

    public SearchProblem(String start, String goal,
                         HashMap<String, LinkedList<String>> wordsByPrefix,
                         HashMap<String, LinkedList<String>> wordsBySuffix) {
        this.start = start;
        this.goal = goal;
        this.wordsByPrefix = wordsByPrefix;
        this.wordsBySuffix = wordsBySuffix;
    }

    public SearchProblem(String start, SearchProblem parentProblem) {
        this.start = start;
        this.goal = parentProblem.goal;
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
        LinkedList<String> result;
        String prefix = start.substring(0, 2);
        String suffix = start.substring(start.length() - 2, start.length());
        result = wordsByPrefix.getOrDefault(suffix, new LinkedList<>());
        result.addAll(wordsBySuffix.getOrDefault(prefix, new LinkedList<>()));
        return result;
    }

    public SearchSolution getSolution() {
        return solution;
    }

    public void setSolution(SearchProblem childProblem) {
        if (childProblem != null) {
            this.solution = new SearchSolution(start, childProblem.getSolution());
            childProblem.solution = null;
        } else {
            this.solution = new SearchSolution(new LinkedList<>(Collections.singletonList(start)));
        }
    }
}
