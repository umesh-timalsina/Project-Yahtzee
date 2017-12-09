/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Random;

/**
 *Dices a class that manage rolling the dices for Yahtzee Game
 * @author tumesh,Nour,Mahdi
 */
public class Dices {
    private ArrayList<Random> diceSet;
    private static final int NUM_DICES = 5;
    private Integer [] rollResult;
    /**
     * Default constructor that initialize the class variables 
     */
    public Dices(){
        this.diceSet = new  ArrayList<Random>(NUM_DICES);
        this.rollResult = new Integer[NUM_DICES];
        for(int i = 0; i < NUM_DICES; i++){
            this.diceSet.add(new Random());
            this.rollResult[i] = new Integer(0);
        }// end for
    }// end Dices
    /**
     * Rolls the dices by randomly choosing 5 numbers between 1 and 6 
     * and store the results in the ArrayList rollResult  
     */
    public void roll(){
        for(int i = 0; i <  this.diceSet.size(); i++){
            this.rollResult[i] = diceSet.get(i).nextInt(6)+1;
        }// end for
        //System.out.println(this.getRollResultString());
    }// end roll
    /**
     * Gets the results of rolling five dices
     * @return Integer[] a copy of rollResults
     */
    public Integer [] getRollResult(){
        Integer [] temp = new Integer[NUM_DICES];
        for(int i = 0; i < temp.length; i++){
            temp[i] = new Integer(0);
        }// end for
        System.arraycopy(this.rollResult, 0, temp, 0, NUM_DICES);
        return temp;
    }// end getRollResult
    /**
     * Gets the results as a String 
     * @return String a copy of the rolled results
     */
    public String getRollResultString(){
        String temp = new String();
        for(int i = 0; i < this.rollResult.length; i++){
            if(i < this.rollResult.length-1){
                temp = temp.concat(this.rollResult[i].toString() + " - ");
            }// end if
            else{
                temp = temp.concat(this.rollResult[i].toString());
            }
        }
        return temp;
    }// end getRollResultString
}// end Dices
