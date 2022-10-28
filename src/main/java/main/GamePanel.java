package main;

import entity.Player;
import object.SuperObject;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    final int FPS = 60;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public UI ui = new UI(this);
    public Sound music = new Sound();
    public Sound effects = new Sound();
    public Player player = new Player(this, keyH);
    public SuperObject[] objects = new SuperObject[10];
    public AssetSetter aSetter = new AssetSetter(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        playMusic(Sound.BG_MUSIC);
    }

    public boolean isOnScreen(int worldX, int worldY) {
        return (worldX > player.worldX - player.screenX - tileSize &&
                worldX < player.worldX + player.screenX + tileSize &&
                worldY > player.worldY - player.screenY - tileSize &&
                worldY < player.worldY + player.screenY + tileSize);
    }

    public boolean isOnScreen(SuperObject obj) {
        return (obj.getWorldX() > player.worldX - player.screenX - tileSize &&
                obj.getWorldX() < player.worldX + player.screenX + tileSize &&
                obj.getWorldY() > player.worldY - player.screenY - tileSize &&
                obj.getWorldY() < player.worldY + player.screenY + tileSize);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        tileM.draw(g2d);

        for (SuperObject object : objects) {
            if (object != null) {
                object.draw(g2d, this);
            }
        }

        player.draw(g2d);

        ui.draw(g2d);
        
        g2d.dispose();
    }

    public void playMusic(int index) {
        music.setFile(index);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int index) {
        effects.setFile(index);
        effects.play();
    }
}
