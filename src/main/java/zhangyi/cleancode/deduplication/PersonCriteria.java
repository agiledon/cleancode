package zhangyi.cleancode.deduplication;

import java.util.function.Predicate;

public class PersonCriteria {
    public static Predicate<Person> equalsName(String name) {
        return p -> p.getName().equals(name);
    }

    public static Predicate<Person> equalsAge(int age) {
        return p -> p.getAge() == age;
    }

    public static Predicate<Person> greaterThanAge(int age) {
        return p -> p.getAge() > age;
    }

    public static Predicate<Person> equalsGender(Gender gender) {
        return p -> p.getGender().equals(gender);
    }
}
