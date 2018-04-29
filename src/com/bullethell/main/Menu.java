/*
 * Code latest updated 29/04/18 15:30.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
package com.bullethell.main;
import javax.swing.*;
import java.awt.*;
class Menu {
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
        int width = 400;
        int height = 500;
        int heightModif =100;
        g.setColor(menuBackground);
        g.fillRect(xPos[main.gameStateI],yPos[main.gameStateI],width,height-heightModif);
        g.setColor(Color.BLACK);
        g.setFont(fontTiny);
        g.drawString("This game is was made as a school project and is",xPos[main.gameStateI]+5,yPos[main.gameStateI]+50);
        g.drawString("a bullethell. Your objective, kill the enemy while dodging the",xPos[main.gameStateI]+5,yPos[main.gameStateI]+65);
        g.drawString( "incoming bullets.",xPos[main.gameStateI]+5,yPos[main.gameStateI]+80);
        g.drawString( "You move around with the arrowkeys and slow down by",xPos[main.gameStateI]+5,yPos[main.gameStateI]+125);
        g.drawString( "holding down shift and pause with Escape.",xPos[main.gameStateI]+5,yPos[main.gameStateI]+140);
        g.drawString( "only the white circle is your hitbox.",xPos[main.gameStateI]+70,yPos[main.gameStateI]+160);
        g.drawImage(hitBox,xPos[main.gameStateI]+5,yPos[main.gameStateI]+145,61,61,null);
        g.setFont(fontSmall);
        //extremely ugly way to do it I know, but it works.
        g.drawString("Keybinds: Z                 X                 C",xPos[main.gameStateI]+5,yPos[main.gameStateI]+height-120-heightModif);
        g.drawImage(bulletTypes,xPos[main.gameStateI]+(width/2)-125,yPos[main.gameStateI]+height-200-heightModif,250,61,null);
        g.setFont(fontMedium);
        g.drawString("Welcome to "+main.gamesName+ "!",xPos[main.gameStateI]+25,yPos[main.gameStateI]+30);
        g.setFont(fontBig);
        g.drawString(" Press",xPos[main.gameStateI]+(width/2)-57,yPos[main.gameStateI]+height-33-heightModif);
        g.setFont(fontSmall);
        g.drawString("Esc to Quit                                    Enter to start",xPos[main.gameStateI]+5,yPos[main.gameStateI]+height-5-heightModif);

    }
    void drawPause(Graphics g) {
        int width = 400;
        int height = 200;
        g.setColor(menuBackground);
        g.setFont(fontBig);
        g.fillRect(xPos[main.gameStateI],yPos[main.gameStateI],width,height);
        g.setColor(Color.BLACK);
        g.drawString("Game Paused",xPos[main.gameStateI]+85,yPos[main.gameStateI]+30);
        g.drawString(" Press",xPos[main.gameStateI]+(width/2)-57,yPos[main.gameStateI]+height-33);
        g.setFont(fontSmall);
        g.drawString("Esc  to Quit       R to restart   Enter to continue",xPos[main.gameStateI]+5,yPos[main.gameStateI]+height-5);
    }
    void drawEnd(Graphics g) {
        int width = 400;
        int height = 200;
        g.setColor(menuBackground);
        g.setFont(fontBig);
        g.fillRect(xPos[main.gameStateI],yPos[main.gameStateI],width,height);
        g.setColor(Color.BLACK);
        g.drawString("  Game Over",xPos[main.gameStateI]+85,yPos[main.gameStateI]+30);
        g.drawString(" Press",xPos[main.gameStateI]+(width/2)-57,yPos[main.gameStateI]+height-33);
        if (main.enemy1.getHealth() <= 0) {
            g.drawString("You Won! ",xPos[main.gameStateI]+120,yPos[main.gameStateI]+70);
        } else {
            g.drawString("You Lost! ",xPos[main.gameStateI]+120,yPos[main.gameStateI]+70);
        }
        g.setFont(fontSmall);
        //extremely ugly way to do it I know.
        g.drawString("Esc to Quit                                      R to Restart",xPos[main.gameStateI]+5,yPos[main.gameStateI]+height-5);
    }
}
