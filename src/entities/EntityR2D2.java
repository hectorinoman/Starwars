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
 * @author Jaime
 */
public class EntityR2D2 extends Entity {

    public EntityR2D2() {
        super();
        icon_ = new ImageIcon(getClass().getResource("defensive.png"));
        icon_ = new ImageIcon(icon_.getImage().getScaledInstance(32, 32, BufferedImage.SCALE_SMOOTH));
    }

    public EntityR2D2(int x, int y) {
        super(x, y);
        icon_ = new ImageIcon(getClass().getResource("defensive.png"));
        icon_ = new ImageIcon(icon_.getImage().getScaledInstance(32, 32, BufferedImage.SCALE_SMOOTH));
        //Colocar a RD2     Land_[x][y].setIcon(icon);
    }
    
    protected void moveRight() {
        x_++;
    }

    protected void moveLeft() {
        x_--;
    }

    protected void moveUp() {
        y_++;
    }

    protected void moveDown() {
        y_--;
    }
    
}
