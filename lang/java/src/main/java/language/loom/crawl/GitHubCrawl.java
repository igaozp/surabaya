package language.loom.crawl;

import language.loom.crawl.page.PageTreeFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;

/**
 * Java Project Loom Example
 * <p>
 * <a href="https://www.youtube.com/watch?v=vvXmO2ZMGsk">Java 19 in Action</a>
 * <p>
 * <a href="https://github.com/nipafx/loom-lab#github-crawler">GitHub Crawler</a>
 *
 * @author igaozp
 * @since 2022/9/15
 */
public class GitHubCrawl {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        System.out.printf("jcmd %s Thread.dump_to_file -format=json threads.json%n", ProcessHandle.current().pid());

        var seedUrl = new URI("https://github.com/junit-pioneer/junit-pioneer/pull/627");

        var client = HttpClient.newHttpClient();
        var factory = new PageTreeFactory(client);
        var rootPage = factory.createPage(seedUrl, 2);

        System.out.println("\n---\n");
        System.out.println(Statistician.evaluate(rootPage));
        System.out.println("\n---\n");
        System.out.println(Pretty.printPageList(rootPage));
        System.out.println("\n---\n");
        System.out.println(Pretty.printPageTree(rootPage));
        System.out.println("\n---\n");
    }
}
