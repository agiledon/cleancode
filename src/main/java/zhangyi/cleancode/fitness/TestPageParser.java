package zhangyi.cleancode.fitness;

public class TestPageParser {

    private final StringBuffer newPageContent;
    private final WikiPage wikiPage;
    private final PageData pageData;

    public TestPageParser(PageData pageData) {
        this.pageData = pageData;
        newPageContent = new StringBuffer();
        wikiPage = this.pageData.getWikiPage();
    }

    public String parse(boolean isSuite) {
        if (isTestPage()) {
            if (isSuite) {
                includeSuiteSetup();
            }
            includeSetup();
            includeTestContent();
            includeTeardown();
            if (isSuite) {
                includeSuiteTeardown();
            }
            this.pageData.setContent(newPageContent.toString());
        }
        return this.pageData.getHtml();
    }

    private void includeSuiteSetup() {
        includeInheritedPage(SuiteResponder.SUITE_SETUP_NAME, "-setup");
    }

    private void includeSetup() {
        includeInheritedPage("SetUp", "-setup");
    }

    private void includeTestContent() {
        this.newPageContent.append(this.pageData.getContent());
    }

    private void includeTeardown() {
        includeInheritedPage("TearDown", "-teardown");
    }

    private void includeSuiteTeardown() {
        includeInheritedPage(SuiteResponder.SUITE_TEARDOWN_NAME, "-teardown");
    }

    private boolean isTestPage() {
        return this.pageData.hasAttribute("Test");
    }

    private void includeInheritedPage(String inheritedPageName, String pageName) {
        WikiPage inheritedPage = PageCrawlerImpl.getInheritedPage(inheritedPageName, this.wikiPage);
        if (inheritedPage != null) {
            WikiPagePath pagePath = inheritedPage.getPageCrawler().getFullPath(inheritedPage);
            String pagePathName = PathParser.render(pagePath);
            this.newPageContent.append("\n!include ")
                    .append(pageName)
                    .append(" .")
                    .append(pagePathName)
                    .append("\n");
        }
    }
}