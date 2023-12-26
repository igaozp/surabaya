package lang.concurrent.realworld.crawl.page;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Stream;

public sealed interface GitHubPage extends SuccessfulPage permits GitHubIssuePage, GitHubPrPage {

    Set<Page> links();

    default Stream<Page> subtree() {
        var subtree = new ArrayList<Page>(Set.of(this));
        var upcomingPages = new LinkedList<>(this.links());

        while (!upcomingPages.isEmpty()) {
            var nextPage = upcomingPages.poll();
            if (!subtree.contains(nextPage) && nextPage instanceof GitHubPage nextGhPage) {
                upcomingPages.addAll(0, nextGhPage.links());
            }
            subtree.add(nextPage);
        }
        return subtree.stream();
    }
}
