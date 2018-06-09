package Buttons;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class ButtonHandler {

	private PApplet p;
	private List<Button> button = new ArrayList<>();
	
	public ButtonHandler(PApplet p) {
		this.p = p;
	}
	
	public void newButton(float a, float b, float c, float d) {
		button.add(0, new Button(p, a, b, c, d));
	}
	
	public void drawButtons() {
		for (Button b : this.button) {
			b.drawButton();
		}
	}

	public void modeButton(int mode) {
		getButton().setMode(mode);
	}

	public void textButton(String string) {
		getButton().setText(string);
	}
	
	public Button getButton() {
		return button.get(0);
	}

	public void textColor(int color) {
		getButton().setTextColor(color);
	}

	public void colorButton(int color) {
		getButton().setColor(color);
	}

	public void textSize(int size) {
		getButton().setTextSize(size);
	}
}
