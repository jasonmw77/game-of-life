package gameoflife;

import java.util.Iterator;

public class GameBoard implements Iterable {
    private final int height;
    private final int width;
    private final Position[] board;

    public GameBoard() { this(10, 10, null); }

    public GameBoard(int height, int width) { this(height, width, null); }

    public GameBoard(GameBoard gameBoard) { this(gameBoard.height, gameBoard.width, gameBoard.board); }

    private GameBoard(int height, int width, Position[] gameBoard) {
        this.board = new Position[height * width];
        this.height = height;
        this.width = width;

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                Position position = new Position(row, col);
                if (gameBoard != null) {
                    position.setState(gameBoard[row * width + col].isAlive());
                }
                board[row * width + col] = position;
            }
        }
    }


    public int numberOfSpaces() { return height * width; }

    public Position getPosition(int space) { return board[space]; }

    public Position getPosition(Position position) { return board[position.getRow() * width + position.getCol()]; }

    public int getHeight() { return height; }

    public int getWidth() { return width; }

    public int getNeighbors(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        int tempRow = row - 1;
        int neighborCount = 0;

        for (int x = 0; x < 3; x++) {
            int tempCol = col - 1;
            for (int y = 0; y < 3; y++) {
                if ((tempRow != row || tempCol != col) && tempCol >= 0 && tempRow >= 0 && tempRow < height && tempCol < width) {
                    neighborCount += getPosition(tempRow * width + tempCol).getValue();
                }
                tempCol++;
            }
            tempRow++;
        }
        return neighborCount;
    }

    public String toString() {
        StringBuilder boardString = new StringBuilder();
        BoardIterator iterator = new BoardIterator();
        while (iterator.hasNext()) {
            Position position = iterator.next();
            boardString.append(position).append(" ");
            if (iterator.currentIndex % width == 0) {
                boardString.append("\n");
            }
        }
        return boardString.toString();
    }

    @Override
    public Iterator iterator() { return new BoardIterator(); }

    private class BoardIterator implements Iterator<Position> {
        private int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < board.length;
        }

        @Override
        public Position next() {
            if (hasNext()) {
                return board[currentIndex++];
            } else {
                return null;
            }
        }
    }

}

