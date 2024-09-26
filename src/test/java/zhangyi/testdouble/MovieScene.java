package zhangyi.testdouble;

public interface MovieScene {
    String actInPlay();
    void setPlayerName(String name);

    default void waitFiveSeconds() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
