package Serial;

import java.util.ArrayList;
import java.util.List;

import Buttons.ExtraFuncties;
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
		if (Serial.list().length > 0) {
			myPort = new Serial(p, Serial.list()[0], 9600);
			myPort.bufferUntil(10);
		}
	}

	public void serialLoop() {

		if (myPort == null || !myPort.active()) {
			serialInit();
			return;
		}

		stringsplitter();
		textMonitor();
		
		while (list.size() > 28) {
			list.remove(0);
		}
		
	}

	public void stringsplitter() {
		String a = "2018/06/06-15:30:45 Temperatuur: 26.00 C Voltage: 0.30 V";
		List<String> b = ExtraFuncties.stringCutter(a);
		temperatuur.add(ExtraFuncties.stringToFloat(b.get(2)));
		zonnekracht.add(ExtraFuncties.stringToFloat(b.get(5)));
	}

	public void textMonitor() {

		p.textAlign(PConstants.LEFT);
		p.textSize(36);
		if (list.size() > 27) {
			p.text(list.get(27), 30, 40);
		}

		p.textAlign(PConstants.LEFT);
		p.textSize(24);
		for (int i = 0; i < list.size(); i++) {
			p.text(list.get(i), 10, 20 * (i + 5));
		}
	}

	public void serialEvent(Serial p) {
		list.add(p.readString());
		str = str + p.readString();
	}

}
