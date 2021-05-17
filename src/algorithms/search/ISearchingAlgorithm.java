package algorithms.search;

/**
 * interface of the searching algorithms
 */
public interface ISearchingAlgorithm {

    Solution solve(ISearchable state);
    int getNumberOfNodesEvaluated();
    String getName();
}
