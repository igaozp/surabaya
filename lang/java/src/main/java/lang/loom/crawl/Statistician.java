package lang.loom.crawl;

import lang.loom.crawl.page.*;

import java.util.HashSet;
import java.util.Set;

public class Statistician {
    private final Set<Page> evaluatedPages;

    private int numberOfIssue;
    private int numberOfPrs;
    private int numberOfExternalLinks;
    private int numberOfErrors;

    private Statistician() {
        this.evaluatedPages = new HashSet<>();
    }

    public static Stats evaluate(Page rootPage) {
        Statistician statistician = new Statistician();
        statistician.evaluateTree(rootPage);
        return statistician.result();
    }

    private void evaluateTree(Page page) {
        if (evaluatedPages.contains(page)) {
            return;
        }
        evaluatedPages.add(page);

        switch (page) {
            case ErrorPage _ -> numberOfErrors++;
            case ExternalPage _ -> numberOfExternalLinks++;
            case GitHubIssuePage _ -> numberOfIssue++;
            case GitHubPrPage _ -> numberOfPrs++;
        }
    }

    private Stats result() {
        return new Stats(numberOfIssue, numberOfPrs, numberOfExternalLinks, numberOfErrors);
    }

    public record Stats(int numberOfIssue, int numberOfPrs, int numberOfExternalLinks, int numberOfErrors) {

    }

}
