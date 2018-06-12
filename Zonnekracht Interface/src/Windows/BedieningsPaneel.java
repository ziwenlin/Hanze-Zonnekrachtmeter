package Windows;

import Buttons.Presets;
import processing.core.PApplet;
import processing.core.PImage;

public class BedieningsPaneel extends PApplet {

	private Presets preset;
	private int inactivity = 0;
	private PImage bg;
	public static boolean mouseP = false;
	
	public void settings() {
		size(1080, 640); // Grootte van het venster
		noSmooth(); // Anti-Aliasing uit, bespaart een hoop energie en rekenkracht
	}

	public void setup() {
		frameRate(60); // Aantal beeldjes dat per seconden wordt aangemaakt.
		noStroke(); // Geen randjes van de knoppen
		preset = new Presets(this); // Nieuwe preset object
		surface.setResizable(true); // Vensters kunnen in groottes worden aangepast
		preset.presetbedieningspaneel(); // Laadt preset van de bedieningspaneel
		bg = loadImage("zonnepaneel.jpg"); // Afbeelding naam
		imageMode(CENTER); // Achtergrond is gecentreerd
	}

	public void draw() {
		image(bg, width/2, height/2, width, height); // Achtergrond
		preset.draw(); 	// Teken de knoppen
		
		if (inactivity > 3) // Inactiviteit tot 3 frames
			noLoop(); // Energie besparing
		inactivity++; // Inactiviteit met 1 omhoog
		mouseP = false; // Muis is niet ingedrukt
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
