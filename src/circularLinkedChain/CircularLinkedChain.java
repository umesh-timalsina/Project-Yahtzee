/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circularLinkedChain;

/**
 *
 * @author SIU854422010
 */
public class CircularLinkedChain<T> implements CircularLinkedInterface<T>{

    private Node firstNode;
    private Node lastNode;
    private Node currentNode;
    
    public CircularLinkedChain(){
    }
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
   
    @Override
    public T getNext(){
        T data = null;
        if(this.firstNode != null){
            this.currentNode = this.currentNode.getNextNode();
            data = this.currentNode.getData();
        }// end if
        return data;
    }// end getNext
    
    public boolean isIncompleteCycle(){
        return currentNode.getNextNode() != this.firstNode;
    }
    
    public void printList(){
        Node currentNode = this.firstNode;
        do{
            System.out.println(currentNode.getData());
            currentNode = currentNode.getNextNode();
        }while(currentNode != this.firstNode);
        // end while
    }// end printList
    
    private class Node{
        private T data;
        private Node nextNode;
        
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
