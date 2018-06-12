package Buttons;

import Windows.BedieningsPaneel;
import processing.core.PApplet;
import processing.core.PConstants;

public class Button {

	private PApplet p; // Nodig om de knop op het beeldscherm te laten weergeven
	private float minX, maxX, minY, maxY; // Posities van de knop
	private int mode = PConstants.CENTER, // Positie van de knop
			color, // Kleur van de knop
			hoverColor, // Kleur verandering van de knop wanneer de muis er op zweeft
			textColor; // Kleur van de text
	private String text; // Tekst van de knop
	private int textSize = 10; // Tekst grootte van de knop
	private float alfa; // Doorzichtigheid van de knop
	private Runnable task; // Taak van de knop
	private boolean running = false, // Knop al ingedrukt?
			hovering = false; // Cursor over de button?

	/**
	 * Knop initialisatie met positie en grootte.
	 * 
	 * @param p PApplet
	 * @param a minX 
	 * @param b	minY
	 * @param c maxX
	 * @param d maxY
	 */
	public Button(PApplet p, float a, float b, float c, float d) {
		this.p = p;
		minX = a;
		minY = b;
		maxX = c;
		maxY = d;
	}

	/**
	 * De knop op het scherm laten weergeven.
	 */
	public void drawButton() {
		hovering = isHovering();
		p.fill((hovering) ? hoverColor : color, alfa);
		p.rectMode(mode);
		p.rect(minX, minY, maxX, maxY);
		
		if (text == null) { // Heeft de knop text?
			return;
		}
		p.fill(textColor, alfa);
		p.textAlign(mode, mode);
		p.textSize(textSize);
		p.text(text, minX, minY - 2.5f, maxX, maxY);
		
		if (!running && BedieningsPaneel.mouseP && hovering && task != null) {
			task.run();		// Lambda expressie (Functie uitvoeren)
			running = true;
		} else {
			running = false;
		}
	}

	/**
	 * Checkt dat de cursor over de knop bevindt.
	 * @return Boolean
	 */
	public boolean isHovering() {
		return (p.mouseX > (minX - maxX / 2) && p.mouseX < (minX + maxX / 2) && p.mouseY > (minY - maxY / 2)
				&& p.mouseY < (minY + maxY / 2));
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void setText(String string) {
		text = string;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setTextColor(int color) {
		textColor = color;
	}

	public int getTextColor() {
		return textColor;
	}

	public void setTextSize(int size) {
		textSize = size;
	}

	public int getHoverColor() {
		return hoverColor;
	}

	public void setHoverColor(int hoverColor) {
		this.hoverColor = hoverColor;
	}

	public void setAlfa(float alfa) {
		this.alfa = alfa;
	}

	public void setTask(Runnable task) {
		this.task = task;
	}

}
