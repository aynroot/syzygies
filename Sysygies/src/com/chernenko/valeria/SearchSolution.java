package com.chernenko.valeria;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SearchSolution {
    List<String> steps;

    public SearchSolution(List<String> steps) {
        this.steps = steps;
    }

    public SearchSolution(String start, SearchSolution childSolution) {
        steps = new LinkedList<>(Collections.singletonList(start));
        steps.addAll(childSolution.steps);
    }

    public List<String> getSteps() {
        return steps;
    }
}
