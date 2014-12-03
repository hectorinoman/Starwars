/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Point;
import java.net.URL;
import javax.swing.ImageIcon;



/**
 *
 * @author Jaime
 */
public abstract class Entity {

    protected ImageIcon icon_;
    protected Point pos_=new Point();
    /**
     * TODO
     * Cambiar x e y por point,
     * Mirar lo de los métodos de movimiento (que entidades deben tenerlo
     * y cuales no.
     * Implementar en R2D2 el algoritmo heurístico Hill Climbing
     * 
     */
    
    
    public Entity() {
       pos_.setLocation(0,0);
    }

    public Entity(int x, int y) {
         pos_.setLocation(x,y);
    }
    
    public Entity(Point pos){
        pos_=pos;
    }

    public ImageIcon getIcon() {
        return icon_;
    }

    public int getX() {
        return pos_.getX();
    }

    public int getY() {
        return pos_.getY();
    }
    
    public Point getPos() {
        return pos_; 
    }

    abstract protected void moveRight();

    abstract protected void moveLeft();

    abstract protected void moveUp();

    abstract protected void moveDown();
    
    abstract public Point back();
}
