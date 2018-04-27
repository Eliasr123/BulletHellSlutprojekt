/*
 * Code latest updated 26/04/18 21:52.
 * Copyright © 2018.  By Elias Renman. All rights reserved
 */

package com.bullethell.bulletTypes;

import com.bullethell.characters.HittableObjects;

import java.awt.*;

public class BouncingBullet extends Bullet {
    private static int xMin = 50;
    private static int xMax = 440;
    private static int yMin = 50;
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

        if (((bCoordinates.x + xDirection) <= (Math.abs(xDirection) + xMin)) || (bCoordinates.x + xDirection) >= xMax && xBounce == true) {
            if(xDirection > 0) {
                xDirection = -xDirection;
            } else {
                xDirection = Math.abs(xDirection);
            }
        }
        if (((bCoordinates.y + yDirection) <= (Math.abs(yDirection) + yMin)) && yBounce == true) {
            yDirection = Math.abs(yDirection);
        }
    }
}