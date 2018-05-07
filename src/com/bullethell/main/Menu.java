/*
 * Code latest updated 07/05/18 14:29.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
/*The menu class object that handles ingame Menus*/
package com.bullethell.main;
import javax.swing.*;
import java.awt.*;

public class Menu {
    // what game state is the game in
    public int gameStateI = 0;
    //Global Variables
    private Main main;
    private int xPos[] = {470,250,250};
    private int yPos[] = {170,250,250};
    private Color menuBackground = new Color(214, 214, 220,200);
    private Font fontBig = new Font("TimesRoman",Font.BOLD,35);
    private Font fontMedium = new Font("TimesRoman",Font.BOLD,27);
    private Font fontSmall = new Font("TimesRoman",Font.PLAIN,19);
    private Font fontTiny = new Font("TimesRoman",Font.PLAIN,15);
    private Image hitBox = new ImageIcon("resource/startMenu/hitbox.png").getImage();
    private Image bulletTypes = new ImageIcon("resource/startMenu/bulletTypes.png").getImage();
    Menu(Main main) {
        this.main = main;
    }
    void drawStart(Graphics g) {
        String[] textLines = {"This game is was made as a school project and is", "a bullethell. Your objective, kill the enemy while dodging the",
                "incoming bullets.","You move around with the arrowkeys and slow down by",
                "holding down shift and pause with Escape.","only the white circle is your hitbox."};
        int width = 400;
        int height = 500;
        int heightMOD =100;
        int lineOffset =0;
        g.setColor(menuBackground);
        g.fillRect(xPos[gameStateI],yPos[gameStateI],width,height-heightMOD);
        g.setColor(Color.BLACK);
        g.setFont(fontTiny);
        for (int i= 0;i <= 5; i++) {
            int xPosOffset = 0;
            if (i == 5) {
                xPosOffset = 65;
            }
            g.drawString(textLines[i],xPos[gameStateI]+5+xPosOffset,yPos[gameStateI]+50+lineOffset);
            lineOffset += 15;
            if(i == 3){
                lineOffset += 30;
            }
        }
        g.drawImage(hitBox,xPos[gameStateI]+5,yPos[gameStateI]+145,61,61,null);
        g.setFont(fontSmall);
        //extremely ugly way to do it I know, but it works.
        g.drawString("Keybinds: Z                 X                 C",xPos[gameStateI]+5,yPos[gameStateI]+height-120-heightMOD);
        g.drawImage(bulletTypes,xPos[gameStateI]+(width/2)-125,yPos[gameStateI]+height-200-heightMOD,250,61,null);
        g.setFont(fontMedium);
        g.drawString("Welcome to "+main.gamesName+ "!",xPos[gameStateI]+25,yPos[gameStateI]+30);
        g.setFont(fontBig);
        g.drawString(" Press",xPos[gameStateI]+(width/2)-57,yPos[gameStateI]+height-33-heightMOD);
        g.setFont(fontSmall);
        g.drawString("Esc to Quit                                    Enter to start",xPos[gameStateI]+5,yPos[gameStateI]+height-5-heightMOD);

    }
    void drawPause(Graphics g) {
        int width = 400;
        int height = 200;
        g.setColor(menuBackground);
        g.setFont(fontBig);
        g.fillRect(xPos[gameStateI],yPos[gameStateI],width,height);
        g.setColor(Color.BLACK);
        g.drawString("Game Paused",xPos[gameStateI]+85,yPos[gameStateI]+30);
        g.drawString(" Press",xPos[gameStateI]+(width/2)-57,yPos[gameStateI]+height-33);
        g.setFont(fontSmall);
        g.drawString("Esc  to Quit       R to restart    Enter to continue",xPos[gameStateI]+5,yPos[gameStateI]+height-5);
    }
    void drawEnd(Graphics g) {
        int width = 400;
        int height = 200;
        g.setColor(menuBackground);
        g.setFont(fontBig);
        g.fillRect(xPos[gameStateI],yPos[gameStateI],width,height);
        g.setColor(Color.BLACK);
        g.drawString("  Game Over",xPos[gameStateI]+85,yPos[gameStateI]+30);
        g.drawString(" Press",xPos[gameStateI]+(width/2)-57,yPos[gameStateI]+height-33);
        if (main.enemy1.getHealth() <= 0) {
            g.drawString("You Won! ",xPos[gameStateI]+120,yPos[gameStateI]+70);
        } else {
            g.drawString("You Lost! ",xPos[gameStateI]+120,yPos[gameStateI]+70);
        }
        g.setFont(fontSmall);
        //extremely ugly way to do it I know.
        g.drawString("Esc to Quit                                      R to Restart",xPos[gameStateI]+5,yPos[gameStateI]+height-5);
    }
}