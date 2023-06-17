package language.loom.crawl.page;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.util.Objects.requireNonNull;

public class PageTreeFactory {
    private final HttpClient client;
    private final ConcurrentMap<URI, Page> resolvedPages;

    public PageTreeFactory(HttpClient client) {
        this.client = requireNonNull(client);
        resolvedPages = new ConcurrentHashMap<>();
    }

    public Page createPage(URI url, int depth) throws InterruptedException {
        if (resolvedPages.containsKey(url)) {
            System.out.printf("Found cached '%s'%n", url);
            return resolvedPages.get(url);
        }

        System.out.printf("Resolving '%s'...%n", url);
        var pageWithLinks = fetchPageWithLinks(url);
        var page = pageWithLinks.page();
        resolvedPages.putIfAbsent(page.url(), page);
        System.out.printf("Resolved '%s' with children: %s%n", url, pageWithLinks.links());

        if (page instanceof GitHubPage ghPage) {
            var resolveLinks = resolveLinks(pageWithLinks.links(), depth - 1);
            ghPage.links().addAll(resolveLinks);
        }
        return pageWithLinks.page();
    }

    private Collection<Page> resolveLinks(Set<URI> links, int depth) throws InterruptedException {
        if (depth < 0) {
            return Collections.emptySet();
        }

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var futurePages = new ArrayList<Future<Page>>();
            links.forEach(link -> futurePages.add(scope.fork(() -> createPage(link, depth))));

            scope.join();
            scope.throwIfFailed();

            return futurePages.stream().map(Future::resultNow).toList();
        } catch (ExecutionException e) {
            throw new IllegalStateException("Error cases should have seen handle during page creation!", e);
        }
    }

    private PageWithLinks fetchPageWithLinks(URI url) throws InterruptedException {
        try {
            var pageBody = fetchPageAsString(url);
            return PageFactory.parsePage(url, pageBody);
        } catch (InterruptedException e) {
            throw e;
        } catch (Exception e) {
            return new PageWithLinks(new ErrorPage(url, e));
        }
    }

    public String fetchPageAsString(URI url) throws IOException, InterruptedException {
        var request = HttpRequest
                .newBuilder(url)
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }
}
