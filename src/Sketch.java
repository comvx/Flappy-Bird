/**
 * author (Max Ole Kleffmann ~ null)
 * version 2.0
 * date (11th of January 2019)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Sketch implements ActionListener, KeyListener {
    public static Sketch sketch;
    public JFrame jFrame;
    public boolean gameStatus = true;
    public EntityHandler entityHandler = new EntityHandler();
    public Random random = new Random();
    public int cols, rows, scl = 0, delay = 60, total, width, height, countDelay;
    public Timer timer = new Timer(1000/delay, this);
    public ArrayList<Point> snakeParts;

    public Sketch() {
        jFrame = new JFrame("Snake - game");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(entityHandler);
        jFrame.setResizable(true);
        jFrame.setSize(600, 600);
        jFrame.addKeyListener(this);
        jFrame.setBackground(new Color(52, 52, 52));

        if (gameStatus) {
            startGrame();
            timer.start();
        }
    }

    public static void main(String[] args) {
        sketch = new Sketch();
    }

    private void startGrame() {
        scl = 40;
        total = 0;

        width = 600;
        height = 600;

        cols = width / scl;
        rows = height / scl;

        countDelay = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStatus) {
            entityHandler.collision();
            entityHandler.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (entityHandler.gameStatus) {
            if (key == KeyEvent.VK_UP | key == KeyEvent.VK_W) {
                    entityHandler.birdJump();
            }
        } else {
            if (key == KeyEvent.VK_ENTER) {
            }
            if (key == KeyEvent.VK_BACK_SPACE) {
                JOptionPane.showMessageDialog(null, "See ya \n ~~~~~~~~~~ \n by: \n comvx ~ Max Ole Kleffmann \n MLGenie ~ Noah Spreen", "Snake - game", JOptionPane.CLOSED_OPTION);
                System.exit(0);
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}