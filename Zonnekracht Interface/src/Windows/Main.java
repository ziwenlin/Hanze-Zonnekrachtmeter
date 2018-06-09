package Windows;
import processing.core.PApplet;

public class Main {

	static BedieningsPaneel paneelBediening = new BedieningsPaneel();
	static TemperatuurPaneel paneelTemperatuur = new TemperatuurPaneel();
	static ZonnekrachtPaneel paneelZonnekracht = new ZonnekrachtPaneel();

	public static void main(String[] args) {
		/* Serial COM scherm */
		paneelBediening();
		paneelZonnekrachtNetto();
		paneelTemperatuur();
	}

	public static void paneelBediening() {
		String[] args = { paneelBediening.getClass().getName() };
		PApplet.runSketch(args, paneelBediening);
	}

	public static void paneelZonnekrachtNetto() {
		String[] args = { paneelZonnekracht.getClass().getName() };
		PApplet.runSketch(args, paneelZonnekracht);
	}

	public static void paneelTemperatuur() {
		String[] args = { paneelTemperatuur.getClass().getName() };
		PApplet.runSketch(args, paneelTemperatuur);
	}
}
