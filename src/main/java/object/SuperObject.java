package object;

import entity.Entity;
import main.GamePanel;
import tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SuperObject {
    private final GamePanel gp;
    private final BufferedImage image;
    private final String name;
    private boolean collision;
    private int worldX, worldY;
    private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    private int solidAreaDefaultX = 0, solidAreaDefaultY = 0;

    public SuperObject(GamePanel gp, BufferedImage image, String name, boolean collision) {
        this.gp = gp;
        this.image = image;
        this.name = name;
        this.collision = collision;
    }

    public SuperObject(GamePanel gp, BufferedImage image, String name) {
        this(gp, image, name, false);
    }

    public SuperObject(GamePanel gp, BufferedImage image) {
        this(gp, image, "Object", false);
    }

    public SuperObject(GamePanel gp) {
        this(gp, null, "Object", false);
    }

    public void draw(Graphics2D g2d, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (gp.isOnScreen(this)) {
            g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public void interact(Entity entity) {
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean collides() {
        return collision;
    }

    public String getName() {
        return name;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getSolidX() {
        return solidArea.x;
    }

    public int getSolidY() {
        return solidArea.y;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setToWorldArea() {
        int sx = worldX + solidArea.x,
                sy = worldY + solidArea.y;
        solidArea.setLocation(sx, sy);
    }

    public void setToDefaultArea() {
        solidArea.setLocation(solidAreaDefaultX, solidAreaDefaultY);
    }

    public boolean intersects(Rectangle rect) {
        return solidArea.intersects(rect);
    }

    public void setSolidLocation(int x, int y) {
        this.solidArea.setLocation(x, y);
    }

    public void setWorldLocation(int x, int y) {
        worldX = x;
        worldY = y;
    }

    public void setWorldTile(int xTile, int yTile) {
        worldX = xTile * gp.tileSize;
        worldY = yTile * gp.tileSize;
    }

    public static BufferedImage loadImage(String name) {
        try {
            String path = String.format("/objects/%s.png", name);
            return ImageIO.read(Objects.requireNonNull(Tile.class.getResourceAsStream(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
