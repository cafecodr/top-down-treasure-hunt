package tiles;

import main.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


public class TileManager {
    static final int I_GRASS = 0, I_WALL = 1, I_WATER = 2, I_EARTH = 3, I_TREE = 4, I_SAND = 5;
    GamePanel gp;
    private HashMap<Integer, Tile> tiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        loadTiles();
        loadMap("world01");
    }

    public void loadMap(String mapName) {
        String filename = String.format("/maps/%s.txt", mapName);
        try {
            InputStream is = getClass().getResourceAsStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTiles() {
        tiles = new HashMap<>();

        tiles.put(I_GRASS, new TlGrass());
        tiles.put(I_WALL, new TlWall());
        tiles.put(I_WATER, new TlWater());

        tiles.put(I_EARTH, new TlEarth());
        tiles.put(I_TREE, new TlTree());
        tiles.put(I_SAND, new TlSand());
    }

    public void draw(Graphics2D g2d) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (gp.isOnScreen(worldX, worldY)) {
                g2d.drawImage(tiles.get(tileNum).getImage(), screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
