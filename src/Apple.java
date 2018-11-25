/*
 * @ Author: Bakhrom Botirov 
 * @ Game Name: Snake
 * @ Professor: Usman Risvi
 * @ Class: C SCI 143 (6146)
 * @ Date: 11/25/2018
 * 
 */
public class Apple extends Block{
	
	private int totalGrids;
	
	public Apple(int size, int totalGrids) {
		super(size, 10, 2);
		this.totalGrids = totalGrids;
	}
	
	public void move() {
		int randomCol = (int) (Math.random() * Math.sqrt(this.totalGrids) - 1);
		int randomRow = (int) (Math.random() * Math.sqrt(this.totalGrids) - 1);
		super.setCol(randomCol);
		super.setRow(randomRow);
	}
}
