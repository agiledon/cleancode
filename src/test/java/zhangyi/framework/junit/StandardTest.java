package zhangyi.framework.junit;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class StandardTest {
    @BeforeAll
    static void initAll() {
        System.out.println("before all / init all");
    }

    @BeforeEach
    void init() {
        System.out.println("before each / init");
    }

    @Test
    void succeedingTest() {
        System.out.println("succeeding test");

        String str = "assertJ provides Fluent assertions for java";
        assertThat(str).startsWith("assertJ")
                .endsWith("java")
                .containsIgnoringCase("fluent");
    }

    @Test
    @Disabled("skip the failure test")
    void failingTest() {
        System.out.println("failing test");
        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        System.out.println("skipped test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("after each / tear down");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("after all / tear down all");
    }
}
