/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author ALEJANDRO
 */
public class EntityStormtrooper extends Entity{
    public EntityStormtrooper(){
        super();
        icon_=new ImageIcon(getClass().getResource("stormtrooper.png"));
        icon_ = new ImageIcon(icon_.getImage().getScaledInstance(32, 32, BufferedImage.SCALE_SMOOTH));
    }
    
    public EntityStormtrooper(int x, int y){
        super(x,y);
        icon_=new ImageIcon(getClass().getResource("stormtrooper.png"));
        icon_ = new ImageIcon(icon_.getImage().getScaledInstance(32, 32, BufferedImage.SCALE_SMOOTH));
    }
    
    protected void moveRight() {
    }

    protected void moveLeft() {
    }

    protected void moveUp() {
    }

    protected void moveDown() {
    }

    public Point back(){
        return null;
    }
}
