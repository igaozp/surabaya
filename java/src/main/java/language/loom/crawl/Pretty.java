package language.loom.crawl;

import language.loom.crawl.page.*;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.joining;

public class Pretty {

    private final Set<Page> printedPages;
    private final StringBuilder result;

    private Pretty(StringBuilder result) {
        this.printedPages = new HashSet<>();
        this.result = result;
    }

    public static String printPageList(Page rootPage) {
        if (!(rootPage instanceof GitHubPage ghPage))
            return createPageName(rootPage);

        return ghPage
                .subtree()
                .map(Pretty::createPageName)
                .collect(joining("\n"));
    }

    private static String createPageName(Page page) {
        return switch (page) {
            case ErrorPage error -> "💥 ERROR: " + error.url().getHost() + " / " + createErrorDetails(error);
            case ExternalPage external -> "💤 EXTERNAL: " + external.url().getHost();
            case GitHubIssuePage issue -> "🐈 ISSUE #" + issue.issueNumber();
            case GitHubPrPage pr -> "🐙 PR #" + pr.prNumber();
        };
    }

    private static String createErrorDetails(ErrorPage error) {
        var cause = getRootCause(error.ex());
        var rootMessage = cause.getMessage();
        var hasRootMessage = rootMessage != null && !rootMessage.isBlank() && !rootMessage.equals("null");
        String message = hasRootMessage ? " - " + cause.getMessage() : "";
        return cause.getClass().getSimpleName() + message;
    }

    private static Throwable getRootCause(Throwable ex) {
        Throwable cause = ex.getCause();
        if (cause == null || ex.equals(cause))
            return ex;
        return getRootCause(ex.getCause());
    }

    public static String printPageTree(Page rootPage) {
        Pretty pretty = new Pretty(new StringBuilder());
        pretty.appendPageTree(rootPage, 0);
        return pretty.result();
    }

    private void appendPageTree(Page page, int level) {
        result.append("\t".repeat(level));
        result.append(createPageName(page));
        if (printedPages.contains(page))
            result.append(" ⤴");
        result.append("\n");

        if (!printedPages.contains(page)) {
            printedPages.add(page);
            if (page instanceof GitHubPage ghPage)
                ghPage.links().forEach(linkedPage -> appendPageTree(linkedPage, level + 1));
        }
    }

    private String result() {
        return result.toString();
    }
}
