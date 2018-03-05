package gameoflife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
    private GameBoard fourBlocksEachCorner, allEdgesOneDeep, noEdgesOrCorners;
    private final int DIMENSION = 10;

    @Before
    public void setup(){
        fourBlocksEachCorner = new GameBoard(DIMENSION, DIMENSION);
        allEdgesOneDeep = new GameBoard(DIMENSION, DIMENSION);
        noEdgesOrCorners = new GameBoard(DIMENSION, DIMENSION);

        for (int i = 0; i < DIMENSION; i++) {
            if (i == 0 || i == 1 || i == 8 || i == 9) {
                fourBlocksEachCorner.getPosition(new Position(0, i)).setState(true);
                fourBlocksEachCorner.getPosition(new Position(1, i)).setState(true);
                fourBlocksEachCorner.getPosition(new Position(8, i)).setState(true);
                fourBlocksEachCorner.getPosition(new Position(9, i)).setState(true);
            }
        }

        for (int j = 0; j < DIMENSION; j++) {
            allEdgesOneDeep.getPosition(j).setState(true);
            allEdgesOneDeep.getPosition(j * DIMENSION).setState(true);
            allEdgesOneDeep.getPosition(j * DIMENSION + (DIMENSION - 1)).setState(true);
            allEdgesOneDeep.getPosition(DIMENSION * (DIMENSION - 1) + j).setState(true);
        }
    }


    @Test
    public void test_LiveCell_LessThanTwoNeighbors(){
        Assert.assertNotEquals(1, fourBlocksEachCorner.getNeighbors(new Position(1,1)));
        Assert.assertNotEquals(1, fourBlocksEachCorner.getNeighbors(new Position(1,DIMENSION - 2)));
        Assert.assertNotEquals(1, fourBlocksEachCorner.getNeighbors(new Position(DIMENSION - 2,1)));
        Assert.assertNotEquals(1, fourBlocksEachCorner.getNeighbors(new Position(DIMENSION - 2,DIMENSION - 2)));

        noEdgesOrCorners.getPosition(0).setState(true);
        noEdgesOrCorners.getPosition(DIMENSION -1).setState(true);
        noEdgesOrCorners.getPosition(DIMENSION * (DIMENSION - 1)).setState(true);
        noEdgesOrCorners.getPosition(DIMENSION * (DIMENSION - 1) + DIMENSION -1).setState(true);

        Assert.assertEquals(1, noEdgesOrCorners.getNeighbors(new Position(1,1)));
        Assert.assertEquals(1, noEdgesOrCorners.getNeighbors(new Position(1,DIMENSION - 2)));
        Assert.assertEquals(1, noEdgesOrCorners.getNeighbors(new Position(DIMENSION - 2,1)));
        Assert.assertEquals(1, noEdgesOrCorners.getNeighbors(new Position(DIMENSION - 2,DIMENSION - 2)));
    }

    @Test
    public void test_LiveCell_TwoOrThreeNeighbors(){
        Assert.assertNotEquals(2, fourBlocksEachCorner.getNeighbors(new Position(1,1)));
        Assert.assertNotEquals(2, fourBlocksEachCorner.getNeighbors(new Position(1,DIMENSION - 2)));
        Assert.assertNotEquals(2, fourBlocksEachCorner.getNeighbors(new Position(DIMENSION - 2,1)));
        Assert.assertNotEquals(2, fourBlocksEachCorner.getNeighbors(new Position(DIMENSION - 2,DIMENSION - 2)));

        Assert.assertNotEquals(3, allEdgesOneDeep.getNeighbors(new Position(1,1)));
        Assert.assertNotEquals(3, allEdgesOneDeep.getNeighbors(new Position(1,DIMENSION - 2)));
        Assert.assertNotEquals(3, allEdgesOneDeep.getNeighbors(new Position(DIMENSION - 2,1)));
        Assert.assertNotEquals(3, allEdgesOneDeep.getNeighbors(new Position(DIMENSION - 2,DIMENSION - 2)));

        Assert.assertEquals(3, fourBlocksEachCorner.getNeighbors(new Position(1,1)));
        Assert.assertEquals(3, fourBlocksEachCorner.getNeighbors(new Position(1,DIMENSION - 2)));
        Assert.assertEquals(3, fourBlocksEachCorner.getNeighbors(new Position(DIMENSION - 2,1)));
        Assert.assertEquals(3, fourBlocksEachCorner.getNeighbors(new Position(DIMENSION - 2,DIMENSION - 2)));

        fourBlocksEachCorner.getPosition(new Position(0,0)).setState(false);
        fourBlocksEachCorner.getPosition(new Position(0,DIMENSION - 1)).setState(false);
        fourBlocksEachCorner.getPosition(new Position(DIMENSION - 1,0)).setState(false);
        fourBlocksEachCorner.getPosition(new Position(DIMENSION - 1,DIMENSION - 1)).setState(false);

        Assert.assertEquals(2, fourBlocksEachCorner.getNeighbors(new Position(1,1)));
        Assert.assertEquals(2, fourBlocksEachCorner.getNeighbors(new Position(1,DIMENSION - 2)));
        Assert.assertEquals(2, fourBlocksEachCorner.getNeighbors(new Position(DIMENSION - 2,1)));
        Assert.assertEquals(2, fourBlocksEachCorner.getNeighbors(new Position(DIMENSION - 2,DIMENSION - 2)));

    }

    @Test
    public void test_LiveCell_MoreThanThreeNeighbors(){
        Assert.assertEquals(5, allEdgesOneDeep.getNeighbors(new Position(1,1)));
        Assert.assertEquals(5, allEdgesOneDeep.getNeighbors(new Position(1,DIMENSION - 2)));
        Assert.assertEquals(5, allEdgesOneDeep.getNeighbors(new Position(DIMENSION - 2,1)));
        Assert.assertEquals(5, allEdgesOneDeep.getNeighbors(new Position(DIMENSION - 2,DIMENSION - 2)));
    }

    @Test
    public void test_DeadCell_ThreeNeighbors(){
        Assert.assertNotEquals(3, allEdgesOneDeep.getNeighbors(new Position(1,1)));
        Assert.assertNotEquals(3, allEdgesOneDeep.getNeighbors(new Position(1,DIMENSION - 2)));
        Assert.assertNotEquals(3, allEdgesOneDeep.getNeighbors(new Position(DIMENSION - 2,1)));
        Assert.assertNotEquals(3, allEdgesOneDeep.getNeighbors(new Position(DIMENSION - 2,DIMENSION - 2)));

        Assert.assertEquals(3, fourBlocksEachCorner.getNeighbors(new Position(1,1)));
        Assert.assertEquals(3, fourBlocksEachCorner.getNeighbors(new Position(1,DIMENSION - 2)));
        Assert.assertEquals(3, fourBlocksEachCorner.getNeighbors(new Position(DIMENSION - 2,1)));
        Assert.assertEquals(3, fourBlocksEachCorner.getNeighbors(new Position(DIMENSION - 2,DIMENSION - 2)));

        allEdgesOneDeep.getPosition(20).setState(false);
        allEdgesOneDeep.getPosition(70).setState(false);
        allEdgesOneDeep.getPosition(2).setState(false);
        allEdgesOneDeep.getPosition(92).setState(false);
        allEdgesOneDeep.getPosition(7).setState(false);
        allEdgesOneDeep.getPosition(97).setState(false);
        allEdgesOneDeep.getPosition(29).setState(false);
        allEdgesOneDeep.getPosition(79).setState(false);

        Assert.assertEquals(3, allEdgesOneDeep.getNeighbors(new Position(1,1)));
        Assert.assertEquals(3, allEdgesOneDeep.getNeighbors(new Position(1,DIMENSION - 2)));
        Assert.assertEquals(3, allEdgesOneDeep.getNeighbors(new Position(DIMENSION - 2,1)));
        Assert.assertEquals(3, allEdgesOneDeep.getNeighbors(new Position(DIMENSION - 2,DIMENSION - 2)));
    }

}
