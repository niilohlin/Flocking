package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineTest {

	@Test
	public void test() {
		Line a = new Line(new Vector(0, 0), new Vector(1, 1));
		Line b = new Line(new Vector(1, 0), new Vector(0, 1));
		assertTrue("lines should cross", a.crossing(b));
		Line c = new Line(new Vector(2, 2), new Vector(5, 5));
		assertFalse("lines shold not cross", a.crossing(c));
	}

}
