package zhangyi.framework.mockito;

import java.util.List;
import java.util.stream.Collectors;

public class Roster {
    private TextFileReader reader;

    public List<Employee> fetchRoster(String fileName) {
        List<String> lines = reader.read(fileName);

        return lines.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private Employee convert(String line) {
        return new Employee(line);
    }

    public void setReader(TextFileReader reader) {
        this.reader = reader;
    }
}
