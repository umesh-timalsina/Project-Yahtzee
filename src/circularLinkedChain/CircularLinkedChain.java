/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circularLinkedChain;

/**A class of Circular linked nodes, that allow for detecting if 
 * a cycle is completed, add an entry to the chain,and get the next object.
 * @author Umesh,Nour,Mahdi
 * @param <T> generic data type
 */
public class CircularLinkedChain<T> implements CircularLinkedInterface<T>{

    private Node firstNode;
    private Node lastNode;
    private Node currentNode;
    
    /**
     *Default constructor
     */
    public CircularLinkedChain(){
    }

    /**
     * Adds a new entry to this chain
     * @param anEntry the object to be added as a new entry 
     * @return true if the addition is successful, or false if not 
     */
    @Override
    public boolean add(T anEntry) {
        Node newNode = new Node(anEntry);
        if(this.firstNode == null){
            this.lastNode = newNode;
            this.currentNode = newNode;
        }// sets the lastNode to point to the newNode Created
        newNode.setNextNode(this.firstNode);
        this.firstNode = newNode;
        this.lastNode.setNextNode(this.firstNode);
        return true;
    }// end add
   
    /**
     * Get the next node in the chain
     * @return T the next Object 
     */
    @Override
    public T getNext(){
        T data = null;
        if(this.firstNode != null){
            this.currentNode = this.currentNode.getNextNode();
            data = this.currentNode.getData();
        }// end if
        return data;
    }// end getNext
    
    /**
     * A cycle is completed when the currentNode equals the firstNode
     * @return boolean True if completed, false otherwise
     */
    public boolean isIncompleteCycle(){
        return currentNode.getNextNode() != this.firstNode;
    }
    
    /**
     *Print the data of all nodes in the chain
     */
    public void printList(){
        Node currentNode = this.firstNode;
        do{
            System.out.println(currentNode.getData());
            currentNode = currentNode.getNextNode();
        }while(currentNode != this.firstNode);
        // end while
    }// end printList
    
    /*Private inner class of Node
    */
    private class Node{
        private T data; // entry
        private Node nextNode;// link to next Node
        
        public Node(){
            this(null, null);
        }// end constructor
        public Node(T data){
            this(data, null);
        }// end constructor
        public Node(T data, Node nextNode){
            this.data = data;
            this.nextNode = nextNode;
        }// end Node
        public T getData() {
            return data;
        }// end getData
        public void setData(T data) {
            this.data = data;
        }// end setData
        public Node getNextNode() {
            return nextNode;
        }// end getNextNode
        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }// end setNextNode
    }// end class Node
}
