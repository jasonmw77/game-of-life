package gameoflife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
    private Game game;
    private GameBoard testBoard;

    @Before
    public void setup(){
        testBoard = new GameBoard();
        testBoard.getPosition(11).setState(true);
        testBoard.getPosition(12).setState(true);
        testBoard.getPosition(17).setState(true);
        testBoard.getPosition(18).setState(true);
        testBoard.getPosition(21).setState(true);
        testBoard.getPosition(27).setState(true);
        testBoard.getPosition(41).setState(true);
        testBoard.getPosition(43).setState(true);
        testBoard.getPosition(46).setState(true);
        testBoard.getPosition(51).setState(true);
        testBoard.getPosition(52).setState(true);
        testBoard.getPosition(53).setState(true);
        testBoard.getPosition(55).setState(true);
        testBoard.getPosition(56).setState(true);
        testBoard.getPosition(57).setState(true);
        testBoard.getPosition(65).setState(true);
        testBoard.getPosition(67).setState(true);
        testBoard.getPosition(81).setState(true);
        testBoard.getPosition(82).setState(true);
        game = new Game(testBoard);
    }

    @Test
    public void testLiveCellUnderPopulatedDies() {
        Assert.assertEquals(true,testBoard.getPosition(81).isAlive());

        game.iterateGame();
        Assert.assertNotEquals(true,game.activeBoard.getPosition(81).isAlive());
    }

    @Test
    public void testLiveCellSurvives() {
        testBoard.getPosition(22).setState(true);
        Assert.assertEquals(true,testBoard.getPosition(27).isAlive());
        game.iterateGame();
        Assert.assertEquals(true,game.activeBoard.getPosition(22).isAlive());
        Assert.assertEquals(true,game.activeBoard.getPosition(27).isAlive());
    }

    @Test
    public void testLiveCellOverpopulatedDies() {
        Assert.assertEquals(true,testBoard.getPosition(52).isAlive());
        Assert.assertEquals(true,testBoard.getPosition(56).isAlive());
        game.iterateGame();
        Assert.assertNotEquals(true,game.activeBoard.getPosition(52).isAlive());
        Assert.assertNotEquals(true,game.activeBoard.getPosition(56).isAlive());
    }

    @Test
    public void testDeadCellReproduces() {
        Assert.assertNotEquals(true,testBoard.getPosition(22).isAlive());
        Assert.assertNotEquals(true,testBoard.getPosition(23).isAlive());
        game.iterateGame();
        Assert.assertEquals(true,game.activeBoard.getPosition(22).isAlive());
        Assert.assertNotEquals(true,game.activeBoard.getPosition(23).isAlive());
    }
}
