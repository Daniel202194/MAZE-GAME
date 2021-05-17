package algorithms.search;

/**
 * This class is abstract class that implements ISearchingAlgorithm
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected String algorithmName;
    protected int numOfEvaluatedNodes;


    public ASearchingAlgorithm(){
        this.algorithmName = "";
        this.numOfEvaluatedNodes = 0;
    }

    /**
     *
     * @return number of the nodes the algorithm passed till find solution
     */
    public int getNumberOfNodesEvaluated(){
        return this.numOfEvaluatedNodes;
    }

    /**
     *
     * @return searching algorithm name
     */
    public String getName(){
        return this.algorithmName;
    }
}
