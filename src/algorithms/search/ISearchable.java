package algorithms.search;


import java.util.ArrayList;
/**
 * Interface to implement for searchable problem
 */
public interface ISearchable {
    MazeState getStartState();
    MazeState getGoalState();
    ArrayList<AState> getAllSuccessors(AState state);
}
