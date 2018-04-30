/*
 * Code latest updated 29/04/18 12:51.
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
    public synchronized void isHit(Bullet bullet) {
        this.health = this.health-bullet.damage;
    }
    void setHealth(int health) {
        this.health = health;
    }
}
