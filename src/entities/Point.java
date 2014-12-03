/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import static java.lang.Math.abs;

public class Point {
    private int x_, y_;
    
    public Point(){
        x_=0;
        y_=0;
    }
    
    public void setLocation(int x, int y){
        x_=x;
        y_=y;
    }
    
    public void setLocation(Point pos){
        x_=pos.x_;
        y_=pos.y_;
    }
    
    public int getX(){
        return x_;
    }
    
    public int getY(){
        return y_;
    }
    
    public int manhatanCost(Point pos){
        return abs(pos.getX()-x_) + abs(pos.getY()-y_);
    }
    
}
