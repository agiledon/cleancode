package zhangyi.cleancode.fitness;

public class HtmlUtil {
    public static String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
        if (isTestPage(pageData)) {
            StringBuffer buffer = new StringBuffer();
            WikiPage wikiPage = pageData.getWikiPage();

            if (includeSuiteSetup) {
                includeSuiteSetup(wikiPage, buffer);
            }
            includeSetup(wikiPage, buffer);
            includeTestContent(pageData, buffer);
            includeTeardown(wikiPage, buffer);
            if (includeSuiteSetup) {
                includeSuiteTeardown(wikiPage, buffer);
            }

            pageData.setContent(buffer.toString());
        }
        return pageData.getHtml();
    }

    private static void includeSuiteSetup(WikiPage wikiPage, StringBuffer buffer) {
        includeInheritedPage(SuiteResponder.SUITE_SETUP_NAME, wikiPage, buffer, "\n!include -setup .");
    }

    private static void includeSetup(WikiPage wikiPage, StringBuffer buffer) {
        includeInheritedPage("SetUp", wikiPage, buffer, "\n!include -setup .");
    }

    private static StringBuffer includeTestContent(PageData pageData, StringBuffer buffer) {
        return buffer.append(pageData.getContent());
    }

    private static void includeTeardown(WikiPage wikiPage, StringBuffer buffer) {
        includeInheritedPage("TearDown", wikiPage, buffer, "\n!include -teardown .");
    }

    private static void includeSuiteTeardown(WikiPage wikiPage, StringBuffer buffer) {
        includeInheritedPage(SuiteResponder.SUITE_TEARDOWN_NAME, wikiPage, buffer, "\n!include -teardown .");
    }

    private static boolean isTestPage(PageData pageData) {
        return pageData.hasAttribute("Test");
    }

    private static void includeInheritedPage(String inheritedPageName, WikiPage wikiPage, StringBuffer buffer, String pageName) {
        WikiPage inheritedPage = PageCrawlerImpl.getInheritedPage(inheritedPageName, wikiPage);
        if (inheritedPage != null) {
            WikiPagePath pagePath = inheritedPage.getPageCrawler().getFullPath(inheritedPage);
            String pagePathName = PathParser.render(pagePath);
            buffer.append(pageName)
                    .append(pagePathName)
                    .append("\n");
        }
    }
}
