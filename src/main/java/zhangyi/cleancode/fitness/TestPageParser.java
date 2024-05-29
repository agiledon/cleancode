package zhangyi.cleancode.fitness;

public class TestPageParser {
    static String parse(PageData pageData, boolean isSuite) {
        if (isTestPage(pageData)) {
            StringBuffer newPageContent = new StringBuffer();
            WikiPage wikiPage = pageData.getWikiPage();

            if (isSuite) {
                includeSuiteSetup(wikiPage, newPageContent);
            }
            includeSetup(wikiPage, newPageContent);
            includeTestContent(pageData, newPageContent);
            includeTeardown(wikiPage, newPageContent);
            if (isSuite) {
                includeSuiteTeardown(wikiPage, newPageContent);
            }

            pageData.setContent(newPageContent.toString());
        }
        return pageData.getHtml();
    }

    static void includeSuiteSetup(WikiPage wikiPage, StringBuffer newPageContent) {
        includeInheritedPage(wikiPage, newPageContent, SuiteResponder.SUITE_SETUP_NAME, "-setup");
    }

    static void includeSetup(WikiPage wikiPage, StringBuffer newPageContent) {
        includeInheritedPage(wikiPage, newPageContent, "SetUp", "-setup");
    }

    static StringBuffer includeTestContent(PageData pageData, StringBuffer newPageContent) {
        return newPageContent.append(pageData.getContent());
    }

    static void includeTeardown(WikiPage wikiPage, StringBuffer newPageContent) {
        includeInheritedPage(wikiPage, newPageContent, "TearDown", "-teardown");
    }

    static void includeSuiteTeardown(WikiPage wikiPage, StringBuffer newPageContent) {
        includeInheritedPage(wikiPage, newPageContent, SuiteResponder.SUITE_TEARDOWN_NAME, "-teardown");
    }

    static boolean isTestPage(PageData pageData) {
        return pageData.hasAttribute("Test");
    }

    static void includeInheritedPage(WikiPage wikiPage, StringBuffer newPageContent, String inheritedPageName, String pageName) {
        WikiPage inheritedPage = PageCrawlerImpl.getInheritedPage(inheritedPageName, wikiPage);
        if (inheritedPage != null) {
            WikiPagePath pagePath = inheritedPage.getPageCrawler().getFullPath(inheritedPage);
            String pagePathName = PathParser.render(pagePath);
            newPageContent.append("\n!include ")
                    .append(pageName)
                    .append(" .")
                    .append(pagePathName)
                    .append("\n");
        }
    }
}