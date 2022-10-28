package main;

import entity.Entity;
import tiles.Tile;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWX = entity.worldX + entity.solidArea.x;
        int entityRightWX = entityLeftWX + entity.solidArea.width;
        int entityTopWY = entity.worldY + entity.solidArea.y;
        int entityBottomWY = entityTopWY + entity.solidArea.height;

        int entityLeftCol = entityLeftWX / gp.tileSize;
        int entityRightCol = entityRightWX / gp.tileSize;
        int entityTopRow = entityTopWY / gp.tileSize;
        int entityBottomRow = entityBottomWY / gp.tileSize;

        Tile tile1;
        Tile tile2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWY - entity.speed) / gp.tileSize;
                tile1 = gp.tileM.getTile(entityLeftCol, entityTopRow);
                tile2 = gp.tileM.getTile(entityRightCol, entityTopRow);
                break;
            case "down":
                entityBottomRow = (entityBottomWY + entity.speed) / gp.tileSize;
                tile1 = gp.tileM.getTile(entityLeftCol, entityBottomRow);
                tile2 = gp.tileM.getTile(entityRightCol, entityBottomRow);
                break;
            case "left":
                entityLeftCol = (entityLeftWX - entity.speed) / gp.tileSize;
                tile1 = gp.tileM.getTile(entityLeftCol, entityTopRow);
                tile2 = gp.tileM.getTile(entityLeftCol, entityBottomRow);
                break;
            case "right":
                entityRightCol = (entityRightWX + entity.speed) / gp.tileSize;
                tile1 = gp.tileM.getTile(entityRightCol, entityTopRow);
                tile2 = gp.tileM.getTile(entityRightCol, entityBottomRow);
                break;
            default:
                return;
        }

        if (tile1.collides() || tile2.collides()) {
            entity.collisionOn = true;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.objects.length; i++) {
            if (gp.objects[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.objects[i].setToWorldArea();

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].getSolidArea())) {
                            if (gp.objects[i].collides()) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                            gp.objects[i].interact(entity);
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].getSolidArea())) {
                            if (gp.objects[i].collides()) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                            gp.objects[i].interact(entity);
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].getSolidArea())) {
                            if (gp.objects[i].collides()) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                            gp.objects[i].interact(entity);
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].getSolidArea())) {
                            if (gp.objects[i].collides()) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                            gp.objects[i].interact(entity);
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                gp.objects[i].setToDefaultArea();
            }
        }

        return index;
    }
}
