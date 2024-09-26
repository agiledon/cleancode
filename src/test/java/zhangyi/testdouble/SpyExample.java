package zhangyi.testdouble;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SpyExample {
    public class GameOfDeath implements MovieScene {
        private String playerName;
        public GameOfDeath(String playerName) {
            this.playerName = playerName;
        }
        @Override
        public String actInPlay() {
            waitFiveSeconds();
            return String.format("fight with %s", playerName);
        }
        @Override
        public void setPlayerName(String name) {
            this.playerName = name;
        }
    }
    @Test
    public void should_action_by_spy() {
        MovieScene real = new GameOfDeath("Bruce Lee");
        MovieScene spy = spy(real);
        doReturn("fight with Bruce Lee").when(spy).actInPlay();

        Director directorWithSpy = new Director(spy);
        assertThat(directorWithSpy.actionWith("Tang Long"))
                .isEqualTo("scene for player Tang Long: fight with Bruce Lee");

        verify(spy).actInPlay();
        verify(spy).setPlayerName("Tang Long");
    }

    @Test
    public void should_action_by_real() {
        MovieScene real = new GameOfDeath("Bruce Lee");

        Director directorWithReal = new Director(real);
        assertThat(directorWithReal.actionWith("Tang Long"))
                .isEqualTo("scene for player Tang Long: fight with Tang Long");

        // verify(real).actInPlay();  // it doesn't work
        // verify(real).setPlayerName("Tang Long");  // it doesn't work
    }
}
