package graphics;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Renderer {
	private List<Drawable> drawables;
	private Graphics g;

	public Renderer(Graphics g) {
		this.g = g;
		drawables = new ArrayList<>();
	}
	
	public synchronized void render() {
		for (Drawable d : drawables) {
			g.setColor(d.getColor());
			d.draw(g);
		}
	}
	
	public synchronized void add(Drawable drawable) {
		drawables.add(drawable);
	}
	
	public synchronized void remove(Drawable drawable) {
		drawables.remove(drawable);
	}
}
