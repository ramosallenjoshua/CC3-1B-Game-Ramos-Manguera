/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.util.ArrayList;
/**
 *
 * @author MAX
 */
public class GameStateManager {
    private GameState[] gameStates;
    private int currentState;
    
    public static int PREVIOUSSTATE;
    public static final int NUMGAMESTATES = 11;
    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
    public static final int LEVEL2BECONTINUED = 2;
    public static final int GAMEOVERSTATE = 10;
    
    public GameStateManager(){
        
        gameStates = new GameState[NUMGAMESTATES];
        
        currentState = MENUSTATE;
        loadState(currentState);
    }
    
    private void unloadState(int state){
        gameStates[state] = null;
    }
    
    private void loadState(int state){
        if (state == MENUSTATE) 
            gameStates[state] = new MenuState(this);
        if (state == LEVEL1STATE){ 
            previousState(state);
            gameStates[state] = new Level1State(this);
        }
        if (state == LEVEL2BECONTINUED) {
            gameStates[state] = new Level2BeContinued(this);
        }
        if (state == GAMEOVERSTATE) {
            gameStates[state] = new GameOverState(this);
        }
    }
    public void previousState(int state){
        PREVIOUSSTATE = state;
    }
    public void setState(int state){
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }
    
    public void update(){
        try{
        gameStates[currentState].update();
        }
        catch(Exception e){
        }
    }
    
    public void draw(java.awt.Graphics2D g){
        try{
        gameStates[currentState].draw(g);
        }catch(Exception e){
            
        }
    }
    
    public void keyPressed(int key){
        gameStates[currentState].keyPressed(key);
    }
    
    public void keyReleased(int key){
        gameStates[currentState].keyReleased(key);
    }
}
