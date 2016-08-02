package zhangyi.cleancode.deduplication;

import java.util.ArrayList;
import java.util.List;

public class People {
    private List<Person> persons = new ArrayList<Person>();

    public List<Person> findByName(String name) {
        List<Person> result = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                result.add(person);
            }
        }
        return result;
    }

    //using guava

    //using jdk8

}
