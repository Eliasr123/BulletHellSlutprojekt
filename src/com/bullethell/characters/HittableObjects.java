/*
 * Code latest updated 07/05/18 14:26.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
/*Hittable objects is parent class of enemy and player it makes them be able to take damage and also have health */
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
