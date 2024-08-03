package zhangyi.cleancode.deduplication;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class People {
    private List<Person> persons = new ArrayList<Person>();

    public List<Person> findBy(Predicate<Person> predicate) {
        return persons.stream().filter(predicate).collect(Collectors.toList());
    }

    public void add(Person person) {
        persons.add(person);
    }
}
