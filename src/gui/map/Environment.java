/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.map;

import entities.Entity;
import entities.EntityR2D2;
import java.awt.Color;
import java.awt.Dimension;
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

    private JLabel floorLayer_[][];  //  Tablero del suelo
    private JLabel entityLayer_[][]; //  Tablero de entidades
    int dimX_, dimY_;           //  Dimensiones del tablero

    private Entity agents_[];   //  Lista de agentes y obstáculo
    int nAgents_;               //  Lúmero de agentes obstáculo
    private Entity mainAgent_;  //  Agente principal (robot) (R2D2)

    private void floorGen() {
        ImageIcon floorTile_ = new ImageIcon( //  Tile que
              getClass() //  representa
                .getResource("floor.png"));     //  el suelo

        floorLayer_ = new JLabel[dimX_][dimY_];
        
        for (int i = 0; i < dimX_; i++) {
            for (int j = 0; j < dimY_; j++) {
                floorLayer_[i][j] = new JLabel();
                floorLayer_[i][j].setIcon(floorTile_);
                add(floorLayer_[i][j], new AbsoluteConstraints(i * 32, j * 32, 32, 32));
            }
        }
    }

    public void agentsGen() {
        entityLayer_ = new JLabel[dimX_][dimY_];
        
        for (int i = 0; i < dimX_; i++) {
            for (int j = 0; j < dimY_; j++) {
                entityLayer_[i][j] = new JLabel();
                add(entityLayer_[i][j], new AbsoluteConstraints(i * 32, j * 32, 32, 32));
            }
        }
        
        mainAgent_ = new EntityR2D2();
        entityLayer_[5][3].setIcon(mainAgent_.getIcon());
        
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
        agentsGen();
        floorGen();
    }

    private Icon ImageIcon(URL resource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
