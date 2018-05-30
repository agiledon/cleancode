package zhangyi.design.visitor.before;

public class QuestionExporter {
    public String export(Section section) {
        StringBuilder builder = new StringBuilder();

        for (SubSection subSection : section.getSubSections()) {
            for (QuestionGroup group : subSection.getQuestionGroups()) {
                for (Question question : group.getQuestions()) {
                    for (QuestionField field : question.getFields()) {
                        builder.append(field.getCode());
                        builder.append(",");
                        builder.append(section.getCode());
                        builder.append(",");
                        builder.append(subSection.getCode());
                        builder.append(",");
                        builder.append(group.getCode());
                        builder.append(",");
                        builder.append(question.getCode());
                        builder.append(",");
                        builder.append("\n");
                    }
                }
            }
        }
        return builder.toString();
    }
}
