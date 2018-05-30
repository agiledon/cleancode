package zhangyi.design.visitor.before;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private String code;
    private List<SubSection> subSections;

    public Section(String code) {
        this.code = code;
        subSections = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public List<SubSection> getSubSections() {
        return subSections;
    }

    public void addSubSection(SubSection subSection) {
        subSections.add(subSection);
    }
}
