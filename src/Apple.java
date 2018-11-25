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
