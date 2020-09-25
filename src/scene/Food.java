package scene;

import static util.Constants.FOOD_COLOR;
import static util.Constants.FOOD_SIZE;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import graphics.Rect;
import util.GameUtils;

public class Food extends Rect {
	private int eatenTimes;

	public Food(Snake snake, Rectangle drawingArea) {
		setRandomLocation(snake, drawingArea);
		setDimension(new Dimension(FOOD_SIZE, FOOD_SIZE));
		setColor(FOOD_COLOR);
	}

	public void setRandomLocation(Snake snake, Rectangle drawingArea) {
		int offset = 3;
		
		do {
			int x = GameUtils.random((int) drawingArea.getMinX() + offset, (int) drawingArea.getMaxX() - FOOD_SIZE - offset);
			int y = GameUtils.random((int) drawingArea.getMinY() + offset, (int) drawingArea.getMaxY() - FOOD_SIZE - offset);
			
			setlocation(new Point(x, y));
		} while(snake.intersects(this));
	}
	
	public void checkIfEaten(Snake snake, Rectangle drawingArea) {
		if (snake.intersects(this)) {
			eatenTimes++;
			setRandomLocation(snake, drawingArea);
			snake.elongate();
		}
	}
	
	public int getEatenTimes() {
		return eatenTimes;
	}
}
