package algorithms.mazeGenerators;

public interface IMazeGenerator {
    /**
     *
     * @param colNum tells number of column in the maze
     * @param rowNum tells number of rows in the maze
     * @return The maze with given size of row and column
     */
    Maze generate(int rowNum,int colNum);

    /**
     *
     * @param colNum
     * @param rowNum
     * @return The time it tooks to generate maze with given rows number and column number
     */
    long measureAlgorithmTimeMillis(int colNum,int rowNum);
}
