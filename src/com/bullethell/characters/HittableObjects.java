/*
 * Code latest updated 28/04/18 17:58.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */

package com.bullethell.characters;

import com.bullethell.bulletTypes.Bullet;

import java.awt.*;

public abstract class HittableObjects {
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
