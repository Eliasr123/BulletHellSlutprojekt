package com.bullethell.characters;
import com.bullethell.bulletTypes.BouncingBullet;
import com.bullethell.bulletTypes.Bullet;
import com.bullethell.bulletTypes.SplittingBullet;
import com.bullethell.main.Main;
import javax.swing.*;
import java.awt.*;

public class Enemy extends HittableObjects {
    //Global Variables
    private  Image eIcon = new ImageIcon("resource/characters/Enemy.png").getImage();
    private int xDirection,yDirection;
    private int damageNormal = 1;
    public Rectangle newCoordinates = null;
    private Main main;
    private int xOffset = 25;
    private int yOffset = 50;
    private int splittingShotReady = 10;
    //Enemy object
    public Enemy(int enemyX, int enemyY, Main main) {
            this.main = main;
            resetHealth();
            coordinates = new Rectangle(enemyX, enemyY,50,50);
        }
        //This starts a thread and handles movement
    public void startMovement() {
        Runnable run = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while(main.running) {
                    if (!main.gamePaused) {
                        move();
                    }
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(run).start();
    }
    public void resetHealth() {
        setHealth(100);
    }
    //Picks a random Coordinate to move to.
    private Rectangle pickMoveLocation() {
        Rectangle newLocation = new Rectangle();
         newLocation.x = 50 + (int) Math.floor(Math.random() * 365);
         newLocation.y = 50 + (int) Math.floor(Math.random() * 280);
         newLocation.setLocation(newLocation.x,newLocation.y);
         return newLocation;
    }
    //moves to a coordinate picked by pickMoveLocation
    private void move() {
        if(newCoordinates == null) {
            newCoordinates = pickMoveLocation();

        }else if (coordinates.x == newCoordinates.x && coordinates.y == newCoordinates.y){

            newCoordinates = pickMoveLocation();
            chooseAttack();
        } else {
            if (newCoordinates.x > coordinates.x) {
                xDirection = 1;
            } else if (newCoordinates.x < coordinates.x) {
                xDirection = -1;
            } else {
                xDirection = 0;
            }
            if (newCoordinates.y > coordinates.y) {
                yDirection = 1;
            } else if (newCoordinates.y < coordinates.y) {
                yDirection = -1;
            } else {
                yDirection = 0;
            }
            coordinates.y += yDirection;
            coordinates.x += xDirection;
        }
    }
    private void chooseAttack() {
        if (splittingShotReady != 0) {
            splittingShotReady--;
            for (int i = 0; i <= 1; i++)
                chooseAttackPattern();
        } else {
            splittingShotReady = 150;
            splittingAttack();
        }
    }
    //Draws The enemy
    public void draw(Graphics g){
        g.drawImage(eIcon, coordinates.x, coordinates.y,50,50, null);
        g.setColor(new Color(255,255,255,0));
        g.fillOval(coordinates.x, coordinates.y, coordinates.width, coordinates.height);
    }
    //Randomly choose a attack pattern.
    private void chooseAttackPattern() {

        int randomNumber = (int) (Math.random() * 2);
        if (randomNumber == 0) {
            strongAttack();
        } else if (randomNumber == 1) {
            normalAttack();
        }
    }
    private void strongAttack() {
        int bSizeXY = 12;
        int upYOffset = -3;
        Color color[] = {new Color(255, 153, 51,255),new Color(255,51,0,255),new Color(102,0,102,255)};
        for (int i = 0; i <= 5; i++) {
            int cModify = (int) Math.floor(Math.random() * 3);
            int damageStrong = 1;
            /** downwards bullets */
            main.bulletManager.addBullet(new Bullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, 4, 0, bSizeXY, bSizeXY, color[cModify], this, damageStrong));
            main.bulletManager.addBullet(new Bullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, -4, 0, bSizeXY, bSizeXY, color[cModify], this, damageStrong));

            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, 3, 1, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, 2, 2, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, 1, 3, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, 0, 4, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, -1, 3, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, -2, 2, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, -3, 1, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));

            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, 2, 1, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, 1, 2, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, 0, 3, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, 0, 4, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, 0, 3, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, -1, 2, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, -2, 1, bSizeXY, bSizeXY, color[cModify], true, false, this, damageStrong));

            /** upward bullets */
            main.bulletManager.addBullet(new Bullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, 4, 0, bSizeXY, bSizeXY, color[cModify], this, damageStrong));
            main.bulletManager.addBullet(new Bullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, -4, 0, bSizeXY, bSizeXY, color[cModify], this, damageStrong));

            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, 3, -1, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, 2, -2, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, 1, -3, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, 0, -4, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, -1, -3, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, -2, -2, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, -3, -1, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));

            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, 2, -1, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, 1, -2, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, 0, -3, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, 0, -4, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, 0, -3, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, -1, -2, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));
            main.bulletManager.addBullet(new BouncingBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + upYOffset, -2, -1, bSizeXY, bSizeXY, color[cModify], true, true, this, damageStrong));

            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void normalAttack() {
        int bSizeXY = 8;
        //Shoot in a random direction
        for (int i = 0; i < 7;i++) {

            main.bulletManager.addBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, 1, 3, bSizeXY, bSizeXY, Color.RED, this,damageNormal);
            main.bulletManager.addBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, 0, 2, bSizeXY, bSizeXY, Color.RED, this,damageNormal);
            main.bulletManager.addBullet(coordinates.x + xOffset-(bSizeXY/2), coordinates.y + yOffset, -1, 3, bSizeXY, bSizeXY, Color.RED, this,damageNormal);
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void splittingAttack(){
        int bSizeXY = 20;
        main.bulletManager.addBullet(new SplittingBullet(coordinates.x+xOffset-(bSizeXY/2),coordinates.y + yOffset,0,3,bSizeXY,bSizeXY,Color.magenta,this,damageNormal,main,15));
    }

}
