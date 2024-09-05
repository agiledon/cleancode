package zhangyi.framework.junit;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class StandardTest {
    public StandardTest() {
        System.out.println("this is a constructor.");
    }

    @BeforeAll
    static void initAll() {
        System.out.println("before all");
    }
    @BeforeEach
    void init() {
        System.out.println("before");
    }
    @Test
    void succeedingTest() {
        System.out.println("succeeding test");
        assertThat(true).isTrue();
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
        System.out.println("after each");
    }
    @AfterAll
    static void tearDownAll() {
        System.out.println("after all");
    }
}
