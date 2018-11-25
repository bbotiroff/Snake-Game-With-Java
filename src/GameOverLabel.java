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

import acm.graphics.GCompound;
import acm.graphics.GLabel;

public class GameOverLabel extends GCompound{
	
	private int fontSize;
	private String fontFamily;
	private boolean isBold; 
	private Color color;
	private int score;
	
	public GameOverLabel(int score) {
		this(30, "Times New Romans", false, Color.WHITE, score);
	}
	
	public GameOverLabel(int fontSize, String fontFamily, boolean isBold, Color color, int score) {
		this.fontSize = fontSize;
		this.fontFamily = fontFamily;
		this.isBold = isBold;
		this.color = color;
		this.setScore(score);
		
		/**Create the labels with the different instructions*/
		GLabel instruction1 = instruction("Game Over!");
		GLabel instruction2 = instruction("You ate " + (this.getScore()) + " apples!");	
		GLabel instruction3 = instruction("Restart Game feature coming soon..");
		

		add(instruction1, 2, 1);
		add(instruction2, 0, instruction1.getHeight()*3);
		add(instruction3, 0, instruction2.getHeight()*5);
	}
	
	
	private GLabel instruction(String instructionText) {
		
		GLabel instructionLabel = new GLabel(instructionText);
		instructionLabel.setFont(new Font(this.fontFamily, this.setBold(), this.fontSize));
		instructionLabel.setColor(this.color);
		
		return instructionLabel;
	}
	
	private int setBold() {
		
		if(this.isBold) {
			return Font.BOLD;
		}
		return Font.PLAIN;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
