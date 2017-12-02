/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.MainController;
import models.GamePlay;
import views.MainView;

/**
 *
 * @author tumesh
 */
public class App {
    public static void main(String[] args){
        MainView newView = new MainView();
        GamePlay newRound = new GamePlay("Player 1");
        MainController newController = new MainController(newView, newRound);
        newController.initController();
    }// end main
}// end class App
