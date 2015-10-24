package com.chernenko.valeria;

public class IterativeDeepeningSearch {
    private final SearchProblem problem;

    public IterativeDeepeningSearch(SearchProblem problem) {
        this.problem = problem;
    }

    public SearchResult run() {
        int depth = 0;
        while (true) {
            SearchResult result = new DepthLimitedSearch(problem, depth).run();
            if (result != SearchResult.CUTOFF) {
                return result;
            }
            depth++;
        }
    }
}
