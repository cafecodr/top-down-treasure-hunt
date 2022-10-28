package main;

import object.ObjBoots;
import object.ObjChest;
import object.ObjDoor;
import object.ObjKey;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.objects[0] = new ObjKey(gp);
        gp.objects[0].setWorldTile(23, 7);

        gp.objects[1] = new ObjKey(gp);
        gp.objects[1].setWorldTile(23, 40);

        gp.objects[2] = new ObjKey(gp);
        gp.objects[2].setWorldTile(38, 8);

        gp.objects[3] = new ObjDoor(gp);
        gp.objects[3].setWorldTile(10, 11);

        gp.objects[4] = new ObjDoor(gp);
        gp.objects[4].setWorldTile(8, 28);

        gp.objects[5] = new ObjDoor(gp);
        gp.objects[5].setWorldTile(12, 22);

        gp.objects[6] = new ObjChest(gp);
        gp.objects[6].setWorldTile(10, 8);

        gp.objects[7] = new ObjBoots(gp);
        gp.objects[7].setWorldTile(37, 42);
    }
}
