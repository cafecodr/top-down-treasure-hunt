package object;

import main.GamePanel;

public class ObjClock extends SuperObject {
    public ObjClock(GamePanel gp) {
        super(gp, SuperObject.loadImage("clock"), "Clock");
    }
}
