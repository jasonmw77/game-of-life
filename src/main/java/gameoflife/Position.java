package gameoflife;

public class Position {
    private final int row;
    private final int col;
    private boolean state;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
        this.state = false;
    }

    public int getRow() { return row; }

    public int getCol() { return col; }

    public boolean isAlive() { return state; }

    public void setState(boolean state) { this.state = state; }

    public int getValue() { return state ? 1 : 0; }

    public void setValue(int value) { state = value == 1; }

    @Override
    public String toString() {
        Integer val = getValue();
        return val.toString();
    }
}
