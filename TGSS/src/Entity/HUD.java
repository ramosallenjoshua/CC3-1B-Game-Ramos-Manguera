/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author MAX
 */
public class HUD {
    
    private Player player;
    private BufferedImage image;
    private Font font;
    
    public HUD(Player p){
        player = p;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/HUD/hud.png"));
            font = new Font("Cambria Math", Font.PLAIN, 14);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g){
         g.drawImage(image, 0, 10,null);
         g.setFont(font);
         g.drawString(player.getHealth() + "/" + player.getMaxHealth(), 30,25);
         g.drawString(player.getFire()/1000 + "/" + player.getMaxFire()/1000, 30, 45);
    }
}
