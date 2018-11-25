/*
 * @ Author: Bakhrom Botirov 
 * @ Game Name: Snake
 * @ Professor: Usman Risvi
 * @ Class: C SCI 143 (6146)
 * @ Date: 11/25/2018
 * 
 */
import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class Block extends GCompound{

	private static final long serialVersionUID = 1L;
	private int col;
	private int row;
	private int blockSize;
	
	public Block(int blockSize, int col, int row) {
		
		this.setCol(col);
		this.setRow(row);
	}
	
	public int getCol() {
		return col;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	
	public boolean isEqual(Block otherBlock) {
		return this.getCol() == otherBlock.getCol() && this.getRow() == otherBlock.getRow();
	}
}
