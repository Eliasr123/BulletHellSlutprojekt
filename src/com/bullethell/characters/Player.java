/*
 * Code latest updated 07/05/18 14:42.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
/*The player class object handles everything player related, including controls and a thread for the player itself*/
package com.bullethell.characters;

import com.bullethell.bulletTypes.BouncingBullet;
import com.bullethell.main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
public class Player extends HittableObjects implements Runnable {
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
        //New player rectangle
        coordinates = new Rectangle(playerXPos, playerYPos, 8, 8);
    }
    /**Player Bullet Thread for*/
    public void startPlayerBulletThread() {
        Runnable run = () -> {
            while (main.gameState.gameRunning) {
                addPlayerBullets();
            }
        };
        new Thread(run).start();
    }
    public void keyPressed(KeyEvent e) {
        if (!main.gameState.gamePaused) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                setXDirection(-1);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                setXDirection(1);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                setYDirection(-1);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                setYDirection(+1);
            }
            if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                cycles = 16;
            }
            if (e.getKeyCode() == KeyEvent.VK_Z) {
                bulletType = 1;
            } else if (e.getKeyCode() == KeyEvent.VK_X) {
                bulletType = 2;
            } else if (e.getKeyCode() == KeyEvent.VK_C) {
                bulletType = 3;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (main.menu.gameStateI == 1 && !main.gameState.gamePaused) {
                main.gameState.gamePaused = true;
            } else {
                System.exit(0);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (main.menu.gameStateI == 0) {
                main.menu.gameStateI = 1;
                main.gameState.gamePaused = false;
            }else if (main.menu.gameStateI == 1){
                if (main.gameState.gamePaused) {
                    main.gameState.gamePaused = false;
                }
            }
        }
        if (main.menu.gameStateI != 0 && e.getKeyCode() == KeyEvent.VK_R && main.gameState.gamePaused) {
            main.gameState.resetVariables();
        }
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            cycles = 6;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setXDirection(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            setYDirection(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_X || e.getKeyCode() == KeyEvent.VK_C) {
            bulletType = 0;
        }
    }
    private void setYDirection(int yDir) {
        yDirection = yDir;
    }
    private void setXDirection(int xDir) {
        xDirection = xDir;
    }
    public void resetHealth() {
        setHealth(10);
    }
    private void move() {
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
        while (main.gameState.gameRunning) {
            if (!main.gameState.gamePaused) {
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