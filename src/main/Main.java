package main;

import javax.swing.JFrame;

public class Main extends JFrame {
	public static Main main;
	public static Canvas canvas;

	// set up that shit
	public Main() {
		setResizable(false);
		setTitle("flocking algorithm");
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		main = new Main();
		canvas = new Canvas();
		main.add(canvas);
		canvas.start();
	}
}
