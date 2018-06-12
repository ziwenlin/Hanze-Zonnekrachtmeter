package Serial;

import java.util.ArrayList;
import java.util.List;

import Main.ExtraFuncties;
import processing.core.PApplet;
import processing.serial.Serial;

public class SerialManager {

	public static PApplet p;
	public static Serial myPort; // 
	public static long startTijd = System.currentTimeMillis();
	// Data input
	public static List<String> inputSerial = new ArrayList<>();
	public static List<Float> tijd = new ArrayList<>();
	public static List<Float> zonnekracht = new ArrayList<>();
	public static List<Float> temperatuur = new ArrayList<>();
	public static boolean splitNow = false;

	public SerialManager(PApplet p) { // Initialisatie van de seriële manager
		SerialManager.p = p;
		inputSerial.add("");
		tijd.add(0f);
		zonnekracht.add(1f);
		temperatuur.add(1f);
	}

	public void serialInit() {
		if (myPort != null) { // Sluit de seriële poort als die nog actief is
			myPort.stop();
			myPort.dispose();
		}
		if (Serial.list().length > 0) { // Er moet een seriële poort aanwezig te zijn
			myPort = new Serial(p, Serial.list()[0], 9600);
			myPort.bufferUntil('\n');
		}
	}

	public void serialLoop() {

		if (myPort == null || !myPort.active()) {
			serialInit(); // Seriële poort is op een of andere manier gestopt.
		}
		
		while (inputSerial.size() > 25) {
			inputSerial.remove(0); // De lijst wordt te lang
		}

		stringsplitter(); // Split het binnen gekomen informatie

	}

	public static void stringsplitter() {
		if (inputSerial.size() == 0 && !splitNow ) { // Nullpointerexception voorkomen
			return;
		}
		List<String> b = ExtraFuncties.stringCutter(inputSerial.get(inputSerial.size() - 1));
		splitNow = false;
		if (b.size() < 7) {
			return; // Het binnengekomen bericht voldoet niet aan de eisen en bevat meestal geen nuttige informatie
		}
		temperatuur.add(ExtraFuncties.stringToFloat(b.get(4))); // Zet de string om naar een getal
		zonnekracht.add(ExtraFuncties.stringToFloat(b.get(9))); // Zet de string om naar een getal
		tijd.add((float) (System.currentTimeMillis() - startTijd)/60000.0f); // De minuten van het programma wordt hier opgeslagen
	}



}
