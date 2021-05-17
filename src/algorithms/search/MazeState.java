package algorithms.search;

import algorithms.mazeGenerators.Position;
import  java.io.Serializable;

/**
 * MazeState class , this class extends abstract class AState
 */
public class MazeState extends AState implements Serializable {
    private Position position;

    /**
     * constructor
     *
     * @param stateName
     * @param position
     */
    public MazeState(String stateName, Position position) {
        super(stateName);
        if (position != null)
            this.position = position;
        else
            this.position = null;
    }


//    public MazeState( MazeState m) {
//        super(m.getStateName());
//        this.position = m.getPosition();
//    }

//    public Position getPosition() {
//        return position;
//    }

    /**
     * @return row of the state
     */
    public int getRow() {
        if (this.position == null)
            return -1;
        return this.position.getRowIndex();
    }

    /**
     * @return column of the state
     */
    public int getColumn() {
        if (this.position == null)
            return -1;
        return this.position.getColumnIndex();
    }

//    public String toString() {
//        if (this.position != null)
//            return "(" + this.position.getColumnIndex() + "," + this.position.getRowIndex() + ")";
//        else return "no position";
//
//    }
}