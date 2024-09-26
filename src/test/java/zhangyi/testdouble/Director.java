package zhangyi.testdouble;

public class Director {
    private MovieScene movieScene;
    public Director(MovieScene movieScene) {
        this.movieScene = movieScene;
    }
    public String actionWith(String playerName) {
        // 假设这里有一些复杂的逻辑
        movieScene.setPlayerName(playerName);
        String scene = movieScene.actInPlay();
        return String.format("scene for player %s: %s", playerName, scene);
    }
}
