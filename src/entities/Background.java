package entities;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import flappyBird.AppPanel;

public class Background extends Entities{
	
	AppPanel appPanel;
	public Background(AppPanel appPanel){
		this.appPanel = appPanel;
		setDefaults();
		loadImage();
	}
	
	public void setDefaults() {
		this.xAxis=0;
		this.yAxis=0;
		this.height=appPanel.SCREEN_HEIGHT;
		this.width=appPanel.SCREEN_WIDTH;
		this.speedx=10;
	}
	
	public void loadImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/background/th1.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update() {
		xAxis = (xAxis -(int) speedx) % appPanel.SCREEN_WIDTH;
		speedx+=0.0001;
	}
	
	public void drawBackground(Graphics g){
		g.drawImage(image, xAxis, yAxis, width, height, null);
		g.drawImage(image, xAxis+appPanel.SCREEN_WIDTH, yAxis, width, height, null);
	}
	
	public void reset() {
		setDefaults();
	}
}
