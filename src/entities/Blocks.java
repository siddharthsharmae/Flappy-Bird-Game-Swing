package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import flappyBird.AppPanel;

public class Blocks extends Entities{
	
	AppPanel appPanel;
	Background bg;
	int length;
	BufferedImage block1,block2,block3,block4,block5,block6,image1,image2;
	public int height1,height2,yAxis1,yAxis2;
	Random random;
	public Blocks(AppPanel appPanel,Background bg){
		this.appPanel = appPanel;
		this.bg = bg;
		length=appPanel.SCREEN_WIDTH/4;
		loadImage();
		random = new Random();
		xAxis=appPanel.SCREEN_WIDTH-width;
		setDefaults();
	}
	
	public Blocks(AppPanel appPanel,Blocks block,Background bg) {
		this.xAxis=block.xAxis+250;
		this.bg = bg;
		this.appPanel = appPanel;
		random = new Random();
		loadImage();
		setDefaults();
	}
	
	public void setDefaults() {
		int gap = 2*appPanel.TILE_SIZE;
		this.height1 = (2+random.nextInt(appPanel.ROWS-3))*appPanel.TILE_SIZE;
		this.height2 = appPanel.SCREEN_HEIGHT - height1 - gap;
		this.yAxis1=0;
		this.yAxis2=appPanel.SCREEN_HEIGHT-height2;
		width=appPanel.TILE_SIZE/2;
		randomBlock();
	}
	
	public void loadImage() {
		try {
			block1 = ImageIO.read(getClass().getResourceAsStream("/block/block1.png"));
			block2 = ImageIO.read(getClass().getResourceAsStream("/block/block2.png"));
			block3 = ImageIO.read(getClass().getResourceAsStream("/block/block3.png"));
			block4 = ImageIO.read(getClass().getResourceAsStream("/block/block4.png"));
			block5 = ImageIO.read(getClass().getResourceAsStream("/block/block5.png"));
			block6 = ImageIO.read(getClass().getResourceAsStream("/block/block6.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update() {
//		this.height1=random.nextInt(appPanel.ROWS-1)*appPanel.TILE_SIZE;
//		this.height2 = appPanel.SCREEN_HEIGHT - height1 - appPanel.TILE_SIZE;
//		this.yAxis1=0;
//		this.yAxis2=appPanel.SCREEN_HEIGHT-height2;
		xAxis-=bg.speedx;
//		if(xAxis<0) {
//			appPanel.deleteBlock();
//		}
	}
	
	public void randomBlock() {
		int img1 =1+ random.nextInt(6);
		switch(img1) {
		case 1 -> image1 = block1;
		case 2 -> image1 = block2;
		case 3 -> image1 = block3;
		case 4 -> image1 = block4;
		case 5 -> image1 = block5;
		case 6 -> image1 = block6;
		}
		int img2 =1+ random.nextInt(6);
		switch(img2) {
		case 1 -> image2 = block1;
		case 2 -> image2 = block2;
		case 3 -> image2 = block3;
		case 4 -> image2 = block4;
		case 5 -> image2 = block5;
		case 6 -> image2 = block6;
		}
	}
	
	public void drawBlocks(Graphics g) {
		g.drawImage(image1, xAxis, yAxis1, width, height1, null);
		g.drawImage(image2, xAxis, yAxis2, width, height2, null);
	}
	
}
