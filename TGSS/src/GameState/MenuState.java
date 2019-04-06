/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.awt.Graphics2D;
import TileMap.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import Audio.AudioPlayer;
/**
 *
 * @author MAX
 */
public class MenuState extends GameState{
    
    private Background bg;
    
    private int currentChoice = 0;
    private String[] options = {"Start", "Quit"};
    
    private Color titleColor;
    
    private Font titleFont;
    private Font font;
    
    private AudioPlayer bgMusic;
    private AudioPlayer selectSFX;
    private AudioPlayer okSFX;
    
    public MenuState(GameStateManager gsm){
        
        this.gsm = gsm;
        
        try{
            
            bg = new Background("/Background/main_menu_background.jpg",0);
            
            titleColor = new Color(255, 0, 0);
            titleFont = new Font("Cambria Math", Font.PLAIN,18);
            font = new Font("Cambria Math", Font.PLAIN, 16);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        init();
    }
    @Override
    public void init(){
        bgMusic = new AudioPlayer("/Music/menu_bgmusic.mp3");
        bgMusic.playLoop();
    }
    @Override
    public void update(){
        bg.update();
    }
    @Override
    public void draw(Graphics2D g){
        bg.draw(g);
        
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Touhou Genso", 180, 90);
        g.drawString("Platformer", 195, 110);
        
        
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.WHITE);
            }else{
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 215, 140 + i * 15);
        }
    }
    
    private void select(){
        
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.LEVEL1STATE);
            bgMusic.stop();
        }
        if (currentChoice == 1) {
            System.exit(0);
        }
    }
    
    @Override
    public void keyPressed(int key){
        selectSFX = new AudioPlayer("/SFX/menu_select.mp3");
        okSFX = new AudioPlayer("/SFX/menu_ok.mp3");
        if (key == KeyEvent.VK_ENTER) {
            okSFX.play();
            select();
        }
        if (key == KeyEvent.VK_UP) {
            selectSFX.play();
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (key == KeyEvent.VK_DOWN) {
            selectSFX.play();
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0; 
            }
        }
    }
    @Override
    public void keyReleased(int key){
        
    }
}
