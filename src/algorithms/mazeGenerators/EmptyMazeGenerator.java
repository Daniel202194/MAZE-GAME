package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * This class extend abstract class AMazeGenerator
 * this class can generate empty maze (maze without walls)
 */
public class EmptyMazeGenerator extends AMazeGenerator implements Serializable {
    @Override
    /**
     *
     * @param colNum tells number of column in the maze
     * @param rowNum tells number of rows in the maze
     * @return The maze with given size of row and column without walls
     */
    public Maze generate(int rowNum, int colNum) {
        if(colNum < 3 || rowNum < 3)
            return null;
        Maze emptyMaze = new Maze(rowNum,colNum);
        for(int row=0;row<rowNum;row++){
            for(int col=0;col<colNum;col++){
                emptyMaze.deleteWall(row,col);
            }
        }
        emptyMaze.startPositionSelect();
        emptyMaze.DefineGoalPosition();
        return emptyMaze;
    }
}
