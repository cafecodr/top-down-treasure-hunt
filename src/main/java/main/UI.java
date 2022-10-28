package main;

import object.ObjClock;
import object.ObjKey;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    final static double FRAME_DURATION = 1.0 / 60.0;
    final static int MESSAGE_DURATION = 120;
    Font arial18 = new Font("Arial", Font.PLAIN, 18);
    Font arial24B = new Font("Arial", Font.BOLD, 22);
    Color panel = new Color(25, 25, 25, 196);
    BufferedImage keyIcon;
    BufferedImage clockIcon;
    GamePanel gp;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;

    public UI(GamePanel gp) {
        this.gp = gp;
        keyIcon = SuperObject.loadImage("key");
        clockIcon = SuperObject.loadImage("clock");
    }

    public void draw(Graphics2D g2d) {
        if (gameFinished) {
            g2d.setColor(panel);
            g2d.fillRect((int) (gp.tileSize * 2.5), (int) (gp.tileSize * 5), (int) (gp.tileSize * 11), (int) (gp.tileSize * 2.5));

            g2d.setFont(arial24B);
            g2d.setColor(Color.WHITE);
            g2d.drawString("Congratulations! You found the TREASURE!", (int) (gp.tileSize * 3.5), (int) (gp.tileSize * 6));
            g2d.drawString("Your time was: " + prettyTime(playTime), (int) (gp.tileSize * 4.5), (int) (gp.tileSize * 6.75));

            gp.gameThread = null;
        } else {
            g2d.setColor(panel);
            g2d.fillRect((int) (gp.tileSize * 0.5), (int) (gp.tileSize * 0.5), (int) (gp.tileSize * 4), (int) (gp.tileSize * 1.5));

            g2d.setFont(arial18);
            g2d.setColor(Color.WHITE);
            g2d.drawImage(keyIcon, gp.tileSize, gp.tileSize, gp.tileSize / 2, gp.tileSize / 2, null);
            g2d.drawString(" x " + gp.player.hasKey, (int) (gp.tileSize * 1.5), (int) (gp.tileSize * 1.5));

            playTime += FRAME_DURATION;

            g2d.setColor(panel);
            g2d.fillRect((int) (gp.tileSize * 11.5), (int) (gp.tileSize * 0.5), (int) (gp.tileSize * 4), (int) (gp.tileSize * 1.5));

            g2d.setFont(arial18);
            g2d.setColor(Color.WHITE);
            g2d.drawImage(clockIcon, gp.tileSize * 12, gp.tileSize, gp.tileSize / 2, gp.tileSize / 2, null);
            g2d.drawString(prettyTime(playTime), (int) (gp.tileSize * 12.5), (int) (gp.tileSize * 1.5));

            if (messageOn) {
                g2d.setColor(panel);
                g2d.fillRect((int) (gp.tileSize * 2.5), (int) (gp.tileSize * 10), (int) (gp.tileSize * 11), (int) (gp.tileSize * 1.5));

                g2d.setColor(Color.WHITE);
                g2d.drawString(message, (int) (gp.tileSize * 3.5), (int) (gp.tileSize * 11));
                messageCounter++;

                if (messageCounter >= MESSAGE_DURATION) {
                    message = "";
                    messageOn = false;
                    messageCounter = 0;
                }
            }
        }
    }

    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }

    public String prettyTime(double time) {
        int hours;
        int minutes;
        int seconds;
        int remainder;

        seconds = (int) Math.floor(time);
        remainder = (int) Math.floor((time - seconds) * 10);
        minutes = seconds / 60;
        seconds -= minutes * 60;
        hours = minutes / 60;
        minutes -= hours * 60;

        String sminutes = "", shours = "", sseconds = "", sremainder = "";

        if (hours < 10) {
            shours += "0";
        }
        shours += String.valueOf(hours);

        if (minutes < 10) {
            sminutes += "0";
        }
        sminutes += String.valueOf(minutes);

        if (seconds < 10) {
            sseconds += "0";
        }
        sseconds += String.valueOf(seconds);

        if (remainder < 10) {
            sremainder += "0";
        }
        sremainder += String.valueOf(remainder);

        return String.format("%2s:%2s:%s.%s", shours, sminutes, sseconds, sremainder);
    }
}
