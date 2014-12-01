/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.map;

import entities.*;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author Jaime
 */
public class Environment extends JPanel {
    //generador de números aleatorios
    private java.util.Random rand= new java.util.Random(System.nanoTime());   
    
    private JLabel floorLayer_[][];     //Tablero del suelo
    private JLabel entityLayer_[][];    //Tablero de entidades
    int dimX_, dimY_;                   //Dimensiones del tablero

    private Entity mainAgent_;          //Agente principal (robot) (R2D2)
    private Entity finalAgent_;         //Objetivo del agente principal (Nave)
    
    private Entity agents_[];           //lista de agentes
    private int nAgents_;               //numero de agentes
    
    
    
    
    
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
            }
        }
        
        
        //Adding random R2D2
        int x= rand()%dimX_;
        int y= rand()%dimY_;
        
        System.out.println(x);
        System.out.println(y);
        
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

    
    private int rand(){
        int n = rand.nextInt();
        if(n<0)
            n*=-1;
        return n;
    }
    
    public void redefine(int x, int y){
        this.removeAll();
        
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        dimX_ = x;
        dimY_ = y;

        entityLayer_ = new JLabel[x][y];
        
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        agentsLayerGen();
        floorGen();
        
        
    }
    
    
    /**
     * Generates a random obstacle in x, y position
     * @param x
     * @param y 
     */
    private Entity genRandomObstacle(int x, int y){
        int c= rand()%2;
        Entity entidad=null;
        switch(c){
            case 0:
                entityLayer_[x][y].setIcon(new EntityStormtrooper().getIcon());
                return new EntityStormtrooper(x,y);
            case 1:
                entityLayer_[x][y].setIcon(new EntityDarthVader().getIcon());
                return new EntityStormtrooper(x,y);
        }
        return entidad;
    }
    
    
    
    
    /**
     * Adds %n obstacles (max 100%, min 0%)
     * @param n 
     */
    public void obstacles(double n){
        
        if (n > 100){
            n=100;
        }
        
        int obstacles = (dimX_* dimY_)*(int)n; //number of obstacles
        
        
        /**
         * Si se quiere rellenar el 100% del mapa lo más eficiente es
         * intentar rellenar cada cuadro que no esté ocupado
         */
        if(n == 100){
            nAgents_=(dimX_*dimY_)-2;
            agents_ = new Entity[nAgents_-2];
            
            int defined=0;
            for(int i=0; i<dimX_; i++){             
                for (int j = 0; j < dimY_; j++) {
                    if(entityLayer_[i][j].getIcon()==null){
                        agents_[defined]=genRandomObstacle(i,j);
                        defined++;
                    }
                }
            }
        }else{
            if (n >= 0){
                nAgents_=(((dimX_*dimY_)-2)*(int)n)/100;
                agents_ = new Entity[nAgents_-2];
                int x;
                int y;
                
                int defined=0;
                while (defined<nAgents_){
                    x=rand()%dimX_;
                    y=rand()%dimY_;
                    if(entityLayer_[x][y].getIcon()==null){
                        agents_[defined]=genRandomObstacle(x,y);                        
                        defined++;
                    }
                }
     
            }else{
                
            }
        }
    }

}
