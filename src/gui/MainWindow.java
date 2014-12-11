package gui;

import entities.EntityDarthVader;
import entities.EntityStormtrooper;
import entities.Point;
import gui.map.Environment;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame {

    private static String APP_NAME = "AIRobot";
    private static int DEFAULT_WIDTH = 300;
    private static int DEFAULT_HEIGHT = 80;

    private static final long serialVersionUID = 1L;
    private static MainWindow s_instance;
    
    private int A_map_;

    private Environment pantalla_ = null;

    /**
     * Botones
     */
    private JPanel buttonPanel_ = new JPanel();
    private JButton startButton_ = new JButton("Start");
    private JButton manualButton_ = new JButton("Manual");
    private JButton randomButton_ = new JButton("Random");
    private JPanel buttonPanel2_ = new JPanel();
    
    private JButton dV_ = new JButton();
    private JButton sT_ = new JButton();
    
    private JPanel infoPanel_ = new JPanel();
    private JLabel mainAgentCoords_ = new JLabel("Initial Agent Coords:");
    private JLabel finalAgentCoords_ = new JLabel("Obective Coords:");
    private JLabel entityAdd1 = new JLabel();
    
    

    private static int dimX_;
    private static int dimY_;

    
    private void refreshMainCoords(){
        
        String coordX= "" + pantalla_.getMainPosition().getX();
        String coordY= "" + pantalla_.getMainPosition().getY();
        String Text= "Initial Agent Coords: ("+coordX+","+coordY+")";
        mainAgentCoords_.setText(Text);

    }
    
    
    private void refreshFinalCoords(){
        
        String coordX= "" + pantalla_.getFinalPosition().getX();
        String coordY= "" + pantalla_.getFinalPosition().getY();
        String Text= "Obective Coords: ("+coordX+","+coordY+")";
        finalAgentCoords_.setText(Text);
        
    }
    
    private void initButtons() {
        buttonPanel_.add(startButton_);
        buttonPanel_.add(manualButton_);
        buttonPanel_.add(randomButton_);
        startButton_.setVisible(true);
        manualButton_.setVisible(true);
        randomButton_.setVisible(true);
        
        
        buttonPanel2_.add(dV_);
        buttonPanel2_.add(sT_);
        dV_.setIcon(new EntityDarthVader().getIcon());
        sT_.setIcon(new EntityStormtrooper().getIcon());
        dV_.setVisible(true);
        sT_.setVisible(true);
        
        
        /**
         * TODO
         */
        startButton_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla_!=null)
                    pantalla_.hillClimbing();
            }
        });

        manualButton_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x,y;
                Point mAP, fAP;
                dimX_ = Integer.parseInt(
                        JOptionPane
                                .showInputDialog(
                                        null, 
                                        "Insert Vector X Size: ", 
                                        "Table Size", 
                                        1));
                
                dimY_ = Integer.parseInt(
                        JOptionPane
                                .showInputDialog(
                                        null,
                                        "Insert Vector Y Size: ", 
                                        "Table Size", 
                                        1));
                
                x = Integer.parseInt(
                        JOptionPane
                                .showInputDialog(
                                        null,
                                        "Position X for R2D2: ", 
                                        "R2D2", 
                                        1));
                
                y = Integer.parseInt(
                        JOptionPane
                                .showInputDialog(
                                        null,
                                        "Position Y for R2D2: ", 
                                        "R2D2", 
                                        1));
                mAP=new Point(x,y);
                
                x = Integer.parseInt(
                        JOptionPane
                                .showInputDialog(
                                        null,
                                        "Position X for Spaceship: ", 
                                        "Falcon Millenium", 
                                        1));
                
                y = Integer.parseInt(
                        JOptionPane
                                .showInputDialog(
                                        null,
                                        "Position Y for Spaceship: ", 
                                        "Falcon Millenium", 
                                        1));
                
                fAP=new Point(x,y);
                
                
                if(pantalla_==null){
                    pantalla_ = new Environment(dimX_, dimY_, mAP, fAP);
                    JScrollPane envScrollPanel = new JScrollPane(pantalla_);
                    add(envScrollPanel, BorderLayout.CENTER);
                    pack();
                }else{
                    pantalla_.redefineManual(dimX_, dimY_, mAP, fAP);
                    pack();
                }
                
                refreshMainCoords();
                refreshFinalCoords();
                
                
            }
        });
        
        randomButton_.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dimX_ = Integer.parseInt(
                        JOptionPane
                                .showInputDialog(
                                        null, 
                                        "Insert Vector X Size: ", 
                                        "Table Size", 
                                        1));
                
                dimY_ = Integer.parseInt(
                        JOptionPane
                                .showInputDialog(
                                        null,
                                        "Insert Vector Y Size: ", 
                                        "Table Size", 
                                        1));
                
                initScrollPanel();  
                refreshMainCoords();
                refreshFinalCoords();
                
                int obstacles = Integer.parseInt(
                        JOptionPane
                                .showInputDialog(
                                        null,
                                        "Insert obstacles percent: ", 
                                        "Obstacles", 
                                        1));
                
                pantalla_.obstacles(obstacles);
                
            }
        });
        
        
        dV_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantalla_.setObstacleType(2);
            }
        });
        
        sT_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantalla_.setObstacleType(1);
            }
        });
        
    }
    
    private void initInfoLabels(){
        infoPanel_.add(mainAgentCoords_);
        infoPanel_.add(finalAgentCoords_);
        mainAgentCoords_.setVisible(true);
        finalAgentCoords_.setVisible(true);
        
    }

    private void initScrollPanel() {
        if(pantalla_==null){
            pantalla_ = new Environment(dimX_, dimY_);
            JScrollPane envScrollPanel = new JScrollPane(pantalla_);
            add(envScrollPanel, BorderLayout.CENTER);
            pack();
        }else{
            pantalla_.redefine(dimX_,dimY_);
            pack();
        }
        
    }

    private void initComponents() {
        add(buttonPanel_, BorderLayout.NORTH);
        add(buttonPanel2_, BorderLayout.WEST);
        add(infoPanel_, BorderLayout.SOUTH);
        initButtons();
        initInfoLabels();
    }

    private MainWindow() {
        setLayout(new BorderLayout());

        initComponents();
              
        pack();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        MainWindow wnd = MainWindow.getInstance();

        wnd.setTitle(APP_NAME);
        wnd.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wnd.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        wnd.setLocation(0,0);
        wnd.setVisible(true);
    }

    /**
     * @return Instancia única de la clase.
     */
    public static MainWindow getInstance() {

        if (s_instance == null) {
            s_instance = new MainWindow();
        }
        return s_instance;
    }

}
