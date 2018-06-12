package Windows;

import Serial.SerialManager;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.serial.Serial;

public class SerieleMonitorPaneel extends PApplet {

	private SerialManager sm;
	public boolean visibility = true;
	
	public void settings() {
		size(1200, 680); // Venster grootte
	}
	
	public void setup() {

		sm = new SerialManager(this); // Seriele manager initialiseren
		sm.serialInit(); // Seriele poort initialiseren
		surface.setResizable(true); // Grootte aanpasbaar
		toggleVisibility(); // Zichtbaarheid van deze venster
	}
	
	public void toggleVisibility() { // Zichtbaarheid van deze venster
		visibility = !visibility;
		surface.setVisible(visibility);
	}
	
	public void draw() {
		background(60); // Achtergrond grijs
		sm.serialLoop(); // Seriele informatie verwerken
		textMonitor(); // Seriele informatie op het scherm laten zien
		noLoop(); // Draw mag niet zo vaak worden uitgevoerd
	}

	public void textMonitor() {
		if (SerialManager.inputSerial.size() == 0) { // Nullpointerexception voorkomen
			return;
		}
		// Tekst van de laatste binnen komende bericht.
		rectMode(PConstants.CORNER);
		textAlign(PConstants.LEFT, PConstants.TOP);
		textSize(32);
		text(SerialManager.inputSerial.get(SerialManager.inputSerial.size() - 1),
				10, 10, width - 10, height - 10);
		
		// Tekst van de laatste aantal binnen komende berichten.
		textAlign(PConstants.LEFT);
		textSize(16);
		for (int i = 0; i < SerialManager.inputSerial.size(); i++) {
			text(SerialManager.inputSerial.get(i), 10, 20 * (i + 4));
		}
	}
	
	public void serialEvent(Serial p) {
		String str = p.readString(); // Lezen van de binnenkomende informatie
		SerialManager.inputSerial.add(str); // Seriële informatie toevoegen aan de lijst.
		SerialManager.splitNow = true;  // Geef commando dat de informatie kan worden geanalyseerd
		loop(); // Draw mag een keer uitgevoerd worden
	}
}
