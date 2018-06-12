package Buttons;

import Windows.BedieningsPaneel;
import processing.core.PApplet;
import processing.core.PConstants;

public class Button { 

	private PApplet p;
	private float minX, maxX, minY, maxY;
	private int mode = PConstants.CENTER, color, hoverColor, textColor;
	private String text;
	private int textSize = 1;
	private float alfa;
	private Runnable task;
	private boolean running = false, hovering = false;
	
	
	public Button(PApplet p, float a, float b, float c, float d) {
		this.p = p;
		minX = a;
		minY = b;
		maxX = c;
		maxY = d;
	}

	public void drawButton() {
		hovering = isHovering();
		p.fill((hovering) ? hoverColor : color, alfa);
		p.rectMode(mode);
		p.rect(minX, minY, maxX, maxY);
		if (text == null) {
			return;
		}
		p.fill(textColor, alfa);
		p.textAlign(mode,mode);
		p.textSize(textSize);
		p.text(text, minX, minY-2.5f, maxX, maxY);
		if (!running && BedieningsPaneel.mouseP && hovering && task != null) {
			task.run();
			running = true;
		} else {
			running = false;
		}
	}

	public boolean isHovering() {
		return (p.mouseX > (minX - maxX/2) && p.mouseX < (minX + maxX/2) && p.mouseY > (minY - maxY/2) && p.mouseY < (minY + maxY/2));
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
