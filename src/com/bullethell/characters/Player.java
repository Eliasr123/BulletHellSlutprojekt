/*
 * Code latest updated 27/04/18 10:35.
 * Copyright © 2018.  By Elias Renman. All rights reserved
 */

package com.bullethell.characters;

import com.bullethell.bulletTypes.BouncingBullet;
import com.bullethell.main.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class Player extends HittableObjects implements Runnable {
    //Global Variables
    int playerXPos, playerYPos;
    private int yDirection, xDirection;
    private int cycles = 6;
    private int bulletType = 0;
    private Main main;
    private Image forwardP = new ImageIcon("resource/characters/ForwardP.png").getImage();
    private Image leftP = new ImageIcon("resource/characters/LeftP.png").getImage();
    private Image rightP = new ImageIcon("resource/characters/RightP.png").getImage();
    public Player(int playerXPos, int playerYPos, Main main) {
        resetHealth();
        this.main = main;
        this.playerXPos = playerXPos;
        this.playerYPos = playerYPos;
        coordinates = new Rectangle(playerXPos, playerYPos, 8, 8);
    }

    /**Player Bullet Thread for*/
    public void startPlayerBulletThread() {
        Runnable run = new Runnable() {
            public void run() {
                while (main.running) {
                    addPlayerBullets();
                }
            }
        };
        new Thread(run).start();
    }


    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_LEFT) {
            setXDirection(-1);
        }
        if (e.getKeyCode() == e.VK_RIGHT) {
            setXDirection(1);
        }
        if (e.getKeyCode() == e.VK_UP) {
            setYDirection(-1);
        }
        if (e.getKeyCode() == e.VK_DOWN) {
            setYDirection(+1);
        }
        if (e.getKeyCode() == e.VK_SHIFT) {
            cycles = 16;
        }
        if (e.getKeyCode() == e.VK_Z) {
            bulletType = 1;

        }
        if (e.getKeyCode() == e.VK_X) {
            bulletType = 2;

        }
        if (e.getKeyCode() == e.VK_C) {
            bulletType = 3;

        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            main.gamePaused = true;
            main.gameStateManager.gamePause();
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SHIFT) {
            cycles = 6;
        }
        if (e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT) {
            setXDirection(0);
        }
        if (e.getKeyCode() == e.VK_UP || e.getKeyCode() == e.VK_DOWN) {
            setYDirection(0);
        }
        if (e.getKeyCode() == e.VK_Z || e.getKeyCode() == e.VK_X || e.getKeyCode() == e.VK_C) {
            bulletType = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {}
    }

    private void setYDirection(int ydir) {
        yDirection = ydir;
    }

    private void setXDirection(int xdir) {
        xDirection = xdir;
    }
    public void resetHealth() {
        setHealth(15);
    }

    public void move() {
        //Movement handling.
        coordinates.y += yDirection;
        coordinates.x += xDirection;
        //Border checking
        int borderXY = 50;
        if (coordinates.y <= +borderXY)
            coordinates.y = borderXY;
        if (coordinates.y >= 590 + borderXY)
            coordinates.y = 590 + borderXY;
        if (coordinates.x <= borderXY)
            coordinates.x = borderXY;
        if (coordinates.x >= 390 + borderXY)
            coordinates.x = 390 + borderXY;
    }

    public void draw(Graphics g) {
        if (xDirection == -1) {
            g.drawImage(leftP, coordinates.x - 21, coordinates.y - 20, 50, 50, null);
        } else if (xDirection == 1) {
            g.drawImage(rightP, coordinates.x - 21, coordinates.y - 20, 50, 50, null);
        } else {
            g.drawImage(forwardP, coordinates.x - 21, coordinates.y - 20, 50, 50, null);
        }
        g.setColor(new Color(255, 255, 255, 200));
        g.fillOval(coordinates.x, coordinates.y, coordinates.width, coordinates.height);
    }

    @Override
    public void run() {
        while (main.running) {
            if (!main.gamePaused) {
                move();
            }
            try {
                Thread.sleep(cycles);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void addPlayerBullets() {
        int sleepMillis = 70;
        if (bulletType == 1) {
            int damageNormal = 1;
            main.bulletManager.addBullet(coordinates.x + 1, coordinates.y - 20, 0, -4, 7, 7, Color.CYAN, this, damageNormal);
            sleepMillis = 70;
        }
        if (bulletType == 2) {
            sleepMillis = 1100;
            int damageScatter = 10;
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + 1, coordinates.y - 20, 0, -1, 15, 15, Color.CYAN,true,false, this, damageScatter));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + 1, coordinates.y - 20, -1, -2, 15, 15, Color.CYAN,true,false, this, damageScatter));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + 1, coordinates.y - 20, 1, -2, 15, 15, Color.CYAN,true,false, this, damageScatter));
        }
        if (bulletType == 3) {
            sleepMillis = 200;
            int damageStrong = 5;
            main.bulletManager.addBullet(coordinates.x - 4, coordinates.y - 20, 0, -2, 15, 15, Color.CYAN, this, damageStrong);
        }
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}