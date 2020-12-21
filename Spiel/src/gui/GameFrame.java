package gui;

import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import gui.levels.Level1;

public class GameFrame extends JFrame{
	
	GamePanel panel;

	public GameFrame(String title) throws HeadlessException {
		
		super(title);
		
		setBounds(100, 0, 1000, 800);
		setVisible(true);
		setLayout(null);
		
		addWindowListener(new WindowAdapter()
	      {
	         public void windowClosing(WindowEvent e)
	         {
	           dispose();
	           System.exit(0); 
	         }
	      });
		
		panel = (GamePanel) add(new GamePanel(20, 20, 940, 720, 1, 0));
		panel.setMyNextPanel(2);
		panel.requestFocus();
		panel.setPlayer(null);
		
		repaint();
	}
	
	public void changePanel(GamePanel panel) {
		
		Player p = panel.getPlayer();
		int level = panel.getMyNextPanel();
		Rectangle bounds = panel.getBounds();
		panel.invalidate();
		getContentPane().remove(panel);
		revalidate();
		
		switch (level) {
		
		case 1:
			panel = (GamePanel) add(new GamePanel(bounds, 10, 5));
			panel.setMyNextPanel(2);
			break;
		case 2:
			panel = (Level1) add(new Level1(bounds));
			panel.setMyNextPanel(1);
			break;

		default:
			break;
		}
		
		panel.requestFocus();
		panel.setPlayer(p);
		panel.validate();
		repaint();
	}
}
