/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author ALEJANDRO
 */
public class EntityFalconMillenium extends Entity{
    public EntityFalconMillenium(){
        super();
        icon_=new ImageIcon(getClass().getResource("halcon.png"));
        icon_ = new ImageIcon(icon_.getImage().getScaledInstance(32, 32, BufferedImage.SCALE_SMOOTH));
    }
    
    public EntityFalconMillenium(int x, int y){
        super(x,y);
        icon_=new ImageIcon(getClass().getResource("halcon.png"));
        icon_ = new ImageIcon(icon_.getImage().getScaledInstance(32, 32, BufferedImage.SCALE_SMOOTH));
    }
    
    public void moveTo(Point pos){
        
    }
    
    public Point back(){
        return null;
    }
}
