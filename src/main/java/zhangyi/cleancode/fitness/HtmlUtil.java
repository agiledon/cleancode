package zhangyi.cleancode.fitness;

public class HtmlUtil {
    public static String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
        WikiPage wikiPage = pageData.getWikiPage();
        StringBuffer buffer = new StringBuffer();
        if (isTestPage(pageData)) {
            if (includeSuiteSetup) {
                includeInheritedPage(SuiteResponder.SUITE_SETUP_NAME, wikiPage, buffer, "\n!include -setup .");
            }
            includeInheritedPage("SetUp", wikiPage, buffer, "\n!include -setup .");
        }
        buffer.append(pageData.getContent());
        if (isTestPage(pageData)) {
            includeInheritedPage("TearDown", wikiPage, buffer, "\n!include -teardown .");
            if (includeSuiteSetup) {
                includeInheritedPage(SuiteResponder.SUITE_TEARDOWN_NAME, wikiPage, buffer, "\n!include -teardown .");
            }
        }
        pageData.setContent(buffer.toString());
        return pageData.getHtml();
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
