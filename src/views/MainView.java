/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author tumesh
 */
public class MainView {
    private JFrame mainFrame;
    private JLabel playerLabel;
    private JLabel roundLabel;
    private JPanel controlPanel;
    private JLabel roundScoreLabel;
    private JLabel totalScoreLabel;
    private JLabel emptyLabel;
    private JButton rollDiceButton;
    private JTextArea resultDisplayArea;
    private JComboBox<String> categories;
    private JLabel categoriesToScore;
    private JLabel chooseChoices;
    private JButton selectChoice;
    private JLabel numPlayerLabel;
    private JComboBox<Integer> numOfPlayers;
    private JLabel numberOfPlayersSelectionLabel;
    private JButton selectNumberOfPlayersBtn;
    
    public MainView(){
        prepareGUI();
    }
    
    private void prepareGUI() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getDefaultScreenDevice();   // Graphics Device GD
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        
        
        mainFrame = new JFrame("Yahtzee...");
        mainFrame.setSize(width/2, height/2);
        mainFrame.setLocation(width/2-mainFrame.getWidth()/2, 
                              height/2 - mainFrame.getHeight()/2);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        //mainFrame.setLayout(new GridLayout(2, 2));
        playerLabel = new JLabel("Player");
        roundLabel = new JLabel("Round");
        roundScoreLabel = new JLabel("Round Score");
        emptyLabel = new JLabel("");
        totalScoreLabel = new JLabel("Total Score");
        rollDiceButton = new JButton("Roll Dices");
        resultDisplayArea = new JTextArea("1-1-1-1-1");
        categoriesToScore = new JLabel("Choices To Score From");
        chooseChoices = new JLabel("Press Button to score");
        selectChoice = new JButton("Select Choice");
        numOfPlayers = new JComboBox<>(new Integer [] {1, 2, 3, 4});
        numPlayerLabel = new JLabel("Select Number of Players:");
        numberOfPlayersSelectionLabel = new JLabel("Press Button to Start Game");
        selectNumberOfPlayersBtn = new JButton("StartGame");
        
        // Creating a new gridBagLayout and setting properties
        GridBagLayout panelGridBag = new GridBagLayout();
        GridBagConstraints panelGridBagConstraints = new GridBagConstraints();
        controlPanel = new JPanel();
        
        // Set Properties for the player text
        panelGridBagConstraints.insets = new Insets(10, 10, 10, 10);
        panelGridBagConstraints.fill = GridBagConstraints.BOTH;
        controlPanel.setLayout(panelGridBag);
        
        panelGridBagConstraints.weightx = 1.0;
        panelGridBag.setConstraints(numPlayerLabel, panelGridBagConstraints);
        //playerLabel.setBorder(new LineBorder(Color.BLACK, 2));
        controlPanel.add(numPlayerLabel);
        // Set properties for rollDiceBtn
        panelGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        panelGridBag.setConstraints(numOfPlayers, panelGridBagConstraints);
        controlPanel.add(numOfPlayers);
        
        panelGridBagConstraints.weightx = 1.0;
        panelGridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        panelGridBag.setConstraints(numberOfPlayersSelectionLabel,
                                        panelGridBagConstraints);
        //playerLabel.setBorder(new LineBorder(Color.BLACK, 2));
        controlPanel.add(numberOfPlayersSelectionLabel);
        // Set properties for rollDiceBtn
        panelGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        panelGridBag.setConstraints(selectNumberOfPlayersBtn,
                                        panelGridBagConstraints);
        controlPanel.add(selectNumberOfPlayersBtn);

        panelGridBagConstraints.weightx = 1.0;
        panelGridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        panelGridBag.setConstraints(playerLabel, panelGridBagConstraints);
        //playerLabel.setBorder(new LineBorder(Color.BLACK, 2));
        controlPanel.add(playerLabel);
        // Set properties for rollDiceBtn
        panelGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        panelGridBag.setConstraints(rollDiceButton, panelGridBagConstraints);
        controlPanel.add(rollDiceButton);
        
        panelGridBagConstraints.weightx = 0.0;
        panelGridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        // add another grid 
        panelGridBag.setConstraints(roundLabel, panelGridBagConstraints);
        controlPanel.add(roundLabel);
        
        panelGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        panelGridBag.setConstraints(resultDisplayArea, panelGridBagConstraints);
        controlPanel.add(resultDisplayArea);
        
        
        panelGridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        panelGridBag.setConstraints(categoriesToScore, panelGridBagConstraints);
        controlPanel.add(categoriesToScore);
        String[] choices = { };
        categories = new JComboBox(choices);
        panelGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        panelGridBag.setConstraints(categories, panelGridBagConstraints);
        controlPanel.add(categories);
        
        panelGridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        panelGridBag.setConstraints(chooseChoices, panelGridBagConstraints);
        controlPanel.add(chooseChoices);
        panelGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        panelGridBag.setConstraints(selectChoice, panelGridBagConstraints);
        controlPanel.add(selectChoice);
        
        panelGridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        panelGridBag.setConstraints(roundScoreLabel, panelGridBagConstraints);
        controlPanel.add(roundScoreLabel);
        panelGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        panelGridBag.setConstraints(totalScoreLabel, panelGridBagConstraints);
        controlPanel.add(totalScoreLabel);
        
        mainFrame.add(controlPanel, BorderLayout.PAGE_START);
        mainFrame.setVisible(true);
    }// end prepareGUI
    
    public JButton getRollDiceBtn(){
        return this.rollDiceButton;
    }// end getRollDiceBtn
    
    public JTextArea getResultDisplayArea(){
        return this.resultDisplayArea;
    }// end getResultDisplayArea
    
    public JLabel getPlayerLabel(){
        return this.playerLabel;
    }// end getPlayerLabel
    
    public JLabel getRoundLabel(){
        return this.roundLabel;
    }// end getRoundLabel
    
    public JComboBox getChoicesScoringComboBox(){
        return this.categories;
    }// end getChoicesScoringComboBox
    
    public JButton getChooseChoicesButton(){
        return this.selectChoice;
    }// end getChooseChoicesButton
    
    public JLabel getRoundScoreLabel(){
        return this.roundScoreLabel;
    }// end getRoundScoreLabel
    
    public JLabel getTotalScoreLabel(){
        return this.totalScoreLabel;
    }// end getTotalScoreLabel

    public JComboBox<Integer> getNumOfPlayers() {
        return numOfPlayers;
    }

    public JButton getSelectNumberOfPlayersBtn() {
        return selectNumberOfPlayersBtn;
    }
}// end main class
