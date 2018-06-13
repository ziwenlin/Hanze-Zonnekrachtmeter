package Windows;

import Buttons.Presets;
import processing.core.PApplet;
import processing.core.PImage;

public class BedieningsPaneel extends PApplet {

	private Presets preset; // Preset
	private PImage bg; // Achtergrond 
	
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

		noLoop();
	}
	
	/*
	 * 
	 * Eventhandlers
	 * 
	 * (non-Javadoc)
	 * @see processing.core.PApplet#mouseReleased()
	 */
	
	public void mouseReleased() {
		loop();
	}
	
	public void mouseDragged() {
		loop();
	}
	
	public void mouseClicked() {
		loop();
	}
	
	public void mouseMoved() {
		loop();
	}

}
