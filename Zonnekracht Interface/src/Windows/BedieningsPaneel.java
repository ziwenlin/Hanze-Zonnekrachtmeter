package Windows;

import Buttons.Presets;
import processing.core.PApplet;
import processing.core.PImage;

public class BedieningsPaneel extends PApplet {

	private Presets preset;
	private int inactivity = 0;
	public static boolean mouseP = false;
	private PImage bg;
	
	public void settings() {
		size(1080, 640);
		noSmooth();
	}

	public void setup() {
		frameRate(40);
		noStroke();
		preset = new Presets(this);
		surface.setResizable(true);
		preset.bedieningspaneel();
		bg = loadImage("zonnepaneel.jpg");
		imageMode(CENTER);
	}

	public void draw() {
		image(bg, width/2, height/2, width, height);
		preset.draw();
		
		if (inactivity > 3) // Inactiviteit tot 3 frames
			noLoop(); // Energie besparing
		inactivity++; // Inactiviteit met 1 omhoog
		mouseP = false;
	}
	
	public void mouseReleased() {
		mouseP = true;
		inactivity = 0;
		loop();
	}
	
	public void mouseDragged() {
		inactivity = 0;
		loop();
	}
	
	public void mouseClicked() {
		mouseP = true;
		inactivity = 0;
		loop();
	}
	
	public void mouseMoved() {
		inactivity = 0;
		loop();
	}

}
