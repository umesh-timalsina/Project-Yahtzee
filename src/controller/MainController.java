/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import models.GamePlay;
import models.Player;
import views.MainView;

public class MainController {
    MainView mainView;
    GamePlay gamePlay;
    
    public MainController(MainView v, GamePlay r){
        mainView = v;
        gamePlay = r;
    }// end constructor
    
    public void initController() {
        this.mainView.getRollDiceBtn().setEnabled(false);
        this.mainView.getChooseChoicesButton().setEnabled(false);
        
        JButton startGameBtn = this.mainView.getSelectNumberOfPlayersBtn();
        startGameBtn.addActionListener((ActionEvent e) -> 
                                      startGame());
        
        // Roll Dices and update the categories accordingly
        JButton rollDiceBtn = this.mainView.getRollDiceBtn();
        rollDiceBtn.addActionListener((ActionEvent e) -> 
                                showRollDicesResult(rollDiceBtn));
        
        JButton roundScoreBtn = this.mainView.getChooseChoicesButton();
        roundScoreBtn.addActionListener((ActionEvent e) -> 
                                scoreAndShowResults(roundScoreBtn));
    }// end initController
    
    private void printName(){
        System.out.println("Hello World");
    }// end printName
    
    private void startGame(){
        this.mainView.getRollDiceBtn().setEnabled(true);
        // To Do : Add Player Logic Here
        Integer numPlayers;
        numPlayers = (Integer) this.mainView.getNumOfPlayers().getSelectedItem();
        this.gamePlay.setPlayers(numPlayers);
        this.mainView.getSelectNumberOfPlayersBtn().setEnabled(false);
        this.runRound();
        
    }// end start Game
    
    private void showRollDicesResult(JButton t){
        if(!this.gamePlay.moreRollsPossible()) t.setEnabled(false);
        this.mainView.getChooseChoicesButton().setEnabled(true);
        this.gamePlay.rollDices();
        this.mainView.getResultDisplayArea().setText
                                    (this.gamePlay.getDiceRollResult());
       this.mainView.getChoicesScoringComboBox().removeAllItems();
       Object [] choices = this.gamePlay.analyzeResults().toArray();
       for(Object anElement : choices){
           this.mainView.getChoicesScoringComboBox().addItem(anElement);
       }// end for
       if(choices.length == 0){
           this.mainView.getChoicesScoringComboBox().addItem("No Score");
       }// end if
    }// end showRollDicesResult
    
    private void scoreAndShowResults(JButton t){
        this.gamePlay.getPlayer().getChoicesScored().add(
                                this.mainView.getChoicesScoringComboBox()
                                        .getSelectedItem().toString());
        this.gamePlay.scoreRound(this.mainView.getChoicesScoringComboBox().
                                    getSelectedItem().toString());
        
        this.mainView.getRoundScoreLabel().setText("Round Score : " + this.gamePlay.getPlayer().getRoundScore().get(this.gamePlay.getPlayer().getRoundNumber()));
        
        this.mainView.getChooseChoicesButton().setEnabled(false);
        
        this.gamePlay.getPlayer().setScore();
        
        this.mainView.getTotalScoreLabel().setText("Total Score: " + this.gamePlay.getPlayer().getScore()
            );
        this.mainView.getRollDiceBtn().setEnabled(false);
//        this.newRound.getPlayer().incrementRoundNumber();
        if(this.gamePlay.getPlayer().hasMoreRoundsToPlay() || 
                this.gamePlay.hasMorePlayersToPlay()){
            this.gamePlay.changePlayer();
            this.runRound();
        } // end if
        else{
            Player winner = this.gamePlay.findWinner();
            String winningStatement = winner.getPlayerID() +" Wins the game "
                                    + " with " + winner.getScore() + " Points.";
            MainView.infoBox(winningStatement, "Game Over");
            System.exit(0);
        }// end else
    }// end scoreAndShowResults
    
    private void runRound(){
        this.gamePlay.getPlayer().incrementRoundNumber();
        this.gamePlay.setNumberOfRolls(1);
        
        // Disable the round score button at first
        this.mainView.getChooseChoicesButton().setEnabled(false);
        
        // Enable the rollDiceBtn
        this.mainView.getRollDiceBtn().setEnabled(true);
        
        // Display the player information on the gameBoard
        JLabel playerLabel = this.mainView.getPlayerLabel();
        playerLabel.setText(this.gamePlay.getPlayerID());
        
        // Display the round information on the gameBoard
        JLabel roundLabel = this.mainView.getRoundLabel();
        roundLabel.setText(this.gamePlay.getRoundID());
    }
}
