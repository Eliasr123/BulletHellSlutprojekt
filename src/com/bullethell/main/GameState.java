/*
 * Code latest updated 07/05/18 11:34.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
package com.bullethell.main;

import com.bullethell.bulletTypes.Bullet;
import com.bullethell.characters.Enemy;
import com.bullethell.characters.Player;

import java.util.ArrayList;
public class GameState {
    private Main main;
    GameState(Main main) {
        this.main = main;
    }
    public void resetVariables() {
        main.bulletManager.bulletTrackerKilled.clear();
        main.bulletManager.bulletTracker.clear();
        main.bulletManager.bulletNew.clear();
        main.player1.coordinates.x = 246;
        main.player1.coordinates.y = 550;
        main.player1.resetHealth();
        main.enemy1.resetHealth();
        main.enemy1.patternBreak = true;
        main.enemy1.coordinates.x = 225;
        main.enemy1.coordinates.y = 200;
        main.enemy1.newCoordinates = null;
        main.enemy1.splittingShotReady = 10;
        main.menu.gameStateI = 1;
        main.gamePaused = false;
        main.enemy1.patternBreak = false;
    }
    // initiates and starts needed threads and draws the canvas once
    void initiate() {
        main.enemy1 = new Enemy(225, 200, main);
        main.player1 = new Player(246, 550, main);
        Thread playerThread = new Thread(main.player1);
        playerThread.start();
        main.player1.startPlayerBulletThread();
        //initially draw the canvas
        main.draw();
        //Starts enemy's movement thread
        main.enemy1.startThread();
    }
    //Main loop
    void running() {
        while (main.running) {
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            main.draw();
            if (!main.gamePaused) {
                //main.draw();
                /*Checks collision, the array is to avoid concurrent modification.*/
                ArrayList<Bullet> collisionTracker = new ArrayList<>(main.bulletManager.bulletTracker);
                main.collision(main.enemy1, collisionTracker);
                main.collision(main.player1, collisionTracker);
                //update bullets coordinates
                main.bulletManager.updateBullet();
            }
        }
    }
}
