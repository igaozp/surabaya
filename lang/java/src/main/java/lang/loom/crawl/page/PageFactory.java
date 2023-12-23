package lang.loom.crawl.page;

import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

class PageFactory {

    private static final Set<String> GITHUB_HOSTS = Set.of("github.com", "user-images.githubusercontent.com");
    private static final Pattern REGISTRATION_LINK_PATTERN = Pattern.compile("/issues/\\d+/?$|/pull/\\d+/?$");

    private PageFactory() {

    }

    public static PageWithLinks parsePage(URI url, String html) {
        return switch (url) {
            case URI u when u.getPath().contains("/issues/") -> parse(new IssuePageParser(url), html);
            case URI u when u.getPath().contains("/pull/") -> parse(new PrPageParser(url), html);
            default -> parse(new ExternalPageParser(url), html);
        };
    }

    private static PageWithLinks parse(PageParser parser, String html) {
        try {
            new ParserDelegator().parse(
                    new InputStreamReader(new ByteArrayInputStream(html.getBytes())),
                    parser,
                    true
            );
            return parser.createResult();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    private static boolean shouldRegisterLink(URI url) {
        if (url.getHost() == null) {
            return false;
        }

        var isExternalUrl = !GITHUB_HOSTS.contains(url.getHost());
        return isExternalUrl || REGISTRATION_LINK_PATTERN.matcher(url.toString()).find();
    }


    private static abstract class PageParser extends HTMLEditorKit.ParserCallback {
        protected final URI url;
        protected final StringBuilder contentBuilder;
        protected final Set<URI> links;

        private boolean recordContent;

        public PageParser(URI url) {
            this.url = requireNonNull(url);
            this.contentBuilder = new StringBuilder();
            this.links = new HashSet<>();
        }

        @Override
        public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int pos) {
            if (!recordContent) {
                recordContent = shouldStartRecording(tag, attributes);
            }
            if (!recordContent) {
                return;
            }

            recordPotentialLink(tag, attributes);
            recordOpeningTag(tag, attributes);
        }

        private void recordPotentialLink(HTML.Tag tag, MutableAttributeSet attributes) {
            if (!tag.toString().equals("a")) {
                return;
            }

            var value = attributes.getAttribute(HTML.Attribute.HREF);
            if (value == null || value.toString().isBlank()) {
                return;
            }

            var href = value.toString();
            try {
                var url = this.url.resolve(new URI(href));
                boolean cyclicLink = this.url.equals(url);
                if (!cyclicLink && shouldRegisterLink(url)) {
                    recordNormalizedLink(url);
                }
            } catch (URISyntaxException ignored) {

            }
        }

        private void recordNormalizedLink(URI url) {
            var resolvedUrl = this.url.resolve(url);
            links.add(resolvedUrl);
        }

        protected abstract boolean shouldStartRecording(HTML.Tag tag, AttributeSet attributes);

        protected abstract boolean shouldEndRecording(HTML.Tag tag);

        public abstract PageWithLinks createResult();

        @Override
        public void handleEndTag(HTML.Tag tag, int pos) {
            if (!recordContent) {
                return;
            }
            recordClosingTag(tag);
            recordContent = !shouldEndRecording(tag);
        }

        protected void recordOpeningTag(HTML.Tag tag, MutableAttributeSet attributes) {

        }

        protected void recordClosingTag(HTML.Tag tag) {

        }

    }

    private static abstract class DivCountingPageParser extends PageParser {

        private int divCounter;

        public DivCountingPageParser(URI url) {
            super(url);
        }

        @Override
        protected boolean shouldEndRecording(HTML.Tag tag) {
            return divCounter == 0;
        }

        protected void recordOpeningTag(HTML.Tag tag, MutableAttributeSet attributes) {
            super.recordOpeningTag(tag, attributes);
            if (tag.toString().equals("div")) {
                divCounter++;
            }
        }

        protected void recordClosingTag(HTML.Tag tag) {
            super.recordClosingTag(tag);
            if (tag.toString().equals("div")) {
                divCounter--;
            }
        }
    }

    private static class IssuePageParser extends DivCountingPageParser {

        private static final Pattern ISSUE_NUMBER_PATTERN = Pattern.compile(".*/issues/(\\d+)/?.*");

        public IssuePageParser(URI url) {
            super(url);
        }

        @Override
        protected boolean shouldStartRecording(HTML.Tag tag, AttributeSet attributes) {
            return Objects.equals(attributes.getAttribute(HTML.Attribute.ID), "show_issue");
        }

        @Override
        public PageWithLinks createResult() {
            var issueNumber = getFirstMatchAsNumber(ISSUE_NUMBER_PATTERN, url);
            return new PageWithLinks(new GitHubIssuePage(url, issueNumber, contentBuilder.toString()), links);
        }
    }

    private static int getFirstMatchAsNumber(Pattern pattern, URI url) {
        var issueNumberMatcher = pattern.matcher(url.toString());
        boolean found = issueNumberMatcher.find();
        if (!found) {
            throw new IllegalStateException("Alleged issue URL %s does not seem to contain a number.".formatted(url));
        }
        return Integer.parseInt(issueNumberMatcher.group(1));
    }

    private static class PrPageParser extends DivCountingPageParser {

        private static final Pattern PR_NUMBER_PATTERN = Pattern.compile(".*/pull/(\\d+)/?.*");

        public PrPageParser(URI url) {
            super(url);
        }

        @Override
        protected boolean shouldStartRecording(HTML.Tag tag, AttributeSet attributes) {
            return Objects.equals(attributes.getAttribute(HTML.Attribute.CLASS), "clearfix js-issue-results");
        }

        @Override
        public PageWithLinks createResult() {
            var prNumber = getFirstMatchAsNumber(PR_NUMBER_PATTERN, url);
            return new PageWithLinks(
                    new GitHubPrPage(url, prNumber, contentBuilder.toString()),
                    links
            );
        }
    }

    private static class ExternalPageParser extends PageParser {

        public ExternalPageParser(URI url) {
            super(url);
        }

        @Override
        protected boolean shouldStartRecording(HTML.Tag tag, AttributeSet attributes) {
            return tag.toString().equals("body");
        }

        @Override
        protected boolean shouldEndRecording(HTML.Tag tag) {
            return tag.toString().equals("body");
        }

        @Override
        public PageWithLinks createResult() {
            return new PageWithLinks(new ExternalPage(url, contentBuilder.toString()));
        }
    }
}
