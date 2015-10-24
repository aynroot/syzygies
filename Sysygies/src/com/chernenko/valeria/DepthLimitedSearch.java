package com.chernenko.valeria;

import java.util.Collections;
import java.util.LinkedList;

public class DepthLimitedSearch {
    private final int limit;
    private final SearchProblem problem;

    public DepthLimitedSearch(SearchProblem problem, int limit) {
        this.problem = problem;
        this.limit = limit;
    }

    public SearchResult run() {
        if (problem.getGoal().equals(problem.getStart())) {
            problem.setSolution(new SearchSolution(new LinkedList<>(Collections.singletonList(problem.getStart()))));
            return SearchResult.SUCCESS;
        } else if (limit == 0) {
            return SearchResult.CUTOFF;
        } else {
            Boolean cutoffOccurred = false;
            for (String child: problem.getChildren()) {
                SearchProblem childProblem = new SearchProblem(child, problem);
                SearchResult result = new DepthLimitedSearch(childProblem, limit - 1).run();
                if (result.equals(SearchResult.CUTOFF)) {
                    cutoffOccurred = true;
                } else if (result.equals(SearchResult.SUCCESS)) {
                    problem.setSolution(new SearchSolution(problem.getStart(), childProblem.getSolution()));
                    return result;
                }
            }
            return cutoffOccurred ? SearchResult.CUTOFF: SearchResult.FAILURE;
        }
    }
}