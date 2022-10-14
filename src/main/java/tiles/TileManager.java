package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];

        getTileImage();
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            g2d.drawImage(tiles[0].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

    public void olddraw(Graphics2D g2d) {
        g2d.drawImage(tiles[1].image, 0, 0, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[1].image, 96, 0, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[1].image, 144, 0, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[1].image, 192, 0, gp.tileSize, gp.tileSize, null);

        g2d.drawImage(tiles[1].image, 0, 48, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[0].image, 48, 48, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[0].image, 96, 48, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[0].image, 144, 48, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[1].image, 192, 48, gp.tileSize, gp.tileSize, null);

        g2d.drawImage(tiles[1].image, 0, 96, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[0].image, 48, 96, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[0].image, 96, 96, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[0].image, 144, 96, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[0].image, 192, 96, gp.tileSize, gp.tileSize, null);

        g2d.drawImage(tiles[1].image, 0, 144, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[0].image, 48, 144, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[0].image, 96, 144, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[0].image, 144, 144, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[1].image, 192, 144, gp.tileSize, gp.tileSize, null);

        g2d.drawImage(tiles[1].image, 0, 192, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[2].image, 48, 192, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[2].image, 96, 192, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[2].image, 144, 192, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tiles[1].image, 192, 192, gp.tileSize, gp.tileSize, null);
    }
}
