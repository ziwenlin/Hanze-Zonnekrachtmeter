import Windows.BedieningsPaneel;
import Windows.TemperatuurPaneel;
import Windows.ZonnekrachtNetto;
import processing.core.PApplet;

public class Main {

	static BedieningsPaneel paneelBediening = new BedieningsPaneel();
	static ZonnekrachtNetto paneelZonnekrachtNetto = new ZonnekrachtNetto();
	static TemperatuurPaneel paneelTemperatuur = new TemperatuurPaneel();

	public static void main(String[] args) {
		/* Serial COM window */
		paneelBediening();
		paneelZonnekrachtNetto();
		paneelTemperatuur();
	}

	public static void paneelBediening() {
		String[] args = { paneelBediening.getClass().getName() };
		PApplet.runSketch(args, paneelBediening);
	}

	public static void paneelZonnekrachtNetto() {
		String[] args = { paneelZonnekrachtNetto.getClass().getName() };
		PApplet.runSketch(args, paneelZonnekrachtNetto);
	}

	public static void paneelTemperatuur() {
		String[] args = { paneelTemperatuur.getClass().getName() };
		PApplet.runSketch(args, paneelTemperatuur);
	}
}
