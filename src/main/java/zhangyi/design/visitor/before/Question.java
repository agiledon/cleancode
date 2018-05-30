package zhangyi.design.visitor.before;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String code;
    private List<QuestionField> fields;

    public Question(String code) {
        this.code = code;
        fields = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public List<QuestionField> getFields() {
        return fields;
    }

    public void addField(QuestionField field) {
        fields.add(field);
    }
}
