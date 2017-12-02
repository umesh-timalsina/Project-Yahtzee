/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import circularLinkedChain.CircularLinkedChain;
/**
 *
 * @author tumesh
 */
public class ArrayCheck {
    public static void main(String[] args) {
        CircularLinkedChain<String> coll1= new CircularLinkedChain<>();
        coll1.add("Nepal");
        coll1.add("Ko Chori");
        coll1.add("Bang Bang");
        coll1.printList();
        System.out.println(coll1.getNext());
        System.out.println(coll1.getNext());
        System.out.println(coll1.getNext());

    }
}
