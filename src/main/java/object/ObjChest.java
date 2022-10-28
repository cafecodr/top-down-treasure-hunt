package object;

import main.GamePanel;

public class ObjChest extends SuperObject {
    public ObjChest(GamePanel gp) {
        super(gp, SuperObject.loadImage("chest"), "Chest");
    }
}

