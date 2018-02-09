package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		gObjects.add(gObject);
	}

	public void remove(GObject gObject) {
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		this.x += dX;
		this.y += dY;
		for(GObject gObject : gObjects) {
			gObject.move(dX, dY);
		}
	}
	
	public void recalculateRegion() {
		GObject g = this.gObjects.get(0);
		int x1 = g.x;
		int y1 = g.y;
		int dX1 = x1 + g.width;
		int dY1 = y1 + g.height;
		for(GObject gObject : this.gObjects) {
			x1 = Math.min(gObject.x, x1);
			y1 = Math.min(gObject.y, y1);
			dX1 = Math.max(gObject.x + gObject.width, dX1);
			dY1 = Math.max(gObject.y + gObject.height, dY1);
		}
		this.x = x1;
		this.y = y1;
		this.width = dX1 - x1;
		this.height = dY1 - y1;
	}

	@Override
	public void paintObject(Graphics g) {
		g.setColor(Color.black);
		for(GObject gO : gObjects) {
			gO.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		g.drawString("Composite", x, y);
	}
	
}
