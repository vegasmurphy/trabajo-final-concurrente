package GUI;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import Misiles.Misil;
import Misiles.MisilEnemigo;
import Misiles.Vector;


public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private MisilEnemigo craft;
    private Misil[] misiles;
    public Board(Misil[] misiles) {
    	this.misiles=misiles;
       // addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        //craft = new MisilEnemigo(new Vector(500,500,100),new Vector(-1,-1,0),0);

        timer = new Timer(5, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
       // g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
       g2d.setColor(Color.red);
       //for(Misil craft:misiles){
       Misil craft=misiles[0];
       g2d.drawLine((int)(250-(craft.getPosicion().getX()/200)),(int)(250-(craft.getPosicion().getY()/200)),(int)(251-(craft.getPosicion().getX()/200)),(int)(251-(craft.getPosicion().getY()/200)));
      // }
       g2d.drawOval(200, 200, 100, 100);
       g2d.setColor(Color.blue);
       g2d.drawOval(0, 0, 500, 500);
        //g2d.drawLine(50, 50, 100, 100);
       // ArrayList ms = craft.getMissiles();

     /*   for (int i = 0; i < ms.size(); i++ ) {
            Missile m = (Missile) ms.get(i);
            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
        }*/

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    public void actionPerformed(ActionEvent e) {
     

        //craft.avanzar();
        repaint();  
    }


   
}