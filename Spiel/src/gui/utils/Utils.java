package gui.utils;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Utils {
	
	public static int distanceBetween(Rectangle p, Rectangle o, int direction) {

		switch (direction) {
		case KeyEvent.VK_LEFT:
			return o.x + o.width - p.x;
		case KeyEvent.VK_RIGHT:
			return p.x + p.width - o.x;
		case KeyEvent.VK_UP:
			return o.y + o.height - p.y;
		case KeyEvent.VK_DOWN:
			return p.y + p.width - o.y;

		default:
			return 0;
		}
	}
	
	public static int distanceToEdge(Rectangle p, Rectangle edger, int direction) {

		switch (direction) {
		case KeyEvent.VK_LEFT:
			return p.x;
		case KeyEvent.VK_RIGHT:
			return edger.width - (p.x + p.width);
		case KeyEvent.VK_UP:
			return p.y;
		case KeyEvent.VK_DOWN:
			return edger.y - (p.y + p.height);

		default:
			return 0;
		}
	}

}
