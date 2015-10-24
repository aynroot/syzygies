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
        logger.info(String.format("Prefix list size: %d.", wordsPreprocessor.getWordsByPrefix().size()));
        logger.info(String.format("Suffix list size: %d.", wordsPreprocessor.getWordsBySuffix().size()));

        SearchProblem problemPrefixFirst = new SearchProblem(start, goal, true,
                wordsPreprocessor.getWordsByPrefix(), wordsPreprocessor.getWordsBySuffix());
        SearchProblem problemSuffixFirst = new SearchProblem(start, goal, false,
                wordsPreprocessor.getWordsByPrefix(), wordsPreprocessor.getWordsBySuffix());

        logger.info("Solving Prefix First problem...");
        SearchSolution prefixFirstSolution = getSearchSolution(problemPrefixFirst);
        logger.info("Solved Prefix First problem.");

        logger.info("Solving Suffix First problem...");
        SearchSolution suffixFirstSolution = getSearchSolution(problemSuffixFirst);
        logger.info("Solved Suffix First problem.");

        printBestSolution(prefixFirstSolution, suffixFirstSolution);
    }

    private static SearchSolution getSearchSolution(SearchProblem problem) {
        IterativeDeepeningSearch iterativeDeepeningSearch = new IterativeDeepeningSearch(problem);
        SearchResult result = iterativeDeepeningSearch.run();
        return result.equals(SearchResult.SUCCESS) ? problem.getSolution() : null;
    }

    private static void printBestSolution(SearchSolution prefixFirstSolution, SearchSolution suffixFirstSolution) {
        if (prefixFirstSolution != null && suffixFirstSolution != null) {
            if (prefixFirstSolution.size() < suffixFirstSolution.size()) {
                System.out.println(prefixFirstSolution.getSteps());
            } else {
                System.out.println(suffixFirstSolution.getSteps());
            }
        } else {
            if (prefixFirstSolution != null) {
                System.out.println(prefixFirstSolution.getSteps());
            } else if (suffixFirstSolution != null) {
                System.out.println(suffixFirstSolution.getSteps());
            } else {
                System.out.println("FAILURE");
            }
        }
    }
}
