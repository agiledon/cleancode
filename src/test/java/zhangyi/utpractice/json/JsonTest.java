package zhangyi.utpractice.json;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {
    private String userJsonStr = "{\"age\":40,\"name\":\"zhangyi\"}";
    @Test
    public void should_parse_java_object_to_string() {
        User user = new User("zhangyi", 40);
        String result = Json.toJsonString(user);

        assertThat(result).isEqualTo(userJsonStr);
    }

    @Test
    public void should_parse_JsonObject_to_java_object() {
        User user = new User("zhangyi", 40);
        User result = Json.toJavaObject(Json.parse(userJsonStr), User.class);

        assertThat(result.getName()).isEqualTo(user.getName());
    }

    @Test
    public void should_parse_string_to_java_object() {
        User user = new User("zhangyi", 40);
        User result = Json.toJavaObject(userJsonStr, User.class);

        assertThat(result.getName()).isEqualTo(user.getName());
    }

    private static class User {
        private String name;
        private int age;

        public User() {
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}