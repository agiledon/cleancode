package zhangyi.cleancode.deduplication;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class People {
    private List<Person> persons = new ArrayList<Person>();

    public List<Person> find(Predicate<Person> predicate) {
        List<Person> result = new ArrayList<Person>();
        for (Person person : persons) {
            if (predicate.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

    public List<Person> findByName(String name) {
        return find(p -> p.getName() == name);
    }

    public List<Person> findByAge(int age) {
        return find(p -> p.getAge() == age);
    }
}
