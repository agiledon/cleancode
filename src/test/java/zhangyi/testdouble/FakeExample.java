package zhangyi.testdouble;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class FakeExample {
    public class MusicProductionScene implements MovieScene {
        protected String playerName;

        public MusicProductionScene(String playerName) {
            this.playerName = playerName;
        }

        @Override
        public String actInPlay() {
            // 这是真实的实现
            // 假设它依赖了非常复杂的外部环境
            waitFiveSeconds();

            return String.format("%s invite Xia Luo", playerName);
        }

        @Override
        public void setPlayerName(String name) {
            this.playerName = name;
        }
    }
    public class FakeMusicProductionScene extends MusicProductionScene {
        public FakeMusicProductionScene(String playerName) {
            super(playerName);
        }

        @Override
        public String actInPlay() {
            return String.format("Fake Na Ying invite Xia Luo");
        }
    }
    @Test
    public void should_action_by_fake() {
        MovieScene fake = new FakeMusicProductionScene(" Fake Na Ying");

        Director director = new Director(fake);
        assertThat(director.actionWith("Na Ying")).
                isEqualTo("scene for player Na Ying: Fake Na Ying invite Xia Luo");
    }
}
