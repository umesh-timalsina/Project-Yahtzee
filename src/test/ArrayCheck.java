/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author tumesh
 */
public class ArrayCheck {
    public static void main(String[] args) {
        int [] arr = {2, 3, 3, 4, 5};
        int count = 0;
        for(int i = 0; i < arr.length-1; i++){
            if(arr[i+1]- arr[i] == 1)
                count++;
        }
        System.out.println(count);
    }
}
