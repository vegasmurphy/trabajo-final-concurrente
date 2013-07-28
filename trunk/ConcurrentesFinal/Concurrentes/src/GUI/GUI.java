package GUI;
import java.util.ArrayList;

import Misiles.*;

import javax.swing.JFrame;

public class GUI extends JFrame {

    public GUI(ArrayList<Misil> misiles) {

        add(new Board(misiles));

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