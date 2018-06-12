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
		paneelBediening();
		/* Grafiek Zonnekracht */
		// paneelZonnekrachtNetto();
		/* Grafiek Temperatuur */
		// paneelTemperatuur();
		/* Seriële monitor */
		paneelSerieleMonitor();
	}
	
	public static void toggleSerialMonitor() {
		paneelMonitor.toggleVisibility();
	}
	
	public static void paneelSerieleMonitor() {
		paneelMonitor = new SerieleMonitorPaneel();
		String[] args = { paneelMonitor.getClass().getName() };
		PApplet.runSketch(args, paneelMonitor);
	}

	public static void paneelBediening() {
		paneelBediening = new BedieningsPaneel();
		String[] args = { paneelBediening.getClass().getName() };
		PApplet.runSketch(args, paneelBediening);
	}

	public static void paneelZonnekrachtNetto() {
		if (paneelZonnekracht != null) {
			return;
		}
		paneelZonnekracht = new ZonnekrachtPaneel();
		String[] args = { paneelZonnekracht.getClass().getName() };
		PApplet.runSketch(args, paneelZonnekracht);
	}

	public static void paneelTemperatuur() {
		if (paneelTemperatuur != null) {
			return;
		}
		paneelTemperatuur = new TemperatuurPaneel();
		String[] args = { paneelTemperatuur.getClass().getName() };
		PApplet.runSketch(args, paneelTemperatuur);
	}
}
