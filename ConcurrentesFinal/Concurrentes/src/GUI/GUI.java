package GUI;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import Misiles.*;

import javax.swing.JFrame;

public class GUI extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUI(CopyOnWriteArrayList<MisilEnemigo> misiles,CopyOnWriteArrayList<MisilDefensivo> misilesDef) {

        add(new Board(misiles,misilesDef));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setTitle("R - Type");
        setResizable(false);
        setVisible(true);
    }

//    public static void main(String[] args) {
//        new GUI();
//    }
}