package gui;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

public class Player extends JButton {

	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	public static final int UP = -2;
	public static final int DOWN = 2;

	private static final int STEP = 20;

	public Player() {
		super();
		setSize(40, 40);
		setBackground(Color.GREEN);
	}

	public void hasToGoTo(int direction) {
		hasToGoTo(direction, STEP);
	}
	
	public void hasToGoBackTo(int direction, int step) {
		go(direction, -step);
	}

	private void hasToGoTo(int direction, int step) {

		if (direction == KeyEvent.VK_LEFT) {
			go(LEFT, step);
		} else if (direction == KeyEvent.VK_RIGHT) {
			go(RIGHT, step);
		} else if (direction == KeyEvent.VK_UP) {
			go(UP, step);
		} else if (direction == KeyEvent.VK_DOWN) {
			go(DOWN, step);
		}
	}

	private void go(int direction, int step) {

		int x = getLocation().x;
		int y = getLocation().y;

		if (direction == LEFT) {
			x = x - step;
		} else if (direction == RIGHT) {
			x = x + step;
		} else if (direction == UP) {
			y = y - step;
		} else if (direction == DOWN) {
			y = y + step;
		}

		setLocation(x, y);
		PlayerPosition.x = x;
		PlayerPosition.y = y;
	}
}
