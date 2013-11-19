package main;

public class Line {
	private Vector a;
	private Vector b;
	
	public Line(Vector a, Vector b) {
		this.a = a;
		this.b = b;
	}
	public boolean crossing(Line l) {
		//I do not understand this. And I do not care to learn.
		Vector e = b.sub(a);
		Vector f = l.getB().sub(l.getA());
		Vector p = new Vector(-e.getFloatY(), e.getFloatX());
		float h = ((a.sub(l.getA()).X(p)) / (f.X(p)));
		return 0 < h && h < 1; //Could be changed to 0 <= h <= 1 if you want to include the cases where the ends touch
	}
	
	public Vector getA() {
		return a;
	}
	public Vector getB() {
		return b;
	}
}
