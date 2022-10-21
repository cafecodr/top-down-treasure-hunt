package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjClock extends SuperObject {
    public ObjClock() {
        name = "Clock";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/clock.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
