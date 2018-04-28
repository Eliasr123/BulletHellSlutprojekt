/*
 * Code latest updated 28/04/18 14:53.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
package com.bullethell.bulletTypes;
import com.bullethell.characters.HittableObjects;

import java.awt.*;
public class Bullet {
    //Global variables
    int x, y, xDirection, yDirection;
    public int damage;
    public Color bColor;
    public HittableObjects origin;
    public Rectangle bCoordinates;
    public Bullet(int x, int y, int xDir, int yDir,int width, int height,Color bColor,HittableObjects origin,int damage){
        this.x = x;
        this.y = y;
        this.bColor = bColor;
        this.origin = origin;
        this.damage = damage;
        setXDirection(xDir);
        setYDirection(yDir);
        //Create 'bCoordinates'
        bCoordinates = new Rectangle(this.x, this.y, width, height);
    }
    protected void setXDirection(int xdir){
        xDirection = xdir;
    }
    protected void setYDirection(int ydir){
        yDirection = ydir;
    }
    public void move(){
        bCoordinates.x += xDirection;
        bCoordinates.y += yDirection;
    }
}