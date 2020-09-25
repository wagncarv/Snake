package core;

import static util.Constants.GAME_MIN_TIME_BETWEEN_KEYBOARD_EVENTS;
import static util.Constants.WINDOW_HEIGHT;
import static util.Constants.WINDOW_TITLE;
import static util.Constants.WINDOW_WIDTH;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import graphics.Renderer;
import scene.Snake;

public class GameWindow extends JFrame implements KeyListener {
	
	private Image buffer;
	private Graphics graphics;
	private Snake snake;
	private Renderer renderer;
	private Rectangle drawingArea;
	private long lastKeyboardEventTime;

	public GameWindow(Snake snake) {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
		setTitle(WINDOW_TITLE);
		setVisible(true);
		addKeyListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		this.snake = snake;
		buffer = createImage(WINDOW_WIDTH, WINDOW_HEIGHT);
		graphics = buffer.getGraphics();
		renderer = new Renderer(graphics);
		
		defineDrawingArea();
	}
	
	private void defineDrawingArea() {
		int upperY = WINDOW_HEIGHT - getContentPane().getSize().height;
		drawingArea = new Rectangle(0, upperY, WINDOW_WIDTH, WINDOW_HEIGHT - upperY);
	}

	public Rectangle getDrawingArea() {
		return drawingArea;
	}
	
	@Override
	public void paint(Graphics g) {
		if (graphics == null || renderer == null) {
			return;
		}

		renderer.render();
		g.drawImage(buffer, 0, 0, null);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		long now = System.currentTimeMillis();
		
		if (now - lastKeyboardEventTime < GAME_MIN_TIME_BETWEEN_KEYBOARD_EVENTS) {
			return;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			snake.up();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			snake.down();	
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			snake.left();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			snake.right();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		lastKeyboardEventTime = now;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
