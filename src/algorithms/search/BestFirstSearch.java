package algorithms.search;

import java.util.*;

/**
 * Best First Search algorithm class , this class extends ASearchingAlgorithm
 */
public class BestFirstSearch extends ASearchingAlgorithm {

    /**
     * constructor
     * set the algorithm name and number of evaluated nodes to be zero
     */
    public BestFirstSearch() {
        this.algorithmName= "Best First Search";
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

        Map<String, Boolean> visited = new HashMap<>();
        PriorityQueue<AState> open = new PriorityQueue<>();

        AState startState = maze.getStartState();
        AState goalState = maze.getGoalState();
        if(startState==null || goalState==null)
            return null;
        open.add(startState);
        visited.put(startState.toString(),true);

        AState currentState;
        while(!open.isEmpty()){
            currentState = open.remove();
            if(currentState.equals(goalState)){
                return new Solution(currentState);
            }
            ArrayList<AState> successors = maze.getAllSuccessors(currentState);
            for(AState successor : successors){
                if(!(visited.containsKey(successor.toString()))){
                    visited.put(successor.toString(),true);
                    successor.setCost(successor.getCost()+currentState.getCost());
                    open.add(successor);
                }
            }
            this.numOfEvaluatedNodes++;
        }
        return new Solution();
    }


}
