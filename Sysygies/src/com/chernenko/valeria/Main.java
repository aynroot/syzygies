package com.chernenko.valeria;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class Main {
    static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();

        String wordsFilePath = args[0];
        String start = args[1];
        String goal = args[2];

        if (start.length() < 2) {
            System.out.println("FAILURE");
            return;
        }

        WordsPreprocessor wordsPreprocessor = new WordsPreprocessor();
        wordsPreprocessor.processWords(WordsPreprocessor.getBufferedReader(wordsFilePath));
        logger.info("Successfully read words and constructed prefix/suffix lists.");
        logger.info(String.format("Prefix list size: %d.", wordsPreprocessor.getChildrenByPrefix().size()));
        logger.info(String.format("Suffix list size: %d.", wordsPreprocessor.getChildrenBySuffix().size()));

        SearchProblem problem = new SearchProblem(start, goal,
                wordsPreprocessor.getChildrenByPrefix(), wordsPreprocessor.getChildrenBySuffix());
        logger.info("Solving problem...");
        IterativeDeepeningSearch iterativeDeepeningSearch = new IterativeDeepeningSearch(problem);
        SearchResult result = iterativeDeepeningSearch.run();
        SearchSolution solution = result.equals(SearchResult.SUCCESS) ? problem.getSolution() : null;
        if (solution != null) {
            System.out.print("\nSolution:\t");
            System.out.println(solution.getSteps());
        } else {
            System.out.println("FAILURE");
        }
    }
}
