package zhangyi.testdouble;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class StubExample {
    public class BathScene implements MovieScene {
        private String playerName;

        public BathScene(String playerName) {
            this.playerName = playerName;
        }

        @Override
        public String actInPlay() {
            waitFiveSeconds();
            return String.format("%s is taking a bath", playerName);
        }

        @Override
        public void setPlayerName(String name) {
            this.playerName = name;
        }
    }
    @Test
    public void should_action_by_stub() {
        try(MockedConstruction<BathScene> ignored = mockConstruction(BathScene.class, (mock, context) -> {
            when(mock.actInPlay()).thenReturn("Shao Xiaoshan is taking a bath");
        })) {
            MovieScene stub = new BathScene("Shao Xiaoshan");
            Director director = new Director(stub);
            assertThat(director.actionWith("Zhang Ziyi"))
                    .isEqualTo("scene for player Zhang Ziyi: Shao Xiaoshan is taking a bath");
        }
    }
}
