package gui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gui.enemies.Covid;
import gui.utils.Utils;

public class GamePanel extends JPanel implements KeyListener {

	private Player player;
	private int myNextPanel;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Covid> covids;

	public GamePanel() {
		super();
	}

	public GamePanel(Rectangle bounds, int obtaclesNumber, int covidNumber) {
		super();
		setBounds(bounds);
		setLayout(null);
		init();
		createObstacles(obtaclesNumber);
		createCovids(covidNumber);
	}

	public GamePanel(int x, int y, int width, int height, int obtaclesNumber, int covidNumber) {
		this(new Rectangle(x, y, width, height), obtaclesNumber, covidNumber);
	}

	protected void init() {
		setBackground(new Color(255, 255, 255));
		addKeyListener(this);
	}

	private void createObstacles(int number) {

		if (!(number > 0))
			return;
		Random wuerfel = new Random();
		int w = (int) getSize().getWidth() - 40;
		int h = (int) getSize().getHeight() - 40;

		obstacles = new ArrayList<Obstacle>(number);

		for (int i = 0; i < number; i++) {

			obstacles.add((Obstacle) add(new Obstacle(wuerfel.nextInt(w), wuerfel.nextInt(h))));
		}
	}

	private void createCovids(int number) {

		if (!(number > 0))
			return;
		Random wuerfel = new Random();
		int w = (int) getSize().getWidth() - 20;
		int h = (int) getSize().getHeight() - 20;

		covids = new ArrayList<Covid>(number);

		for (int i = 0; i < number; i++) {

			covids.add((Covid) add(new Covid(wuerfel.nextInt(w), wuerfel.nextInt(h))));
		}
		for (Covid covid : covids) {
			covid.start();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		player.hasToGoTo(code);

		if (playerOnGoal()) {
			nextLevel();
		} else if (playerOnCovid()) {
			reset();
		} else {

			int step = checkPlayerOnObstacle(code);
			if (step > 0)
				player.hasToGoBackTo(code, step);
			else {
				step = playerOnEdge(code);
				if (step > 0)
					player.hasToGoBackTo(code, step);
			}
		}
	}
	
	int playerOnEdge(int direction) {
		
		return Utils.distanceToEdge(player.getBounds(), this.getBounds(), direction);
	}

	boolean playerOnEdge() {
		return playerOnLeftEdge() || playerOnUpperEdge() || playerOnLowerEdge();
	}

	boolean playerOnLeftEdge() {
		return player.getBounds().intersectsLine(getBounds().x, getBounds().y, getBounds().getWidth(), getBounds().y);
	}

	boolean playerOnRightEdge() {
		return player.getBounds().intersectsLine(getBounds().getWidth(), getBounds().y, getBounds().getWidth(),
				getBounds().getHeight());
	}

	boolean playerOnUpperEdge() {
		return player.getBounds().intersectsLine(getBounds().x, getBounds().y, getBounds().getWidth(), getBounds().y);
	}

	boolean playerOnLowerEdge() {
		return player.getBounds().intersectsLine(getBounds().x, getBounds().getHeight(), getBounds().getWidth(),
				getBounds().getHeight());
	}

	boolean playerOnGoal() {

		return playerOnRightEdge();
	}

	boolean playerOnObstacle() {

		for (Obstacle obstacle : obstacles) {
			if (player.getBounds().intersects(obstacle.getBounds()))
				return true;
		}
		return false;
	}

	int checkPlayerOnObstacle(int direction) {

		Rectangle p;
		Rectangle o;
		for (Obstacle obstacle : obstacles) {
			if (player.getBounds().intersects(obstacle.getBounds())) {
				
				return Utils.distanceBetween(player.getBounds(), obstacle.getBounds(), direction);
//				p = player.getBounds();
//				o = obstacle.getBounds();
//				switch (direction) {
//				case KeyEvent.VK_LEFT:
//					return obstacle.getBounds().x + obstacle.getBounds().width - player.getBounds().x;
//				case KeyEvent.VK_RIGHT:
//					return player.getBounds().x + player.getBounds().width - obstacle.getBounds().x;
//				case KeyEvent.VK_UP:
//					return obstacle.getBounds().y + obstacle.getBounds().height - player.getBounds().y;
//				case KeyEvent.VK_DOWN:
//					return player.getBounds().y + player.getBounds().width - obstacle.getBounds().y;
//
//				default:
//					break;
//				}
			}
		}
		return 0;
	}

	private boolean playerOnCovid() {
		for (Covid covid : covids) {
			if (player.getBounds().intersects(covid.getBounds()))
				return true;
		}
		return false;
	}

	private void nextLevel() {
		((GameFrame) SwingUtilities.getRoot(this)).changePanel(this);
	}

	public void reset() {

		resetObstacles();
		resetCovids();
		positionPlayer();
	}

	public void resetObstacles() {

	}

	public void resetCovids() {

	}

	private void stopCovids() {
		for (Covid covid : covids) {
			covid.stop();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		if (player == null)
			player = new Player();

		this.player = (Player) add(player);
		positionPlayer();
	}

	private void positionPlayer() {
		this.player.setLocation(5, getBounds().height / 2);
	}

	public int getMyNextPanel() {
		return myNextPanel;
	}

	public void setMyNextPanel(int myNextPanel) {
		this.myNextPanel = myNextPanel;
	}
}
