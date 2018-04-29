package zhangyi.cleancode.deduplication;

import java.util.ArrayList;
import java.util.List;

public class People {
    private List<Person> persons = new ArrayList<Person>();

    public List<Person> find(Matcher matcher) {
        List<Person> result = new ArrayList<Person>();
        for (Person person : persons) {
            if (matcher.match(person)) {
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

    private interface Matcher {
        boolean match(Person person);
    }
}
