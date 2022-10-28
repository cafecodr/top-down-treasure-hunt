package object;

import main.GamePanel;

public class ObjKey extends SuperObject {
    public ObjKey(GamePanel gp) {
        super(gp, SuperObject.loadImage("key"), "Key");
    }
}
