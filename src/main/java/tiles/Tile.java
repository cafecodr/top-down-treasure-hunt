package tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Tile {
    private final BufferedImage image;
    private boolean collision = false;

    public Tile(BufferedImage image, boolean collision) {
        this.image = image;
        this.collision = collision;
    }

    public Tile(BufferedImage image) {
        this(image, false);
    }

    public Tile() {
        this(null, false);
    }

    public static BufferedImage loadImage(String name) {
        try {
            String path = String.format("/tiles/%s.png", name);
            return ImageIO.read(Objects.requireNonNull(Tile.class.getResourceAsStream(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean collides() {
        return collision;
    }
}
