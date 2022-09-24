package org.example.view;

import org.example.controller.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends Canvas implements ActionListener {

    private static final int FRAME_WIDTH =  500;
    private static final int FRAME_HEIGHT =  750;
    private static final int SQUARE_SIZE = 25;

    private final Engine engine;

    private final Timer repaintTimer;

    GUI() {
        int nRows = FRAME_HEIGHT / SQUARE_SIZE;
        int nCols = FRAME_WIDTH / SQUARE_SIZE;
        int stepInterval = 500;
        engine = new Engine(nRows, nCols, stepInterval);
        repaintTimer = new Timer(stepInterval, this);
        repaintTimer.start();
    }

    public void paint(Graphics g) {
        drawGrid(g);
        engine.drawPieces(g, SQUARE_SIZE);
    }

    private void drawGrid(Graphics g) {
        for (int i = 0; i <=  FRAME_HEIGHT / SQUARE_SIZE; i++) {
            g.drawLine(0, i * SQUARE_SIZE, FRAME_WIDTH, i * SQUARE_SIZE); //horizontal line
            g.drawString(""+i, 0, i * SQUARE_SIZE);
        }
        for (int i = 0; i <= FRAME_WIDTH / SQUARE_SIZE; i++) {
            g.drawLine(i * SQUARE_SIZE, 0, i * SQUARE_SIZE, FRAME_HEIGHT);//vertical line
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(repaintTimer)) {
            if (engine.isGameOver()){
                ((Timer)e.getSource()).stop();
            }
            repaint();

        }
    }

    public static void createAndShowGui() {
        JFrame frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        GUI gui = new GUI();
        frame.add(gui);
        frame.addKeyListener(gui.getKeyListener());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH + 50, FRAME_HEIGHT + 50);
        frame.setVisible(true);
    }

    private KeyListener getKeyListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    engine.left();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    engine.right();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    engine.down();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    engine.rotate();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        };
    }
}
