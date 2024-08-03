package zhangyi.cleancode.deduplication;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static zhangyi.cleancode.deduplication.PersonCriteria.*;


public class PeopleTest {
    @Test
    public void should_demonstrate_how_to_find_person_by_specific_criteria() {
        People people = new People();
        people.add(new Person("Bruce", 40, Gender.Male));
        people.add(new Person("Bruce", 20, Gender.Male));
        people.add(new Person("James", 15, Gender.Male));
        people.add(new Person("Rose", 33, Gender.Female));
        people.add(new Person("Marry", 40, Gender.Female));

        List<Person> result = people.findBy(equalsName("Bruce"));
        assertThat(result).hasSize(2).contains(
                new Person("Bruce", 40, Gender.Male),
                new Person("Bruce", 20, Gender.Male));

        result = people.findBy(equalsAge(15));
        assertThat(result).hasSize(1).containsOnly(new Person("James", 15, Gender.Male));

        result = people.findBy(equalsName("Bruce").and(equalsAge(40)));
        assertThat(result).hasSize(1).containsOnly(new Person("Bruce", 40, Gender.Male));

        result = people.findBy(greaterThanAge(33).and(equalsGender(Gender.Female)));
        assertThat(result).hasSize(1).containsOnly(new Person("Marry", 40, Gender.Female));

        result = people.findBy(greaterThanAge(33).or(equalsAge(33)).and(equalsGender(Gender.Female)));
        assertThat(result).hasSize(2).containsOnly(
                new Person("Rose", 33, Gender.Female),
                new Person("Marry", 40, Gender.Female));
    }
}