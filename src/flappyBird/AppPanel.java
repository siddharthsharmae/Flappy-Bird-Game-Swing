package flappyBird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import entities.Background;
import entities.Bird;
import entities.Blocks;

public class AppPanel extends JPanel implements ActionListener{
	public final int TILE_SIZE = 100;
	public final int ROWS = 6;
	public final int COLUMNS = 8;
	public final int SCREEN_HEIGHT = ROWS*TILE_SIZE;
	public final int SCREEN_WIDTH = COLUMNS*TILE_SIZE;
	public int score = 0;
	public int highscore = 0;
	
	KeyAction key;
	Bird bird;
	Timer timer;
	ArrayList<Blocks> blockList;
	Background bg;
	public boolean gameover;
	boolean restartgame;
	
	
	
	AppPanel(){
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		key = new KeyAction();
		this.addKeyListener(key);
		this.setFocusable(true);
		bird = new Bird(this,key);
		bg = new Background(this);
		blockList = new ArrayList<>();
		addFirstBlock();
		addInitialBlock();
		startGame();
	}
	
	public void startGame() {
		timer = new Timer(16,this);
		timer.start();
	}
	
	public void update() {
		if(gameover) return;
		bird.update();
		bg.update();
		for(var item:blockList) {
			item.update();
		}
		if(blockList.getLast().xAxis<SCREEN_WIDTH) {
			addBlocks();
		}
		isGameOver();
		scoreUpdate();
	}
	
	public void isGameOver() {
		for(var item:blockList) {
			if ((bird.xAxis + bird.width > item.xAxis && bird.xAxis < item.xAxis + item.width) && 
				    ((bird.yAxis < item.yAxis1 + item.height1) || 
				     (bird.yAxis + bird.height > item.yAxis2))) {
				    gameover = true;
				}
		}
	}
	
	public void scoreUpdate() {
		for(var item:blockList) {
			if (bird.xAxis + bird.width > item.xAxis + item.width) {
				    score++;
				}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bg.drawBackground(g);
		bird.drawBird(g);
		for(var item:blockList) {
			item.drawBlocks(g);
		}
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("SCORE:"+String.valueOf(score), SCREEN_WIDTH-200, 50);
		g.drawString("HIGH SCORE:"+String.valueOf(highscore), SCREEN_WIDTH-200, 100);
	}
	
	public void addFirstBlock() {
		blockList.add(new Blocks(this,bg));
	}
	
	public void addInitialBlock() {
		for(int i=1;i<6;i++) {
			blockList.add(new Blocks(this,blockList.getLast(),bg));
		}
	}
	
	public void addBlocks() {
			blockList.removeFirst();
			blockList.add(new Blocks(this,blockList.getLast(),bg));
	}

	public void restartGame() {
		if(gameover && key.R) {
			bird.reset();
			bg.reset();
			blockList.clear();
			if(score>highscore) {
				highscore = score;
			}
			score=0;
			blockList = new ArrayList<>();
			addFirstBlock();
			addInitialBlock();
			gameover = false;
//			startGame();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!gameover) {
			update();
		}
		restartGame();
		repaint();
		
	}
}