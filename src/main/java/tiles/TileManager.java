package tiles;

import main.GamePanel;

import java.awt.*;
import java.util.HashMap;


public class TileManager {
    static final int I_GRASS = 0, I_WALL = 1, I_WATER = 2, I_EARTH = 3, I_TREE = 4, I_SAND = 5;
    GamePanel gp;
    private HashMap<Integer, Tile> tiles;
    public MapManager mManager;
    private int worldNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        worldNum = 1;
        mManager = new MapManager(gp);

        loadTiles();
        mManager.loadMap(mapName());
    }

    private String mapName() {
        String num = "";
        if (worldNum < 10) {
            num += "0";
        }
        num += String.valueOf(worldNum);
        return String.format("world%s", num);
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
            int tileNum = mManager.getTileNum(worldCol, worldRow);

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

    public Tile getTile(int index) {
        return tiles.get(index);
    }

    public Tile getTile(int mapCol, int mapRow) {
        int index = mManager.getTileNum(mapCol, mapRow);
        return tiles.get(index);
    }
}
