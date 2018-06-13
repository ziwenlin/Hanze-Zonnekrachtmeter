package Buttons;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class ButtonHandler {

	private PApplet p;
	private List<Button> button = new ArrayList<>();

	public ButtonHandler(PApplet p) { // Button handler initialiseren
		this.p = p;
	}

	public void buttonNew(float a, float b, float c, float d) { // Toevoegen nieuwe knop
		button.add(0, new Button(p, a, b, c, d)); // First in, last out
	}

	public void drawButtons() { // Knoppen renderen op het scherm
		for (Button b : this.button) { // Iteratie over de "Array"
			b.drawButton();
		}
	}

	public Button getButton() {
		return button.get(0);
	}

	/*
	 * 
	 * Settings instellen voor de buttons
	 * 
	 */
	
	public void buttonMode(int mode) {
		getButton().setMode(mode);
	}

	public void textButton(String string) {
		getButton().setText(string);
	}

	public void textColor(int color) {
		getButton().setTextColor(color);
	}

	public void buttonColor(int color) {
		getButton().setColor(color);
	}

	public void textSize(int size) {
		getButton().setTextSize(size);
	}

	public void buttonHoverColor(int color) {
		getButton().setHoverColor(color);
	}

	public void buttonAlfa(float alfa) {
		getButton().setAlfa(alfa);
	}

	public void buttonTask(Runnable task) {
		getButton().setTask(task);
	}
}
