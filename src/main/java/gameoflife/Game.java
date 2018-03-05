package gameoflife;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    private final LinkedList<GameBoard> BOARDS;
    GameBoard activeBoard;

    public Game() { this(10, 10, null); }

    public Game(GameBoard gameBoard) { this(gameBoard.getHeight(), gameBoard.getWidth(), gameBoard); }

    public Game(int height, int width) { this(height, width, null); }

    private Game(int height, int width, GameBoard gameBoard) {
        this.BOARDS = new LinkedList<>();
        if (gameBoard == null) {
            activeBoard = new GameBoard(height, width);
        } else {
            activeBoard = gameBoard;
        }
        BOARDS.push(activeBoard);
    }

    public void seedGame() {
        Random random = new Random();
        int numberOfSpaces = activeBoard.numberOfSpaces();
        int numberOfSeeds = random.nextInt(numberOfSpaces) + 1;
        for (int i = 0; i < numberOfSeeds; i++) {
            int position = random.nextInt(numberOfSpaces);
            activeBoard.getPosition(position).setState(true);
        }
    }

    public void iterateGame() {
        GameBoard newBoard = new GameBoard(activeBoard);
        Iterator boardIterator = newBoard.iterator();

        while (boardIterator.hasNext()) {
            Position position = (Position) boardIterator.next();
            boolean isAlive = position.isAlive();
            int neighborCount = activeBoard.getNeighbors(position);
            if (isAlive && (neighborCount < 2 || neighborCount > 3)) {
                position.setState(false);
            } else if (neighborCount == 3) {
                position.setState(true);
            }

        }
        BOARDS.push(newBoard);
        activeBoard = newBoard;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        Iterator<GameBoard> boardIterator = BOARDS.descendingIterator();
        while (boardIterator.hasNext()) {
            output.append((boardIterator.next())).append("\n");
        }
        return output.toString();
    }
}
