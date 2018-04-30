/*
 * Code latest updated 28/04/18 14:58.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
package com.bullethell.main;
import com.bullethell.bulletTypes.Bullet;
import com.bullethell.characters.HittableObjects;

import java.awt.*;
public class BulletManager {
    private Main main;
    BulletManager(Main main) {
        this.main = main;
    }
    public void addBullet(int x, int y, int xdir, int ydir, int bwidth, int bheight, Color bColor, HittableObjects origin, int damage){
        main.bulletNew.add(new Bullet(x,y,xdir,ydir,bwidth,bheight,bColor,origin,damage));
    }
    public void addBullet(Bullet b) {
        main.bulletNew.add(b);
    }
    private void removeBullet(Bullet bullet) {
        if (bullet.bCoordinates.y <= 0 || bullet.bCoordinates.y >= 690 || bullet.bCoordinates.x <= 0 || bullet.bCoordinates.x >= 490) {
            main.bulletTrackerKilled.add(bullet);
        }
    }
    void updateBullet(){
        for (Bullet trackedBullet : main.bulletTracker) {
            if (trackedBullet != null) {
                removeBullet(trackedBullet);
                trackedBullet.move();
            }
        }
        main.bulletTracker.removeAll(main.bulletTrackerKilled);
        main.bulletTrackerKilled.clear();
    }
}
