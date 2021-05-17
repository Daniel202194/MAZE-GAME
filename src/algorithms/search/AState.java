package algorithms.search;

import java.io.Serializable;

/**
 * This class is abstract class that implements Comparable interface
 * This class represent the state of the problem
 */
public abstract class AState implements Comparable, Serializable {
    private String stateName;
    private double cost;
    private AState cameFrom;

    /**
     * This is constructor of state
     * This function get name and set the name of the state
     * @param stateName
     */
    public AState(String stateName){
        if(stateName != null)
            this.stateName = stateName;
        else
            this.stateName = "";
        this.cameFrom=null;
    }

    /**
     * This funcion get object and return true if it the same object like this
     * otherwise return false
     * @param o - object
     * @return is the same state
     */
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() != o.getClass() || this==null) return false;
        else {
            boolean sameState=this.toString().equals(((AState)o).toString());
            return sameState;
        }
    }

    /**
     *
     * @return the hash code of the string
     */
    @Override
    public int hashCode() {
        return stateName != null ? stateName.hashCode() : 0;
    }

    /**
     *
     * @return string of the searching algorithm name
     */
    public String toString(){
        return this.stateName;
    }

    /**
     *  This funcion get cost of state and set the state with this cost
     * @param cost
     */
    public void setCost(double cost){
        this.cost = cost;
    }

    /**
     * This function get state that parent of this and set parent of this(cameFrom) to be the given state
     * @param cameFrom
     */
    public void setCameFrom(AState cameFrom){
        this.cameFrom = cameFrom;
    }

    /**
     *
     * @return the cost of this state
     */
    public double getCost(){
        return this.cost;
    }

    /**
     *
     * @return the parent of the state
     */
    public AState getCameFrom(){
        return this.cameFrom;
    }

    /**
     * This funcion get object and compare between this and the object following the cost of the state
     * @param o
     * @return who is bigger
     */
    public int compareTo(Object o){
        if (o != null)
            return (int) (this.cost - ((AState) o).cost);
        return -1;
    }

    public String getStateName() {
        return stateName;
    }
}
