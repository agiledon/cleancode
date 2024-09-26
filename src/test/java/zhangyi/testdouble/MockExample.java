package zhangyi.testdouble;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MockExample {
    public class GameOfDeath implements MovieScene {
        private String playerName;

        public GameOfDeath(String playerName) {
            this.playerName = playerName;
        }

        @Override
        public String actInPlay() {
            waitFiveSeconds();
            return String.format("fight with %s", this.playerName);
        }

        @Override
        public void setPlayerName(String name) {
            this.playerName = name;
        }
    }

    @Test
    public void should_action_by_mock() {
        try(MockedConstruction<GameOfDeath> ignored = mockConstruction(GameOfDeath.class, (mock, context) -> {
            when(mock.actInPlay()).thenReturn("fight with Bruce Lee");
        })) {
            MovieScene mock = new GameOfDeath("Bruce Lee");
            Director director = new Director(mock);

            assertThat(director.actionWith("Tang Long"))
                    .isEqualTo("scene for player Tang Long: fight with Bruce Lee");
            verify(mock).actInPlay();
        }
    }
}
