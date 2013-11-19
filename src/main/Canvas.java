package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;

public class Canvas extends JPanel implements KeyListener {
	private int FPS = 60;
	private Flock flock;
	
	public Canvas() {
		setVisible(true);
		setFocusable(true);
		addKeyListener(this);
	}

	private void init() {
		updateTime = System.nanoTime() / 1000000;
		flock = new Flock();
		Random r = new Random();
		for(int i = 0; i < 100; i++)
			flock.add(new Boid(new Vector(r.nextInt(800),r.nextInt(600))));
	}
	
	public void start() {
		
		init();
		while (true) {
			update();
			tick();
			repaint();
		}
	}

	private void update() {
		flock.update();
	}

	private long updateTime;

	private void tick() {
		try {
			Thread.sleep(Math.max(0, 1000 / FPS
					- (System.nanoTime() / 1000000 - updateTime)));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		updateTime = System.nanoTime() / 1000000;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fillRect(0, 0, getWidth(), getHeight());
		flock.drawFlock(g2);
	}
	// do nothing with keypresses for now
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
