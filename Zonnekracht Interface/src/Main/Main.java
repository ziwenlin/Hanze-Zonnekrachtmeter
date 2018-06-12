package Main;

import Windows.BedieningsPaneel;
import Windows.SerieleMonitorPaneel;
import Windows.TemperatuurPaneel;
import Windows.ZonnekrachtPaneel;
import processing.core.PApplet;

public class Main {

	static BedieningsPaneel paneelBediening;
	static TemperatuurPaneel paneelTemperatuur;
	static ZonnekrachtPaneel paneelZonnekracht;
	static SerieleMonitorPaneel paneelMonitor;

	public static void main(String[] args) {
		/* Serial COM scherm */
		startPaneelBediening();
		/* Grafiek Zonnekracht */
		// paneelZonnekrachtNetto();
		/* Grafiek Temperatuur */
		// paneelTemperatuur();
		/* Seriële monitor */
		startPaneelSerieleMonitor();
	}
	
	public static void toggleSerialMonitor() {
		if (paneelMonitor == null) {	// paneelMonitor moet een keer geopend zijn.
			return; // Er is geen een paneelMonitor aanwezig.
		}
		paneelMonitor.toggleVisibility(); // Aan of uitzetten van de seriële monitor.
	}
	
	public static void startPaneelSerieleMonitor() {
		if (paneelMonitor != null) {	// paneelMonitor mag niet nog een keer geopend worden.
			return; // Er is al een paneelMonitor aanwezig.
		}
		paneelMonitor = new SerieleMonitorPaneel(); // Maak een nieuwe paneel aan.
		String[] args = { paneelMonitor.getClass().getName() }; // Argumenten voor het starten van een paneel
		PApplet.runSketch(args, paneelMonitor); // Start een nieuwe sketch/window/paneel
	}

	public static void startPaneelBediening() { // Lees startPaneelSerieleMonitor
		if (paneelBediening != null) {
			return;
		}
		paneelBediening = new BedieningsPaneel();
		String[] args = { paneelBediening.getClass().getName() };
		PApplet.runSketch(args, paneelBediening);
	}

	public static void startPaneelZonnekrachtNetto() { // Lees startPaneelSerieleMonitor
		if (paneelZonnekracht != null) {
			return;
		}
		paneelZonnekracht = new ZonnekrachtPaneel();
		String[] args = { paneelZonnekracht.getClass().getName() };
		PApplet.runSketch(args, paneelZonnekracht);
	}

	public static void startPaneelTemperatuur() { // Lees startPaneelSerieleMonitor
		if (paneelTemperatuur != null) {
			return;
		}
		paneelTemperatuur = new TemperatuurPaneel();
		String[] args = { paneelTemperatuur.getClass().getName() };
		PApplet.runSketch(args, paneelTemperatuur);
	}
}
