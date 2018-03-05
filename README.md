# game-of-life
Console program that evolves generations through the "game of life". 

## Code creates new board for new generation based on the following rules
1) Any live cell with fewer than two live neighbours dies (underpopulation)
2) Any live cell with two or three live neighbours lives on to the next generation (survival)
3) Any live cell with more than three live neighbours dies (overcrowding)
4) Any dead cell with exactly three live neighbours becomes a live cell (reproduction)

## To run:
To run with gradle wrapper type:

```
gradlew.bat run
```

or

```
gradlew run
```