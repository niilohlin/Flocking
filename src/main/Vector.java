package main;

public class Vector {
	private float x;
	private float y;

	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float distance(Vector other) {
		float dx = x - other.getFloatX();
		float dy = y - other.getFloatY();
		return (float) Math.sqrt(dx * dx + dy * dy);
	}
	
	public Vector sub(Vector other) {
		return new Vector(x - other.getFloatX(), y - other.getFloatY());
	}
	
	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public Vector normal() {
		if(x == 0.0f && y == 0.0f)
			return new Vector(0.0f, 0.0f);
		else
			return div(magnitude());
	}

	public float X(Vector other) {
		//X is mnemonic for the cross product
		return getFloatX() * other.getFloatX() + getFloatY() * other.getFloatY();
	}

	public Vector div(float a) {
		return new Vector(x / a, y / a);
	}
	
	public Vector add(Vector other) {
		return new Vector(x + other.getFloatX(), y + other.getFloatY());
	}
	
	public Vector limit(float max) {
		if(magnitude() > max) 
			return normal().mul(max);
		else
			return new Vector(getFloatX(), getFloatY());
	}
	public Vector mul(float a) {
		return new Vector(x * a, y * a);
	}
	public float getFloatX() {
		return x;
	}
	public float getFloatY() {
		return y;
	}
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = (float) x;
	}
	
	public void setY(int y) {
		this.y = (float) y;
	}
	
	
}
