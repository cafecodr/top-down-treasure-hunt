package object;

import main.GamePanel;

public class ObjDoor extends SuperObject {
    public ObjDoor(GamePanel gp) {
        super(gp, SuperObject.loadImage("door"), "Door", true);
    }
}

