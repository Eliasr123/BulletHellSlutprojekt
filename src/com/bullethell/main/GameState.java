/*
 * Code latest updated 29/04/18 15:28.
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
        main.bulletTrackerKilled.removeAll(main.bulletTrackerKilled);
        main.bulletTracker.removeAll(main.bulletTracker);
        main.bulletNew.removeAll(main.bulletNew);
        main.player1.coordinates.x = 246;
        main.player1.coordinates.y = 550;
        main.player1.resetHealth();
        main.enemy1.resetHealth();
        main.patternBreak = true;
        main.enemy1.coordinates.x = 225;
        main.enemy1.coordinates.y = 200;
        main.enemy1.newCoordinates = null;
        main.enemy1.splittingShotReady = 10;
        main.gameStateI = 1;
        main.gamePaused = false;
        main.patternBreak = false;
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
                ArrayList<Bullet> collisionTracker = main.bulletTracker;
                main.collision(main.enemy1, collisionTracker);
                main.collision(main.player1, collisionTracker);
                //update bullets coordinates
                main.bulletManager.updateBullet();
            }
        }
    }
}
