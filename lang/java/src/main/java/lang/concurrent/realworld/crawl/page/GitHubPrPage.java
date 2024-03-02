package lang.concurrent.realworld.crawl.page;

import java.net.URI;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public record GitHubPrPage(URI url, int prNumber, String content, Set<Page> links) implements GitHubPage {

    public GitHubPrPage {
        requireNonNull(url);
        if (prNumber <= 0) {
            throw new IllegalArgumentException("PR number must be 1 or greater - was '%s' at '%s'.".formatted(prNumber, url));
        }
        requireNonNull(content);
        links = new HashSet<>(links);
    }

    public GitHubPrPage(URI url, int issueNumber, String content) {
        this(url, issueNumber, content, new HashSet<>());
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this
                || obj instanceof GitHubPrPage page
                && this.url.equals(page.url());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url);
    }

    @Override
    public String toString() {
        return STR."GitHub PR @ \{url.toString()}";
    }
}
