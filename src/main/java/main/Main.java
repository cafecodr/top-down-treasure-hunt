package main;

import javax.swing.*;

public class Main {
    final static String NAME = "2D Adventure";
    final static int MAJOR = 0;
    final static int MINOR = 0;
    final static int COMMIT = 2;
    final static String TITLE = String.format("%s v%d.%d.%d",
            NAME, MAJOR, MINOR, COMMIT);

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(TITLE);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
