package Buttons;

import processing.core.PApplet;

public class Button { // Niet extenden: https://processing.org/tutorials/eclipse/ 

	private PApplet p;
	private float minX, maxX, minY, maxY;
	private int mode, color, textColor;
	private String text;
	private int textSize;
	
	
	public Button(PApplet p, float a, float b, float c, float d) {
		this.p = p;
		minX = a;
		minY = b;
		maxX = c;
		maxY = d;
	}

	public void drawButton() {
		p.fill(color);
		p.rect(minX, minY, maxX, maxY);
		p.rectMode(mode);
		p.fill(textColor);
		p.text(text, minX, minY);
		p.textAlign(mode,mode);
		p.textSize(textSize);
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

}
