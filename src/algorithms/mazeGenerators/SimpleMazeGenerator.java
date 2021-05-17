package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator implements Serializable {
    @Override
    /**
     *
     * @param colNum tells number of column in the maze
     * @param rowNum tells number of rows in the maze
     * @return The maze with given size of row and column
     */
    public Maze generate(int rowNum, int colNum) {
        Random rand = new Random();
        Maze simpleMaze = new Maze(rowNum,colNum);
        for(int row=0;row<rowNum;row++){
            for (int col=0;col<colNum;col++){
                simpleMaze.deleteWall(row,col);
            }
        }
        for(int row=0; row < rowNum; row++){
            for(int col=0; col < colNum; col++){
                int random=rand.nextInt(2);
                if(random==1)
                    simpleMaze.setWall(row,col);
            }
        }
        simpleMaze.startPositionSelect();
        createPath(simpleMaze.getStartPosition(),rowNum,colNum,simpleMaze);
        return simpleMaze;
    }

    private void createPath(Position startPos, int rowNum, int colNum, Maze maze){
        if(maze==null)
            throw new NullPointerException("The maze not declared or null");
        int startRowPos = startPos.getRowIndex();
        int startColumnPos = startPos.getColumnIndex();
        if((startRowPos==rowNum-1 ||startRowPos==0) && (startColumnPos!=0 && startColumnPos!=colNum-1)){
            for (int curentRow=0; curentRow<rowNum; curentRow++){
                maze.deleteWall(curentRow,startColumnPos);
            }
            if(startRowPos==0)
                maze.setGoalPosition(rowNum-1,startColumnPos);
            else
                maze.setGoalPosition(0,startColumnPos);
        }
        else if((startColumnPos==0 || startColumnPos==colNum-1) && (startRowPos!=rowNum-1 && startRowPos!=0) ){
            for (int curentCol=0; curentCol<colNum; curentCol++){
                maze.deleteWall(startRowPos,curentCol);
            }
            if(startColumnPos==0)
                maze.setGoalPosition(startRowPos,colNum-1);
            else
                maze.setGoalPosition(startRowPos,0);
        }
        else{
            if(startColumnPos==0 && startRowPos==0){
                for (int curentRow=0; curentRow<rowNum; curentRow++){
                    maze.deleteWall(curentRow,startColumnPos);
                }
                maze.deleteWall(rowNum-1,startColumnPos+1);
                maze.setGoalPosition(rowNum-1,startColumnPos+1);
            }
            else if(startColumnPos==0 && startRowPos==rowNum-1){
                for (int currentRow=0; currentRow<rowNum; currentRow++){
                    maze.deleteWall(currentRow,startColumnPos);
                }
                maze.deleteWall(0,startColumnPos+1);
                maze.setGoalPosition(0,startColumnPos+1);
            }
            else if(startColumnPos==colNum-1 && startRowPos==0){
                for (int currentRow=0; currentRow<rowNum; currentRow++){
                    maze.deleteWall(currentRow,startColumnPos);
                }
                maze.deleteWall(rowNum-1,startColumnPos-1);
                maze.setGoalPosition(rowNum-1,startColumnPos-1);
            }
            else if(startColumnPos==colNum-1 && startRowPos==rowNum-1){
                for (int currentRow=0; currentRow<colNum; currentRow++){
                    maze.deleteWall(currentRow,startColumnPos);
                }
                maze.deleteWall(0,startColumnPos-1);
                maze.setGoalPosition(0,startColumnPos-1);
            }
        }

    }
}
