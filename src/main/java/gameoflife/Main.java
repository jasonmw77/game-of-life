package gameoflife;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(10,10);
        game.seedGame();
        for (int i = 0; i < 10; i++) { game.iterateGame(); }
        System.out.println(game);
    }
}
