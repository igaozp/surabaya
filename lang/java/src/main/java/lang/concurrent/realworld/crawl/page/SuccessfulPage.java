package lang.concurrent.realworld.crawl.page;

public sealed interface SuccessfulPage extends Page permits ExternalPage, GitHubPage {
    String content();
}
