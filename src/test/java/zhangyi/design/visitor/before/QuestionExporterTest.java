package zhangyi.design.visitor.before;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class QuestionExporterTest {

    private Section section;

    @Before
    public void setup() {
        QuestionField nameField = new QuestionField("name");
        QuestionField typeField = new QuestionField("type");

        Question question = new Question("basic");
        question.addField(nameField);
        question.addField(typeField);

        QuestionGroup group = new QuestionGroup("group");
        group.addQuestion(question);

        SubSection subSection = new SubSection("sub");
        subSection.addQuestionGroup(group);

        section = new Section("section");
        section.addSubSection(subSection);
    }

    @Test
    public void should_export_section() {
        QuestionExporter exporter = new QuestionExporter();
        String result = exporter.export(section);
        assertThat(result).isEqualTo("name,section,sub,group,basic,\n" +
                "type,section,sub,group,basic,\n");
    }

}