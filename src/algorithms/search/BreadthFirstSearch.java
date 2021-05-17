package algorithms.search;
import java.util.*;


/**
 * Breadth First Search (BFS) algorithm class , this class extends ASearchingAlgorithm
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {

    /**
     * constructor
     * set the algorithm name and number of evaluated nodes to be zero
     */
    public BreadthFirstSearch() {
        this.algorithmName = "BFS search";
        this.numOfEvaluatedNodes = 0;
    }
    /**
     * This function get problem (ISearchable) and solve it
     * if the given problem is null the function return null
     * @param maze
     * @return solution (all the path) to the given problem
     */
    @Override
    public Solution solve(ISearchable maze) {
        if(maze == null)
            return null;
        AState startState = maze.getStartState();
        AState goalState = maze.getGoalState();
        if(startState==null || goalState==null)
            return null;
        AState currentState = startState;
        Map<String,Boolean> visited = new HashMap<>();
        Queue<AState> Frontier = new LinkedList<>();
        Frontier.add(startState);
        ArrayList<AState> successors;
        visited.put(currentState.toString(),true);
        while (!(Frontier.isEmpty())){
            currentState = Frontier.remove();
            successors = maze.getAllSuccessors(currentState);
            if(goalState.equals(currentState)){
                return new Solution(currentState);
            }
            for(AState successor : successors){
                if(!(visited.containsKey(successor.toString()))){
                    visited.put(successor.toString(),true);
                    Frontier.add(successor);
                }
            }
            this.numOfEvaluatedNodes++;
        }
        return new Solution();
    }
}
