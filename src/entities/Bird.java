package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import flappyBird.*;

public class Bird extends Entities {

    KeyAction key;
    AppPanel appPanel;
    float gravity = 0.9f;
    BufferedImage up1, up2, down1, down2;
    int wait = 0;
    int animationFrame = 1;
    boolean up, down;

    public Bird(AppPanel appPanel, KeyAction key) {
        this.key = key;
        this.appPanel = appPanel;
        setDefaults();
        loadBird();
    }
    
    public void setDefaults() {
        this.xAxis = appPanel.TILE_SIZE;
        this.yAxis = appPanel.SCREEN_HEIGHT / 2;
        this.height = appPanel.TILE_SIZE / 2 ;
        this.width = appPanel.TILE_SIZE / 2;
        this.speedx = 5;
        this.speedy = 5;
    }

    public void loadBird() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/bird/b1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/bird/b2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/bird/b3.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/bird/b2.png"));
            image = up1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
//        if (key.up && yAxis > 0) { 
        	if (key.up) { 
            up = true;
            down = false;
            wait++;
            if (wait > 5) {
                animationFrame = (animationFrame == 1) ? 2 : 1;
                wait = 0;
            }
            speedy = -5;  // Use a negative value to move up
            yAxis += speedy;
            if(yAxis<0) {
            	appPanel.gameover = true;
            }
        } else {
            if (yAxis + height < appPanel.SCREEN_HEIGHT) {
                down = true;
                up = false;
                speedy = Math.min(speedy + gravity, 8);  // Cap maximum fall speed
                yAxis += speedy;
            } else {
                speedy = 0;
                appPanel.gameover=true;
            }
        }
    }

    public void drawBird(Graphics g) {
        if (up) {
            image = (animationFrame == 1) ? up1 : up2;
        } else if (down) {
            image = down1;
        }

        g.drawImage(image, xAxis, yAxis, height, width, null);
    }
    
    public void reset() {
    	setDefaults();
    }
}
