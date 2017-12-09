/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circularLinkedChain;

/**
 *
 * @author Umesh,Nour,Mahdi
 */
public interface CircularLinkedInterface<T> {
    /**
     * Adds a new entry to this chain
     * @param anEntry the object to be added as a new entry 
     * @return true if the addition is successful, or false if not 
     */
    public boolean add(T anEntry);
    /**
     * Get the next nod in the chain
     * @return T the next Object 
     */
    public T getNext();
}
