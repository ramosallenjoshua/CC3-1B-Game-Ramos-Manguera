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
public class Level2BeContinued extends GameState{
    
    private Background bg;
    
    private Color titleColor;
    
    private Font titleFont;
    private Font font;
    
    private AudioPlayer okSFX;
    
    public Level2BeContinued(GameStateManager gsm){
        
        this.gsm = gsm;
        
        try{
            
            bg = new Background("/Background/level2becontinued.png",0);
            
            titleColor = new Color(255, 0, 0);
            titleFont = new Font("Cambria Math", Font.PLAIN,18);
            font = new Font("Cambria Math", Font.PLAIN, 16);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void init(){
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
        g.drawString("Level 2", 130, 90);
        g.drawString("Be Coninued...", 110, 110);
        
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("(Press Enter)", 115, 130);
    }
    
    
    @Override
    public void keyPressed(int key){
        okSFX = new AudioPlayer("/SFX/menu_ok.mp3");
        if (key == KeyEvent.VK_ENTER) {
            okSFX.play();
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }
    @Override
    public void keyReleased(int key){
        
    }
}
