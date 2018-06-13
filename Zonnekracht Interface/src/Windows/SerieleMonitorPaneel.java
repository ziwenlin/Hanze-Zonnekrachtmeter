package Windows;

import Serial.SerialManager;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.serial.Serial;

public class SerieleMonitorPaneel extends PApplet {

	private SerialManager serialM; // Serial Manager
	public boolean visibility = true; // Zichtbaarheid
	
	public void settings() {
		size(1200, 680); // Venster grootte
	}
	
	public void setup() {

		serialM = new SerialManager(this); // Seriele manager initialiseren
		serialM.serialInit(); // Seriele poort initialiseren
		surface.setResizable(true); // Grootte aanpasbaar
		toggleVisibility(); // Zichtbaarheid van deze venster
	}
	
	public void toggleVisibility() { // Zichtbaarheid van deze venster
		visibility = !visibility;
		surface.setVisible(visibility);
	}
	
	public void draw() {
		background(60); // Achtergrond grijs
		serialM.serialLoop(); // Seriele informatie verwerken
		textMonitor(); // Seriele informatie op het scherm laten zien
		noLoop(); // Draw mag niet zo vaak worden uitgevoerd
	}

	public void textMonitor() {
		if (SerialManager.inputSerial.size() == 0) { // Nullpointer Exception Error/Crash voorkomen
			return;
		}
		// Tekst van de laatste binnen komende bericht.
		rectMode(PConstants.CORNER); // Positie van de tekst box
		textAlign(PConstants.LEFT, PConstants.TOP); // Positie van de tekst op het scherm
		textSize(32); // Tekst grootte 
		text(SerialManager.inputSerial.get(SerialManager.inputSerial.size() - 1), // Print de tekst op het scherm
				10, 10, width - 10, height - 10);
		
		// Tekst van de laatste aantal binnen komende berichten.
		textAlign(PConstants.LEFT); // Tekst positie
		textSize(16); // Tekst grootte
		for (int i = 0; i < SerialManager.inputSerial.size(); i++) { // Print de tekst op het scherm
			text(SerialManager.inputSerial.get(i), 10, 20 * (i + 4)); // Print de tekst op het scherm
		}
	}
	
	public void serialEvent(Serial p) {
		String str = p.readString(); // Lezen van de binnenkomende informatie
		SerialManager.inputSerial.add(str); // Seriële informatie toevoegen aan de lijst.
		SerialManager.splitNow = true;  // Geef commando dat de informatie kan worden geanalyseerd
		loop(); // Draw mag een keer uitgevoerd worden
	}
}
