package zhangyi.design.visitor.before;

import java.util.ArrayList;
import java.util.List;

public class SubSection {
    private String code;
    private List<QuestionGroup> groups;

    public SubSection(String code) {
        this.code = code;
        groups = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public List<QuestionGroup> getQuestionGroups() {
        return groups;
    }

    public void addQuestionGroup(QuestionGroup group) {
        groups.add(group);
    }
}
