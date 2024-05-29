package zhangyi.cleancode.fitness;

public class HtmlUtil {
    public static String testableHtml(PageData pageData, boolean isSuite) throws Exception {
        return new TestPageParser(pageData).parse(isSuite);
    }

}
