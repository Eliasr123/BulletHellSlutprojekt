/*
 * Code latest updated 29/04/18 12:55.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
package com.bullethell.bulletTypes;

import com.bullethell.characters.HittableObjects;

import java.awt.*;
public class BouncingBullet extends Bullet {
    private boolean xBounce;
    private boolean yBounce;
    public BouncingBullet(int x, int y, int xDir, int yDir, int width, int height, Color color, Boolean xBounce, Boolean yBounce, HittableObjects origin, int damage) {
        super(x,y,xDir,yDir,width,height,color,origin,damage);
        this.xBounce = xBounce;
        this.yBounce = yBounce;
    }
    public void move() {
        bCoordinates.x += xDirection;
        bCoordinates.y += yDirection;
        int xMax = 440;
        int xMin = 50;
        if (((bCoordinates.x + xDirection) <= (Math.abs(xDirection) + xMin)) || (bCoordinates.x + xDirection) >= xMax && xBounce) {
            if(xDirection > 0) {
                xDirection = -xDirection;
            } else {
                xDirection = Math.abs(xDirection);
            }
        }
        int yMin = 50;
        if (((bCoordinates.y + yDirection) <= (Math.abs(yDirection) + yMin)) && yBounce) {
            yDirection = Math.abs(yDirection);
        }
    }
}