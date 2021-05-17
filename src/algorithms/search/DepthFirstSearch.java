package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


/**
 * Depth First Search (DFS) algorithm class , this class extends ASearchingAlgorithm
 */
public class DepthFirstSearch extends ASearchingAlgorithm {
    /**
     * constructor
     * set the algorithm name and number of evaluated nodes to be zero
     */
    public DepthFirstSearch() {
        this.algorithmName = "DFS search";
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
        Stack<AState> open = new Stack<>();
        Map<String,Boolean> visited = new HashMap<>();
        ArrayList<AState> successors;
        AState startState = maze.getStartState();
        AState goalState = maze.getGoalState();
        if(startState==null || goalState==null)
            return null;
        open.push(startState);

        AState currentState;
        while(!open.empty()){
            currentState = open.pop();
            if(currentState.equals(goalState)){
                return new Solution(currentState);
            }
            successors = maze.getAllSuccessors(currentState);
            for(AState successor : successors){
                if(!(visited.containsKey(successor.toString()))){
                    if(successor.equals(goalState)){
                        return new Solution(successor);
                    }
                    visited.put(successor.toString(),true);
                    open.push(successor);
                }
            }
            this.numOfEvaluatedNodes++;

        }
        return new Solution();
    }
}
