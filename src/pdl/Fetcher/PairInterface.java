package pdl.Fetcher;

public interface PairInterface {

    /**

     * Checks the two objects for equality by delegating to their respective

     * @param o the object to which this one is to be checked for equality

     * @return true if the underlying objects of the Pair are both considered

     *         equal

     */

    boolean equals(Object o);



    /**

     * Compute a hash code using the hash codes of the underlying objects

     *

     * @return a hashcode of the Pair

     */

    @Override

    int hashCode();



}
