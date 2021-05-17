
package algorithms.mazeGenerators;
import com.sun.prism.shader.Texture_ImagePattern_AlphaTest_Loader;

import java.io.Serializable;
import java.util.Random;

/**
 * class that representing the maze
 */
public class Maze implements Serializable {
    private int rows;
    private int cols;
    private int theMaze[][];
    private Position start;
    private Position goal;

    /**
     * Maze constractor , build the maze with given size of rows and column,
     * the columns number and rows number must be at least 3 , otherwise maze
     * reference to null
     *
     * @param colNum
     * @param rowNum
     */
    public Maze(int rowNum, int colNum) {
        this.cols = colNum;
        this.rows = rowNum;
        if (colNum < 2 || rowNum < 2)
            this.theMaze = null;
        else
            theMaze = new int[rowNum][colNum];
        start = null;
        goal = null;
    }

    /**
     * Maze constractor , build the maze with by the array the function get,each element in the array hlpes to build the new maze.
     * reference to null
     *
     * @param array
     *
     */
    public Maze(byte[] array) {
        int rowsNum = (int)array[0]+(int)array[1]+(int)array[2]+(int)array[3]+512;
        int colsNum = (int)array[4]+(int)array[5]+(int)array[6]+(int)array[7]+512;
        int startRow = (int)array[8]+(int)array[9]+(int)array[10]+(int)array[11]+512 ;
        int startCol = (int)array[12]+(int)array[13]+(int)array[14]+(int)array[15]+512;
        int goalRow = (int)array[16]+(int)array[17]+(int)array[18]+(int)array[19]+512;
        int goalCol = (int)array[20]+(int)array[21]+(int)array[22]+(int)array[23]+512;
        this.rows = rowsNum;
        this.cols = colsNum;
        this.theMaze = createMaze(array);
        this.setStartPosition(startRow,startCol);
        this.setGoalPosition(goalRow,goalCol);
    }
    /**
     * private function,remove element from one array to another.
     * reference to null
     *
     * @param array
     *
     */

    private int[][] createMaze(byte[] array) {
        int count = 24;
        int[][] maze = new int[this.rows][this.cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = array[count];
                count++;
            }
        }
        return maze;
    }

    /**
     * This function define randomly start position on 1 of the 4 edge
     * of the maze
     */
    public void startPositionSelect() {
        Random row = new Random();
        Random column = new Random();
        int rowIndex = row.nextInt(this.rows);
        int colIndex;
        if (rowIndex != 0 && rowIndex != this.rows - 1) {
            colIndex = column.nextInt(2);
            if (colIndex == 0)
                start = new Position(rowIndex, 0);
            else
                start = new Position(rowIndex, this.cols - 1);
        } else if (rowIndex == this.rows - 1) {
            colIndex = column.nextInt(this.cols);
            start = new Position(rowIndex, colIndex);
        } else {
            colIndex = column.nextInt(this.cols);
            start = new Position(0, colIndex);
        }
    }

    /**
     * This function get row and column and return 'cell value' of the maze in the given row,column position.
     * if row and col not in the maze limit it returns  -1
     * otherwise return 1(wall) or 0(path)
     *
     * @param row
     * @param col
     * @return int - cell value
     */
    public int getCellValue(int row, int col) {
        if (row < 0 || col < 0 || row > this.rows - 1 || col > this.cols - 1 || this.theMaze == null)
            return -1;
        return this.theMaze[row][col];
    }

    /**
     * Set goal position with given row index and column index
     *
     * @param row
     * @param col
     */
    public void setGoalPosition(int row, int col) {
        this.goal = new Position(row, col);
    }

    public void setStartPosition(int row, int col) {
        this.start = new Position(row, col);
    }

    /**
     * This function returns columns number of the maze
     *
     * @return int - maze columns number
     */
    public int getColNumbers() {
        return this.cols;
    }

    /**
     * This function returns rows number of the maze
     *
     * @return int - maze rows number
     */
    public int getRowNumbers() {
        return this.rows;
    }

    /**
     * This function get row index and column index and if the given column and row is in
     * the limit of the maze size the function set in the cell value=1 (wall) in
     * the given row,column position
     *
     * @param row
     * @param col
     */
    protected void setWall(int row, int col) {
        if (row < 0 || col < 0)
            throw new IndexOutOfBoundsException("Can not set: 'row or col is out of the maze' ");
        if (row >= this.rows || col >= this.cols)
            throw new IndexOutOfBoundsException("Can not set: 'row or col is out of the maze' ");
        if (this.theMaze == null)
            throw new NullPointerException("The maze is not declared or reference to null");
        this.theMaze[row][col] = 1;
    }

    /**
     * This function get row index and column index and if the given column and row is in
     * the limit of the maze size the function set in the cell value=0 (delete wall) in
     * the given row,column position
     *
     * @param row
     * @param col
     */
    protected void deleteWall(int row, int col) {
        if (row < 0 || col < 0)
            throw new IndexOutOfBoundsException("Can not delete: 'row or col is out of the maze' ");
        if (row >= this.rows || col >= this.cols)
            throw new IndexOutOfBoundsException("Can not delete: 'row or col is out of the maze' ");
        if (this.theMaze == null)
            throw new NullPointerException("The maze is not declared or reference to null");
        this.theMaze[row][col] = 0;
    }


    /**
     * This function return start Position
     *
     * @return Position
     */
    public Position getStartPosition() {
        return this.start;
    }

    /**
     * This function return Goal position
     *
     * @return
     */
    public Position getGoalPosition() {
        return this.goal;
    }

    /**
     * This function print the maze when '1' is wall , '0' is path
     * 'S' is the start position and 'E' is the goal position
     */
    public void print() {
        for (int row = 0; row < getRowNumbers(); row++) {
            for (int col = 0; col < getColNumbers(); col++) {
                if (row == start.getRowIndex() && col == start.getColumnIndex()) {
                    System.out.print("S");
                } else if (row == goal.getRowIndex() && col == goal.getColumnIndex()) {
                    System.out.print("E");
                } else {
                    System.out.print(String.format("%s", getCellValue(row, col)));
                }
            }
            System.out.println("");

        }
    }

    /**
     * This function define randomly but not on the same edge
     * the goal position of the maze
     */
    public void DefineGoalPosition() {

        int startRow = getStartPosition().rowPosition;
        int startCol = getStartPosition().colPosition;
        Random rand = new Random();
        if (this.rows < 3 || this.cols < 3) {
            if (this.rows < 3 && this.cols >= 3) {
                if (startRow == 0) {
                    for (int col = 0; col < this.cols; col++) {
                        if (getCellValue(1, col) == 0) {
                            setGoalPosition(1, col);
                            return;
                        }
                    }
                } else if (startRow == 1) {
                    for (int col = 0; col < this.cols; col++) {
                        if (getCellValue(0, col) == 0) {
                            setGoalPosition(0, col);
                            return;
                        }
                    }
                }
            } else if (this.rows >= 3 && this.cols < 3) {
                if (startCol == 0) {
                    for (int row = 0; row < this.rows; row++) {
                        if (getCellValue(row, 1) == 0) {
                            setGoalPosition(row, 1);
                            return;
                        }
                    }
                } else if (startCol == 1) {
                    for (int row = 0; row < this.rows; row++) {
                        if (getCellValue(row, 0) == 0) {
                            setGoalPosition(row, 0);
                            return;
                        }
                    }
                }
            } else {
                if (startCol == 0 & startRow == 0) {
                    if (getCellValue(1, 1) == 0)
                        setGoalPosition(1, 1);
                    else if (getCellValue(1, 0) == 0)
                        setGoalPosition(1, 0);
                    else
                        setGoalPosition(0, 1);
                    return;
                } else if (startCol == 1 & startRow == 0) {
                    if (getCellValue(1, 0) == 0)
                        setGoalPosition(1, 0);
                    else if (getCellValue(1, 1) == 0)
                        setGoalPosition(1, 1);
                    else
                        setGoalPosition(0, 0);
                    return;
                } else if (startCol == 0 & startRow == 1) {
                    if (getCellValue(0, 1) == 0)
                        setGoalPosition(0, 1);
                    else if (getCellValue(1, 1) == 0)
                        setGoalPosition(1, 1);
                    else
                        setGoalPosition(0, 0);
                    return;
                } else if (startCol == 1 & startRow == 1) {
                    if (getCellValue(0, 0) == 0)
                        setGoalPosition(0, 0);
                    else if (getCellValue(1, 0) == 0)
                        setGoalPosition(1, 0);
                    else
                        setGoalPosition(0, 1);
                    return;
                }
            }
        }
        int colIndex, rowIndex;

        setGoalPosition(0, 0);

        if (startRow == 0) {
            colIndex = rand.nextInt(getColNumbers() - 2) + 1;
            while (getCellValue(getRowNumbers() - 1, colIndex) != 0) {
                colIndex = rand.nextInt(getColNumbers() - 2) + 1;
            }
            setGoalPosition(getRowNumbers() - 1, colIndex);
        } else if (startRow == getRowNumbers() - 1) {
            colIndex = rand.nextInt(getColNumbers() - 2) + 1;
            while (getCellValue(0, colIndex) != 0) {
                colIndex = rand.nextInt(getColNumbers() - 2) + 1;
            }
            setGoalPosition(0, colIndex);
        } else if (startCol == 0) {
            rowIndex = rand.nextInt(getRowNumbers() - 2) + 1;
            while (getCellValue(rowIndex, getColNumbers() - 1) != 0) {
                rowIndex = rand.nextInt(getRowNumbers() - 2) + 1;
            }
            setGoalPosition(rowIndex, getColNumbers() - 1);
        } else if (startCol == getColNumbers() - 1) {
            rowIndex = rand.nextInt(getRowNumbers() - 2) + 1;
            while (getCellValue(rowIndex, 0) != 0) {
                rowIndex = rand.nextInt(getRowNumbers() - 2) + 1;
            }
            setGoalPosition(rowIndex, 0);
        }
    }

    /**
     * This function get byte array and convert the byte element to byte number without  twoscompliment
     *
     *

     */
    private void enterRowDetails(byte[] detailsOnMaze) {
        int counter = 0;
        int rowsNum = getRowNumbers();
        while (rowsNum > 0) {
            if (rowsNum >= 255) {

                detailsOnMaze[counter] = (byte)127;
                rowsNum -= 255;
                counter++;
            }
            else if (rowsNum > 0) {

                detailsOnMaze[counter] = (byte) (rowsNum-128);
                rowsNum = 0;
                counter++;
            }
        }
    }

    /**
     * This function get byte array and convert the byte element to byte number without  twoscompliment
     *
     *

     */
    private void enterColumnDetails(byte[] detailsOnMaze){
        int counter = 4;
        int colsNum = getColNumbers();
        while (colsNum > 0) {
            if (colsNum >= 255) {
                detailsOnMaze[counter] = (byte)127;
                colsNum -= 255;
                counter++;
            } else if (colsNum > 0) {

                detailsOnMaze[counter] = (byte) (colsNum-128);
                colsNum = 0;
                counter++;
            }
        }

    }

    /**
     * This function get byte array and convert the byte element to byte number without  twoscompliment
     *
     *

     */
    private void enterStartRowPositionDetails(byte[] detailsOnMaze){
        int counter = 8;
        int startRow = getStartPosition().getRowIndex();
        while (startRow > 0) {
            if (startRow >= 255) {
                detailsOnMaze[counter] = (byte)127;
                startRow -= 255;
                counter++;
            } else if (startRow > 0) {
                detailsOnMaze[counter] = (byte)(startRow-128);
                startRow = 0;
                counter++;
            }
        }

    }

    /**
     * This function get byte array and convert the byte element to byte number without  twoscompliment
     *
     *

     */
    private void enterStartColumnPositionDetails(byte[] detailsOnMaze){
        int counter = 12;
        int startColumn = getStartPosition().getColumnIndex();
        while (startColumn > 0) {
            if (startColumn >= 255) {
                detailsOnMaze[counter] = (byte)127;
                startColumn -= 255;
                counter++;
            } else if (startColumn > 0) {
                detailsOnMaze[counter] = (byte) (startColumn-128);
                startColumn = 0;
                counter++;
            }
        }
    }

    /**
     * This function get byte array and convert the byte element to byte number without  twoscompliment
     *
     *

     */
    private void enterGoalRowPositionDetails(byte[] detailsOnMaze){
        int counter = 16;
        int goalRow = getGoalPosition().getRowIndex();
        while (goalRow > 0) {
            if (goalRow >= 255) {
                detailsOnMaze[counter] = (byte)127;
                goalRow -= 255;
                counter++;
            } else if (goalRow > 0) {
                detailsOnMaze[counter] = (byte) (goalRow-128);
                goalRow = 0;
                counter++;
            }
        }
    }

    /**
     * This function get byte array and convert the byte element to byte number without  twoscompliment
     *
     *

     */



    private void enterGoalColumnPositionDetails(byte[] detailsOnMaze){
        int counter = 20;
        int goalColumn = getGoalPosition().getColumnIndex();
        while (goalColumn > 0) {
            if (goalColumn >= 255) {
                detailsOnMaze[counter] = (byte)127;
                goalColumn -= 255;
                counter++;
            } else if (goalColumn > 0) {
                detailsOnMaze[counter] = (byte) (goalColumn-128);
                goalColumn = 0;
                counter++;
            }
        }
    }

    /**
     * This function turn array into byte array
     *
     *
     * @return byte array.
     */


    public byte[] toByteArray() {
        int counter = 24;
        int size = rows * cols + 24;
        byte[] detailsOnMaze = new byte[size];
        for (int i = 0; i < 24; i++) {
            detailsOnMaze[i] = (byte)-128;
        }
        enterRowDetails(detailsOnMaze);
        enterColumnDetails(detailsOnMaze);
        enterStartRowPositionDetails(detailsOnMaze);
        enterStartColumnPositionDetails(detailsOnMaze);
        enterGoalRowPositionDetails(detailsOnMaze);
        enterGoalColumnPositionDetails(detailsOnMaze);
        for (int i = 0; i < getRowNumbers(); i++) {
            for (int j = 0; j < getColNumbers(); j++) {
                detailsOnMaze[counter] = (byte) getCellValue(i,j);
                counter++;
            }
        }
        return  detailsOnMaze;
    }
}

