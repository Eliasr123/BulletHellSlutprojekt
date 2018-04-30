/*
 * Code latest updated 29/04/18 12:57.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
package com.bullethell.bulletTypes;

import com.bullethell.characters.HittableObjects;

import java.awt.*;
public class Bullet {
    int xDirection, yDirection;
    public int damage;
    public Color bColor;
    public HittableObjects origin;
    public Rectangle bCoordinates;
    public Bullet(int x, int y, int xDir, int yDir,int width, int height,Color bColor,HittableObjects origin,int damage){
        //Global variables
        this.bColor = bColor;
        this.origin = origin;
        this.damage = damage;
        setXDirection(xDir);
        setYDirection(yDir);
        //Create 'bCoordinates'
        bCoordinates = new Rectangle(x, y, width, height);
    }
    private void setXDirection(int xDir){
        xDirection = xDir;
    }
    private void setYDirection(int yDir){
        yDirection = yDir;
    }
    public void move(){
        bCoordinates.x += xDirection;
        bCoordinates.y += yDirection;
    }
}