/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *A Player class that keep track of scores, choices selected and rounds 
 * @author tumesh,Nour,Mahdi
 */
public class Player {
    private static Dices dices;
    private int score;
    private HashSet<String> choicesScored;
    private int roundNumber;
    static final int MAX_ROUNDS = 4; 
    private Integer [] results;
    private String player_id;
    private int yahtzeeCount;
    private Map<Integer, Integer> roundScores;
    private Map<String, Boolean> choicesAlreadyScored;
    /**
     * Keep track of how many Yahtzee's has the player scored
     * @return Integer the number of time Yahtzee has been scored
     */
    public int getYahtzeeCount() {
        return yahtzeeCount;
    }// end getYahtzeeCount
    /**
     * Increment the variable yahtzeeCount by one 
     */
    public void incrementYahtzeeCount(){
        this.yahtzeeCount++;
    }// end incrementYahtzeeCount
    
    /**
     * Default constructor that initialize the class variables 
     * @param id of each player 
     */
    public Player(String id){
        dices = new Dices();
        this.score = 0;
        this.choicesScored = new HashSet<>();
        this.roundScores = new HashMap<>(13);
        this.choicesAlreadyScored = new HashMap<>();
        this.initializeChoicesAlreadyScored();
        this.roundNumber = 0;
        this.player_id = id;
    }// end player
    /**
    * Initialize the Map choisesAlreadyScored 
    */
    private void initializeChoicesAlreadyScored(){
        this.choicesAlreadyScored.put("Yahtzee", Boolean.FALSE);
        this.choicesAlreadyScored.put("Full House", Boolean.FALSE);
        this.choicesAlreadyScored.put("Large Striaght", Boolean.FALSE);
        this.choicesAlreadyScored.put("Small Striaght", Boolean.FALSE);
        this.choicesAlreadyScored.put("Three of a Kind", Boolean.FALSE);
        this.choicesAlreadyScored.put("Four of a Kind", Boolean.FALSE);
        this.choicesAlreadyScored.put("Chance", Boolean.FALSE);
        this.choicesAlreadyScored.put("Upper One", Boolean.FALSE);
        this.choicesAlreadyScored.put("Upper Two", Boolean.FALSE);
        this.choicesAlreadyScored.put("Upper Three", Boolean.FALSE);
        this.choicesAlreadyScored.put("Upper Four", Boolean.FALSE);
        this.choicesAlreadyScored.put("Upper Five", Boolean.FALSE);
        this.choicesAlreadyScored.put("Upper Six", Boolean.FALSE);
        //this.printChoicesAlreadyScored();
    }// end initializeChoicesAlreadyScored
    /**
     * Print the values and corresponding keys in choicesAlreadyScored
     */
    public void printChoicesAlreadyScored(){
        for(String anEntry : this.choicesAlreadyScored.keySet()){
            System.out.println(anEntry+ " " + 
                    this.choicesAlreadyScored.get(anEntry));
        }// end for
    }// end printChoicesAlreadyScored
    /**
     * Call the Dice method roll() and getRollResults(),
     * store the results in Integer array results
     * and sort the content of results
     */
    public void rollDice(){
        dices.roll();
        this.results = dices.getRollResult();
        Arrays.sort(this.results);
    }// end rollDice
    
    /**
     * Update Round Score for the player
     * @param roundScore the Integer updated score 
     */
    public void updateRoundScore(int roundScore){
        this.roundScores.put(roundNumber, roundScore);
    }
    
    /**
     *Set the current score
    */
    public void setScore(){
        this.score = scoreSum();
    }// end setScore
    /**
     * Get the current score 
     * @return Integer the player's score
     */
    public int getScore(){
        return this.score;
    }
    
    /**
     * Score for each player is sum of scores in each round
     * @return Integer the player's total score
     */
    private int scoreSum(){
        int sum = 0;
        for(Map.Entry<Integer, Integer> roundScore : 
                                this.roundScores.entrySet()){
            sum += roundScore.getValue();
        }// end for
        return sum;
    }// end scoreSum
    
    /**
     * Get the Integer array of results
     * @return Integer[] the results
     */
    public Integer [] getResults(){
        return this.results;
    }// end getResults
    /**
     * Get the Player's ID 
     * @return String, the Players ID as a String
     */
    public String getPlayerID(){
        return this.player_id;
    }// end getPlayerID
    /**
     * Get the Round ID
     * @return String,the roundNumber as a String
     */
    public String getRoundID(){
        return "Round " + this.roundNumber;
    }// end getRoundID
    /**
     * Get the HahSet of scored choices
     * @return HashSet<String> choicesScored
     */
    public HashSet<String> getChoicesScored(){
        return (HashSet) this.choicesScored;
    }// end getChoices Scored
    /**
     * Store the Score of the current round in the HashMap roundScore
     * @param roundScore,Integer the score of the round 
     */
    public void setRoundScore(int roundScore){
        this.roundScores.put(this.roundNumber, roundScore);
        //System.out.println(this.choicesScored.size());
    }
    /**
     * Get the Scores as a HashMap roundScore
     * @return  HashMap<Integer, Integer>, roundScore
     */
    public HashMap<Integer, Integer> getRoundScore(){
        return (HashMap<Integer, Integer>)this.roundScores;
    }// end getRoundScore
    /**
     * Get the current round Number
     * @returnInteger, the number of the round
     */
    public int getRoundNumber(){
        return this.roundNumber;
    }// end getRoundNumber
   /**
     * Increment the current round Number by one
     */
    public void incrementRoundNumber(){
        this.roundNumber++;
    }// end incrementRoundNumber
    /**
     * Check if the player has more rounds to play
     * @return boolean,True if the player still have more rounds to play or False otherwise
     */
    public boolean hasMoreRoundsToPlay(){
        return this.roundNumber < MAX_ROUNDS;
    }// end has More Rounds to play
    /**
     *Get the HashMap of already scored choices
     * @return HashMap<String,Boolean> choicesAlreadyScored
     */
    public HashMap<String, Boolean> getChoicesAlreadyScored(){
        return (HashMap)this.choicesAlreadyScored;
    }// end getChoicesAlreadyScored
}// end Player
