package com.bullethell.bulletTypes;

import com.bullethell.characters.HittableObjects;

import java.awt.*;


public class Bullet {

    //Global variables
    int x, y, xDirection, yDirection;
    public int damage;
    public Color bColor;
    public HittableObjects origin;
    //Score
    int p1Score, p2Score;


    public Rectangle bCoordinates;

    public Bullet(int x, int y, int xDir, int yDir,int width, int height,Color bColor,HittableObjects origin,int damage){
        p1Score = p2Score = 0;
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
    public void setXDirection(int xdir){
        xDirection = xdir;
    }
    public void setYDirection(int ydir){
        yDirection = ydir;
    }

    public void draw(Graphics g){
        g.setColor(Color.CYAN);
        g.fillOval(bCoordinates.x, bCoordinates.y, bCoordinates.width, bCoordinates.height);


    }
    public void move(){

        bCoordinates.x += xDirection;
        bCoordinates.y += yDirection;

    }

}