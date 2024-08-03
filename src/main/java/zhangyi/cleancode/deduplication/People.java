package zhangyi.cleancode.deduplication;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class People {
    private List<Person> persons = new ArrayList<Person>();

    public List<Person> findByName(String name) {
        return findBy(equalsName(name));
    }

    public List<Person> findByAge(int age) {
        return findBy(equalsAge(age));
    }

    public List<Person> findByGender(Gender gender) {
        return findBy(equalsGender(gender));
    }

    public static Predicate<Person> equalsName(String name) {
        return p -> p.getName().equals(name);
    }

    public static Predicate<Person> equalsAge(int age) {
        return p -> p.getAge() == age;
    }

    public static Predicate<Person> equalsGender(Gender gender) {
        return p -> p.getGender().equals(gender);
    }

    public List<Person> findBy(Predicate<Person> predicate) {
        return persons.stream().filter(predicate).collect(Collectors.toList());
    }

    public void add(Person person) {
        persons.add(person);
    }
}
