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
	
	public void buttonNew(float a, float b, float c, float d) { // Toevoegen knop
		button.add(0, new Button(p, a, b, c, d)); // First in, last out
	}
	
	public void drawButtons() { // Knop renderen
		for (Button b : this.button) {
			b.drawButton();
		}
	}

	public void buttonMode(int mode) { // Knop positie
		getButton().setMode(mode);
	}

	public void textButton(String string) { // Knop tekst
		getButton().setText(string);
	}
	
	public Button getButton() { // Knop van de lijst
		return button.get(0);
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
