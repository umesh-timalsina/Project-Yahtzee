/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author tumesh
 */
public class Round {
    private Player p;
    private Integer [] result;
    private static final int MAX_ROLLS = 3;
    private int numRolls;
    private Map<Integer, Integer> categoriesFrequency;
    
    public Round(String player_id){
        this.p = new Player(player_id);
        this.numRolls = 1;
        this.categoriesFrequency = new HashMap<>();
    }// end constructor
    
    public void rollDices(){
        this.p.rollDice();
        this.numRolls++;
        this.result = this.p.getResults();
        this.populateCategoriesFrequency();
    }// end rollDices
    
    public String getDiceRollResult(){
        String temp = new String();
        for(int i = 0; i < this.result.length; i++){
            temp = temp.concat(this.result[i].toString() + " - ");
        }
        return temp;
    }// end getDiceRollResult
    
    public boolean moreRollsPossible(){
        return this.numRolls < MAX_ROLLS;
    }// end moreRollsPossible
    
    public String getPlayerID(){
        return this.p.getPlayerID();
    }// end getPlayerID
    
    public String getRoundID(){
        return this.p.getRoundID();
    }// end getRoundID
    
    public ArrayList<String> analyzeResults(){
        ArrayList<String> resultAnalyzed = findFitCategories(this.result);
        Iterator resultAnalyzedIterator = resultAnalyzed.iterator();
        while(resultAnalyzedIterator.hasNext()){
            String temp = resultAnalyzedIterator.next().toString();
            if(this.getPlayer().getChoicesAlreadyScored().get(temp)){
                resultAnalyzedIterator.remove();
            }// end if
        }// end while
        return resultAnalyzed;
    }// end analyzeResults
    
    private void populateCategoriesFrequency(){
        LinkedList<Integer> temp = 
                        new LinkedList<>(Arrays.asList(result));
        Integer freq;
        for(int i = 1; i <= 6; i++){
            freq = Collections.frequency(temp, i);
            this.categoriesFrequency.put(i, freq);
        }// end for
    }// end populateCategoriesFrequency
    
    private ArrayList<String> findFitCategories(Integer [] res){
        ArrayList<String> resultAnalyzed = new ArrayList<>();
        
        /* Adding Categories to be scored */
        if(isYahtzee(res))      resultAnalyzed.add("Yahtzee");
        if(isFullHouse())       resultAnalyzed.add("Full House");
        if(isLargeStraight())   resultAnalyzed.add("Large Striaght");
        if(isSmallStraight())   resultAnalyzed.add("Small Striaght");
        if(isThreeOfAKind())    resultAnalyzed.add("Three of a Kind");
        if(isFourOfAKind())     resultAnalyzed.add("Four of a Kind");
        if(isChance())          resultAnalyzed.add("Chance");
        if(isUpperOne())        resultAnalyzed.add("Upper One");
        if(isUpperTwo())        resultAnalyzed.add("Upper Two");
        if(isUpperThree())      resultAnalyzed.add("Upper Three");
        if(isUpperFour())       resultAnalyzed.add("Upper Four");
        if(isUpperFive())       resultAnalyzed.add("Upper Five");
        if(isUpperSix())        resultAnalyzed.add("Upper Six");
       
        return resultAnalyzed;
    }// end findFitCategories
    
    public Player getPlayer(){
        return this.p;
    }// end getPlayer
    
    private boolean isYahtzee(Integer [] arr){
        boolean resultBool = true;
        Integer temp = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(!temp.equals(arr[i])){
                resultBool = false;
                break;
            }// end if
        }// end for
        return resultBool;
    }// end isYahtzee
    
    private boolean isFullHouse(){
        boolean countTwo = false, countThree = false;
        for(Integer anElement : this.categoriesFrequency.values()){
            if(anElement.equals(2)) countTwo = true;
            if(anElement.equals(3)) countThree = true;
        }// end if
        return (countTwo && countThree);
    }// end isFullHouse
    
    private boolean isLargeStraight(){
        int count = 0;
        boolean isLargeStraight = false;
        for(int i = 0; i < this.result.length-1; i++){
            if(this.result[i+1].equals(this.result[i] + 1)){
                count++;
            }// end if
        }// end for
        if(count == 4) isLargeStraight = true;
        return isLargeStraight;
    }// end isLargeStraight
    
    private boolean isSmallStraight(){
        boolean isSmallStraight = false;
        int count = 0;
        for(int i = 0; i < this.result.length-1; i++){
            if(this.result[i+1] - this.result[i] == 1)
                count++;
        }//end for
        if(count == 3) isSmallStraight = true;
        System.out.println(isSmallStraight);
        return isSmallStraight;
    }// end isSmallStraight
    
    private boolean isThreeOfAKind() {
        boolean isThreeOfAKind = false;
        for(Integer frequency : this.categoriesFrequency.values()){
            if(frequency == 3)   isThreeOfAKind = true;
        }// end if
        return isThreeOfAKind;
    }// end isThreeOfAKind
    
    private boolean isFourOfAKind(){
        boolean isFourOfAKind = false;
        for(Integer frequency : this.categoriesFrequency.values()){
            if(frequency == 4)  isFourOfAKind = true;
        }// end if
        return isFourOfAKind;
    }// end isFourOfAKind
    
    private boolean isUpperOne(){
        return (this.categoriesFrequency.get(1) > 0);
    }// end isUpperOne

    private boolean isUpperTwo(){
        return (this.categoriesFrequency.get(2) > 0);
    }// end isUpperTwo
    
    private boolean isUpperThree(){
        return (this.categoriesFrequency.get(3) > 0);
    }// end isUpperThree
    
    private boolean isUpperFour(){
        return (this.categoriesFrequency.get(4) > 0);
    }// end isUpperFour
    
    private boolean isUpperFive(){
        return (this.categoriesFrequency.get(5) > 0);
    }// end isUpperFive
    
    private boolean isUpperSix(){
        return (this.categoriesFrequency.get(6) > 0);
    }// end isUpperSix
    
    private boolean isChance(){
        return true;
    }// end isChance
    
    public void scoreRound(String choice){
        switch (choice) {
            case "Chance":
                this.p.setRoundScore(sum());
                break;
            case "Yahtzee":
                if(this.p.getYahtzeeCount() > 1){
                    this.p.setRoundScore(100);
                }// end if
                else{
                    this.p.setRoundScore(50);
                }// end else
                this.p.incrementYahtzeeCount();
                break;
            case "Full House":
                this.p.setRoundScore(25);
                break;
            case "Large Striaght":
                this.p.setRoundScore(40);
                break;
            case "Small Striaght":
                this.p.setRoundScore(30);
                break;
            case "Three of a Kind":
                this.p.setRoundScore(sum());
                break;
            case "Four of a Kind":
                this.p.setRoundScore(sum());
                break;
            case "Upper One":
                this.p.setRoundScore(1 * this.categoriesFrequency.get(1));
                break;
            case "Upper Two":
                this.p.setRoundScore(2 * this.categoriesFrequency.get(2));
                break;
            case "Upper Three":
                this.p.setRoundScore(3 * this.categoriesFrequency.get(3));
                break;
            case "Upper Four":
                this.p.setRoundScore(4 * this.categoriesFrequency.get(4));
                break;
            case "Upper Five":
                this.p.setRoundScore(5 * this.categoriesFrequency.get(5));
                break;
            case "Upper Six":
                this.p.setRoundScore(6 * this.categoriesFrequency.get(6));
                break;
            case "No Score":
                this.p.setRoundScore(0);
                break;
            default:
                break;
        }
        if(!choice.equals("Yahtzee")){
            this.p.getChoicesAlreadyScored().put(choice, Boolean.TRUE);
        }// end if
    }// end scoreRound
    
    public void setNumberOfRolls(int numRollsIn){
        this.numRolls = numRollsIn;
    }
    private int sum(){
        int sum = 0;
        for(int i = 0; i < this.result.length; i++){
            sum+=this.result[i];
        }// end for
        return sum;
    }
}// end Class Round
