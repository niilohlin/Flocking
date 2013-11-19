package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Flock {
	private ArrayList<Boid> boids;
	public Flock() {
		boids = new ArrayList<Boid>();
	}
	
	public void update() {
		for(Boid boid : boids) {
			boid.update(boids);
		}
	}
	
	public void drawFlock(Graphics2D g) {
		for(Boid boid : boids) {
			boid.drawBoid(g);
		}
		
	}
	public void add(Boid b) {
		boids.add(b);
	}
}
