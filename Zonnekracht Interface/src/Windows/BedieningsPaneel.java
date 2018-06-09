package Windows;

import Buttons.ButtonHandler;
import Serial.SerialManager;
import processing.core.PApplet;

public class BedieningsPaneel extends PApplet {

	private SerialManager sm = new SerialManager(this);
	private ButtonHandler b = new ButtonHandler(this);

	public void settings() {
		size(900, 600);
	}

	public void setup() {
		frameRate(5);
		surface.setResizable(true);
		b.newButton(200, 100, 300, 100);
		b.modeButton(CENTER);
		b.colorButton(color(90));
		b.textButton("Start Serial COM");
		b.textColor(color(255));
		b.textSize(36);
	}

	public void draw() {
		background(20);
		sm.stringsplitter();
		b.drawButtons();
	}

	

}
