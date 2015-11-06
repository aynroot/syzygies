package com.chernenko.valeria;

import java.util.*;

public class SearchProblem {
    private final String start;
    private final String goal;
    private final HashMap<String, LinkedList<String>> childrenByPrefix;
    private final HashMap<String, LinkedList<String>> childrenBySuffix;
    private SearchSolution solution = null;

    public SearchProblem(String start, String goal,
                         HashMap<String, LinkedList<String>> childrenByPrefix,
                         HashMap<String, LinkedList<String>> childrenBySuffix) {
        this.start = start;
        this.goal = goal;
        this.childrenByPrefix = childrenByPrefix;
        this.childrenBySuffix = childrenBySuffix;
    }

    public SearchProblem(String start, SearchProblem parentProblem) {
        this.start = start;
        this.goal = parentProblem.goal;
        this.childrenByPrefix = parentProblem.childrenByPrefix;
        this.childrenBySuffix = parentProblem.childrenBySuffix;
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
        result = new LinkedList<>(childrenByPrefix.getOrDefault(prefix, new LinkedList<>()));
        result.addAll(childrenBySuffix.getOrDefault(suffix, new LinkedList<>()));
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
