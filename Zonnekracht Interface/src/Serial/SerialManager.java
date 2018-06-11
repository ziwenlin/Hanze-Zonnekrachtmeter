package Serial;

import java.util.ArrayList;
import java.util.List;

import Main.ExtraFuncties;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.serial.Serial;

public class SerialManager {

	private static PApplet p;
	private static Serial myPort;
	private static List<String> list = new ArrayList<>();
	public static List<Float> zonnekracht = new ArrayList<>();
	public static List<Float> temperatuur = new ArrayList<>();
	private static String str;

	public SerialManager(PApplet p) {
		SerialManager.p = p;
	}

	public void serialInit() {
		if (myPort != null) {
			myPort.stop();
			myPort.dispose();
		}
		if (Serial.list().length > 0) {
			myPort = new Serial(p, Serial.list()[0], 9600);
			myPort.bufferUntil(10);
		}
	}

	public void serialLoop() {

		if (myPort == null || !myPort.active()) {
			serialInit();
		}
		
		while (list.size() > 25) {
			list.remove(0);
		}

		// list.add("2018/06/06-15:30:45 Temperatuur: 26.00 C Voltage: 0.30 V");

		stringsplitter();

	}

	public static void stringsplitter() {
		if (list.size() == 0) {
			return;
		}
		List<String> b = ExtraFuncties.stringCutter(list.get(list.size() - 1));
		if (b.size() < 7) {
			return;
		}
		temperatuur.add(ExtraFuncties.stringToFloat(b.get(2)));
		zonnekracht.add(ExtraFuncties.stringToFloat(b.get(5)));
	}

	public static void textMonitor(PApplet p) {
		if (list.size() == 0) {
			return;
		}
		p.rectMode(PConstants.CORNER);
		p.textAlign(PConstants.LEFT, PConstants.TOP);
		p.textSize(32);
		p.text(list.get(list.size() - 1), 10, 10, p.width - 10, p.height - 10);

		p.textAlign(PConstants.LEFT);
		p.textSize(16);
		for (int i = 0; i < list.size(); i++) {
			p.text(list.get(i), 10, 20 * (i + 10));
		}
	}

	public void serialEvent(Serial p) {
		list.add(p.readString());
		str = str + p.readString();
	}

}
