package gui;

import java.awt.Color;

import javax.swing.JPanel;

public class Obstacle extends JPanel {

	public Obstacle(int x, int y) {
		super();
		setBounds(x, y, 40, 40);
		setBackground(Color.black);
	}
}
