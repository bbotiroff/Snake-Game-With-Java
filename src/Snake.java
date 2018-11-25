import java.util.ArrayList;

public class Snake{
	
	private ArrayList<Block> snake;
	private int snakeLength;
	private int blockSize;
	private String direction;
	private String nextDirection;
	private Block block;
	
	public Snake(int blockSize) {
		
		this.snake = new ArrayList<Block>();
		this.blockSize = blockSize;
		this.snakeLength = 3;
		this.direction = "down";
		this.nextDirection = "down";
		this.initSnake();
	}

	public ArrayList<Block> getSnake() {
		return this.snake;
	}
	
	private void initSnake() {
		
		for(int i=0; i<this.snakeLength; i++) {
			this.block = new Block(this.blockSize, i, 2);
			this.snake.add(this.block);
		}
	}

	public int getSnakeLength() {
		return snakeLength;
	}

	public String getDirection() {
		return this.direction;
	}
	
	public void grow() {
		
		Block tail = this.snake.get(this.snake.size() - 1);
		Block newTail = new Block(this.blockSize, tail.getCol(), tail.getRow());

		this.snakeLength++;
		this.snake.add(newTail);
	}

	public boolean didEatApple(Block apple) {

		if(this.snake.get(0).isEqual(apple)) {			
			return true;
		}
		
		return false;
	}
	
	public void move() {
		
		Block head = this.snake.get(0);
		Block newHead = null;
		
		this.direction = this.nextDirection;
		
		if(this.direction.equals("left")) {
			newHead = new Block(this.blockSize, head.getCol() - 1, head.getRow());
		}
		if(this.direction.equals("right")) {
			newHead = new Block(this.blockSize, head.getCol() + 1, head.getRow());
		}
		if(this.direction.equals("down")) {
			newHead = new Block(this.blockSize, head.getCol(), head.getRow() + 1);
		}
		if(this.direction.equals("up")) {
			newHead = new Block(this.blockSize, head.getCol(), head.getRow() - 1);
		}
		
		this.snake.add(0, newHead);
		this.snake.remove(this.snake.size() - 1);
	}
	
	public void setDirection(String direction) {
		
		if(this.direction.equals("right") && direction.equals("left")) {
			return;
		}
		if(this.direction.equals("left") && direction.equals("right")) {
			return;
		}
		if(this.direction.equals("up") && direction.equals("down")) {
			return;
		}
		if(this.direction.equals("down") && direction.equals("up")) {
			return;
		}
		
		this.nextDirection = direction;
	}
	
	public boolean isSelfCollide() {
		
		boolean selfCollision = false;
		
		for(int i = 1; i<this.snakeLength; i++) {
			if(this.snake.get(0).isEqual(this.snake.get(i))) {
				return true;
			}
		}
		
		return selfCollision;
	}
	
}
