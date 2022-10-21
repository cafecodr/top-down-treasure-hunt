package tiles;

import main.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class MapManager {
    GamePanel gp;
    private int[][] mapTileNum;

    public MapManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
    }

    public int getTileNum(int col, int row) {
        return mapTileNum[col][row];
    }

    public void loadMap(String mapName) {
        String filename = String.format("/maps/%s.txt", mapName);
        try {
            InputStream is = getClass().getResourceAsStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

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
}
