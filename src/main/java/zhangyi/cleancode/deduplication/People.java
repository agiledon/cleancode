package zhangyi.cleancode.deduplication;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class People {
    private List<Person> persons = new ArrayList<Person>();

    public List<Person> findByName(String name) {
        return findBy(p -> p.getName().equals(name));
    }

    public List<Person> findByAge(int age) {
        return findBy(p -> p.getAge() == age);
    }

    public List<Person> findByGender(Gender gender) {
        return findBy(p -> p.getGender().equals(gender));
    }

    private List<Person> findBy(Predicate<Person> predicate) {
        return persons.stream().filter(predicate).collect(Collectors.toList());
    }


}
