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

public class Instructions extends GCompound{
	
	private int fontSize;
	private String fontFamily;
	private boolean isBold; 
	private Color color;
	
	public Instructions() {
		this(25, "Sans serif", false, Color.WHITE);
	}
	
	public Instructions(int fontSize, String fontFamily, boolean isBold, Color color) {
		this.fontSize = fontSize;
		this.fontFamily = fontFamily;
		this.isBold = isBold;
		this.color = color;
		
		/**Create the labels with the different instructions*/
		GLabel author = instruction("Author: Bakhrom Botirov");
		GLabel professor = instruction("Professor: Usman Risvi");
		GLabel highlineClass = instruction("Class: C SCI 143 (6146)");
		GLabel purpose = instruction("Purpose: Final Project");
		GLabel gameName = instruction("Snake Game");
		GLabel instruction1 = instruction("Instructions: Click in the window to start.");
		GLabel instruction2 = instruction("Eat the apple to score.");	
		GLabel instruction3 = instruction("Hit the wall or yourself and you lose!");
		

		add(author, 0, 0);
		add(professor, 0, author.getHeight()*2);
		add(highlineClass, 0, professor.getHeight()*3);
		add(purpose, 0, highlineClass.getHeight()*4);
		add(gameName, (getWidth()-gameName.getWidth())/2, purpose.getHeight()*6);
		add(instruction1, 0, gameName.getHeight()*8);
		add(instruction2, 0, instruction1.getHeight()*9);
		add(instruction3, 0, instruction2.getHeight()*10);
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
}
