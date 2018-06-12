package Windows;

import Serial.SerialManager;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.serial.Serial;

public class SerieleMonitorPaneel extends PApplet {

	private SerialManager sm;
	public boolean visibility = true;
	
	public void settings() {
		size(1200, 680);
	}
	
	public void setup() {

		sm = new SerialManager(this);
		sm.serialInit();
		surface.setResizable(true);
		toggleVisibility();
	}
	
	public void toggleVisibility() {
		visibility = !visibility;
		surface.setVisible(visibility);
	}
	
	public void draw() {
		background(100);
		sm.serialLoop();
		textMonitor();
		noLoop();
	}

	public void textMonitor() {
		if (SerialManager.inputSerial.size() == 0) {
			return;
		}
		rectMode(PConstants.CORNER);
		textAlign(PConstants.LEFT, PConstants.TOP);
		textSize(32);
		text(SerialManager.inputSerial.get(SerialManager.inputSerial.size() - 1),
				10, 10, width - 10, height - 10);
		textAlign(PConstants.LEFT);
		textSize(16);
		for (int i = 0; i < SerialManager.inputSerial.size(); i++) {
			text(SerialManager.inputSerial.get(i), 10, 20 * (i + 4));
		}
	}
	
	public void serialEvent(Serial p) {
		String str = p.readString();
		SerialManager.inputSerial.add(str);
		SerialManager.canSplit = true;
		loop();
	}
}
