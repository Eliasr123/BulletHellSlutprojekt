/*
 * Code latest updated 26/04/18 21:52.
 * Copyright © 2018.  By Elias Renman. All rights reserved
 */

package com.bullethell.characters;

import com.bullethell.bulletTypes.Bullet;

import java.awt.*;

public class HittableObjects {
    private int health;
    public Rectangle coordinates;
    public int getHealth() {
        return health;
    }
    public void isHit(Bullet bullet) {
        this.health = this.health-bullet.damage;
    }
    protected void setHealth(int health) {
        this.health = health;
    }
}
