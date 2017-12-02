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
 *
 * @author tumesh
 */
public class Player {
    private static Dices dices;
    private int score;
    private HashSet<String> choicesScored;
    private int roundNumber;
    static final int MAX_ROUNDS = 2; 
    private Integer [] results;
    private String player_id;
    private int yahtzeeCount;
    private Map<Integer, Integer> roundScores;
    private Map<String, Boolean> choicesAlreadyScored;
    
    public int getYahtzeeCount() {
        return yahtzeeCount;
    }// end getYahtzeeCount
    
    public void incrementYahtzeeCount(){
        this.yahtzeeCount++;
    }// end incrementYahtzeeCount
    
    
    
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
    
    public void printChoicesAlreadyScored(){
        for(String anEntry : this.choicesAlreadyScored.keySet()){
            System.out.println(anEntry+ " " + 
                    this.choicesAlreadyScored.get(anEntry));
        }// end for
    }// end printChoicesAlreadyScored
    public void rollDice(){
        dices.roll();
        this.results = dices.getRollResult();
        Arrays.sort(this.results);
    }// end rollDice
    
    // update Round Score for the player
    public void updateRoundScore(int roundScore){
        this.roundScores.put(roundNumber, roundScore);
    }
    
    // Set the score for this player
    public void setScore(){
        this.score = scoreSum();
    }// end setScore
    
    public int getScore(){
        return this.score;
    }
    
    // Score for each player is sum of score in each round
    private int scoreSum(){
        int sum = 0;
        for(Map.Entry<Integer, Integer> roundScore : 
                                this.roundScores.entrySet()){
            sum += roundScore.getValue();
        }// end for
        return sum;
    }// end scoreSum
    
    
    public Integer [] getResults(){
        return this.results;
    }// end getResults
    
    public String getPlayerID(){
        return this.player_id;
    }// end getPlayerID
    
    public String getRoundID(){
        return "Round " + this.roundNumber;
    }// end getRoundID
    
    public HashSet<String> getChoicesScored(){
        return (HashSet) this.choicesScored;
    }// end getChoices Scored
    
    public void setRoundScore(int roundScore){
        this.roundScores.put(this.roundNumber, roundScore);
        //System.out.println(this.choicesScored.size());
    }
    
    public HashMap<Integer, Integer> getRoundScore(){
        return (HashMap<Integer, Integer>)this.roundScores;
    }// end getRoundScore
    
    public int getRoundNumber(){
        return this.roundNumber;
    }// end getRoundNumber
   
    public void incrementRoundNumber(){
        this.roundNumber++;
    }// end incrementRoundNumber
    
    public boolean hasMoreRoundsToPlay(){
        return this.roundNumber < MAX_ROUNDS;
    }// end has More Rounds to play
    
    public HashMap<String, Boolean> getChoicesAlreadyScored(){
        return (HashMap)this.choicesAlreadyScored;
    }// end getChoicesAlreadyScored
}// end Player
