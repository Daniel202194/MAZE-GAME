package algorithms.mazeGenerators;
import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;

/**
 * class MyMazeGenerator , this class extends AMazeGenerator
 * this class represent maze that generated with prim's algorithm
 */


public class MyMazeGenerator extends AMazeGenerator implements Serializable {

    private ArrayList<Position> wallsList;
    @Override
    /**
     * This function generate the maze with given  size (columns,rows)
     * if it can not declare the maze return null otherwise return the maze
     * The cells value of the maze ('path and walls) chosen randomly
     * but the maze always has solution
     * @param colNum tells number of column in the maze
     * @param rowNum tells number of rows in the maze
     * @return Maze that generate randomly
     */
    public Maze generate(int rowNum, int colNum) {
        Maze myMaze = new Maze(rowNum, colNum);
        if(myMaze==null){
            return null;
        }
        if(colNum < 3 || rowNum < 3)
            return null;
        setWalls(myMaze);
        myMaze.startPositionSelect();
        Position start = myMaze.getStartPosition();
        int startRow = start.getRowIndex();
        int startColumn = start.getColumnIndex();
        myMaze.deleteWall(startRow,startColumn);

        wallsList = new ArrayList<>();
        addWalls(myMaze,wallsList,startRow,startColumn);

        Random rand = new Random();
        Position currentPos;
        int visitedNeighbors,currentPosRow,currentPosCol;
        while(wallsList.size()!=0){
            currentPos = wallsList.remove(rand.nextInt(wallsList.size()));
            currentPosRow = currentPos.getRowIndex();
            currentPosCol = currentPos.getColumnIndex();
            visitedNeighbors = getNumOfVisitedNeighbors(myMaze,currentPosRow,currentPosCol);

            if(visitedNeighbors<=1){
                myMaze.deleteWall(currentPosRow,currentPosCol);
                addWalls(myMaze,wallsList,currentPosRow,currentPosCol);
            }
        }
        myMaze.DefineGoalPosition();
        return myMaze;
    }

    /**
     * This function get the maze and a specific position(row,column)
     * and return the number of visited neighbors of the given position
     * @param myMaze
     * @param currentPosRow
     * @param currentPosCol
     * @return  int - number of visited neighbors of the given position
     */
    private int getNumOfVisitedNeighbors(Maze myMaze,int currentPosRow,int currentPosCol){
        int numOfVisitedNeighbors = 0;
        if(currentPosCol > 0){
            if(myMaze.getCellValue(currentPosRow,currentPosCol-1)==0){
                numOfVisitedNeighbors++;
            }
        }
        if(currentPosCol < myMaze.getColNumbers()-1){
            if(myMaze.getCellValue(currentPosRow,currentPosCol+1)==0){
                numOfVisitedNeighbors++;
            }
        }
        if(currentPosRow > 0){
            if(myMaze.getCellValue(currentPosRow-1,currentPosCol)==0){
                numOfVisitedNeighbors++;
            }
        }
        if(currentPosRow < myMaze.getRowNumbers()-1){
            if(myMaze.getCellValue(currentPosRow+1,currentPosCol)==0){
                numOfVisitedNeighbors++;
            }
        }
        return numOfVisitedNeighbors;
    }

    /**
     * Private function that get walls list ,maze and Position(row,column)
     * and add to the walls list all the walls the beside to the given positon
     * @param myMaze
     * @param wallsList
     * @param currentPosRow
     * @param currentPosCol
     */
    private void addWalls(Maze myMaze,ArrayList wallsList ,int currentPosRow, int currentPosCol){
        int leftValue,rightValue,downValue,upperValue;
        if(currentPosCol > 0){
            leftValue = myMaze.getCellValue(currentPosRow,currentPosCol-1);
            if(leftValue==1 ){
                wallsList.add(new Position(currentPosRow,currentPosCol-1));
            }
        }
        if(currentPosCol < myMaze.getColNumbers()-1 ){
            rightValue = myMaze.getCellValue(currentPosRow,currentPosCol+1);
            if(rightValue==1) {
                wallsList.add(new Position(currentPosRow,currentPosCol+1));
            }
        }
        if(currentPosRow > 0){
            upperValue = myMaze.getCellValue(currentPosRow-1,currentPosCol);
            if(upperValue==1) {
                wallsList.add(new Position(currentPosRow - 1, currentPosCol));
            }
        }
        if(currentPosRow < myMaze.getRowNumbers()-1  ){
            downValue = myMaze.getCellValue(currentPosRow+1,currentPosCol);
            if(downValue==1){
                wallsList.add(new Position(currentPosRow+1,currentPosCol));
            }
        }
    }

    /**
     * private function that set in every cell of the maze wall
     * @param myMaze
     */
    private void setWalls(Maze myMaze){
        for (int row = 0 ; row < myMaze.getRowNumbers() ; row++){
            for(int col = 0 ; col < myMaze.getColNumbers() ; col++){
                myMaze.setWall(row,col);
            }
        }
    }
}

