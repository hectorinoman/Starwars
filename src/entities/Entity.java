/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.text.Position;

/**
 *
 * @author Jaime
 */
public abstract class Entity {

    protected ImageIcon icon_;
    protected int x_, y_;
    /**
     * TODO
     * Cambiar x e y por point,
     * Mirar lo de los métodos de movimiento (que entidades deben tenerlo
     * y cuales no.
     * Implementar en R2D2 el algoritmo heurístico Hill Climbing
     * 
     */
    
    
    public Entity() {
        x_ = 0;
        y_ = 0;
    }

    public Entity(int x, int y) {
        x_ = x;
        y_ = y;
    }

    public ImageIcon getIcon() {
        return icon_;
    }

    public int getX() {
        return x_;
    }

    public int getY() {
        return y_;
    }

    public void moveRight() {
        x_++;
    }

    public void moveLeft() {
        x_--;
    }

    public void moveUp() {
        y_++;
    }

    public void moveDown() {
        y_--;
    }

}
