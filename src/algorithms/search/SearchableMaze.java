package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.*;

/**
 * This class is specific problem ,maze problem , this class implements ISearchable
 */
public class SearchableMaze implements ISearchable {
    private Maze myMaze;
    private MazeState startState;
    private MazeState goalState;

    /**
     * constructor
     * this function get Maze and do it searchable maze problem
     * @param myMaze
     */
    public SearchableMaze(Maze myMaze){
        this.myMaze = myMaze;
        if(myMaze != null){
            String start = myMaze.getStartPosition().toString();
            String end = myMaze.getGoalPosition().toString();
            this.startState = new MazeState(start,myMaze.getStartPosition());
            this.goalState = new MazeState(end,myMaze.getGoalPosition());

        }
        else{
            this.goalState = null;
            this.startState = null;
        }

    }

    /**
     *
     * @return start state of the maze
     */
    @Override
    public MazeState getStartState() {
        return  this.startState;
    }

    /**
     *
     * @return goal state of the maze
     */
    @Override
    public MazeState getGoalState() {
        return this.goalState;
    }

    /**
     *
     * @param state
     * @return all the successors of the given state
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState state) {
        ArrayList<AState> possibleStates = new ArrayList<>();
        Map<String,Boolean> visited = new HashMap<>();
        Position pos;
        MazeState curState;
        if(state != null){
            MazeState stateMaze = (MazeState)state;
            int stateRow = stateMaze.getRow();
            int stateColumn = stateMaze.getColumn();
            boolean leftState,rightState,upperState,downState;
            boolean leftUpState,rightUpState,leftDownState,rightDownState;
            leftState = isValid(stateMaze , stateRow , stateColumn-1);
            rightState = isValid(stateMaze , stateRow , stateColumn+1);
            upperState = isValid(stateMaze , stateRow-1 , stateColumn);
            downState = isValid(stateMaze , stateRow+1 , stateColumn);
            leftUpState = isValid(stateMaze ,stateRow-1 , stateColumn-1);
            rightUpState = isValid(stateMaze ,stateRow-1 , stateColumn+1);
            leftDownState = isValid(stateMaze ,stateRow+1 , stateColumn-1);
            rightDownState = isValid(stateMaze ,stateRow+1 , stateColumn+1);
            if(leftState ){
                pos = new Position(stateRow,stateColumn-1);
                if(!(visited.containsKey(pos.toString()))){
                    curState= new MazeState(pos.toString(),pos);
                    curState.setCameFrom(state);
                    curState.setCost(10);
                    possibleStates.add(curState);
                }

            }
            if(rightState){
                pos = new Position(stateRow,stateColumn+1);
                if(!(visited.containsKey(pos.toString()))){
                    curState= new MazeState(pos.toString(),pos);
                    curState.setCameFrom(state);
                    curState.setCost(10);
                    possibleStates.add(curState);
                }
            }
            if(upperState){
                pos = new Position(stateRow-1,stateColumn);
                if(!(visited.containsKey(pos.toString()))){
                    curState= new MazeState(pos.toString(),pos);
                    curState.setCameFrom(state);
                    curState.setCost(10);
                    possibleStates.add(curState);
                }
            }
            if(downState){
                pos = new Position(stateRow+1,stateColumn);
                if(!(visited.containsKey(pos.toString()))){
                    curState= new MazeState(pos.toString(),pos);
                    curState.setCameFrom(state);
                    curState.setCost(10);
                    possibleStates.add(curState);
                }
            }
            if(leftUpState && (leftState || upperState)){
                pos = new Position(stateRow-1,stateColumn-1);
                if(!(visited.containsKey(pos.toString()))){
                    curState= new MazeState(pos.toString(),pos);
                    curState.setCameFrom(state);
                    curState.setCost(15);
                    possibleStates.add(curState);
                }
            }
            if(leftDownState && (leftState || downState)){
                pos = new Position(stateRow+1,stateColumn-1);
                if(!(visited.containsKey(pos.toString()))){
                    curState= new MazeState(pos.toString(),pos);
                    curState.setCameFrom(state);
                    curState.setCost(15);
                    possibleStates.add(curState);
                }
            }
            if(rightDownState && (rightState || downState)){
                pos = new Position(stateRow+1,stateColumn+1);
                if(!(visited.containsKey(pos.toString()))){
                    curState= new MazeState(pos.toString(),pos);
                    curState.setCameFrom(state);
                    curState.setCost(15);
                    possibleStates.add(curState);
                }
            }
            if(rightUpState && (rightState || upperState)){
                pos = new Position(stateRow-1,stateColumn+1);
                if(!(visited.containsKey(pos.toString()))){
                    curState= new MazeState(pos.toString(),pos);
                    curState.setCameFrom(state);
                    curState.setCost(15);
                    possibleStates.add(curState);
                }
            }
        }
        return possibleStates;
    }

    /**
     * private function that get state , row and column
     * this function check if state in given row,column is valid successor to the given state
     * @param state
     * @param row
     * @param col
     * @return is valid successor
     */
    private boolean isValid(MazeState state,int row,int col){
        int rowsInMaze = this.myMaze.getRowNumbers();
        int colsInMaze = this.myMaze.getColNumbers();
        MazeState cameFrom = (MazeState) state.getCameFrom();
        int cameFromRow = -1;
        int cameFromColumn = -1;
        int cellValue;
        if(cameFrom != null){
            cameFromRow = cameFrom.getRow();
            cameFromColumn = cameFrom.getColumn();
        }
        if(cameFromRow == row && cameFromColumn == col)
            return false;
        else if(row < rowsInMaze && col < colsInMaze && row >= 0 && col >= 0){
            cellValue = this.myMaze.getCellValue(row,col);
            if(cellValue == 0)
                return true;
        }
        return false;
    }
}
