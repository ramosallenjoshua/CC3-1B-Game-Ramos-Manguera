/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Entity.*;
import Entity.Enemies.Slugger;
import Main.GamePanel;
import TileMap.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import Audio.AudioPlayer;

/**
 *
 * @author MAX
 */
public class Level1State extends GameState{
    
    private TileMap tileMap;
    private Background bg;
    
    private Player player;
    
    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;
    
    private HUD hud;
    
    private AudioPlayer bgMusic;
    
    public Level1State(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset.png");
        tileMap.loadMap("/Maps/level1-1.map");
        
        tileMap.setPosition(0, 0);
        tileMap.setTween(0.07);
        bg = new Background("/Background/forestbg1.png", 0.1);
        
        player = new Player(tileMap);
        
        player.setPosition(100, 200);
        
        populateEnemies();
        
        explosions = new ArrayList<Explosion>();
        
        hud = new HUD(player);
        
        bgMusic = new AudioPlayer("/Music/lvl1_bgmusic.mp3");
        bgMusic.playLoop();
    }
    private void populateEnemies(){
        enemies = new ArrayList<Enemy>();
        
        Slugger s;
        Point[] point = new Point[]{new Point(860, 200), new Point(1525, 200), new Point(1680, 200),new Point(1800, 200)};
        
        for (int i = 0; i < point.length; i++) {
            s = new Slugger(tileMap);
            s.setPosition(point[i].x, point[i].y);
            enemies.add(s);
        }
    }

    @Override
    public void update() {
        
        if (player.gety() > tileMap.getHeight() -20) {
            player.hit(1);
            player.setPosition(100, 100);
        }
        if (player.getx() > tileMap.getWidth() - 20) {
            bgMusic.stop();
            gsm.setState(GameStateManager.LEVEL2BECONTINUED);
        }
        if (player.isDead()) {
            gsm.setState(GameStateManager.GAMEOVERSTATE);
            bgMusic.stop();
        }
        
        player.update();
        
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(),GamePanel.HEIGHT / 2 - player.gety());
        
        bg.setPosition(tileMap.getx(), tileMap.gety());
        
        player.checkAttack(enemies);
        
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
            if (e.isDead()) {
                enemies.remove(i);
                i--;
                explosions .add(new  Explosion(e.getx(), e.gety()));
            }
        }
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).update();
            if (explosions.get(i).shouldRemove()) {
                explosions.remove(i);
                i--;
            }
        }
        
        
    }
    
    

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
       
        tileMap.draw(g);
        
        player.draw(g);
        
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
        
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).setMapPosition((int)tileMap.getx(), (int)tileMap.gety());
            explosions.get(i).draw(g);
        }
        
        hud.draw(g);
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_LEFT) player.setLeft(true);
        if(key == KeyEvent.VK_RIGHT) player.setRight(true);
        if(key == KeyEvent.VK_UP) player.setUp(true);
        if(key == KeyEvent.VK_DOWN) player.setDown(true);
        if(key == KeyEvent.VK_SPACE) player.setJumping(true);
        if(key == KeyEvent.VK_SHIFT) player.setGliding(true);
        if(key == KeyEvent.VK_X) player.setFiring();
    }

    @Override
    public void keyReleased(int key) {
        if(key == KeyEvent.VK_LEFT) player.setLeft(false);
        if(key == KeyEvent.VK_RIGHT) player.setRight(false);
        if(key == KeyEvent.VK_UP) player.setUp(false);
        if(key == KeyEvent.VK_DOWN) player.setDown(false);
        if(key == KeyEvent.VK_SPACE) player.setJumping(false);
        if(key == KeyEvent.VK_SHIFT) player.setGliding(false);
    }
}
