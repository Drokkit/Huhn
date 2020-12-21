package gui.enemies;

import java.awt.Color;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import gui.PlayerPosition;

public class Covid extends JPanel {

	Timer timer = new Timer();
	int step = 1;

	public Covid(int x, int y) {
		super();
		setBackground(Color.red);
		setBounds(x, y, 20, 20);
	}

	public void start() {
		timer.schedule(new TimerTask() {
			public void run() {
				
				changePosition();
			}

		}, 0, 100);

	}
	
	private void changePosition() {

		int px = PlayerPosition.x;
		int py = PlayerPosition.y;
		int x = getLocation().x;
		int y = getLocation().y;
		
		x = x < px ? x + step : x - step;
		y = y < py ? y + step : y - step;
		setLocation(x, y);	
	}
	
	public void stop() {
		timer.cancel();
	}

	public Covid() {
		super();
	}
}
