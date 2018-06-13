package Buttons;

import Main.Main;
import processing.core.PApplet;

public class Presets {

	PApplet p; // Processing class/sketch
	ButtonHandler b; // Knoppen handler

	public Presets(PApplet p) {
		this.p = p;							// (this) Verwijzing naar de class Processing Sketch
		this.b = new ButtonHandler(p);		// (new) Nieuw object van de class ButtonHandler
	}

	public void presetbedieningspaneel() { // Knoppen instellingen voor de bedieningspaneel
		b.buttonNew(200, 80, 400, 100);
		b.buttonColor(p.color(160));
		b.buttonHoverColor(p.color(120));
		b.buttonAlfa(220);
		b.buttonTask(()->{Main.startPaneelZonnekrachtNetto();}); // Lambda expressie (Functies doorgeven)
		b.textButton("Grafiek Zonnekracht");
		b.textColor(p.color(255));
		b.textSize(36);

		b.buttonNew(200, 200, 400, 100);
		b.buttonColor(p.color(160));
		b.buttonHoverColor(p.color(120));
		b.buttonAlfa(220);
		b.buttonTask(()->{Main.startPaneelTemperatuur();});
		b.textButton("Grafiek Temperatuur");
		b.textColor(p.color(255));
		b.textSize(36);
//
//		b.buttonNew(200, 320, 400, 100);
//		b.buttonColor(p.color(160));
//		b.buttonHoverColor(p.color(120));
//		b.buttonAlfa(220);
//		b.buttonTask(null);
//		b.textButton("Start RS232");
//		b.textColor(p.color(255));
//		b.textSize(36);

		b.buttonNew(200, 320, 400, 100);
		b.buttonColor(p.color(160));
		b.buttonHoverColor(p.color(120));
		b.buttonAlfa(220);
		b.buttonTask(()->{Main.toggleSerialMonitor();});
		b.textButton("Seriële Monitor");
		b.textColor(p.color(255));
		b.textSize(36);
//
//		b.buttonNew(200, 560, 400, 100);
//		b.buttonColor(p.color(160));
//		b.buttonHoverColor(p.color(120));
//		b.buttonAlfa(220);
//		b.buttonTask(null);
//		b.textButton("Corrigeer Tijdmodule");
//		b.textColor(p.color(255));
//		b.textSize(36);
	}

	public void draw() {
		b.drawButtons(); // Knoppen tekenen
	}
}
