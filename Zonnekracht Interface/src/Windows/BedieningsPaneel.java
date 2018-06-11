package Windows;

import java.util.ArrayList;
import java.util.List;

import Buttons.Presets;
import Serial.SerialManager;
import processing.core.PApplet;
import processing.core.PImage;

public class BedieningsPaneel extends PApplet {

	private SerialManager sm = new SerialManager(this);
	private Presets preset = new Presets(this);
	private int inactivity = 0;
	private PImage bg;
	
	public void settings() {
		size(1080, 640);
		noSmooth();
	}

	public void setup() {
		frameRate(40);
		noStroke();
		surface.setResizable(true);
		preset.bedieningspaneel();
		bg = loadImage("zonnepaneel.jpg");
		imageMode(CENTER);
		sm.serialInit();
	}

	public void draw() {
		image(bg, width/2, height/2, width, height);
		sm.serialLoop();
		preset.draw();
		
		if (inactivity > 3) // Inactiviteit tot 3 frames
			noLoop(); // Energie besparing
		inactivity++; // Inactiviteit met 1 omhoog
	}
	
	public void mouseReleased() {
		inactivity = 0;
		loop();
	}
	
	public void mouseDragged() {
		inactivity = 0;
		loop();
	}
	
	public void mouseClicked() {
		inactivity = 0;
		loop();
	}
	
	public void mouseMoved() {
		inactivity = 0;
		loop();
	}

}
