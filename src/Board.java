/*
 * @ Author: Bakhrom Botirov 
 * @ Game Name: Snake
 * @ Professor: Usman Risvi
 * @ Class: C SCI 143 (6146)
 * @ Date: 11/25/2018
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.text.html.FormView;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class Board extends GraphicsProgram{
	
	private static final long serialVersionUID = 1L;
	private final int WINDOW_X = 25 * 30;
	private final int WINDOW_Y = 25 * 30;
	private final int GRID_BLOCK = 25;
	private final int TOTAL_GRID_BLOCKS = (WINDOW_X/GRID_BLOCK) * (WINDOW_Y/GRID_BLOCK);
	private final int ANIMATION_DELAY = 150;
	private final Color WINDOW_BACKGROUND_COLOR = Color.DARK_GRAY;
	private final Color SCORE_LABEL_COLOR = Color.WHITE;
	private final Color APPLE_COLOR = Color.ORANGE;
	private final Color SNAKE_HEAD_COLOR = Color.GREEN;
	private final Color SNAKE_BODY_COLOR = Color.YELLOW;
	
	private Snake snake;
	private ArrayList<GOval> drawSnakeSegments;
	private Apple apple;
	private GOval drawApple;
	private GLabel scoreLabel;;
	private GameOverLabel gameOverLabel;
	private Instructions instructions;
	private int score = 0;
	private boolean isGameOver = false;
		
	public static void main(String[] args) {
		Board b = new Board();
	}
	
	public Board() {
	}
	
	public void init() {
		
		this.renderBoard();
		this.renderInstructions();
		this.renderScore();
		this.renderApple();
		this.renderSnake(true);
	}
	
	public void run() {
		
		waitForClick();
		remove(this.instructions);
		
		while(!this.isGameOver) {
			
			pause(ANIMATION_DELAY);
			this.checkIsGameOver();
			this.snake.move();
			this.renderSnake(false);	
		}
	}
	
	public void keyPressed(KeyEvent e) {
		
		if(this.isGameOver) {
			if(KeyEvent.VK_SPACE == e.getKeyCode()) {
				new Board();
			}
		}
		
		if(KeyEvent.VK_LEFT == e.getKeyCode()) {
			this.snake.setDirection("left");
		}else if(KeyEvent.VK_RIGHT == e.getKeyCode()) {
			this.snake.setDirection("right");
		}else if(KeyEvent.VK_UP == e.getKeyCode()) {
			this.snake.setDirection("up");
		}else if(KeyEvent.VK_DOWN == e.getKeyCode()) {
			this.snake.setDirection("down");
		}
	}
	
	private GOval drawOval(Block block, Color color) {
		
		GOval oval = new GOval(GRID_BLOCK, GRID_BLOCK);
		oval.setFilled(true);
		oval.setFillColor(color);
		return oval;
	}
	

	private GRect drawSquare(Block block, Color color) {
		
		GRect square = new GRect(GRID_BLOCK, GRID_BLOCK);
		square.setFilled(true);
		square.setColor(color);
		return square;
	}
	
	private void checkIsGameOver() {
		
		if(this.snake.isSelfCollide() || this.isWallCollide()) {
			
			isGameOver = true;
			this.renderGameOverLabel();
			
			
		}else if(this.snake.didEatApple(this.apple)) {
			
			this.apple.move();
			this.renderApple();
			this.snake.grow();
			this.renderSnake(true);
			this.score++;
			this.renderScore();
		}
	}
	
	private boolean isWallCollide() {
		
		GOval head = this.drawSnakeSegments.get(0);
		
		boolean isLeftCollision = (head.getX() < 0);
		boolean isRightCollision = (head.getX() > WINDOW_X);
		boolean isTopCollide = (head.getY() < 0);
		boolean isBottomCollide = (head.getY() > WINDOW_Y);
		
		return (isLeftCollision || isRightCollision || isTopCollide || isBottomCollide);
	}
	
	private void renderBoard() {
		
		setSize(WINDOW_X, WINDOW_Y);
		setBackground(WINDOW_BACKGROUND_COLOR);
		addKeyListeners();
		addMouseListeners();

		this.apple = new Apple(GRID_BLOCK, TOTAL_GRID_BLOCKS);
		this.snake = new Snake(GRID_BLOCK);
		this.drawSnakeSegments = new ArrayList<GOval>();
	}

	private void renderGrid() {
		
		for(int x=0; x<WINDOW_X; x+=GRID_BLOCK) {
			
			for(int y=0; y<WINDOW_Y; y+=GRID_BLOCK) {
				
				GRect rectBlock = new GRect(GRID_BLOCK, GRID_BLOCK);
				rectBlock.setColor(Color.decode("#ffffff"));
				add(rectBlock, x, y);
			}
		}
	}
	
	private void renderInstructions() {
		
		this.instructions = new Instructions();
		
		int centerX = (int)(WINDOW_X-instructions.getWidth())/2;
		int centerY = (int)(WINDOW_Y-instructions.getHeight())/2;
		
		add(this.instructions, centerX, centerY);
	}
	
	private void renderGameOverLabel() {
		
		this.gameOverLabel = new GameOverLabel(this.score);
		
		int centerX = (int)(WINDOW_X-instructions.getWidth())/2;
		int centerY = (int)(WINDOW_Y-instructions.getHeight())/2;
		
		add(this.gameOverLabel, centerX, centerY);
	}
	
	private void renderScore() {
		
		if(this.scoreLabel != null) {
			
			this.scoreLabel.setLabel("Score: " + this.score);
			
		}else {
			
			this.scoreLabel = new GLabel("Score: " + this.score);
			
			this.scoreLabel.setFont(new Font("Times New Romans", Font.BOLD, GRID_BLOCK));
			this.scoreLabel.setColor(SCORE_LABEL_COLOR);
			
			double xPos = WINDOW_Y - scoreLabel.getWidth() - GRID_BLOCK;
			double yPos = GRID_BLOCK;
			
			add(scoreLabel, xPos, yPos);
			
		}
	}
	
	private void renderApple() {
		if(this.drawApple != null) {
			this.drawApple.setLocation(GRID_BLOCK * this.apple.getCol(), GRID_BLOCK * this.apple.getRow());
			return;
		}
		drawApple = drawOval(this.apple, APPLE_COLOR);
		add(drawApple,GRID_BLOCK * this.apple.getCol(), GRID_BLOCK * this.apple.getRow());
	}
	
	private void renderSnake(boolean needRerender) {
		GOval temp;
		Block block;
		
		if(this.drawSnakeSegments.isEmpty() || needRerender) {
			
			for(int i=0; i<this.drawSnakeSegments.size(); i++) {
				remove(this.drawSnakeSegments.get(i));
			}
			this.drawSnakeSegments.clear();
			
			for(int i=0; i<this.snake.getSnakeLength(); i++) {
				
				block = this.snake.getSnake().get(i);
				
				if(i == 0) {
					temp = drawOval(block, SNAKE_HEAD_COLOR);
					add(temp, GRID_BLOCK * block.getCol(), GRID_BLOCK * block.getRow());
				} else {
					temp = drawOval(block, SNAKE_BODY_COLOR);
					add(temp, GRID_BLOCK * block.getCol(), GRID_BLOCK * block.getRow());
				}
				
				this.drawSnakeSegments.add(temp);

			}

		} else {
			for(int i=0; i<this.snake.getSnakeLength(); i++) {
				
				block = this.snake.getSnake().get(i);
				temp =  this.drawSnakeSegments.get(i);//drawOval(block, SNAKE_HEAD_COLOR);
				temp.setLocation(GRID_BLOCK * block.getCol(), GRID_BLOCK * block.getRow());
				
			}
		}
	}
}
