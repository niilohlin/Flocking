package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Boid {
	private final float maxForce = 0.01f;
	private final float maxSpeed = 2.0f;
	private final float defaultRadius = 50.0f;
	
	private Vector pos;
	private Vector acc;
	private Vector vel;
	private float mf;
	private float ms;
	
	private void setUp(Vector pos, Vector acc, Vector vel, float maxSpeed, float maxForce) {
		this.pos = pos;
		this.acc = acc;
		this.vel = vel;
		this.ms = maxSpeed;
		this.mf = maxForce;
	}

	public Boid(Vector pos) {
		setUp(pos, new Vector(0, 0), new Vector(0,0), maxSpeed, maxForce);
	}
	
	public Boid() {
		setUp(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), maxSpeed, maxForce);
	}
	
	public void flock(ArrayList<Boid> boids) {
		Vector sep = separate(boids);
		Vector ali = align(boids);
		Vector coh = cohesion(boids);
	
		// multiply some constants
		sep = sep.mul(2.0f);
		coh = coh.mul(0.05f);
		
		acc = acc.add(sep);
		acc = acc.add(ali);
		acc = acc.add(coh);
	}

	//following three function has proposed default values. but those can change
	// by calling the functions directly
	public Vector align(ArrayList<Boid> boids) {
		return align(boids, defaultRadius);
	}

	public Vector cohesion(ArrayList<Boid> boids) {
		return cohesion(boids, defaultRadius);
	}

	public Vector separate(ArrayList<Boid> boids) {
		return separate(boids, defaultRadius / 2);
	}
	

	public Vector align(ArrayList<Boid> boids, float radius) {
		Vector sum = new Vector(0, 0);
		int count = 0;
		for(Boid boid : boids) {
			float d = pos.distance(boid.getPos());
			if(0 < d && d < radius) {
				sum = sum.add(boid.getVel());
				count++;
			}
		}
		
		if(count > 0) {
			sum = sum.div(count);
			return sum.limit(0.05f);
		}
		else {
			return sum;
		}
	}
	
	
	public Vector separate(ArrayList<Boid> boids, float radius) {
		//add boids in the same height and width as the boid in question.
		//serving as a border.
		Boid[] border = new Boid[4];
		border[0] = new Boid(new Vector(pos.getFloatX(), 0));
		border[1] = new Boid(new Vector(pos.getFloatX(), 600));
		border[2] = new Boid(new Vector(0, pos.getFloatY()));
		border[3] = new Boid(new Vector(800, pos.getFloatY()));
		
		Vector sum = new Vector(0, 0);
		int count = 0;
		// for every boid in the system, check if it's too close
		for(Boid boid : boids) {
			float d = pos.distance(boid.getPos());
			if((0 < d) && (d < radius)) {
				Vector diff = pos.sub(boid.getPos());
				Vector ndiff = diff.normal();
				Vector divdiff = ndiff.div(d);
				sum = sum.add(divdiff);
				count++;
				
			}
		}
		
		//simple copy/paste
		for(Boid boid : border) {
			float d = pos.distance(boid.getPos());
			if((0 < d) && (d < radius)) {
				Vector diff = pos.sub(boid.getPos());
				Vector ndiff = diff.normal();
				Vector divdiff = ndiff.div(d);
				sum = sum.add(divdiff);
				count++;
				
			}
		}

		if(count > 0)
			return sum.div((float) count);
		else 
			return sum;
	}
	
	public Vector cohesion(ArrayList<Boid> boids, float radius) {
		//add all boids vectors together that are inside radius.
		//and take the mean. then steer towards the mean
		Vector sum = new Vector(0, 0);
		int count = 0;
		
		for(Boid boid : boids) {
			float d = pos.distance(boid.getPos());
			if(0 < d && d < radius) {
				sum = sum.add(boid.getPos());
				count++;
			}
		}
		if(count > 0) {
			sum = sum.div((float)count);
			return steer(sum);
		} else {
			return new Vector(0,0);
		}
	}
	
	public Vector steer(Vector target) {
		return steer(target, false);
	}
	public Vector steer(Vector target, boolean slowdown) {
		Vector steer;
		Vector desired = target.sub(pos);
		float d = desired.magnitude();
		
		if(d > 0) {
			desired = desired.normal();
			
			if(slowdown && d < 100.0f)
				desired = desired.mul(ms*(d/100.0f));
			else
				desired = desired.mul(ms);
			
			steer = desired.sub(vel);
			steer = steer.limit(mf);
			return steer;
		} else {
			return new Vector(0, 0);
		}
	}
	
	public void update(ArrayList<Boid> boids) {
		flock(boids);
		
		vel = vel.add(acc);
		vel = vel.limit(0.7f);
		pos = pos.add(vel);
		acc = new Vector(0, 0);
		
	}
	
	public void drawBoid(Graphics2D g) {
		g.setColor(Color.red);
		float r = 7;
		g.drawOval(pos.getX(), pos.getY(), (int)r, (int)r);
		
		Vector center = pos.add(new Vector(r / 2, r / 2));
		
		g.drawLine(center.getX(), center.getY(), center.add(vel.mul(r * 2)).getX(), center.add(vel.mul(r * 2)).getY());
	}
	
	public Vector getVel() {
		return vel;
	}
	public Vector getPos() {
		return pos;
	}
}
