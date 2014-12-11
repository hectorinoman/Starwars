/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.map;

import entities.*;
import entities.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.text.Position;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
/**
 *
 * @author Jaime
 */
public class Environment extends JPanel implements MouseListener{
    //generador de números aleatorios
    private java.util.Random rand= new java.util.Random(System.nanoTime());   
    
    private JLabel floorLayer_[][];     //Tablero del suelo
    private JLabel entityLayer_[][];    //Tablero de entidades
    int dimX_, dimY_;                   //Dimensiones del tablero

    private Entity mainAgent_;          //Agente principal (robot) (R2D2)
    private Entity finalAgent_;         //Objetivo del agente principal (Nave)
    
    int selectedIcon_=1;                  //obstaculo o agente seleccionado


    
    private final ImageIcon routeIcon_= new ImageIcon(    //icono casilla de ruta
            getClass()
                    .getResource("route.png"));
    
    private final ImageIcon wrongIcon_= new ImageIcon(    //icono de casilla visitada
            getClass()
                    .getResource("wrong.png"));
   
    
    
    private void floorGen() {
        ImageIcon floorTile_ = new ImageIcon( //  Tile que representa el suelo
              getClass() 
                .getResource("floor.png"));     

        floorLayer_ = new JLabel[dimX_][dimY_];
        
        for (int i = 0; i < dimX_; i++) {
            for (int j = 0; j < dimY_; j++) {
                floorLayer_[i][j] = new JLabel();
                floorLayer_[i][j].setIcon(floorTile_);
                add(floorLayer_[i][j], new AbsoluteConstraints(i * 32, j * 32, 32, 32));
            }
        }
    }
    

    private void agentsLayerGen() {
        entityLayer_ = new JLabel[dimX_][dimY_];
        
        for (int i = 0; i < dimX_; i++) {
            for (int j = 0; j < dimY_; j++) {
                entityLayer_[i][j] = new JLabel();
                add(entityLayer_[i][j], new AbsoluteConstraints(i * 32, j * 32, 32, 32));
                entityLayer_[i][j].addMouseListener(this);
            }
        }
        
        
        //Adding random R2D2
        int x= rand()%dimX_;
        int y= rand()%dimY_;
        
        
        mainAgent_ = new EntityR2D2(x,y);
        entityLayer_[x][y].setIcon(mainAgent_.getIcon());
        
        
        
        while(true){
            x= rand()%dimX_;
            y= rand()%dimY_;
            
            if(entityLayer_[x][y].getIcon()==null){
                finalAgent_=new EntityFalconMillenium(x,y);
                entityLayer_[x][y].setIcon(finalAgent_.getIcon());
                break;
            }
        }
        System.out.println("Manhattan Cost desde R2D2 vale:"+manhattan_cost(mainAgent_.getPos()));
        
    }   
    
    private void agentsLayerGenManual(Point mAP, Point fAP) {
        entityLayer_ = new JLabel[dimX_][dimY_];
        
        for (int i = 0; i < dimX_; i++) {
            for (int j = 0; j < dimY_; j++) {
                entityLayer_[i][j] = new JLabel();
                add(entityLayer_[i][j], new AbsoluteConstraints(i * 32, j * 32, 32, 32));
                entityLayer_[i][j].addMouseListener(this);
            }
        }
        
        mainAgent_ = new EntityR2D2(mAP.getX(),mAP.getY());
        entityLayer_[mAP.getX()][mAP.getY()].setIcon(mainAgent_.getIcon());
        
        
        
        while(true){
            
            if(entityLayer_[fAP.getX()][fAP.getY()].getIcon()==null){
                finalAgent_ = new EntityFalconMillenium(fAP.getX(),fAP.getY());
                entityLayer_[fAP.getX()][fAP.getY()].setIcon(finalAgent_.getIcon());
                break;
            }
        }
        System.out.println("Manhattan Cost desde R2D2 vale:"+manhattan_cost(mainAgent_.getPos()));
        
    } 
    
    public Environment() {
        super();
    }

    public Environment(int x, int y) {
        super();
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        dimX_ = x;
        dimY_ = y;

        entityLayer_ = new JLabel[x][y];
        
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        agentsLayerGen();
        floorGen();
    }
    
    public Environment(int x, int y, Point mAP, Point fAP) {
        super();
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        dimX_ = x;
        dimY_ = y;

        entityLayer_ = new JLabel[x][y];
        
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        agentsLayerGenManual(mAP,fAP);
        floorGen();
    }

    
    private int rand(){
        int n = rand.nextInt();
        if(n<0)
            n*=-1;
        return n;
    }
    
    private int manhattan_cost(Point pos) {
        return finalAgent_.getPos().manhatanCost(pos);
    }
    
    public void redefine(int x, int y){
        this.removeAll();
        
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        dimX_ = x;
        dimY_ = y;

        entityLayer_ = new JLabel[x][y];
        
        agentsLayerGen();
        floorGen();
        
        System.out.println("Manhattan Cost desde R2D2 vale:"+manhattan_cost(mainAgent_.getPos()));
    }
    
    public void redefineManual(int x, int y, Point mAP, Point mAF){
        this.removeAll();
        
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        dimX_ = x;
        dimY_ = y;

        entityLayer_ = new JLabel[x][y];
        
        agentsLayerGenManual(mAP, mAF);
        floorGen();
        
        System.out.println("Manhattan Cost desde R2D2 vale:"+manhattan_cost(mainAgent_.getPos()));
    }   
    /**
     * Generates a random obstacle in x, y position
     * @param x
     * @param y 
     */
    private void genRandomObstacle(int x, int y){
        int c= rand()%2;
        switch(c){
            case 0:
                entityLayer_[x][y].setIcon(new EntityStormtrooper().getIcon());
                break;
            case 1:
                entityLayer_[x][y].setIcon(new EntityDarthVader().getIcon());
                break;
        }
    }
    
    private Point chooseBest(){
        int best=Integer.MAX_VALUE;
        int mCost;
        Point rPoint=null;
        
        if((mainAgent_.getX()+1)<dimX_){
            if(entityLayer_[mainAgent_.getX()+1][mainAgent_.getY()].getIcon()==null){
                mCost=manhattan_cost(new Point(mainAgent_.getX()+1,mainAgent_.getY()));
                if(mCost<best){
                    best=mCost;
                    rPoint=new Point(mainAgent_.getX()+1,mainAgent_.getY());
                }
            }
        }
        
        if ((mainAgent_.getX()-1)>=0){
            if(entityLayer_[mainAgent_.getX()-1][mainAgent_.getY()].getIcon()==null){
                mCost=manhattan_cost(new Point(mainAgent_.getX()-1,mainAgent_.getY()));
                if(mCost<best){
                    best=mCost;
                    rPoint=new Point(mainAgent_.getX()-1,mainAgent_.getY());
                }
            }
        }
        
        if ((mainAgent_.getY()+1)<dimX_){
            if(entityLayer_[mainAgent_.getX()][mainAgent_.getY()+1].getIcon()==null){
                mCost=manhattan_cost(new Point(mainAgent_.getX(),mainAgent_.getY()+1));
                if(mCost<best){
                    best=mCost;
                    rPoint=new Point(mainAgent_.getX(),mainAgent_.getY()+1);
                }
            }
        }
        
        if ((mainAgent_.getY()-1)>=0){
            if(entityLayer_[mainAgent_.getX()][mainAgent_.getY()-1].getIcon()==null){
                mCost=manhattan_cost(new Point(mainAgent_.getX(),mainAgent_.getY()-1));
                if(mCost<best){
                    best=mCost;
                    rPoint=new Point(mainAgent_.getX(),mainAgent_.getY()-1);
                }
            }
        }
        
        return rPoint;
    }
    
    
    public void hillClimbing(){
        Point tPoint;
        
        while(true){
            tPoint=chooseBest();
           
            if(tPoint==null){
                entityLayer_[mainAgent_.getX()][mainAgent_.getY()].setIcon(wrongIcon_);
                       //retorna un valor hacia atrás
                
                tPoint=mainAgent_.back();
                
            }else{
                
                entityLayer_[mainAgent_.getX()][mainAgent_.getY()].setIcon(routeIcon_);
                mainAgent_.moveTo(tPoint);
                
            }
            
            entityLayer_[mainAgent_.getX()][mainAgent_.getY()].setIcon(mainAgent_.getIcon());
            
            if(tPoint==null){
                JOptionPane.showMessageDialog(null, "No existe solucion!");
            }
            
            if(manhattan_cost(mainAgent_.getPos())==1){
                JOptionPane.showMessageDialog(null, "Existe solucion!");
            }

            if(manhattan_cost(mainAgent_.getPos())==1 || tPoint==null)
                break;
        }
    }
    
    /**
     * Adds %n obstacles (max 100%, min 0%)
     * @param n 
     */
    public void obstacles(double n){
        
        
        
        /**
         * Si se quiere rellenar el 100% del mapa lo más eficiente es
         * intentar rellenar cada cuadro que no esté ocupado
         */
        if(n == 100){
            int nAgents_=(dimX_*dimY_)-2;
            
            int defined=0;
            for(int i=0; i<dimX_; i++){             
                for (int j = 0; j < dimY_; j++) {
                    if(entityLayer_[i][j].getIcon()==null){
                        genRandomObstacle(i,j);
                        defined++;
                    }
                }
            }
        }else{
            if (n >= 0){
                int nAgents_=(((dimX_*dimY_)-2)*(int)n)/100;
                int x;
                int y;
                
                int defined=0;
                while (defined<nAgents_){
                    x=rand()%dimX_;
                    y=rand()%dimY_;
                    if(entityLayer_[x][y].getIcon()==null){
                        genRandomObstacle(x,y);                        
                        defined++;
                    }
                }
     
            }else{
                
            }
        }
    }

    
    public Point getMainPosition(){
        return mainAgent_.getPos();
    }
    
    public Point getFinalPosition(){
        return finalAgent_.getPos();
    }
    
    
    private void addObstacle(JLabel actual){
        if(actual.getIcon()==null){
            switch(selectedIcon_){
                case 1:
                    actual.setIcon(new EntityStormtrooper().getIcon());
                    break;
                case 2:
                    actual.setIcon(new EntityDarthVader().getIcon());
                    break;
            }
        }
    }
    
    public void setObstacleType(int icon){
        if(icon==1 || icon==2){
            selectedIcon_=icon;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel actual= (JLabel) e.getSource();
        
        if(e.getButton()==MouseEvent.BUTTON1){
            addObstacle(actual);
        }else if(e.getButton()==MouseEvent.BUTTON3){
            actual.setIcon(null);
            
            entityLayer_[mainAgent_.getX()][mainAgent_.getY()]
                    .setIcon(mainAgent_.getIcon());
            
            entityLayer_[finalAgent_.getX()][finalAgent_.getY()]
                    .setIcon(finalAgent_.getIcon());
        }
    }
   
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
