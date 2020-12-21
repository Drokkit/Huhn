package gui.levels;

import java.awt.Color;
import java.awt.Rectangle;

import gui.GamePanel;

public class Level1 extends GamePanel{
	
	public Level1(Rectangle bounds) {
		super(bounds, 15, 6);
	}

	public Level1(Rectangle bounds, int i, int j) {
		super(bounds, 1, 0);
	}

	protected void init() {
		setBackground(new Color(200, 255, 255));
		addKeyListener(this);
	}
}
