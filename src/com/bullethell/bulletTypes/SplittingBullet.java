/*
 * Code latest updated 07/05/18 13:28.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
/*Sub class of Bullet handles Splitting bullets and its logic */
package com.bullethell.bulletTypes;

import com.bullethell.characters.HittableObjects;
import com.bullethell.main.Main;

import java.awt.*;

import static java.lang.Math.ceil;
public class SplittingBullet extends Bullet {
    private Main main;
    private int ticks;
    private int initialTicks;
    public SplittingBullet(int x, int y, int xDir, int yDir, int width, int height, Color bColor, HittableObjects origin, int damage,Main main,int ticks) {
        super(x, y, xDir, yDir, width, height, bColor, origin, damage);
        this.main = main;
        this.ticks = ticks;
        initialTicks = ticks;
    }
    public void move() {
        ticks--;
        if (ticks == 0) {
            //after Ticks amount of time bullet will split into more smaller bullets
            if (super.bCoordinates.width > 9 && super.bCoordinates.height > 9)  {
                ticks =(int)ceil(initialTicks * 1.6);
                main.bulletManager.addBullet(new SplittingBullet(super.bCoordinates.x, super.bCoordinates.y, -2,0,
                        super.bCoordinates.width -4,super.bCoordinates.height -4,super.bColor,origin,damage,main,ticks));
                main.bulletManager.addBullet(new SplittingBullet(super.bCoordinates.x, super.bCoordinates.y, 2,0,
                        super.bCoordinates.width -4, super.bCoordinates.height -4,super.bColor,origin,damage,main,ticks));
                main.bulletManager.addBullet(new SplittingBullet(super.bCoordinates.x, super.bCoordinates.y, -1,1,
                        super.bCoordinates.width -4, super.bCoordinates.height -4,super.bColor,origin,damage,main,ticks));
                main.bulletManager.addBullet(new SplittingBullet(super.bCoordinates.x, super.bCoordinates.y, 1,1,
                        super.bCoordinates.width -4, super.bCoordinates.height -4,super.bColor,origin,damage,main,ticks));
                main.bulletManager.addBullet(new SplittingBullet(super.bCoordinates.x, super.bCoordinates.y, 0,2,
                        super.bCoordinates.width -4, super.bCoordinates.height -4,super.bColor,origin,damage,main,ticks));
                main.bulletManager.addBullet(new SplittingBullet(super.bCoordinates.x, super.bCoordinates.y, 0,-2,
                        super.bCoordinates.width -4, super.bCoordinates.height -4,super.bColor,origin,damage,main,ticks));
                main.bulletManager.addBullet(new SplittingBullet(super.bCoordinates.x, super.bCoordinates.y, -1,-1,
                        super.bCoordinates.width -4, super.bCoordinates.height -4,super.bColor,origin,damage,main,ticks));
                main.bulletManager.addBullet(new SplittingBullet(super.bCoordinates.x, super.bCoordinates.y, 1,-1,
                        super.bCoordinates.width -4, super.bCoordinates.height -4,super.bColor,origin,damage,main,ticks));
            }
            if (super.bCoordinates.width > 9 && super.bCoordinates.height > 9){
                main.bulletManager.bulletTrackerKilled.add(this);
            }
        }
        super.move();
    }
}
