/*
 * Code latest updated 28/04/18 17:58.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */

package com.bullethell.main;
import com.bullethell.bulletTypes.Bullet;
import com.bullethell.characters.Enemy;
import com.bullethell.characters.HittableObjects;
import com.bullethell.characters.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class Main extends JFrame {

    //Double buffering
    Image dbImage;
    Graphics dbg;
    //playingfield graphics
    Image OverlayImage = new ImageIcon("resource/playingfield/BackgroundImg.png").getImage();
    Image PlayFieldImage = new ImageIcon("resource/playingfield/PlayField.png").getImage();
    //Bullet objects
    public ArrayList<Bullet> bulletTrackerKilled = new ArrayList<>();
    protected ArrayList<Bullet> bulletTracker = new ArrayList<>();
    protected ArrayList<Bullet> bulletNew = new ArrayList<>();
    //Enemy object false boolean for hittable
    protected Enemy enemy1;
    // player object true boolean for hittable
    protected Player player1;
    public BulletManager bulletManager = new BulletManager(this);
    public GameState gameState = new GameState(this);
    protected Menu menu = new Menu(this);
    //Variables for screen size
    int GWIDTH = 900, GHEIGHT = 700;
    //Dimension of GWIDTH*GHEIGHT
    Dimension screenSize = new Dimension(GWIDTH, GHEIGHT);
    //the program isnt running yet
    public boolean running = true;
    public boolean gamePaused = true;
    // what game state is the game in
    public int gameStateI = 0;
    //break bullet loops
    public boolean patternBreak = false;
    //games name
    public String gamesName = "placeHolder";
    //main code that starts everything
    public static void main(String[] args){
            new Main();
    }
    //Create constructor to spawn window
    public Main(){
        this.setTitle(gamesName);
        this.setSize(screenSize);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.GRAY);
        this.addKeyListener(new Key());
        gameState.initiate();
        gameState.running();
    }
    protected void draw() {
        //Add all the new bullets that were spawned so they can drawn and moved.
        bulletTracker.addAll(bulletNew);
        bulletNew.clear();
        //Creates a new separate bullet ArrayList to use for drawing to deal with concurrent modification.
        ArrayList<Bullet> bulletToDraw = new ArrayList<Bullet>();
        bulletToDraw.addAll(bulletTracker);
        Runnable run = new Runnable() {
            public void run() {
                dbImage = createImage(getWidth(), getHeight());
                dbg = dbImage.getGraphics();
                /** coordinates printing and bCoordinates printing */
                dbg.drawImage(PlayFieldImage,0,0,900,700,null);
                player1.draw(dbg);
                enemy1.draw(dbg);
                /** Draws all the bullets*/
                for (Bullet trackedBullet : bulletToDraw) {
                    if (trackedBullet != null) {
                        dbg.setColor(trackedBullet.bColor);
                        dbg.fillOval(trackedBullet.bCoordinates.x, trackedBullet.bCoordinates.y, trackedBullet.bCoordinates.width, trackedBullet.bCoordinates.height);
                    }
                }
                /** overlay*/
                dbg.drawImage(OverlayImage,0,0,900,700,null);
                /** Sidemenu overlay */
                dbg.setColor(Color.WHITE);
                dbg.setFont((new Font("TimesRoman",Font.BOLD,35)));
                if (gameStateI > 0) {
                    dbg.drawString("Enemy HP "+ enemy1.getHealth(), 470, 100);
                    dbg.drawString( "Player  HP "+player1.getHealth() , 470, 146);
                }
                if (gamePaused) {
                    if (gameStateI == 0) {
                        menu.drawStart(dbg);
                    } else if (gameStateI == 1) {
                        menu.drawPause(dbg);
                    } else {
                        menu.drawEnd(dbg);
                    }
                }
                getGraphics().drawImage(dbImage, 0, 0, null);
                bulletToDraw.clear();
            }
        };
        new Thread(run).start();
    }
    void collision(HittableObjects hittable, ArrayList<Bullet> colisionTracker){
        Runnable run = new Runnable() {
            public void run() {
                for (Bullet bullet : colisionTracker) {
                    if (bullet != null){
                        if (hittable.coordinates.intersects(bullet.bCoordinates) && bullet.origin.getClass() != hittable.getClass()) {
                            hittable.isHit(bullet);
                            if (bullet.origin.getClass() != player1.getClass()) {
                                bulletTrackerKilled.addAll(colisionTracker);
                            }
                            else {
                                bulletTrackerKilled.add(bullet);
                            }
                            break;
                        }
                    }
                }
                if (hittable.getHealth() <= 0){
                    gameStateI=2;
                    gamePaused = true;
                    System.out.println(hittable.getClass().getName()+ " got game over");
                }
            }
        };
        new Thread(run).start();


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