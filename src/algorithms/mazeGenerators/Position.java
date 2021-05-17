package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * This class represent position(row,column) in the maze
 */
public class Position implements Serializable {
    protected int rowPosition;
    protected int colPosition;

    /**
     * This is constructor that get row index and column index and set it to the position
     * @param rowIndex
     * @param colIndex
     */
    public Position(int rowIndex, int colIndex){
        this.rowPosition=rowIndex;
        this.colPosition=colIndex;
    }

    /**
     *
     * @return int - row index of the position
     */
    public int getRowIndex(){
        return this.rowPosition;
    }

    /**
     *
     * @return int - column index of the position
     */
    public int getColumnIndex(){
        return this.colPosition;
    }

    /**
     * This function convert the position to string represent
     * @return String of the Position
     */
    public String toString(){
        return "{"+ this.getRowIndex()+","+ this.getColumnIndex()+"}";
    }


}
