/*
 * Code latest updated 28/04/18 17:58.
 * Written  By Elias Renman.
 * Copyright © 2018.
 */
package com.bullethell.main;
import javax.swing.*;
import java.awt.*;
class Menu {
    private Main main;
    private int xPos[] = {470,250,250};
    private int yPos[] = {100,250,250};
    private Color menuBackground = new Color(214, 214, 220,155);
    private Font fontBig = new Font("TimesRoman",Font.BOLD,35);
    private Font fontMedium = new Font("TimesRoman",Font.BOLD,27);
    private Font fontSmall = new Font("TimesRoman",Font.PLAIN,19);
    private Image hitBox = new ImageIcon("resource/startMenu/hitbox.png").getImage();
    private Image bulletTypes = new ImageIcon("resource/startMenu/bulletTypes.png").getImage();
    Menu(Main main) {
        this.main = main;
    }
    void drawStart(Graphics g) {

        int width = 400;
        int height = 500;

        g.setColor(menuBackground);
        g.fillRect(xPos[main.gameStateI],yPos[main.gameStateI],width,height);

        g.setColor(Color.BLACK);
        g.setFont(fontMedium);
        g.drawString("Welcome to "+main.gamesName+ "!",xPos[main.gameStateI]+25,yPos[main.gameStateI]+30);
        g.setFont(fontBig);
        g.drawString(" Press",xPos[main.gameStateI]+(width/2)-57,yPos[main.gameStateI]+height-33);
        g.setFont(fontSmall);

        g.drawString("Esc to Quit                                    Enter to start",xPos[main.gameStateI]+5,yPos[main.gameStateI]+height-5);

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
        g.drawString("Escape to Quit   R to restart   Enter to continue",xPos[main.gameStateI]+5,yPos[main.gameStateI]+height-5);
    }
    void drawEnd(Graphics g) {
        int width = 400;
        int height = 200;
        g.setColor(menuBackground);
        g.setFont(fontBig);
        g.fillRect(xPos[main.gameStateI],yPos[main.gameStateI],width,height);
        g.setColor(Color.BLACK);
        g.drawString("Game Paused",xPos[main.gameStateI]+85,yPos[main.gameStateI]+30);
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
