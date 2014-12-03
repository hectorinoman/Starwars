/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Stack;
import javax.swing.ImageIcon;

/**
 *
 * @author Jaime
 */
public class EntityR2D2 extends Entity {
    private Stack<Point> stack_ = new Stack<Point> ();

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
        stack_.push(pos_);
        pos_.setLocation(pos_.getX()+1,pos_.getY());
    }

    protected void moveLeft() {
        stack_.push(pos_);
        pos_.setLocation(pos_.getX()-1,pos_.getY());
    }

    protected void moveUp() {
        stack_.push(pos_);
        pos_.setLocation(pos_.getX(),pos_.getY()+1);
    }

    protected void moveDown() {
        stack_.push(pos_);
        pos_.setLocation(pos_.getX(),pos_.getY()-1);
    }
    
    public Point back(){
        if(!stack_.empty()){
            pos_=stack_.pop();
            return pos_;
        }else{
            return null;
        }
    }
    
}
