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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import circularLinkedChain.CircularLinkedChain;

/**
 *A class that has a circular Linked chain of Players, keep track of their results,
 * number of rolls, decide on the fit categories they can choose from, and finds the winner
 * @author tumesh,Nour,Mahdi
 */
public class GamePlay {
    private CircularLinkedChain<Player> players;
    private Player p;
    private Integer [] result;
    private static final int MAX_ROLLS = 3;
    private int numRolls;
    private Map<Integer, Integer> categoriesFrequency;
    private int numPlayers; // number of players
    /**
     *Default constructor that initialize the class variables 
     */
    public GamePlay(){
        //this.p = new Player(player_id);
        this.numRolls = 1;
        this.categoriesFrequency = new HashMap<>();
    }// end constructor
    /**
     * Call the player's rollDice method,increment the variable numRolls to
     * keep track of the number of time the dices were rolled for a certain round,
     * get the results of rolling, and call the method populateCategoriesFrequency()
     */
    public void rollDices(){
        this.p.rollDice();
        this.numRolls++;
        this.result = this.p.getResults();
        this.populateCategoriesFrequency();
    }// end rollDices
    /**
     * Concatenate the results of rolling into a String
     * @return String represent the results of rolling the dices
     */
    public String getDiceRollResult(){
        String temp = new String();
        for(int i = 0; i < this.result.length; i++){
            if(i < this.result.length-1)
                temp = temp.concat(this.result[i].toString() + " - ");
            else
                temp = temp.concat(this.result[i].toString());
        }
        return temp;
    }// end getDiceRollResult
     /**
     * Check if the player is allowed to roll the dices again
     * @return True if rolling again is possible, False otherwise
     */
    public boolean moreRollsPossible(){
        return this.numRolls < MAX_ROLLS;
    }// end moreRollsPossible
    /**
     * Get the current Player's ID 
     * @return String, the Players ID as a String
     */
    public String getPlayerID(){
        return this.p.getPlayerID();
    }// end getPlayerID
    /**
     * Get the Round ID
     * @return String,the roundNumber as a String
     */
    public String getRoundID(){
        return this.p.getRoundID();
    }// end getRoundID
    /**
     * Analyze the results, by calling the findFitCategories method, then 
     * iterating through the choicesAlreadyScored Map to remove any categories that
     * were previously scored
     * @return ArrayList<String> the available categories that a player can choose from 
     */
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
     /**
     * Create a temporary instance of the Array results as a LinkedList,
     * to check for the frequency of each number(from 1 to 6),
     * then store that into the HashMap categoriesFrequency
     */
    private void populateCategoriesFrequency(){
        LinkedList<Integer> temp = 
                        new LinkedList<>(Arrays.asList(result));
        Integer freq;
        for(int i = 1; i <= 6; i++){
            freq = Collections.frequency(temp, i);
            this.categoriesFrequency.put(i, freq);
        }// end for
    }// end populateCategoriesFrequency
    /**
     * Find the fit categories for the sample of rolled dices, by checking 
     * if each category is applicable or not, then storing the fit ones in 
     * the ArrayList  resultAnalyzed
     * @return ArrayList<String>,the fit categories.
     */
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
    /**
     * Get the Player
     * @return Player, the current player
     */
    public Player getPlayer(){
        return this.p;
    }// end getPlayer
    /**
     * Check if the Yahtzee category is applicable or not for the sample of rolled dices
     * can only be scored if the dice show five of the same number
     * @return True if applicable, False otherwise
     */
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
    /**
     * Check if the FullHouse category is applicable or not for the sample of rolled dices
     * can only be scored if the dice show three of one number and two of another, therfore 
     * the categoriesFrequency must contain the values 2 and 3
     * @return True if applicable, False otherwise
     */
    private boolean isFullHouse(){
        boolean countTwo = false, countThree = false;
        for(Integer anElement : this.categoriesFrequency.values()){
            if(anElement.equals(2)) countTwo = true;
            if(anElement.equals(3)) countThree = true;
        }// end if
        return (countTwo && countThree);
    }// end isFullHouse
    /**
     * Check if the LargeStraight category is applicable or not for the sample of rolled dices
     * can only be scored if the dice show any sequence of five numbers.
     * @return True if applicable, False otherwise
     */
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
    /**
     * Check if the SmallStraight category is applicable or not for the sample of rolled dices
     * can only be scored if the dice show any sequence of four numbers.
     * @return True if applicable, False otherwise
     */
    private boolean isSmallStraight(){
        boolean isSmallStraight = false;
        int count = 0;
        for(int i = 0; i < this.result.length-1; i++){
            if(this.result[i+1] - this.result[i] == 1)
                count++;
        }//end for
        if(count == 3) isSmallStraight = true;
       //System.out.println(isSmallStraight);
        return isSmallStraight;
    }// end isSmallStraight
    /**
     * Check if the ThreeOfAKind category is applicable or not for the sample of rolled dices
     * can only be scored if the dice include three or more of the same number
     * @return True if applicable, False otherwise
     */
    private boolean isThreeOfAKind() {
        boolean isThreeOfAKind = false;
        for(Integer frequency : this.categoriesFrequency.values()){
            if(frequency == 3)   isThreeOfAKind = true;
        }// end if
        return isThreeOfAKind;
    }// end isThreeOfAKind
    /**
     * Check if the FourOfAKind category is applicable or not for the sample of rolled dices
     * can only be scored if the dice include four or more of the same number
     * @return True if applicable, False otherwise
     */
    private boolean isFourOfAKind(){
        boolean isFourOfAKind = false;
        for(Integer frequency : this.categoriesFrequency.values()){
            if(frequency == 4)  isFourOfAKind = true;
        }// end if
        return isFourOfAKind;
    }// end isFourOfAKind
    /**
     * Check if the UpperOne category is applicable or not for the sample of rolled dices
     * can be scored if the dice include at least one 1
     * @return True if applicable, False otherwise
     */
    private boolean isUpperOne(){
        return (this.categoriesFrequency.get(1) > 0);
    }// end isUpperOne
     /**
     * Check if the UpperTwo category is applicable or not for the sample of rolled dices
     * can be scored if the dice include at least one 2
     * @return True if applicable, False otherwise
     */
    private boolean isUpperTwo(){
        return (this.categoriesFrequency.get(2) > 0);
    }// end isUpperTwo
    /**
     * Check if the UpperThree category is applicable or not for the sample of rolled dices
     * can be scored if the dice include at least one 3
     * @return True if applicable, False otherwise
     */
    private boolean isUpperThree(){
        return (this.categoriesFrequency.get(3) > 0);
    }// end isUpperThree
     /**
     * Check if the UpperFour category is applicable or not for the sample of rolled dices
     * can be scored if the dice include at least one 4
     * @return True if applicable, False otherwise
     */
    private boolean isUpperFour(){
        return (this.categoriesFrequency.get(4) > 0);
    }// end isUpperFour
     /**
     * Check if the UpperFive category is applicable or not for the sample of rolled dices
     * can be scored if the dice include at least one 5
     * @return True if applicable, False otherwise
     */
    private boolean isUpperFive(){
        return (this.categoriesFrequency.get(5) > 0);
    }// end isUpperFive
    /**
     * Check if the UpperSix category is applicable or not for the sample of rolled dices
     * can be scored if the dice include at least one 6
     * @return True if applicable, False otherwise
     */
    private boolean isUpperSix(){
        return (this.categoriesFrequency.get(6) > 0);
    }// end isUpperSix
    /**
     * Check if the Chance category is applicable for the rolled dices
     * @return True
     */
    private boolean isChance(){
        return true;
    }// end isChance
    /**
     *Get the score of the chosen category
     * @param choice is the score choice of the user
     */
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
    /**
     *Set the numRolls variable
     * @param numRollsIn, th current number of rolls for the round
     */
    public void setNumberOfRolls(int numRollsIn){
        this.numRolls = numRollsIn;
    }
    /*
    *Sum the values of all five dices stored in results
    @return Integer,the sum of all dices values
    */
    private int sum(){
        int sum = 0;
        for(int i = 0; i < this.result.length; i++){
            sum+=this.result[i];
        }// end for
        return sum;
    }
     /**
     * Get the number of Players
     * @return Integer, the number of Players
     */
    public int getNumPlayers() {
        return numPlayers;
    }
    /**
     *Set the number of players and call the initializePlayer method
     * @param numPlayers, the selected number of players
     */
    public void setPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
        initializePlayers();
    }
    /**
     *Create a new CircularLinkedChain of Players, of length numPlayers
     * 
     */
    private void initializePlayers(){
        this.players = new CircularLinkedChain<>();
        for(int i = this.numPlayers; i >= 1; i--){
            players.add(new Player("Player " + i));
        }// end for
        this.p = players.getNext();
    }// end initializePlayers
     /**
     * Get the next  player in the chain
     */
    public void changePlayer(){
        this.p = players.getNext();
    }// end changePlayer
     /**
     * Check if there are more players to play, by checking if the cycle is completed or not
     * @return True if cycle is completed, False otherwise
     */
    public boolean hasMorePlayersToPlay(){
        return this.players.isIncompleteCycle();
    }// end hasMorePlayersToPlay
    /**
     *Find the winner by iterating through the chain of players and finding 
     * the one with the highest score
     * @return Player with the highest score
     */
    public Player findWinner(){
        Player winner = this.players.getNext();
        while(this.players.isIncompleteCycle()){
            Player temp = this.players.getNext();
            if(winner.getScore() < temp.getScore())
                winner = temp;
        }// end while
        return winner;
    }// end findWinner
    
    
}// end Class Round
