/*
 * Code latest updated 07/05/18 11:29.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
package com.bullethell.main;
import com.bullethell.bulletTypes.Bullet;
import com.bullethell.characters.HittableObjects;

import java.awt.*;
import java.util.ArrayList;

public class BulletManager {
    //Bullet objects
    ArrayList<Bullet> bulletNew = new ArrayList<>();
    ArrayList<Bullet> bulletTracker = new ArrayList<>();
    public ArrayList<Bullet> bulletTrackerKilled = new ArrayList<>();


    BulletManager() {

    }
    public void addBullet(int x, int y, int xdir, int ydir, int bwidth, int bheight, Color bColor, HittableObjects origin, int damage){
        bulletNew.add(new Bullet(x,y,xdir,ydir,bwidth,bheight,bColor,origin,damage));
    }
    public void addBullet(Bullet b) {
        bulletNew.add(b);
    }
    private void removeBullet(Bullet bullet) {
        if (bullet.bCoordinates.y <= 0 || bullet.bCoordinates.y >= 690 || bullet.bCoordinates.x <= 0 || bullet.bCoordinates.x >= 490) {
            bulletTrackerKilled.add(bullet);
        }
    }
    void updateBullet(){
        for (Bullet trackedBullet : bulletTracker) {
            if (trackedBullet != null) {
                removeBullet(trackedBullet);
                trackedBullet.move();
            }
        }
        bulletTracker.removeAll(bulletTrackerKilled);
        bulletTrackerKilled.clear();
    }
}
