/*
 * Code latest updated 07/05/18 14:29.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
/*Starts the game and handles most of the global variables needed across the game as well as being the drawing the Canvas*/
package com.bullethell.main;

import com.bullethell.bulletTypes.Bullet;
import com.bullethell.characters.Enemy;
import com.bullethell.characters.HittableObjects;
import com.bullethell.characters.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
public class Main extends JFrame {

    //playing field graphics
    private Image OverlayImage = new ImageIcon("resource/playingfield/BackgroundImg.png").getImage();
    private Image PlayFieldImage = new ImageIcon("resource/playingfield/PlayField.png").getImage();
    //Creates objects
    Enemy enemy1;
    Player player1;
    public BulletManager bulletManager = new BulletManager();
    public GameState gameState = new GameState(this);
    public Menu menu = new Menu(this);
    //games name
    String gamesName = "placeHolder";

    public static void main(String[] args){
            new Main();
    }
    //Create constructor to spawn window
    public Main(){
        this.setTitle(gamesName);
        //Dimension of windows width x height.
        Dimension screenSize = new Dimension(900, 700);
        this.setSize(screenSize);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.GRAY);
        this.addKeyListener(new Key());
        gameState.initiate();
        gameState.running();
    }
    void draw() {
        //Add all the new bullets that were spawned so they can drawn and moved.
        bulletManager.bulletTracker.addAll(bulletManager.bulletNew);
        bulletManager.bulletNew.clear();
        //Creates a new separate bullet ArrayList to use for drawing to deal with concurrent modification.
        ArrayList<Bullet> bulletToDraw = new ArrayList<>(bulletManager.bulletTracker);
        Runnable run = () -> {
            Image dbImage = createImage(getWidth(), getHeight());
            Graphics dbg = dbImage.getGraphics();
            /* coordinates printing and bCoordinates printing */
            dbg.drawImage(PlayFieldImage,0,0,900,700,null);
            player1.draw(dbg);
            enemy1.draw(dbg);
            /* Draws all the bullets*/
            for (Bullet trackedBullet : bulletToDraw) {
                if (trackedBullet != null) {
                    dbg.setColor(trackedBullet.bColor);
                    dbg.fillOval(trackedBullet.bCoordinates.x, trackedBullet.bCoordinates.y, trackedBullet.bCoordinates.width, trackedBullet.bCoordinates.height);
                }
            }
            /* overlay*/
            dbg.drawImage(OverlayImage,0,0,900,700,null);
            /* Side Menu overlay */
            dbg.setColor(Color.WHITE);
            dbg.setFont((new Font("TimesRoman",Font.BOLD,35)));
            if (menu.gameStateI > 0) {
                dbg.drawString("Enemy HP "+ enemy1.getHealth(), 470, 100);
                dbg.drawString( "Player  HP "+player1.getHealth() , 470, 146);
            }
            if (gameState.gamePaused) {
                if (menu.gameStateI == 0) {
                    menu.drawStart(dbg);
                } else if (menu.gameStateI == 1) {
                    menu.drawPause(dbg);
                } else {
                    menu.drawEnd(dbg);
                }
            }
            getGraphics().drawImage(dbImage, 0, 0, null);
            bulletToDraw.clear();
        };
        new Thread(run).start();
    }
    void collision(HittableObjects hittable, ArrayList<Bullet> collisionTracker){
            for (Bullet bullet : collisionTracker) {
                if (bullet != null){
                    if (hittable.coordinates.intersects(bullet.bCoordinates) && bullet.origin.getClass() != hittable.getClass()) {
                        hittable.isHit(bullet);
                        if (bullet.origin.getClass() != player1.getClass()) {
                            //this goes through the bullets and on player hit only removes the enemies bullets
                            for (Bullet bulletRemove: bulletManager.bulletTracker) {
                                if (bulletRemove.origin.getClass() != player1.getClass()) {
                                    bulletManager.bulletTrackerKilled.add(bulletRemove);
                                }
                            }

                        }
                        else {
                            bulletManager.bulletTrackerKilled.add(bullet);
                        }
                        break;
                    }
                }
            }
            if (hittable.getHealth() <= 0){
                menu.gameStateI=2;
                gameState.gamePaused = true;
            }
    }
    public class Key implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) { }
        @Override
        public void keyPressed(KeyEvent e){
            player1.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
        }
    }
}