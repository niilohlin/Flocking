package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoidTest {

	@Test
	public void test() {
		Vector v = new Vector(2, 33);
		Boid a = new Boid(v);
		Boid b = new Boid(v);
		assertEquals("Boid vectors must be equal", a.getPos().getX(), b.getPos().getX());
		
		
	}

}
