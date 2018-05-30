package zhangyi.design.visitor.before;

import java.util.ArrayList;
import java.util.List;

public class QuestionGroup {
    private String code;
    private List<Question> questions;

    public QuestionGroup(String code) {
        this.code = code;
        questions = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
